package no.ntnu;
/**
 * A producer of items to be sent over the Channel
 *
 * @author Girts Strazdins, 2016-02-08
 */
public class Producer extends Thread {

    final int pause; // how long to sleep after producing each item (milliseconds)
    final int numItems; // Number of items to produce
    final Channel channel;

    /**
     * @param channel Channel to use
     * @param pause how long to sleep after producing each item (milliseconds)
     * @param numItems Number of items to produce
     */
    public Producer(Channel channel, int pause, int numItems) {
        this.channel = channel;
        this.pause = pause;
        this.numItems = numItems;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= numItems; ++i) {
                String msg = "Cake " + i;
                System.out.println("Producing " + msg + "...");
                channel.send(msg);
                System.out.println("Done sending " + msg);
                System.out.println("QueueStatus: " + channel.getQueueItemList());
                sleep(pause);
            }
        } catch (InterruptedException ex) {
            System.out.println("Someone interrupted the producer, cancelling");
        }
        System.out.println("Producer going home");
    }

}
