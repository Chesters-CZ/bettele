import java.util.Objects;
import java.util.Scanner;

public class Gaym {

    public void play(Enemy.Difficulty difficulty) throws Exception {
        System.out.println(difficulty);
        Enemy enemy = new Enemy(difficulty);
        if (Enemy.enemyDifficulty == Enemy.Difficulty.KAREL || Enemy.enemyDifficulty == Enemy.Difficulty.HOUBA)
            Instance.fieldInstance.youfield[Instance.fieldInstance.youfield.length / 2][Instance.fieldInstance.youfield[0].length / 2] = Tiles.makeTile(Tiles.Type.SPORE);
        while (Instance.fieldInstance.whoseEnemyWon(Player.ENEMY) && Instance.fieldInstance.whoseEnemyWon(Player.YOU)) {
            System.out.println("Tvoje pole: \t\t\t\tNepřítelovo pole:");
            System.out.println(mergeStrings(printField(Player.YOU), printField(Player.ENEMY)));
            int x;
            int y;
            boolean works = true;
            while (works) {
                Scanner scanner = new Scanner(System.in);
                try {
                    System.out.println(MikolasovyConsoleBarvy.CYAN + "zvol X útoku");
                    x = scanner.nextInt() - 1;
                    System.out.println(MikolasovyConsoleBarvy.CYAN + "zvol Y útoku");
                    y = scanner.nextInt() - 1;
                    System.out.println(MikolasovyConsoleBarvy.CYAN + "útočím...");
                    Instance.fieldInstance.strike(x, y);
                    works = false;

                } catch (Exception e) {
                    System.out.println(MikolasovyConsoleBarvy.CYAN + "Něcos pokazil. Zkus to znovu. (" + e + ")");
                    works = true;
                }
            }
            System.out.println(MikolasovyConsoleBarvy.CYAN + "AI útočí...");
            enemy.attack();
        }
        try {
            switch (Objects.requireNonNull(Instance.fieldInstance.whoWon())) {
                case YOU -> System.out.println("gg, vyhrál jsi!");
                case ENEMY -> System.out.println("vyhrál oponent 😕");
            }
        } catch (Exception e) {
            throw new Exception(MikolasovyConsoleBarvy.PURPLE + "ERROR: Hra se ukončila protože někdo vyhrál, ale vypadá to že nikdo nevyhrál.");
        }
    }

    // čí pole se vypíše
    public String printField(Player player) {
        if (player == Player.ENEMY) {
            return printField();
        } else {
            StringBuilder output = new StringBuilder();
            for (Tiles[] tiles : Instance.fieldInstance.youfield) {
                print(output, tiles);
            }
            return output.toString();
        }
    }

    private void print(StringBuilder output, Tiles[] tiles) {
        for (Tiles tile : tiles) {
            switch (tile.tiletype) {
                case SHIP, WATER -> output.append(MikolasovyConsoleBarvy.WHITE + "?" + MikolasovyConsoleBarvy.RESET + " ");
                case HIT -> output.append(MikolasovyConsoleBarvy.RED + "H" + MikolasovyConsoleBarvy.RESET + " ");
                case MISS -> output.append(MikolasovyConsoleBarvy.BLUE + "W" + MikolasovyConsoleBarvy.RESET + " ");
                case SPORE -> output.append(MikolasovyConsoleBarvy.BLACK + "K" + MikolasovyConsoleBarvy.RESET + " ");
                default -> output.append(MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + " ");
            }
        }
        output.append("\n");
    }

    public String printField() {
        StringBuilder output = new StringBuilder();
        for (Tiles[] tiles : Instance.fieldInstance.botfield) {
            print(output, tiles);
        }
        return output.toString();
    }

    public String mergeStrings(String first, String second) {
        String[] firstSplit = first.split("\n");
        String[] secondSplit = second.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < firstSplit.length; i++) {
            result.append(firstSplit[i]).append("\t\t\t").append(secondSplit[i]).append("\n");
        }
        return result.toString();
    }
}
