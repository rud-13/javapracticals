class SharedBuffer {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int val) throws InterruptedException {
        while (hasData) {
            wait();
        }
        this.data = val;
        this.hasData = true;
        System.out.println("[PRODUCER] Produced item: " + val);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (!hasData) {
            wait();
        }
        int val = this.data;
        this.hasData = false;
        System.out.println("[CONSUMER] Consumed item: " + val);
        notify();
        return val;
    }
}

public class ProducerConsumerBuffer {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i * 10);
                    Thread.sleep(50);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                    Thread.sleep(100);
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Producer-Consumer coordination completed with zero loss.");
    }
}