public class Enemy {
    public static void attack() throws Exception {
        int[] coords = getAttack();
        switch (Field.youfield[coords[0]][coords[1]]) {
            case WATER -> Field.youfield[coords[0]][coords[1]] = Tiles.MISS;
            case SHIP -> Field.youfield[coords[0]][coords[1]] = Tiles.HIT;
            case MISS, HIT -> throw new Exception(MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + "nepřítel se posral"); // HIT OR MISS
            default -> throw new Exception("zjebec.");
        }
    }

    public static int[] getAttack() throws Exception {
        switch (Gaym.botDifficulty) {
            case WATERWORKS:
                if (Field.containsWater()) {
                    while (true) {
                        int x;
                        int y = -1;
                        for (Tiles[] tiles : Field.youfield) {
                            y++;
                            x = -1;
                            for (Tiles tile : tiles) {
                                x++;
                                if (tile == Tiles.WATER && Math.random() > 0.75) return new int[]{x, y};
                            }
                        }
                    }
                }
            case BOGO:
                return new int[]{(int) (Math.random() * 1000) % Field.youfield.length, (int) (Math.random() * 1000) % Field.youfield.length};
            case AIMBOT:
                while (true) {
                    int x;
                    int y = -1;
                    for (Tiles[] tiles : Field.youfield) {
                        y++;
                        x = -1;
                        for (Tiles tile : tiles) {
                            x++;
                            if (tile == Tiles.SHIP && Math.random() > 0.75) return new int[]{x, y};
                        }
                    }
                }
            case VENDETTA:
                if (75 - 3 * Field.countLode(Player.ENEMY) < Math.random() * 100) {
                    while (true) {
                        int x;
                        int y = -1;
                        for (Tiles[] tiles : Field.youfield) {
                            y++;
                            x = -1;
                            for (Tiles tile : tiles) {
                                x++;
                                if (tile == Tiles.WATER && Math.random() > 0.75) return new int[]{x, y};
                            }
                        }
                    }
                } else {
                    while (true) {
                        int x;
                        int y = -1;
                        for (Tiles[] tiles : Field.youfield) {
                            y++;
                            x = -1;
                            for (Tiles tile : tiles) {
                                x++;
                                if (tile == Tiles.SHIP && Math.random() > 0.75) return new int[]{x, y};
                            }
                        }
                    }
                }
            default:
                throw new Exception("zjebec.");
        }
    }


    public enum Difficulty {
        WATERWORKS, BOGO, VENDETTA, AIMBOT
    }
}
