public class Field {
    static Tiles[][] field = {
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
        switch (field[x][y]) {
            case WATER:
                if (field[x][y] == Tiles.WATER) {
                    field[x][y] = Tiles.MISS;
                } else throw new Exception("zjebec.");
                break;
            case MISS:
                throw new Exception("Tady už tvoje munice plave.");
            case HIT:
                throw new Exception("Tam jsi už něco trefil. ");
            case SHIP:
                if (field[x][y] == Tiles.WATER) {
                    field[x][y] = Tiles.HIT;
                }
                break;
        }


    }

    @Deprecated
    public static void breakShip(int x, int y) { // gets coordinates of a known hit, checks adjacent cells to see how far the ship goes. if the entire ship is broken, surrounds it with misses.
        Tiles[][] fieldbackup = field;
        int xx = x;
        int yy = y;

        while (field[x][y] != Tiles.WATER || field[x][y] != Tiles.MISS) {
        }
    }

    public static boolean won() {
        for (Tiles[] tiles : field) {
            for (Tiles tile : tiles) {
                if (tile == Tiles.SHIP) return false;
            }
        }
        return true;
    }
}
