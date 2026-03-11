import com.arroz097.models.Cat;
import com.arroz097.utils.Utility;

public class CatManager {

    private static final Utility utility = new Utility();

    public static void main(String[] argumentos) {

        var jorgineo = new Cat("jorgineo");

        utility.sleep(3);

        jorgineo.roar();

    }

}
