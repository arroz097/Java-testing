public class Utility {

    public void write(Object message) {
        System.out.println(message);
    }

    // nao há necessidade de fazer int lerp, double ja consegue dar conta

    // public int lerp(int a, int b, int t) {
    //    return a + (b - a) * t;
    //}

    public double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    public void sleep(int threadDelay) {
        try {
            Thread.sleep(threadDelay * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sleep(double threadDelay) {
        try {
            Thread.sleep((long) (threadDelay * 1000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
