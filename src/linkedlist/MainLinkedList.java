package linkedlist;

public class MainLinkedList {
    public static void main(String[] args) {
        DaftarMahasiswa daftar = new DaftarMahasiswa();
        daftar.tambahMahasiswa("12345", "Andi", 85);
        daftar.tambahMahasiswa("67890", "Budi", 90);

        System.out.println("Daftar Mahasiswa:");
        daftar.tampilkanDaftar();
        System.out.println();

        System.out.println("Mengupdate nilai mahasiswa (Budi -> 95)");
        daftar.updateNilai("67890", 95);
        System.out.println("Daftar Mahasiswa setelah update:");
        daftar.tampilkanDaftar();
        System.out.println();

        daftar.tambahMahasiswa("11111", "Siti", 88);
        System.out.println("Setelah menambah Siti:");
        daftar.tampilkanDaftar();
        System.out.println();

        Mahasiswa dihapus = daftar.hapusMahasiswa("12345");
        System.out.println("Menghapus mahasiswa: " + dihapus.getNama());
        System.out.println("Daftar Mahasiswa setelah penghapusan:");
        daftar.tampilkanDaftar();
    }
}
