// Dibuat oleh: Mahasiswa 1 (Arsitek Sistem & UI) - Merancang Kerangka Antarmuka
// Digabungkan oleh: Mahasiswa 2 (Engineer Keandalan & Integrasi) - Proses Merge Tim
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("   " + GameConfig.NAMA_GAME);
        System.out.println("=================================");
        System.out.print("Masukkan nama pemain: ");
        String nama = input.nextLine();

        Pemain pemain = new Pemain(nama);

        Musuh[] daftarMusuh = {
            new Musuh("Goblin",    40,  8,  50),
            new Musuh("Ork",       70,  15, 80),
            new Musuh("Dark Lord", 120, 25, 150)
        };

        Toko toko = new Toko();
        boolean isRunning = true;

        while (isRunning && pemain.isHidup()) {

            System.out.println("\n=================================");
            System.out.println("   " + GameConfig.NAMA_GAME);
            System.out.println("=================================");
            pemain.tampilStatus();
            System.out.println("\n1. Pergi ke Toko");
            System.out.println("2. Pergi ke Arena");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            // Dibuat oleh: Mahasiswa 2 (Engineer Keandalan & Integrasi) - Try-Catch Utama UI
            try {
                int menu = input.nextInt();

                if (menu == 0) {
                    isRunning = false;

                } else if (menu == 1) {
                    // Eksekusi Thread 1 (Oleh Mahasiswa 3)
                    Thread tokoThread = new Thread(new TokoThread());
                    tokoThread.start();
                    tokoThread.join();

                    boolean belanja = true;
                    while (belanja) {
                        toko.tampilItem();
                        System.out.print("Pilih item (0 untuk selesai): ");
                        int pilih = input.nextInt();

                        if (pilih == 0) {
                            belanja = false;
                        } else {
                            Item item = toko.getItem(pilih - 1);
                            if (item == null) {
                                System.out.println("Item tidak tersedia!");
                            } else {
                                try {
                                    pemain.beliItem(item);
                                } catch (GoldKurangException e) {
                                    System.out.println("ERROR: " + e.getMessage());
                                }
                            }
                        }
                    }

                } else if (menu == 2) {
                    System.out.println("\nPilih musuh:");
                    for (int i = 0; i < daftarMusuh.length; i++) {
                        System.out.println((i + 1) + ". " + daftarMusuh[i].nama +
                                           " [HP: " + daftarMusuh[i].hp +
                                           " | ATK: " + daftarMusuh[i].attack + "]");
                    }
                    System.out.print("Pilih musuh: ");
                    int pilihMusuh = input.nextInt();

                    if (pilihMusuh < 1 || pilihMusuh > daftarMusuh.length) {
                        System.out.println("Musuh tidak valid!");
                    } else {
                        Musuh musuhDipilih = new Musuh(
                            daftarMusuh[pilihMusuh - 1].nama,
                            daftarMusuh[pilihMusuh - 1].hp,
                            daftarMusuh[pilihMusuh - 1].attack,
                            daftarMusuh[pilihMusuh - 1].goldReward
                        );

                        // Eksekusi Thread 2 (Oleh Mahasiswa 3)
                        Thread arenaThread = new Thread(new ArenaThread());
                        arenaThread.start();
                        arenaThread.join();

                        Arena arena = new Arena(pemain, musuhDipilih, input);
                        arena.mulaiPertarungan();
                    }

                } else {
                    System.out.println("Pilihan tidak valid!");
                }

            } catch (InputMismatchException e) {
                System.out.println("INPUT HARUS ANGKA!");
                input.nextLine();
            }
        }

        if (!pemain.isHidup()) {
            System.out.println("\n💀 GAME OVER! " + pemain.nama + " telah gugur.");
        }

        System.out.println("Total pertarungan: " + Pemain.totalPertarungan);
        System.out.println("\nTerima kasih sudah bermain!");
        input.close();
    }
}