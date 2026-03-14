import com.arroz097.models.Cat;
import com.arroz097.utils.Utility;

import java.util.Random;

public class CatManager {

    private static final Utility utility = new Utility();
    private static final Random rng = new Random();

    public static void main(String[] argumentos) {

        Cat jorgineo = new Cat("jorgineo");
        Cat fabricio = new Cat("fabricio");
        Cat jose = new Cat("jose");

        utility.sleep(3);

        jorgineo.roar();

        utility.sleep(2);

        fabricio.roar();

        utility.sleep(2);

        jose.roar();

    }

}
