// Dibuat oleh: Ladhisya - Exception Handling & Try-Catch-Finally
import java.util.Scanner;

class Arena {
    private Pemain  pemain;
    private Musuh   musuh;
    private Scanner input;

    Arena(Pemain pemain, Musuh musuh, Scanner input) {
        this.pemain = pemain;
        this.musuh  = musuh;
        this.input  = input;
    }

    void mulaiPertarungan() {
        Pemain.totalPertarungan++;
        System.out.println("\n⚔ PERTARUNGAN #" + Pemain.totalPertarungan +
                           ": " + pemain.nama + " VS " + musuh.nama);

        while (pemain.isHidup() && musuh.isHidup()) {
            tampilStatus();
            giliranPemain();
            if (!musuh.isHidup()) break;
            giliranMusuh();
        }

        if (pemain.isHidup()) {
            System.out.println("\n🏆 Kamu menang! +" + musuh.goldReward + " gold");
            pemain.gold += musuh.goldReward;
        } else {
            System.out.println("\n💀 Kamu kalah...");
        }
    }

    private void giliranPemain() {
        System.out.println("\nGiliranmu!");
        System.out.println("1. Serang");
        System.out.println("2. Gunakan Item");
        System.out.print("Pilih aksi: ");

        try {
            int aksi = input.nextInt();
            if (aksi == 1) {
                musuh.terimaDamage(pemain.attack);
            } else if (aksi == 2) {
                if (pemain.inventory.isEmpty()) {
                    System.out.println("Inventory kosong! Menyerang otomatis.");
                    musuh.terimaDamage(pemain.attack);
                } else {
                    pemain.tampilInventory();
                    System.out.print("Pilih item: ");
                    int pilihItem = input.nextInt() - 1;
                    Item item = (pilihItem >= 0 && pilihItem < pemain.inventory.size())
                                ? pemain.inventory.get(pilihItem) : null;
                    if (item == null) {
                        System.out.println("Item tidak valid! Menyerang otomatis.");
                        musuh.terimaDamage(pemain.attack);
                    } else {
                        try {
                            pemain.gunakanItem(item);
                        } catch (ItemTidakAdaException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    }
                }
            } else {
                System.out.println("Pilihan tidak valid, menyerang otomatis.");
                musuh.terimaDamage(pemain.attack);
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("INPUT HARUS ANGKA! Menyerang otomatis.");
            input.nextLine();
            musuh.terimaDamage(pemain.attack);
        } finally {
            System.out.println("[Sistem] Giliran selesai dieksekusi.");
        }
    }

    private void giliranMusuh() {
        System.out.println("\nGiliran " + musuh.nama + "!");
        musuh.serang(pemain);
    }

    private void tampilStatus() {
        System.out.println("\n--- STATUS ---");
        pemain.tampilStatus();
        System.out.println("[ " + musuh.nama + " ] HP: " + musuh.hp);
        System.out.println("--------------");
    }
}