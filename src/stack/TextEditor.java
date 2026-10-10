package stack;

import java.util.ArrayDeque;

public class TextEditor {
    private String teks;
    private final ArrayDeque<String> undoStack;
    private final ArrayDeque<String> redoStack;

    public TextEditor() {
        this.teks = "";
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    // Simpan teks lama ke undo, buang isi redo (branch baru)
    public void tambahTeks(String tambahan) {
        undoStack.push(teks);
        teks = teks + tambahan;
        redoStack.clear();
    }

    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }
        redoStack.push(teks);
        teks = undoStack.pop();
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        undoStack.push(teks);
        teks = redoStack.pop();
        return true;
    }

    public String getTeks() {
        return teks;
    }
}
