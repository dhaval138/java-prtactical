class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class CounterRace {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();
        int threadCount = 10;
        int increments = 100000;
        Thread[] threads = new Thread[threadCount];




        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) {
                    counter.increment();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        int expected = threadCount * increments;
        System.out.println("25AIML039");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + counter.count);
    }
}