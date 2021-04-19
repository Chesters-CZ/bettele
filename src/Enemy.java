public class Enemy {
    public static void attack() throws Exception {
        int[] coords = getAttack();
        switch (Field.youfield[coords[0]][coords[1]].tiletype) {
            case WATER, SHIP -> Field.youfield[coords[0]][coords[1]].hit();
            case MISS, HIT -> throw new Exception(MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + "nepřítel se posral"); // HIT OR MISS
            default -> throw new Exception("zjebec.");
        }
        Field.breakShip(Field.youfield[coords[0]][coords[1]].shipid, Field.youfield, Player.YOU);
    }

    public static int[] getAttack() throws Exception {
        switch (Gaym.botDifficulty) {
            case WATERWORKS:
                if (Field.containsWater()) {
                    return getRandomTile(Tiles.Type.WATER);
                }
            case BOGO:
                return new int[]{(int) (Math.random() * 1000) % Field.youfield.length, (int) (Math.random() * 1000) % Field.youfield.length};
            case AIMBOT:
                return getRandomTile(Tiles.Type.SHIP);
            case VENDETTA:
                if (75 - 3 * Field.countLode(Player.ENEMY) < Math.random() * 100) {
                    return getRandomTile(Tiles.Type.WATER);
                } else {
return getRandomTile(Tiles.Type.SHIP);
                }
            default:
                throw new Exception("zjebec.");
        }
    }

    private static int[] getRandomTile(Tiles.Type onlyif) {
        while (true) {
            int x;
            int y = -1;
            for (Tiles[] tiles : Field.youfield) {
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
