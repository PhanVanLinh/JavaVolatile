import java.util.Calendar;

public class Main {

//    private static volatile boolean running = true; // using volatile can called
    private static volatile boolean running = true; // without using volatile Stopped never called

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
                while (running) {
                    //System.out.println("dadsadas");
                }
                System.out.println("Stopped");
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("Stop");
        running = false;
    }
}