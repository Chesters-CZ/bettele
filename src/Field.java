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

    static Tiles[][] visible = {
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER},
            {Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER, Tiles.WATER}
    };

    public static String printField() {
        StringBuilder output = new StringBuilder();
        for (Tiles[] tiles : visible) {
            for (Tiles tile : tiles) {
                switch (tile) {
                    case WATER -> output.append(MikolasovyConsoleBarvy.ANSI_WHITE + "?" + MikolasovyConsoleBarvy.ANSI_RESET + " ");
                    case HIT -> output.append(MikolasovyConsoleBarvy.ANSI_RED + "H" + MikolasovyConsoleBarvy.ANSI_RESET + " ");
                    case MISS -> output.append(MikolasovyConsoleBarvy.ANSI_BLUE + "W" + MikolasovyConsoleBarvy.ANSI_RESET + " ");
                    case SHIP -> output.append(MikolasovyConsoleBarvy.ANSI_YELLOW + "S" + MikolasovyConsoleBarvy.ANSI_RESET + " ");
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    public static void strike(int x, int y) throws Exception {
        switch (field[x][y]) {
            case WATER:
                if (visible[x][y] == Tiles.WATER) {
                    field[x][y] = Tiles.MISS;
                    visible[x][y] = Tiles.MISS;
                } else throw new Exception("An error occured.");
                break;
            case MISS:
                throw new Exception("There already is a crater there.");
            case HIT:
                throw new Exception("You already hit that part of the ship");
            case SHIP:
                if (visible[x][y] == Tiles.WATER) {
                    field[x][y] = Tiles.HIT;
                    visible[x][y] = Tiles.HIT;
                }
                break;
        }


    }

    @Deprecated
    public static void breakShip(int x, int y) { // gets coordinates of a known hit, checks adjacent cells to see how far the ship goes. if the entire ship is broken, surrounds it with misses.
        Tiles[][] fieldbackup = field;
        Tiles[][] visiblebackup = visible;
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
