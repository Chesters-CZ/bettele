public class Enemy {
    public void attack() throws Exception {
        int[] coords = getAttack();
        switch (Instance.fieldInstance.youfield[coords[0]][coords[1]].tiletype) {
            case WATER, SHIP -> Instance.fieldInstance.youfield[coords[0]][coords[1]].hit();
            case MISS, HIT -> throw new Exception(MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + "nepřítel se posral"); // HIT OR MISS
            default -> throw new Exception("zjebec.");
        }
        Instance.fieldInstance.breakShip(Instance.fieldInstance.youfield[coords[0]][coords[1]].shipid, Instance.fieldInstance.youfield, Player.YOU);
    }

    public int[] getAttack() throws Exception {
        switch (Main.gaymInstance.botDifficulty) {
            case WATERWORKS:
                if (Instance.fieldInstance.containsWater()) {
                    return getRandomTile(Tiles.Type.WATER);
                }
            case BOGO:
                boolean firstRun = true;
                int x = 0;
                int y = 0;
                while (firstRun || Instance.fieldInstance.youfield[x][y].tiletype != Tiles.Type.SHIP && Instance.fieldInstance.youfield[x][y].tiletype != Tiles.Type.WATER) {
                    firstRun = false;
                    //System.out.println("pog");
                    x = (int) (Math.random() * 1000) % Instance.fieldInstance.youfield.length;
                    y = (int) (Math.random() * 1000) % Instance.fieldInstance.youfield.length;
                }
                return new int[]{x, y};
            case AIMBOT:
                return getRandomTile(Tiles.Type.SHIP);
            case VENDETTA:
                if (75 - 3 * Instance.fieldInstance.countLode(Player.ENEMY) < Math.random() * 100) {
                    return getRandomTile(Tiles.Type.WATER);
                } else {
                    return getRandomTile(Tiles.Type.SHIP);
                }
            default:
                throw new Exception("zjebec.");
        }
    }

    private int[] getRandomTile(Tiles.Type onlyif) {
        while (true) {
            int x;
            int y = -1;
            for (Tiles[] tiles : Instance.fieldInstance.youfield) {
                y++;
                x = -1;
                for (Tiles tile : tiles) {
                    x++;
                    if (tile.tiletype == onlyif && Math.random() > 0.75) return new int[]{x, y};
                }
            }
        }
    }


    public enum Difficulty {
        WATERWORKS, BOGO, VENDETTA, AIMBOT
    }
}
