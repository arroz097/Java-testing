import javax.swing.*;
import java.util.Random;
// importar pacotes de utilidade propriamente meus? talvez

public class ButtonTest {

    // private impede de consguirem modificar a variavel de fora
    // por ser uma varivel pertencente a classe Button, o this.isMoving funciona, ja que está fora do main que é uma função static
    // (this) consegue referenciar classes
    private boolean isMoving = false;

    // static permite a classe utility ser usada em todos metodos e funções
    // final permite que a variavel nao seja alterada
    private static final Utility utility = new Utility();
    private static final ButtonTest button = new ButtonTest();
    private static final Random rng = new Random();

    private static final JFrame frame = new JFrame("this is a test!!");
    private static final JButton buttonGui = new JButton("move!");

    public void toggleMove(boolean toggle) {
        this.isMoving = toggle;
        // return  this.isMoving;
    }

    public void toggleMove() {
        // daria pra ter usado também this.isMoving = !this.isMoving;
        // ! é operador de negação, tal qual NOT
        if (this.isMoving) {
            this.isMoving = false;
        } else {
            this.isMoving = true;
        }
    }

    public void someTest() {
        this.isMoving = false;
    }

    // int[] var1 é possivel também, etc
    // String[] args é uma variavel de arrays, com nome de args
    public static void main(String[] args) {

        /*
         var ajuda a nao ter que repetir a tipagem de classes ao instanciar com new
         var utility = new Utility();
         var rng = new Random();
         var button = new ButtonTest();
        */

        // button1.moveButton(30, 40);

        //button1.moveButton();

        //button1.createButton();

        //JFrame frame = new JFrame("this is a test!!");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); // centraliza o gui no meio da tela
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonGui.setBounds(70, 70, 150, 30);
        buttonGui.setFocusPainted(false);

        frame.add(buttonGui);
        frame.setVisible(true);

        button.someTest();

        var button1 = new ButtonCreator("frame1", "move me");
        button1.frame.setLocation(button1.frame.getX() / 2 + frame.getX(), button1.frame.getY());

        var rngButton = new ButtonCreator("rng master", "create buttons");
        rngButton.frame.setLocation(-rngButton.frame.getX() / 2 + frame.getX(), rngButton.frame.getY());

        button1.moveButton(0, 200);

        // { e } podem ser usados para criar escopos
        {
            int[] intArrays = {};
            String text1 = "this is a string";
            utility.write(text1);
        }
        // variavel text1 nao existe fora do escopo, se tentar chamar vai dar erro


        // variaveis criadas dentro de lambda -> só pertencem aquele escopo
        // nao se pode usar variavel fora de lambda e referenciar dentro de alguma escopo lambda
        // e aparenemtente lambda é uma função anonima, então nao cosnegue usar variaveis de fora dentro
        buttonGui.addActionListener(e -> {
            //button.moveButton();
            moveButton();
        });

        // swing utiliters invoke later aqui talvez também
        rngButton.button.addActionListener(e -> {
            for (int i = 0; i < 10 ; i++) {
                int xLocation = rng.nextInt(-400, 400);
                int yLocation = rng.nextInt(-400, 400);

                var holder = new ButtonCreator("frame " + i, "dont mind me");

                holder.frame.setLocation(rngButton.frame.getX() + xLocation, rngButton.frame.getY() + yLocation);

            }
        });

    }

    public static void moveButton() {
        if (button.isMoving) return;

        button.toggleMove(true);

        // recomendado usar swingUtilities em vez de novas threads aparentemente
        // new Thread parece ser pesado se criado toda hora, mas por ora serve
        new Thread(() -> {
            double testVar = 0;
            double location = 0;

            int currentX = frame.getX();
            int currentY = frame.getY();

            int randomPos = rng.nextInt(100, 400);

            boolean bool = rng.nextBoolean();

            utility.write(randomPos);
            utility.write(bool);

            buttonGui.setText("moving...");

            for (int i = 0; i < 30; i++) {
                testVar = utility.lerp(testVar, 10, 0.1);
                location = utility.lerp(location, randomPos, 0.1);

                // tal do cast, usa (variavel)
                // cast transforma um tipo de variavel em outra diretamente, então os double vira int por exemplo, 5.5 > 5 etc
                //botao.setLocation((int) location, (int) location);

                // (cast) é necessario pois frame trabalha apenas com numeros inteiros devido aos pixeis, entao a conver~sao é obrigatoria
                // e por lerp ser double naturalmente, a conversão é de double > int
                if (bool) {
                    frame.setLocation(currentX + (int) location, currentY + (int) location);
                } else {
                    frame.setLocation(currentX + (int) -location, currentY + (int) -location);
                }

                // utility.write(testVar);

                utility.sleep(0.1);
                // utility.write("le fishe repeat " + i);
            }

            essay();

            utility.sleep(1);

            button.toggleMove();

            buttonGui.setText("move!");

        }).start();
    }

    public static void essay() {
        System.out.println("\u2060Meu nome é Yoshikage Kira. Tenho 33 anos. Minha casa fica na parte nordeste de Morioh, onde todas as casas estão, e eu não sou casado. Eu trabalho como funcionário das lojas de departamentos Kame Yu e chego em casa todos os dias às oito da noite, no máximo. Eu não fumo, mas ocasionalmente bebo. Estou na cama às 23 horas e me certifico de ter oito horas de sono, não importa o que aconteça. Depois de tomar um copo de leite morno e fazer cerca de vinte minutos de alongamentos antes de ir para a cama, geralmente não tenho problemas para dormir até de manhã. Assim como um bebê, eu acordo sem nenhum cansaço ou estresse pela manhã. Foi-me dito que não houve problemas no meu último check-up. Estou tentando explicar que sou uma pessoa que deseja viver uma vida muito tranquila. Eu cuido para não me incomodar com inimigos, como ganhar e perder, isso me faria perder o sono à noite. É assim que eu lido com a sociedade e sei que é isso que me traz felicidade. Embora, se eu fosse lutar, não perderia para ninguém.\n");
    }

}