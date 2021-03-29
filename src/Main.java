public class Main {
    public static void main(String[] Args) {
        System.out.println("Vítej v bettele, retardované verzi lodí. \n" +
                "Vysvětlivky pro mapu:  \n" +
                MikolasovyConsoleBarvy.WHITE + "?" + MikolasovyConsoleBarvy.RESET + "\t obsah tohoto políčka je neznámý. \n" +
                MikolasovyConsoleBarvy.RED + "H" + MikolasovyConsoleBarvy.RESET + "\t tady býval kus lodi. býval... \n" +
                MikolasovyConsoleBarvy.BLUE + "W" + MikolasovyConsoleBarvy.RESET + "\t  tady plave kus tvojí munice \n" +
                MikolasovyConsoleBarvy.PURPLE + "!" + MikolasovyConsoleBarvy.RESET + "\t jestli někde uvidíš tohle, tak jsem něco pokazil. zkus mi dát vědět."
        );

        Gaym.play();
        System.out.println("gg, vyhrál jsi!");
    }
}