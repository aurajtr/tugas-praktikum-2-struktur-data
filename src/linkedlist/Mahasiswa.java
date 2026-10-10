package linkedlist;

/**
 * Class Mahasiswa menyimpan data NIM, nama, dan nilai seorang mahasiswa.
 * Seluruh atribut bersifat private (enkapsulasi) dan diakses melalui getter/setter.
 */
public class Mahasiswa {
    private String nim;
    private String nama;
    private int nilai;

    // Constructor
    public Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    // Getter NIM
    public String getNim() {
        return nim;
    }

    // Getter nama
    public String getNama() {
        return nama;
    }

    // Getter nilai
    public int getNilai() {
        return nilai;
    }

    // Setter nilai (digunakan saat update nilai)
    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    @Override
    public String toString() {
        return "NIM: " + nim + ", Nama: " + nama + ", Nilai: " + nilai;
    }
}
