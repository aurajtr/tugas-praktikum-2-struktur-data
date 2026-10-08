package queue;

public class MainQueue {
    public static void main(String[] args) {
        CustomerQueue antrean = new CustomerQueue();

        // Tambah pelanggan
        antrean.enqueue("Andi");
        antrean.enqueue("Budi");
        antrean.enqueue("Siti");

        antrean.tampilkan();
        System.out.println();

        // Layani pelanggan paling depan
        antrean.dequeue();
        System.out.println();

        antrean.tampilkan();
    }
}
