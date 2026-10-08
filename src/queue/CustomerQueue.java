package queue;

/**
 * Antrean customer service berbasis Linked List (FIFO).
 * front = pelanggan paling depan (dilayani duluan)
 * rear  = pelanggan paling belakang (baru masuk antrean)
 */
public class CustomerQueue {
    private Node front;
    private Node rear;
    private int size;

    public CustomerQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Cek apakah antrean kosong
    public boolean isEmpty() {
        return front == null;
    }

    // Jumlah pelanggan dalam antrean
    public int getSize() {
        return size;
    }

    // Enqueue: tambah pelanggan baru ke belakang antrean -> O(1)
    public void enqueue(String nama) {
        Node baru = new Node(nama);
        if (isEmpty()) {
            front = baru;
            rear = baru;
        } else {
            rear.setNext(baru);
            rear = baru;
        }
        size++;
    }

    // Dequeue: layani (hapus) pelanggan paling depan -> O(1)
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Antrean kosong, tidak ada pelanggan yang dilayani.");
            return null;
        }
        String nama = front.getNama();
        front = front.getNext();
        if (front == null) {   // antrean jadi kosong
            rear = null;
        }
        size--;
        System.out.println("Melayani pelanggan: " + nama);
        return nama;
    }

    // Tampilkan semua pelanggan dari depan sampai belakang -> O(n)
    public void tampilkan() {
        System.out.println("Pelanggan dalam antrean:");
        if (isEmpty()) {
            System.out.println("(kosong)");
            return;
        }
        Node current = front;
        int nomor = 1;
        while (current != null) {
            System.out.println(nomor + ". " + current.getNama());
            current = current.getNext();
            nomor++;
        }
    }
}
