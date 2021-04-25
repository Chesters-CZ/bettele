import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] Args) throws Exception {
        System.out.println("Vítej v bettele, retardované verzi lodí. \n" +
                "Vysvětlivky pro mapu:  \n" +
                MikolasovyConsoleBarvy.WHITE + "?" + MikolasovyConsoleBarvy.RESET + "\t obsah tohoto políčka je neznámý. \n" +
                MikolasovyConsoleBarvy.RED + "H" + MikolasovyConsoleBarvy.RESET + "\t tady býval kus lodi. býval... \n" +
                MikolasovyConsoleBarvy.BLUE + "W" + MikolasovyConsoleBarvy.RESET + "\t  tady plave kus tvojí munice \n" +
                MikolasovyConsoleBarvy.PURPLE + "ERR" + MikolasovyConsoleBarvy.RESET + "\t jestli tohle někde uvidíš, tak jsem něco pokazil. zkus mi dát vědět.\n"
        );

        System.out.println("Vyber si obtížnost: \n" +
                MikolasovyConsoleBarvy.WHITE + "WATERWORKS" + MikolasovyConsoleBarvy.RESET + "\t Počítač bude preferovat vodu před loděma \n" +
                MikolasovyConsoleBarvy.GREEN + "BOGO" + MikolasovyConsoleBarvy.RESET + "\t\t Počítač hraje náhodně. \n" +
                MikolasovyConsoleBarvy.YELLOW + "VENDETTA" + MikolasovyConsoleBarvy.RESET + "\t čím víc počítač umírá, tím víc se trefuje. \n" +
                MikolasovyConsoleBarvy.RED + "AIMBOT" + MikolasovyConsoleBarvy.RESET + "\t\t Počítač se pokaždé trefí.");
        Scanner scanner = new Scanner(System.in);
        boolean firstrun = true;
        while (firstrun || scanner.nextLine().toLowerCase(Locale.ROOT).contains("y")) {
            firstrun = !firstrun;
            try {
                new Gaym().play(Enemy.Difficulty.valueOf(scanner.nextLine().toUpperCase()));
            } catch (Exception e) {
                System.out.println(MikolasovyConsoleBarvy.PURPLE + e.getMessage());
                throw e;
            }
            System.out.println("Dáš si rematch? (y/n)");
        }
        System.out.println("k, thx.");
    }
}