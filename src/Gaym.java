import java.util.Scanner;

public class Gaym {
    public static void play() {
        while (!Field.won()) {
            System.out.println(printField());
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
        }

    }

    public static String printField() {
        StringBuilder output = new StringBuilder();
        for (Tiles[] tiles : Field.field) {
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
