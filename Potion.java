// Dibuat oleh: Nadiya - Inheritance & Polymorphism
class Potion extends Item {
    private int bonusHp;

    Potion(String nama, int harga, int bonusHp) {
        super(nama, harga, "HP +" + bonusHp);
        this.bonusHp = bonusHp;
    }

    @Override
    public void gunakanItem(Pemain p) {
        p.hp = Math.min(p.hp + bonusHp, p.maxHp);
        System.out.println(p.nama + " menggunakan " + nama + "! HP +" + bonusHp);
    }
}