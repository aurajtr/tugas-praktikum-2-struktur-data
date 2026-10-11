
import queue.CustomerQueue;
import stack.TextEditor;
import linkedlist.DaftarMahasiswa;

public class Main {

    private static final int JUMLAH_UJI = 10000;

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" INTEGRASI QUEUE, STACK, LINKED LIST");
        System.out.println("======================================");

        demonstrasiQueue();
        demonstrasiStack();
        demonstrasiLinkedList();

        System.out.println("\n======================================");
        System.out.println(" PENGUJIAN WAKTU EKSEKUSI");
        System.out.println("======================================");

        ujiQueue();
        ujiStack();
        ujiLinkedList();

        tampilkanKompleksitas();
    }

    // CASE A: QUEUE
    private static void demonstrasiQueue() {
        System.out.println("\n--- CASE A: QUEUE ---");

        CustomerQueue antrean = new CustomerQueue();

        antrean.enqueue("Andi");
        antrean.enqueue("Budi");
        antrean.enqueue("Siti");

        antrean.tampilkan();
        antrean.dequeue();

        System.out.println("Jumlah pelanggan tersisa: "
                + antrean.getSize());
    }

    // CASE B: STACK
    private static void demonstrasiStack() {
        System.out.println("\n--- CASE B: STACK ---");

        TextEditor editor = new TextEditor();

        editor.tambahTeks("Selamat");
        editor.tambahTeks(" datang");

        System.out.println("Teks saat ini: " + editor.getTeks());

        editor.undo();
        System.out.println("Setelah undo: " + editor.getTeks());

        editor.redo();
        System.out.println("Setelah redo: " + editor.getTeks());
    }

    // CASE C: LINKED LIST
    private static void demonstrasiLinkedList() {
        System.out.println("\n--- CASE C: LINKED LIST ---");

        DaftarMahasiswa daftar = new DaftarMahasiswa();

        daftar.tambahMahasiswa("24001", "Andi", 85);
        daftar.tambahMahasiswa("24002", "Budi", 90);
        daftar.tambahMahasiswa("24003", "Siti", 88);

        System.out.println("Daftar mahasiswa awal:");
        daftar.tampilkanDaftar();

        daftar.updateNilai("24002", 95);
        System.out.println("\nSetelah nilai Budi diperbarui:");
        daftar.tampilkanDaftar();

        daftar.hapusMahasiswa("24001");
        System.out.println("\nSetelah mahasiswa pertama dihapus:");
        daftar.tampilkanDaftar();
    }

    // Pengujian waktu enqueue
    private static void ujiQueue() {
        CustomerQueue antrean = new CustomerQueue();

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_UJI; i++) {
            antrean.enqueue("Pelanggan-" + i);
        }

        long selesai = System.nanoTime();

        tampilkanWaktu(
            "Queue - enqueue " + JUMLAH_UJI + " data",
            selesai - mulai
        );
    }

    // Pengujian operasi tambah, undo, dan redo pada Stack
    private static void ujiStack() {
        TextEditor editor = new TextEditor();

        long mulaiTambah = System.nanoTime();

        for (int i = 0; i < JUMLAH_UJI; i++) {
            editor.tambahTeks("x");
        }

        long selesaiTambah = System.nanoTime();

        tampilkanWaktu(
            "Stack - tambah teks " + JUMLAH_UJI + " kali",
            selesaiTambah - mulaiTambah
        );

        long mulaiUndo = System.nanoTime();

        for (int i = 0; i < JUMLAH_UJI; i++) {
            editor.undo();
        }

        long selesaiUndo = System.nanoTime();

        tampilkanWaktu(
            "Stack - undo " + JUMLAH_UJI + " kali",
            selesaiUndo - mulaiUndo
        );

        long mulaiRedo = System.nanoTime();

        for (int i = 0; i < JUMLAH_UJI; i++) {
            editor.redo();
        }

        long selesaiRedo = System.nanoTime();

        tampilkanWaktu(
            "Stack - redo " + JUMLAH_UJI + " kali",
            selesaiRedo - mulaiRedo
        );
    }

    // Pengujian tambah dan hapus depan pada Linked List
    private static void ujiLinkedList() {
        DaftarMahasiswa daftar = new DaftarMahasiswa();

        long mulaiTambah = System.nanoTime();

        for (int i = 0; i < JUMLAH_UJI; i++) {
            daftar.tambahDiAkhir(
                "NIM" + i, "Mahasiswa" + i, 80
            );
        }

        long selesaiTambah = System.nanoTime();

        tampilkanWaktu(
            "Linked List - tambah " + JUMLAH_UJI + " data",
            selesaiTambah - mulaiTambah
        );

        long mulaiHapus = System.nanoTime();

        for (int i = 0; i < JUMLAH_UJI; i++) {
            daftar.hapusDepan();
        }

        long selesaiHapus = System.nanoTime();

        tampilkanWaktu(
            "Linked List - hapus depan " + JUMLAH_UJI + " data",
            selesaiHapus - mulaiHapus
        );
    }

    private static void tampilkanWaktu(String operasi, long nanodetik) {
        double milidetik = nanodetik / 1_000_000.0;

        System.out.printf(
            "%-48s : %.3f ms%n",
            operasi, milidetik
        );
    }

    // Tabel analisis kompleksitas Big-O
    private static void tampilkanKompleksitas() {
        System.out.println("\n======================================");
        System.out.println(" ANALISIS KOMPLEKSITAS BIG-O");
        System.out.println("======================================");

        System.out.printf(
            "%-24s %-24s %-20s%n",
            "Struktur Data", "Operasi", "Kompleksitas"
        );

        System.out.println(
            "------------------------------------------------------------------"
        );

        System.out.printf("%-24s %-24s %-20s%n",
                "Queue", "Enqueue", "O(1)");
        System.out.printf("%-24s %-24s %-20s%n",
                "Queue", "Dequeue", "O(1)");
        System.out.printf("%-24s %-24s %-20s%n",
                "Queue", "Tampilkan semua", "O(n)");

        System.out.printf("%-24s %-24s %-20s%n",
                "Stack", "Undo", "O(1)*");
        System.out.printf("%-24s %-24s %-20s%n",
                "Stack", "Redo", "O(1)*");
        System.out.printf("%-24s %-24s %-20s%n",
                "Stack", "Tambah teks", "O(L)*");

        System.out.printf("%-24s %-24s %-20s%n",
                "Linked List", "Tambah di akhir", "O(1)**");
        System.out.printf("%-24s %-24s %-20s%n",
                "Linked List", "Hapus depan", "O(1)");
        System.out.printf("%-24s %-24s %-20s%n",
                "Linked List", "Cari berdasarkan NIM", "O(n)");
        System.out.printf("%-24s %-24s %-20s%n",
                "Linked List", "Tampilkan semua", "O(n)");

        System.out.println("\nCatatan:");
        System.out.println("* Undo/redo mengasumsikan operasi stack "
                + "berbiaya konstan; salin referensi String tetap O(1).");
        System.out.println("* Tambah teks juga menyalin isi String lama, "
                + "sehingga biayanya bergantung pada panjang teks (L).");
        System.out.println("** tambahDiAkhir() O(1) karena menggunakan tail "
                + "dan tidak mencari NIM duplikat.");
        System.out.println("tambahMahasiswa() O(n) karena memeriksa "
                + "duplikasi NIM terlebih dahulu.");
    }
}
