import java.util.Scanner;

public class Gaym {
    public static void play(){
        while(Field.victory()){
            System.out.println(Field.printField());
            int x;
            int y;
            boolean works = true;
            while(works){
                Scanner scanner = new Scanner(System.in);
                try{
                    System.out.println(MikolasovyConsoleBarvy.ANSI_CYAN + "zvol X útoku");
                    x = scanner.nextInt()-1;
                    System.out.println(MikolasovyConsoleBarvy.ANSI_CYAN + "zvol Y útoku");
                    y = scanner.nextInt()-1;
                    System.out.println(MikolasovyConsoleBarvy.ANSI_CYAN + "útočím...");
                    Field.strike(x,y);
                    works = false;

                } catch (Exception e){
                    System.out.println(MikolasovyConsoleBarvy.ANSI_CYAN + "Něcos pokazil. Zkus to znovu.");
                    works = true;
                }
            }
        }

    }
}
