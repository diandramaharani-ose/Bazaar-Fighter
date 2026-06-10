 // Dibuat oleh: Nadiya - Class & Relasi Objek
import java.util.ArrayList;

class Toko {
    ArrayList<Item> daftarItem;
    final double PAJAK = 0.0;   // Final variable

    Toko() {
        daftarItem = new ArrayList<>();
        daftarItem.add(new Senjata("Whispering Dagger",   50, 10));
        daftarItem.add(new Senjata("Dreadnaught Cleaver", 80, 20));
        daftarItem.add(new Potion("Dewdrop Tonic",        30, 30));
        daftarItem.add(new Potion("Yggdrasil Nectar",     60, 70));
    }

    void tampilItem() {
        System.out.println("\n=== DAFTAR ITEM TOKO ===");
        for (int i = 0; i < daftarItem.size(); i++) {
            System.out.println((i + 1) + ". " + daftarItem.get(i));
        }
        System.out.println("0. Selesai belanja");
    }

    Item getItem(int index) {
        if (index < 0 || index >= daftarItem.size()) return null;
        return daftarItem.get(index);
    }

    int hitungHarga(Item item) {
        return (int)(item.getHarga() * (1 + PAJAK));
    }
}
