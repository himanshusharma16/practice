package test;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    Queue<Integer> q = new LinkedList<>();

    public int ping(int t) {
        q.offer(t);
        if(t < 3001)
            return q.size();
        else {
            int start = t - 3000;
            while(!q.isEmpty()){
                var e = q.peek();
                if(e < t)
                    q.poll();
                else
                    break;
            }
            return q.size();
        }
    }

    public static void main(String[] args) {
        var obj = new QueueTest();
        obj.ping(1);
        obj.ping(2);
        obj.ping(3000);
        obj.ping(3001);
        obj.ping(3002);
    }
}
