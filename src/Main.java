import java.util.Random;

public class Main {

    // nao é necessario sempre ter main, mas normalmente é onde o codigo principal vai rodar
    public static void main(String[] args) {

        // Classes usam letras maiusculas de inicio, (Cat, Random, Utility)
        // var ajuda a nao ter que repetir a tipagem de classes ao instanciar com new
        var jorge = new Cat("jorge");
        var eduardo = new Cat("eduardo");
        var rng = new Random();
        var utility = new Utility();
        // var button = new ButtonTest();

        // se caso .isMoving nao fosse private
        // button.isMoving = false;

        int valorAleatorio;
        int valorDesignado;
        int delay = 250;

        var jorgeButton = new ButtonCreator("cat  " + jorge.name, "ROAR!", 200, 150, 100, 30);
        var eduardoButton = new ButtonCreator("cat " + eduardo.name, "ROAR!", 200, 150, 100, 30);

        var jorgeBed = new ButtonCreator(jorge.name + "'s bed", "sleep!", 200, 150, 100, 30);
        var eduardoBed = new ButtonCreator(eduardo.name + "'s bed", "sleep!", 200, 150, 100, 30);

        jorgeButton.button.addActionListener(e -> jorge.roar());
        eduardoButton.button.addActionListener(e -> eduardo.roar());

        jorgeBed.button.addActionListener(e -> jorge.sleep());
        eduardoBed.button.addActionListener(e -> eduardo.sleep());

        valorDesignado = rng.nextInt(60);

        utility.write("Valor designado: " + valorDesignado + "   ");

        while (true) { // ou while != valorDesignado, ?, possivel

            // utility.lerp(testVar, 10,  0.1);

            valorAleatorio = rng.nextInt(60);
            //write(valorAleatorio);

            System.out.print("\rValor: " + valorAleatorio);

            if (valorAleatorio == valorDesignado) break;

            // try catch é basicamente um pcall() necesario em sleeps, java parece obrigar pra caso tenha erro ao contrario de lua
            // interruptedException e runtimeException parecem ser necesasrios em outras partes também, sem try catch

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // utility.sleep(2.5);
        }
        System.out.println();

        System.out.println("loop encontrou valor aleatorio (" + valorDesignado + ") designado.");
       // write(" loop encontrou valor aleatorio " + valorDesignado + " designado");

        String phrase = writeReturn("i am a phrase wassup!");

        utility.write(phrase);

    }

    // funções sem void precisam retornar valor, sendo necessario declarar tipo de variavel
    public static String writeReturn(String text) {
        System.out.println(text);

        return text;
    }

    // funções com void apenas "ignoram" (no caso nao existe, pois void é nulo) e rodam a função diretamente, sem retornar nada
    public void write(String text) {
        System.out.println(text);
    }

    public void write(int number) {
        System.out.println(number);
    }

    public int somar(int n1, int n2) {
        return n1 + n2;
    }

}