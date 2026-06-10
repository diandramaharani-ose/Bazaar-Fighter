// Dibuat oleh: Nadiya - Inheritance & Polymorphism
class Senjata extends Item {
    private int bonusAtk;

    Senjata(String nama, int harga, int bonusAtk) {
        super(nama, harga, "ATK +" + bonusAtk);
        this.bonusAtk = bonusAtk;
    }

    @Override
    public void gunakanItem(Pemain p) {
        p.attack += bonusAtk;
        System.out.println(p.nama + " menggunakan " + nama + "! ATK +" + bonusAtk);
    }
}