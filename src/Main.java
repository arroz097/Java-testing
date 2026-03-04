import java.util.Random;

public class Main {

    private static final Utility utility = new Utility();
    private static final Random rng = new Random();

    // nao é necessario sempre ter main, mas normalmente é onde o codigo principal vai rodar
    public static void main(String[] args) {

        // Classes usam letras maiusculas de inicio, (Cat, Random, Utility)
        // var ajuda a nao ter que repetir a tipagem de classes ao instanciar com new
        var jorge = new Cat("jorge");
        var eduardo = new Cat("eduardo");
        //var rng = new Random();
        //var utility = new Utility();
        // var button = new ButtonTest();

        // se caso .isMoving nao fosse private
        // button.isMoving = false;

        String jorgeName = jorge.getName();
        int jorgeEnergy = jorge.getEnergy();
        utility.write(jorgeName);
        utility.write(jorgeEnergy);

        // organizar frames u mem cima do outro
        var jorgeButton = new ButtonCreator("cat  " + jorge.getName(), "ROAR!", 200, 150, 100, 30);
        var eduardoButton = new ButtonCreator("cat " + eduardo.getName(), "ROAR!", 200, 150, 100, 30);

        var jorgeBed = new ButtonCreator(jorge.getName() + "'s bed", "sleep!", 200, 150, 100, 30);
        var eduardoBed = new ButtonCreator(eduardo.getName() + "'s bed", "sleep!", 200, 150, 100, 30);

        var loopButton = new ButtonCreator("loop ft. rng", "do the loop!");

        loopButton.getFrame().setLocation(loopButton.getFrame().getX(), 200);

        // nao é a melhor das formas para posicionar um gui
        // já que não posiciona de acordo com a dimensão da tela, e sim com valores puros absolutos
        jorgeButton.getFrame().setLocation(jorgeButton.getFrame().getX() + 200, jorgeButton.getFrame().getY());
        jorgeBed.getFrame().setLocation(jorgeButton.getFrame().getX(), jorgeButton.getFrame().getY() + 150);

        eduardoButton.getFrame().setLocation(eduardoButton.getFrame().getX() - 200, eduardoButton.getFrame().getY());
        eduardoBed.getFrame().setLocation(eduardoButton.getFrame().getX(), eduardoButton.getFrame().getY() + 150);

        // percebi que se spammar os botões as funções do OOP gato executam varias vezes, como se estivesse nuam fila
        // java aparentemente coloca os cliques em uma fila de execução
        // da pra desativar botão no clique ou criar um sistema de debounce
        jorgeButton.getButton().addActionListener(e -> {
            if (jorge.getAction()) {
                return;
            }

            // devido a função roar() ter sleep em suas threads, é necessario separar para detectar debounce do getAction()
            new Thread(jorge::roar).start();
        });
        eduardoButton.getButton().addActionListener(e -> eduardo.roar());

        jorgeBed.getButton().addActionListener(e -> jorge.sleep());
        eduardoBed.getButton().addActionListener(e -> eduardo.sleep());

        loopButton.getButton().addActionListener(e -> {
            loopButton.getButton().setEnabled(false);

            // por ser na mesma thread, o loop vai impedir os outros botões de funcionarem até terminar
            // nova thread criada puramente para o loop
            loopValue();
        });

    }

    public static void loopValue() {

        new Thread(() -> {
            int valorAleatorio;
            int valorDesignado;
            int delay = 250;

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
        }).start();

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