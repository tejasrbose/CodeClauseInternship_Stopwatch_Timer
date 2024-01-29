import java.util.*;

public class CodeClauseProject {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option: \n1. Stopwatch \n2. Timer \n3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runStopwatch();
                    break;
                case 2:
                    runTimer(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void runStopwatch() throws InterruptedException {
        System.out.println("Stopwatch started. (Press 'Enter' to stop)");
        long startTime = System.currentTimeMillis();

        new Thread(() -> {
            try { System.in.read(); } catch (Exception e) {}
            System.exit(0);
        }).start();

        while (true) {
            long elapsed = System.currentTimeMillis() - startTime;
            displayElapsedTime(elapsed);
            Thread.sleep(1000);
        }
    }

    private static void runTimer(Scanner scanner) throws InterruptedException {
        System.out.print("Enter time (hours, minutes, seconds): ");
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        int seconds = scanner.nextInt();
        int timeInSeconds = hours * 3600 + minutes * 60 + seconds;
    
        System.out.println("Timer started for " + hours + "h " + minutes + "m " + seconds + "s.");
        long endTime = System.currentTimeMillis() + timeInSeconds * 1000;
    
        while (timeInSeconds > 0) {
            long currentTime = System.currentTimeMillis();
            long remainingTime = endTime - currentTime;
            displayRemainingTime(remainingTime);
            Thread.sleep(1000);
            timeInSeconds = (int) (remainingTime / 1000);
        }
        System.out.println("\nTime's up!");
    }

    private static void displayRemainingTime(long remainingTime) {
        long seconds = (remainingTime / 1000) % 60;
        long minutes = (remainingTime / (1000 * 60)) % 60;
        long hours = (remainingTime / (1000 * 60 * 60)) % 24;
        System.out.printf("\rTime remaining: %02d:%02d:%02d", hours, minutes, seconds);
    }
    private static void displayElapsedTime(long elapsedTime) {
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
        System.out.printf("\rElapsed time: %02d:%02d:%02d", hours, minutes, seconds);
    }
}
