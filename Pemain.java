// Dibuat oleh: Nadiya - Inheritance & Interface
import java.util.ArrayList;

class Pemain implements DapatDiserang {
    String nama;
    int hp;
    int maxHp;
    int attack;

    Integer gold;               // Dibuat oleh: Diandra - Wrapper Class
    ArrayList<Item> inventory;  // Dibuat oleh: Nadiya  - Relasi Agregasi
    static int totalPertarungan = 0; // Dibuat oleh: Diandra - Static Variable

    Pemain(String nama) {
        this.nama      = nama;
        this.hp        = GameConfig.HP_AWAL;
        this.maxHp     = GameConfig.HP_AWAL;
        this.attack    = GameConfig.ATTACK_AWAL;
        this.gold      = GameConfig.GOLD_AWAL;  // Autoboxing
        this.inventory = new ArrayList<>();
    }

    // Dibuat oleh: Ladhisya - Custom Exception Handling
    void beliItem(Item item) throws GoldKurangException {
        if (gold < item.getHarga()) {            // Unboxing
            throw new GoldKurangException(
                "Gold tidak cukup! Kamu punya Rp" + gold +
                ", butuh Rp" + item.getHarga()
            );
        }
        gold -= item.getHarga();
        inventory.add(item);
        System.out.println("Berhasil membeli " + item.getNama() + "!");
    }

    // Dibuat oleh: Ladhisya - Custom Exception Handling
    void gunakanItem(Item item) throws ItemTidakAdaException {
        if (!inventory.contains(item)) {
            throw new ItemTidakAdaException(
                "Item " + item.getNama() + " tidak ada di inventory!"
            );
        }
        item.gunakanItem(this);
        inventory.remove(item);
    }

    @Override
    public void terimaDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    @Override
    public boolean isHidup() { return hp > 0; }

    void tampilStatus() {
        System.out.println("[ " + nama + " ] HP: " + hp + "/" + maxHp +
                           " | ATK: " + attack + " | Gold: Rp" + gold);
    }

    void tampilInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory kosong.");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }
    }
}