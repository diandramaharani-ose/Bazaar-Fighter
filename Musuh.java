// Dibuat oleh: Nadiya - Class & Relasi Objek
class Musuh {
    String nama;
    int hp;
    int attack;
    int goldReward;

    Musuh(String nama, int hp, int attack, int goldReward) {
        this.nama       = nama;
        this.hp         = hp;
        this.attack     = attack;
        this.goldReward = goldReward;
    }

    void serang(Pemain p) {
        p.terimaDamage(attack);
        System.out.println(nama + " menyerang " + p.nama + "! -" + attack + " HP");
    }

    void terimaDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
        System.out.println(nama + " menerima " + dmg + " damage! HP tersisa: " + hp);
    }

    boolean isHidup() { return hp > 0; }
}