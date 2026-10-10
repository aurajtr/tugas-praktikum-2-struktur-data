package linkedlist;

/**
 * Manajemen Data Mahasiswa dengan Linked List
 *
 * Menyimpan pointer head (node pertama) dan tail (node terakhir) sehingga penambahan di akhir list dapat dilakukan dalam O(1).
 */
public class DaftarMahasiswa {
    private Node head;   // node pertama
    private Node tail;   // node terakhir
    private int size;    // jumlah mahasiswa

    // Constructor: list awal kosong
    public DaftarMahasiswa() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Menambahkan mahasiswa baru di akhir list (insert at tail). O(1) NIM harus unik; jika NIM sudah ada, data tidak ditambahkan.
     * @return true jika berhasil ditambahkan
     */
    public boolean tambahMahasiswa(String nim, String nama, int nilai) {
        if (cariNode(nim) != null) {
            return false;   // NIM sudah terdaftar
        }
        Node nodeBaru = new Node(new Mahasiswa(nim, nama, nilai));
        if (head == null) {
            head = nodeBaru;
            tail = nodeBaru;
        } else {
            tail.setNext(nodeBaru);
            tail = nodeBaru;
        }
        size++;
        return true;
    }

    /**
     * Menambahkan mahasiswa tanpa pengecekan duplikat. O(1)
     * Digunakan pada pengujian waktu eksekusi agar yang diukur murni operasi insert.
     */
    public void tambahDiAkhir(String nim, String nama, int nilai) {
        Node nodeBaru = new Node(new Mahasiswa(nim, nama, nilai));
        if (head == null) {
            head = nodeBaru;
            tail = nodeBaru;
        } else {
            tail.setNext(nodeBaru);
            tail = nodeBaru;
        }
        size++;
    }

    /**
     * Menghapus mahasiswa berdasarkan NIM. O(n) karena harus mencari node sekaligus menyimpan node sebelumnya (single linked list tidak punya pointer prev).
     * @return data mahasiswa yang dihapus, atau null jika tidak ditemukan
     */
    public Mahasiswa hapusMahasiswa(String nim) {
        Node current = head;
        Node previous = null;

        while (current != null && !current.getData().getNim().equals(nim)) {
            previous = current;
            current = current.getNext();
        }
        if (current == null) {
            return null;   // NIM tidak ditemukan
        }

        if (previous == null) {
            head = current.getNext();          // hapus node pertama
        } else {
            previous.setNext(current.getNext()); // lewati node yang dihapus
        }
        if (current == tail) {
            tail = previous;                   // perbarui tail jika node terakhir dihapus
        }
        size--;
        return current.getData();
    }

    /**
     * Menghapus node pertama (delete at head). O(1)
     * Digunakan pada pengujian waktu eksekusi.
     */
    public Mahasiswa hapusDepan() {
        if (head == null) {
            return null;
        }
        Mahasiswa data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    /**
     * Mengupdate nilai mahasiswa berdasarkan NIM. O(n) untuk pencarian.
     * @return true jika berhasil diupdate
     */
    public boolean updateNilai(String nim, int nilaiBaru) {
        Node target = cariNode(nim);
        if (target == null) {
            return false;
        }
        target.getData().setNilai(nilaiBaru);   // akses data melalui getter & setter
        return true;
    }

    /**
     * Mencari mahasiswa berdasarkan NIM (linear search). O(n)
     */
    public Mahasiswa cariMahasiswa(String nim) {
        Node node = cariNode(nim);
        return node == null ? null : node.getData();
    }

    // Helper: mencari node berdasarkan NIM dengan traversal dari head. O(n)
    private Node cariNode(String nim) {
        Node current = head;
        while (current != null) {
            if (current.getData().getNim().equals(nim)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Menampilkan seluruh mahasiswa (traversal dari head ke tail). O(n)
     */
    public void tampilkanDaftar() {
        if (head == null) {
            System.out.println("(daftar kosong)");
            return;
        }
        Node current = head;
        int nomor = 1;
        while (current != null) {
            System.out.println(nomor + ". " + current.getData());
            current = current.getNext();
            nomor++;
        }
    }

    // Jumlah mahasiswa dalam list. O(1)
    public int getSize() {
        return size;
    }
}
