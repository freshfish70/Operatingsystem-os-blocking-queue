package no.ntnu;

/**
 * A consumer that will receive objects from the Channel
 * @author Girts Strazdins, 2016-02-08
 */
public class Consumer extends Thread {
    Channel channel;
    final int pause; // how long to sleep after producing each item (milliseconds)
    final int numItems; // Number of items to produce
    
    /**
     * @param channel Channel to use
     * @param pause how long to sleep after receiving each item (milliseconds)
     * @param numItems Number of items to consume
     */
    public Consumer(Channel channel, int pause, int numItems) {
        this.channel = channel;
        this.pause = pause;
        this.numItems = numItems;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= numItems; ++i) {
                sleep(pause);
                System.out.println("Receiving item " + i + "...");
                Object o = channel.receive();
                System.out.println("Done receiving item " + i + ": " 
                        + (o != null ? o.toString() : "null"));
                System.out.println("QueueStatus: " + channel.getQueueItemList());
            }
        } catch (InterruptedException ex) {
            System.out.println("Someone interrupted the consumer, cancelling");
        }
        System.out.println("Consumer going home");
    }
    
}
