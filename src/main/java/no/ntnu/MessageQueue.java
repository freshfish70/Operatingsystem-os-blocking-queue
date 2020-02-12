package no.ntnu;

import java.util.ArrayList;

public class MessageQueue implements Channel {

    private final ArrayList<Object> queue;
    private final int size;

    public MessageQueue(int size) {
        if (size < 1) {
            size = 1; // Do not allow weird size
        }

        this.size = size;
        queue = new ArrayList<>(size);
    }

    @Override
    public synchronized void send(Object item) {
        try {
            while (this.queue.size() == this.size) {
                System.out.println("Queue full - Waiting");
                wait();
            }
        } catch (InterruptedException e) {
        } finally {
            queue.add(item);
            notify();
        }
    }


    // implements a nonblocking receive
    @Override
    public synchronized Object receive() {
        try {
            while (queue.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
        } finally {
            Object data = queue.remove(0);
            notify();
            return data;
        }
    }

    /**
     * Returns the size of the queue
     *
     * @return size of the queue
     */
    @Override
    public synchronized int getNumQueuedItems() {
        return queue.size();
    }

    /**
     * Return comma-separated objects
     *
     * @return comma-separated objects
     * <p>
     * This method is synchronized to avoid <code>concurrentmodificationexception</code>
     * where a thread might iterate over the list and another thread is also manipulating the list.
     */
    @Override
    public synchronized String getQueueItemList() {
        String res = "";
        for (Object item : queue) {
            res += item + ",";
        }
        return res;
    }
}
