package stack;

public class MainStack {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        // Tambah teks
        editor.tambahTeks("Selamat");
        editor.tambahTeks(" datang");

        System.out.println("Teks saat ini: \"" + editor.getTeks() + "\"");

        // Undo: kembali ke kondisi sebelum perubahan terakhir
        editor.undo();
        System.out.println("Undo: \"" + editor.getTeks() + "\"");

        // Redo: kembalikan perubahan yang telah di-undo
        editor.redo();
        System.out.println("Redo: \"" + editor.getTeks() + "\"");
    }
}
