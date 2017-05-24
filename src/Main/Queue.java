package Main;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private List<Integer> list;

    public Queue()
    {
        list = new LinkedList<>();
    }

    public void push(int val)
    {
        list.add(val);
    }

    public int pop()
    {
        int result = 0;
        if (!list.isEmpty())
        {
            result = list.get(0);
            list.remove(0);
        }

        return result;
    }

    public int getFirst()
    {
        if (!list.isEmpty())
            return list.get(0);

        return 0;
    }
}
