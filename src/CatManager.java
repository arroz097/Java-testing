import com.arroz097.models.Cat;
import com.arroz097.utils.Utility;

import java.util.Random;

public class CatManager {

    private static final Utility utility = new Utility();
    private static final Random rng = new Random();

    public static void main(String[] argumentos) {

        // fazer botao de sapwnar de gatos e tal?

        // e aparentemente javaSwing nao é bom pra animação e sprites
        // testar javaFX depois para sprites mais elaborados
        Cat jorgineo = new Cat("jorgineo");
        Cat fabricio = new Cat("fabricio");
        Cat jose = new Cat("jose");

//        for (int i = 0; i < 6; i++) {
//            utility.sleep(1.5);
//            jorgineo.roar();
//        }
//
//        jorgineo.sleep();

        utility.sleep(3);

        jorgineo.initialize();
        fabricio.initialize();
        jose.initialize();

//        Cat fabricio = new Cat("fabricio");
//        Cat jose = new Cat("jose");
//
//        utility.sleep(3);
//
//        jorgineo.roar();
//
//        utility.sleep(2);
//
//        fabricio.roar();
//
//        utility.sleep(2);
//
//        jose.roar();

    }

}