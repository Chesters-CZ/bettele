import java.util.Objects;
import java.util.Scanner;

public class Gaym {
    public Enemy.Difficulty botDifficulty;

    public void play(Enemy.Difficulty difficulty) throws Exception {
        System.out.println(difficulty);
        botDifficulty = difficulty;
        while (Instance.fieldInstance.whoseEnemyWon(Player.ENEMY) && Instance.fieldInstance.whoseEnemyWon(Player.YOU)) {
            System.out.println("Tvoje pole: \n" + printField(Player.YOU) + "\n");
            System.out.println("Nepřítelovo pole: \n" + printField() + "\n");
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
            try {
                Instance.enemyInstance.attack();
            } catch (Exception e) {
                throw new Exception(MikolasovyConsoleBarvy.PURPLE + "ERROR: AI zaútočilo neplatným způsobem.");
            }
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
}
