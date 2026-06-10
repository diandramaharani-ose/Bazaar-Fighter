// Dibuat oleh: Nadiya - Abstract Class OOP
abstract class Item {
    protected String nama;
    protected int harga;
    protected String deskripsi;

    Item(String nama, int harga, String deskripsi) {
        this.nama      = nama;
        this.harga     = harga;
        this.deskripsi = deskripsi;
    }

    public abstract void gunakanItem(Pemain p);

    public String getNama()  { return nama; }
    public int    getHarga() { return harga; }

    public String toString() {
        return nama + " [Rp" + harga + "] - " + deskripsi;
    }
}