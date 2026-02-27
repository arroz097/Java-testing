import java.awt.*;

public class Utility {

    // tudo em java é Object, tudo herda de Object, classes, strings, numeros, etc
    public void write(Object message) {
        System.out.println(message);
    }

    /*
     nao há necessidade de fazer int lerp, double ja consegue dar conta

     public int lerp(int a, int b, int t) {
        return a + (b - a) * t;
    }
     */

    public double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    public void setSizeCentered(Component gui, int width, int height) {
        int centerX = gui.getX() + gui.getWidth() / 2;
        int centerY = gui.getY() + gui.getHeight() / 2;

        gui.setBounds(centerX - width / 2, centerY - height / 2, width, height);
    }

    // entender o conceito de "L" em numeros longos, tem relação com milimis
    public void sleep(int threadDelay) {
        try {
            Thread.sleep(threadDelay * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // entender o conceito de (long) depois, ainda nao compreendi muito bem
    public void sleep(double threadDelay) {
        try {
            // (long) é um tipo de cast, tal qual (int)
            Thread.sleep((long) (threadDelay * 1000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
