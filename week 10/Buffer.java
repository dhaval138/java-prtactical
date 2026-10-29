class Buff {
    int data;
    boolean empty = true;

    synchronized void produce(int val) throws InterruptedException {
        
        while (!empty) wait();
        data = val;
        empty = false;
        System.out.println("Produced: " + val);
        notifyAll();
    }

    synchronized int consume() throws InterruptedException {
        while (empty) wait();
        empty = true;
        System.out.println("Consumed: " + data);
        notifyAll();
        return data;
    }
}

public class Buffer {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("25AIML039");
        Buff b = new Buff();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) b.produce(i);
            } catch (InterruptedException e) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) b.consume();
            } catch (InterruptedException e) {}
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Done.");
    }
}