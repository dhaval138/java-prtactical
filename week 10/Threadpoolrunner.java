import java.util.concurrent.*;

public class Threadpoolrunner{
    public static void main(String[] arg)
    {
        System.out.println("25AIML039");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        System.out.println("10 Treds are handeling with 3 pool");

    for (int i = 0; i<=10;i++)
    {
        final int taskid = i;
        executor.submit(() ->
        {
            String threadname = Thread.currentThread().getName();
            System.out.println("task no: " + taskid +" running on " + threadname);
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });
    }
        
          executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
    
}
}