// Dibuat oleh: Diandra - Multithreading (Thread 2)
class ArenaThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Bersiap untuk bertarung...");
            for (int i = 3; i >= 1; i--) {
                System.out.println(i + "...");
                Thread.sleep(700);
            }
            System.out.println("MULAI!\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}