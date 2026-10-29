import java.util.concurrent.atomic.AtomicInteger;

class Total {
    int total = 0;

    void add(int n) {
        total += n;
    }

    synchronized void addSync(int n) {
        total += n;
    }
}

public class ParallelSum {
    public static void main(String[] args) throws Exception {
        int[] numbers = new int[100000];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 1;
        }

        // Without synchronization
        Total total1 = new Total();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 25000; i++) {
                total1.add(numbers[i]);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 25000; i < 50000; i++) {
                total1.add(numbers[i]);
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 50000; i < 75000; i++) {
                total1.add(numbers[i]);
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 75000; i < 100000; i++) {
                total1.add(numbers[i]);
            }
        });

        long start = System.nanoTime();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        long end = System.nanoTime();

        System.out.println("25AIML039");
        System.out.println("Without synchronization: " + total1.total);
        System.out.println("Time: " + (end - start) / 1000000 + " ms");


        // With synchronized
        Total total2 = new Total();

        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 25000; i++) {
                total2.addSync(numbers[i]);
            }
        });

        Thread t6 = new Thread(() -> {
            for (int i = 25000; i < 50000; i++) {
                total2.addSync(numbers[i]);
            }
        });

        Thread t7 = new Thread(() -> {
            for (int i = 50000; i < 75000; i++) {
                total2.addSync(numbers[i]);
            }
        });

        Thread t8 = new Thread(() -> {
            for (int i = 75000; i < 100000; i++) {
                total2.addSync(numbers[i]);
            }
        });

        start = System.nanoTime();

        t5.start();
        t6.start();
        t7.start();
        t8.start();

        t5.join();
        t6.join();
        t7.join();
        t8.join();

        end = System.nanoTime();

        System.out.println("With synchronized: " + total2.total);
        System.out.println("Time: " + (end - start) / 1000000 + " ms");


        // With AtomicInteger
        AtomicInteger atomicTotal = new AtomicInteger(0);

        Thread t9 = new Thread(() -> {
            for (int i = 0; i < 25000; i++) {
                atomicTotal.addAndGet(numbers[i]);
            }
        });

        Thread t10 = new Thread(() -> {
            for (int i = 25000; i < 50000; i++) {
                atomicTotal.addAndGet(numbers[i]);
            }
        });

        Thread t11 = new Thread(() -> {
            for (int i = 50000; i < 75000; i++) {
                atomicTotal.addAndGet(numbers[i]);
            }
        });

        Thread t12 = new Thread(() -> {
            for (int i = 75000; i < 100000; i++) {
                atomicTotal.addAndGet(numbers[i]);
            }
        });

        start = System.nanoTime();

        t9.start();
        t10.start();
        t11.start();
        t12.start();

        t9.join();
        t10.join();
        t11.join();
        t12.join();

        end = System.nanoTime();

        System.out.println("With AtomicInteger: " + atomicTotal.get());
        System.out.println("Time: " + (end - start) / 1000000 + " ms");
    }
}