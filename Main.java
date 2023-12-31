import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args){

        Instant start;
        Instant end;
        //System.out.println("Hello World");
        //We will make 100 threads and each will count to 1,000,000

        //Instantiate Instant variables to measure start and end time
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        //ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<Integer> task1 = () -> sumUpTo(20000);
        Callable<Integer> task2 = () -> sumUpTo(20000);
        Callable<Integer> task3 = () -> sumUpTo(20000);
        Callable<Integer> task4 = () -> sumUpTo(20000);
        Callable<Integer> task5 = () -> sumUpTo(20000);

        //call start instant
        start = Instant.now();

        Future<Integer> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);
        Future<Integer> future3 = executorService.submit(task3);
        Future<Integer> future4 = executorService.submit(task4);
        Future<Integer> future5 = executorService.submit(task5);

        try {
            int result1 = future1.get();
            int result2 = future2.get();
            int result3 = future3.get();
            int result4 = future4.get();
            int result5 = future5.get();

            //call end instant, find time difference, and print out result

            int totalSum = result1 + result2 + result3 + result4 + result5;
            end = Instant.now();
            Duration duration = Duration.between(start, end);


            System.out.println("Time taken: " + duration.toMillis() + " milliseconds");
            System.out.println("Total Sum: " + totalSum);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    } 
    private static int sumUpTo(int n) {
        int sum = 0;
        for (int i = 1; i <= 20000000; i++) {
            sum += 1;
        }
        return sum;
    }
}
