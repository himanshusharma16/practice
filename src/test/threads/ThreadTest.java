package test.threads;

import java.util.concurrent.*;

public class ThreadTest {

    class Runner implements Runnable{

        private String name;

        public Runner(String name){
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(this.name);
            System.out.println("Start thread - This is a message from run method of - "+ Thread.currentThread().getName());
            try {
                if(this.name.contains("5"))
                    Thread.sleep(8000);
                else
                    Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End Thread");
        }
    }

    public static void main(String[] args) {
        new ThreadTest().run();
    }

    private void run() {
        var es = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 3; i++) {
            es.execute(new Runner(i+""));
        }
        es.shutdown();
        while(!es.isTerminated()){

        }
        System.out.println("Main thread over");

        //Get the ThreadFactory implementation to use

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(3, 4, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory);
        //start the monitoring thread

        //submit work to the thread pool
        for(int i=0; i<6; i++){
            executorPool.execute(new Runner("Thread-"+i));
        }


        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
    }
}
