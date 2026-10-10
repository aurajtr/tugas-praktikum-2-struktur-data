package linkedlist;

/**
 * Node untuk Single Linked List data mahasiswa.
 * Menyimpan data Mahasiswa dan pointer ke node berikutnya.
 * Data diakses melalui getter dan setter.
 */
public class Node {
    private Mahasiswa data;   // data mahasiswa
    private Node next;        // pointer ke node berikutnya

    // Constructor
    public Node(Mahasiswa data) {
        this.data = data;
        this.next = null;
    }

    // Getter data
    public Mahasiswa getData() {
        return data;
    }

    // Setter data
    public void setData(Mahasiswa data) {
        this.data = data;
    }

    // Getter next
    public Node getNext() {
        return next;
    }

    // Setter next
    public void setNext(Node next) {
        this.next = next;
    }
}
