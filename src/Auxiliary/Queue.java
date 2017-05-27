package Auxiliary;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private List<Integer> list;

    public Queue()
    {
        list = new LinkedList<>();
    }

    public synchronized void push(int val)
    {
        list.add(val);
    }

    public synchronized int pop()
    {
        int result = 0;
        if (!list.isEmpty())
        {
            result = list.get(0);
            list.remove(0);
        }

        return result;
    }

    public synchronized int peek()
    {
        if (!list.isEmpty())
            return list.get(0);

        return 0;
    }

    public synchronized boolean isEmpty()
    {
        return list.isEmpty();
    }
}
