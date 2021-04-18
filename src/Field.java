public class Field {
    static Tiles[][] botfield = {   //Lodě enemáka
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.SHIP, Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.WATER, Tiles.SHIP, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.SHIP, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER}
    };


    static Tiles[][] youfield = {   //Lodě tebe
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.SHIP, Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.WATER, Tiles.SHIP, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.SHIP, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.SHIP, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER}
    };

    public static void strike(int x, int y) throws Exception {
        switch (botfield[x][y]) {
            case WATER:
                botfield[x][y] = Tiles.MISS;
                break;
            case MISS:
                throw new Exception("Tady už tvoje munice plave.");
            case HIT:
                throw new Exception("Tam jsi už něco trefil. ");
            case SHIP:
                if (botfield[x][y] == Tiles.WATER) {
                    botfield[x][y] = Tiles.HIT;
                }
                break;
            default:
                throw new Exception("zjebec.");
        }


    }

    @Deprecated
    public static void breakShip(int x, int y) { // gets coordinates of a known hit, checks adjacent cells to see how far the ship goes. if the entire ship is broken, surrounds it with misses.
        Tiles[][] fieldbackup = botfield;
        int xx = x;
        int yy = y;

        while (botfield[x][y] != Tiles.WATER || botfield[x][y] != Tiles.MISS) {
        }
    }

    // čí soupeř vyhrál
    public static boolean whoseEnemyWon(Player player) {
        return countLode(player) > 0;
    }

    public static Player whoWon() {
        if (whoseEnemyWon(Player.ENEMY)) return Player.YOU;
        if (whoseEnemyWon(Player.YOU)) return Player.ENEMY;
        return null;
    }

    public static boolean containsWater() {
        for (Tiles[] tiles : Field.youfield) {
            for (Tiles tile : tiles) {
                if (tile == Tiles.WATER) return true;
            }
        }
        return false;
    }

    public static int countLode(Player player) {
        int count = 0;
        if (player == Player.ENEMY) {
            for (Tiles[] tiles : botfield) {
                for (Tiles tile : tiles) {
                    if (tile == Tiles.SHIP) count++;
                }
            }
        } else {
            for (Tiles[] tiles : youfield) {
                for (Tiles tile : tiles) {
                    if (tile == Tiles.SHIP) count++;
                }
            }
        }
        return count;
    }
}
