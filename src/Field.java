public class Field {
    Tiles[][] botfield = {   //Lodě enemáka
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.ship(1), Tiles.ship(1), Tiles.ship(1), Tiles.water(), Tiles.ship(2), Tiles.ship(2), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.ship(3), Tiles.water(), Tiles.ship(4), Tiles.ship(4), Tiles.water(), Tiles.ship(5), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.ship(4), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.ship(6), Tiles.ship(6), Tiles.ship(6), Tiles.ship(6), Tiles.ship(6), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()}
    };


    Tiles[][] youfield = {   //Lodě tebe
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.ship(1), Tiles.ship(1), Tiles.ship(1), Tiles.water(), Tiles.ship(2), Tiles.ship(2), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.ship(3), Tiles.water(), Tiles.ship(4), Tiles.ship(4), Tiles.water(), Tiles.ship(5), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.ship(4), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.ship(6), Tiles.ship(6), Tiles.ship(6), Tiles.ship(6), Tiles.ship(6), Tiles.water(), Tiles.water()},
            {Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water(), Tiles.water()}
    };

    public void strike(int x, int y) throws Exception {
        switch (botfield[x][y].tiletype) {
            case WATER -> botfield[x][y].tiletype = Tiles.Type.MISS;
            case MISS -> throw new Exception("Tady už tvoje munice plave.");
            case HIT -> throw new Exception("Tam jsi už něco trefil. ");
            case SHIP -> {
                botfield[x][y].hit();
                breakShip(botfield[x][y].shipid, botfield, Player.ENEMY);
            }
            default -> throw new Exception("zjebec.");
        }


    }

    /**
     * gets shipid & map of a known hit, checks map for ship cells with same shipid. if no ships with matching shipid are found, the entire ship is considered broken and all tiles surrounding the ship tiles are hit
     */
    public void breakShip(int shipid, Tiles[][] field, Player attacked) {
        boolean isSunk = true;
        for (Tiles[] tiles : field) {
            for (Tiles tile : tiles) {
                if (shipid != -1 && tile == Tiles.ship(shipid)) {
                    isSunk = false;
                    break;
                }
            }
        }
        if (isSunk) {
            if (attacked == Player.ENEMY) {
                System.out.println(MikolasovyConsoleBarvy.GREEN + "Výborně, zničil jsi soupeřovu " + shipid + ". loď!" + MikolasovyConsoleBarvy.RESET);
            } else
                System.out.println(MikolasovyConsoleBarvy.RED + "Ale ne! Soupeř zničil tvojí " + shipid + ". loď!" + MikolasovyConsoleBarvy.RESET);
            //FIXME: z nějakýho důvodu se každej hit považuje za potopenou loď
            int x = 0;
            int y;
            for (Tiles[] tiles : field) {
                x++;
                y = 0;
                for (Tiles tile : tiles) {
                    y++;
                    if (shipid != -1 && tile == Tiles.ship(shipid)) {
                        for (int i = 0; i < 9; i++) {
                            field[x - 1 + i % 3][y - 1 + i / 3].hit();
                        }
                    }
                }
            }
        }
    }


    // čí soupeř vyhrál
    public boolean whoseEnemyWon(Player player) {
        return countLode(player) > 0;
    }

    public Player whoWon() {
        if (whoseEnemyWon(Player.ENEMY)) return Player.YOU;
        if (whoseEnemyWon(Player.YOU)) return Player.ENEMY;
        return null;
    }

    public boolean containsWater() {
        for (Tiles[] tiles : Instance.fieldInstance.youfield) {
            for (Tiles tile : tiles) {
                if (tile == Tiles.water()) return true;
            }
        }
        return false;
    }

    public int countLode(Player player) {
        int count = 0;
        if (player == Player.ENEMY) {
            for (Tiles[] tiles : botfield) {
                for (Tiles tile : tiles) {
                    if (tile.tiletype == Tiles.Type.SHIP) count++;
                }
            }
        } else {
            for (Tiles[] tiles : youfield) {
                for (Tiles tile : tiles) {
                    if (tile.tiletype == Tiles.Type.SHIP) count++;
                }
            }
        }
        return count;
    }

    public void spreadKarel(int x, int y) {
        youfield[x][y] = youfield[x - 1][y] = youfield[x][y - 1] = youfield[x - 1][y - 1] = youfield[x + 1][y] = youfield[x][y + 1] = youfield[x + 1][y + 1] = Tiles.makeTile(Tiles.Type.SPORE);
    }
}
