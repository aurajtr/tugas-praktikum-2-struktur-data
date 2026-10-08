package queue;

/**
 * Node untuk menyimpan satu pelanggan di dalam antrean (Linked List).
 */
public class Node {
    private String nama;   // nama pelanggan
    private Node next;     // penunjuk ke pelanggan berikutnya

    public Node(String nama) {
        this.nama = nama;
        this.next = null;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
