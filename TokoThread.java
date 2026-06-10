// Dibuat oleh: Diandra - Multithreading (Thread 1)
class TokoThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.print("Membuka toko");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(400);
                System.out.print(".");
            }
            System.out.println(" Selamat datang!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}