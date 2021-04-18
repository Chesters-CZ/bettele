import java.rmi.UnexpectedException;
import java.util.Objects;
import java.util.Scanner;

public class Gaym {
    public static Enemy.Difficulty botDifficulty;

    public static void play(Enemy.Difficulty difficulty) throws Exception {
        botDifficulty = difficulty;
        while (!Field.whoseEnemyWon(Player.ENEMY) && !Field.whoseEnemyWon(Player.YOU)) {
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
                    Field.strike(x, y);
                    works = false;

                } catch (Exception e) {
                    System.out.println(MikolasovyConsoleBarvy.CYAN + "Něcos pokazil. Zkus to znovu. (" + e + ")");
                    works = true;
                }
            }
            System.out.println("AI útočí...");
            try {
                Enemy.attack();
            } catch (Exception e) {
                throw new Exception(MikolasovyConsoleBarvy.PURPLE + "ERROR: AI zaútočilo neplatným způsobem.");
            }
        }
        try {
            switch (Objects.requireNonNull(Field.whoWon())) {
                case YOU -> System.out.println("gg, vyhrál jsi!");
                case ENEMY -> System.out.println("vyhrál oponent 😕");
            }
        } catch (Exception e) {
            throw new Exception(MikolasovyConsoleBarvy.PURPLE + "ERROR: Hra se ukončila protože někdo vyhrál, ale vypadá to že nikdo nevyhrál.");
        }

    }

    // čí pole se vypíše
    public static String printField(Player player) {
        if (player == Player.ENEMY) {
            return printField();
        } else {
            StringBuilder output = new StringBuilder();
            for (Tiles[] tiles : Field.youfield) {
                for (Tiles tile : tiles) {
                    switch (tile) {
                        case SHIP, WATER -> output.append(MikolasovyConsoleBarvy.WHITE + "?" + MikolasovyConsoleBarvy.RESET + " ");
                        case HIT -> output.append(MikolasovyConsoleBarvy.RED + "H" + MikolasovyConsoleBarvy.RESET + " ");
                        case MISS -> output.append(MikolasovyConsoleBarvy.BLUE + "W" + MikolasovyConsoleBarvy.RESET + " ");
                        default -> output.append(MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + " ");
                    }
                }
                output.append("\n");
            }
            return output.toString();
        }
    }

    public static String printField() {
        StringBuilder output = new StringBuilder();
        for (Tiles[] tiles : Field.botfield) {
            for (Tiles tile : tiles) {
                switch (tile) {
                    case SHIP, WATER -> output.append(MikolasovyConsoleBarvy.WHITE + "?" + MikolasovyConsoleBarvy.RESET + " ");
                    case HIT -> output.append(MikolasovyConsoleBarvy.RED + "H" + MikolasovyConsoleBarvy.RESET + " ");
                    case MISS -> output.append(MikolasovyConsoleBarvy.BLUE + "W" + MikolasovyConsoleBarvy.RESET + " ");
                    default -> output.append(MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + " ");
                }
            }
            output.append("\n");
        }
        return output.toString();
    }
}
