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
    public void send(Object item) {
        // TODO - block if the queue is full
        queue.add(item);
    }

    // implements a nonblocking receive
    @Override
    public Object receive() {
        // TODO - block if the queue is empty, and always return the first 
        // element in the queue
        if (queue.isEmpty()) {
            return null;
        } else {
            return queue.remove(0);
        }
    }

    @Override
    public int getNumQueuedItems() {
        return queue.size();
    }

    /**
     * Return comma-separated objects
     * @return 
     */
    @Override
    public String getQueueItemList() {
        String res = "";
        for (Object item : queue) {
            res += item + ",";
        }
        return res;
    }
}
