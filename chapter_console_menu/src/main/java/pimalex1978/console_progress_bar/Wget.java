package pimalex1978.console_progress_bar;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    System.out.print("\rLoading : " + i + " %");
                    Thread.sleep(500);
                }
                System.out.print("\rLoaded successfully.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
