package no.ntnu;

/**
 * Test class for Exercise on Blocking MessageQueue class. See constants
 * and their comments below
 * 
 * @author Girts Strazdins, 2016-02-08
 */
public class MainClass {
    /** Max items in the queue */
    private static final int QUEUE_SIZE = 3;

    /** How many items to produce */
    private static final int MAX_ITEMS = 10;
    
    /** How long the producer should sleep after producing an item */
    private static final int PRODUCER_SLEEP_TIME = 3000;
    
    /* If you have a non-blocking queue:
     * 1) If consumer sleep time is lower than producer sleep, consumer will
     *     get null items
     * 2) If consumer sleep time is greater, the producer will overflow the 
     *     queue 
     */
    /** How long the consumer should sleep after consuming an item */
    private static final int CONSUMER_SLEEP_TIME = 10000;
    
    public static void main(String args[]) {
        // Create a shared Message Queue
        MessageQueue queue = new MessageQueue(QUEUE_SIZE);
        
        // Create a producer and a consumer
        Producer p = new Producer(queue, PRODUCER_SLEEP_TIME, MAX_ITEMS);
        Consumer c = new Consumer(queue, CONSUMER_SLEEP_TIME, MAX_ITEMS);
       
        // Run the produce+consumer processes
        p.start();
        c.start();
        
        // Wait for producer and consumer to finish
        try {
            p.join();
            c.join();
        } catch (InterruptedException ex) {
            System.out.println("Parent process interrupted"); 
        }
        
    }
}
