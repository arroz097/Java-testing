import javax.swing.*;
import java.awt.*;

public class Button {

    // String[] args é uma variavel de arrays, com nome de args
    public static void main(String[] args) {

        var utility = new Utility();

        JFrame frame = new JFrame("this is a test!!");
        frame.setSize(300, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton botao = new JButton("move!");
        botao.setBounds(70, 70, 150, 30);

        //botao.setSize(50, 50);

        frame.add(botao);

        // { e } podem ser usados para criar escopos
        {
            String text1 = "this is a string";
            utility.write(text1);
        }

        // variavel text1 nao existe fora do escopo, se tentar chamar vai dar erro


        // variaveis criadas dentro de lambda -> só pertencem aquele escopo
        // nao se pode usar variavel fora de lambda e referenciar dentro de alguma escopo lambda
        botao.addActionListener(e -> {

            new Thread(() -> {
                double testVar = 0;
                double location = 0;

                int currentX = frame.getX();
                int currentY = frame.getY();

                utility.write("le fishe");

                for (int i = 0; i < 30; i++) {
                    testVar = utility.lerp(testVar, 10, 0.1);
                    location = utility.lerp(location, 100, 0.1);

                    // tal do cast, usa (variavel)
                    // cast transforma um tipo de variavel em outra diretamente, então os double vira int por exemplo, 5.5 > 5 etc
                    //botao.setLocation((int) location, (int) location);

                    // (cast) é necessario pois frame trabalha apenas com numeros inteiros devido aos pixeis, entao a conver~sao é obrigatoria
                    // e por lerp ser double naturalmente, a conversão é de double > int
                    frame.setLocation(currentX + (int) location, currentY + (int) location);

                    utility.write(testVar);

                    utility.sleep(0.1);
                    // utility.write("le fishe repeat " + i);
                }

                essay();

            }).start();

        });

        frame.getContentPane().add(botao);
        frame.setVisible(true);

    }

    public static void essay() {
        System.out.println("\u2060Meu nome é Yoshikage Kira. Tenho 33 anos. Minha casa fica na parte nordeste de Morioh, onde todas as casas estão, e eu não sou casado. Eu trabalho como funcionário das lojas de departamentos Kame Yu e chego em casa todos os dias às oito da noite, no máximo. Eu não fumo, mas ocasionalmente bebo. Estou na cama às 23 horas e me certifico de ter oito horas de sono, não importa o que aconteça. Depois de tomar um copo de leite morno e fazer cerca de vinte minutos de alongamentos antes de ir para a cama, geralmente não tenho problemas para dormir até de manhã. Assim como um bebê, eu acordo sem nenhum cansaço ou estresse pela manhã. Foi-me dito que não houve problemas no meu último check-up. Estou tentando explicar que sou uma pessoa que deseja viver uma vida muito tranquila. Eu cuido para não me incomodar com inimigos, como ganhar e perder, isso me faria perder o sono à noite. É assim que eu lido com a sociedade e sei que é isso que me traz felicidade. Embora, se eu fosse lutar, não perderia para ninguém.\n");
    }

}