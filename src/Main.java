import java.util.Scanner;

public class Main {
    public static void main(String[] Args) {
        System.out.println("Vítej v bettele, retardované verzi lodí. \n" +
                "Vysvětlivky pro mapu:  \n" +
                MikolasovyConsoleBarvy.WHITE + "?" + MikolasovyConsoleBarvy.RESET + "\t obsah tohoto políčka je neznámý. \n" +
                MikolasovyConsoleBarvy.RED + "H" + MikolasovyConsoleBarvy.RESET + "\t tady býval kus lodi. býval... \n" +
                MikolasovyConsoleBarvy.BLUE + "W" + MikolasovyConsoleBarvy.RESET + "\t  tady plave kus tvojí munice \n" +
                MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + "\t jestli někde uvidíš tohle, tak jsem něco pokazil. zkus mi dát vědět."
        );

        System.out.println("Vyber si obtížnost: \n" +
                MikolasovyConsoleBarvy.WHITE + "WATERWORKS" + MikolasovyConsoleBarvy.RESET + "\t Počítač bude preferovat vodu před loděma \n" +
                MikolasovyConsoleBarvy.GREEN + "BOGO" + MikolasovyConsoleBarvy.RESET + "\t Počítač hraje náhodně. \n" +
                MikolasovyConsoleBarvy.YELLOW + "VENDETTA" + MikolasovyConsoleBarvy.RESET + "\t čím víc počítač umírá, tím víc se trefuje. \n" +
                MikolasovyConsoleBarvy.RED + "AIMBOT" + MikolasovyConsoleBarvy.RESET + "\t Počítač se pokaždé trefí.");
        Scanner scanner = new Scanner(System.in);
        try {
            Gaym.play(Enemy.Difficulty.valueOf(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println(MikolasovyConsoleBarvy.PURPLE + e.getMessage());
            System.exit(2);
        }
        System.out.println("gg, vyhrál jsi!");
    }
}