public class VolatileObject {


    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(""+A.getInstance().getValue());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(""+A.getInstance().getValue());
            }
        }).start();

    }

    static class A {
        int value;
        static volatile A instance;

        private A() {
            for (int i = 0; i < 100000; i++) {
                value = i;
            }
        }

        public static A getInstance() {
            System.out.println(""+instance);
            if (instance == null) {
                synchronized (A.class) {
                    if (instance == null) {
                        instance = new A();
                    }
                }
            }
            return instance;
        }

        public int getValue() {
            return value;
        }
    }
}
