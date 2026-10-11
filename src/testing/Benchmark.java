package testing;

import java.util.*;
import java.io.*;

public class Benchmark {
    static final int RUNS = 9;
    static Object sink;

    static double med(double[] a) {
        Arrays.sort(a);
        return a[a.length / 2];
    }

    static final PrintStream REAL = System.out;
    static final PrintStream NULL = new PrintStream(OutputStream.nullOutputStream());

    // setup dan operasi dipisah agar hanya operasi yang diukur
    interface Setup<T> {
        T make(int n);
    }

    interface Op<T> {
        void run(T t, int n);
    }

    static <T> double bench(Setup<T> s, Op<T> o, int n) {
        double[] r = new double[RUNS];
        for (int i = 0; i < RUNS; i++) {
            T t = s.make(n);
            System.setOut(NULL);
            long st = System.nanoTime();
            o.run(t, n);
            long en = System.nanoTime();
            System.setOut(REAL);
            r[i] = (en - st) / 1e6;
        }
        return med(r);
    }

    static queue.CustomerQueue qFull(int n) {
        queue.CustomerQueue c = new queue.CustomerQueue();
        for (int i = 0; i < n; i++)
            c.enqueue("P" + i);
        return c;
    }

    static stack.TextEditor tFull(int n) {
        stack.TextEditor t = new stack.TextEditor();
        for (int i = 0; i < n; i++)
            t.tambahTeks("a");
        return t;
    }

    static stack.TextEditor tUndone(int n) {
        stack.TextEditor t = tFull(n);
        for (int i = 0; i < n; i++)
            t.undo();
        return t;
    }

    static ArrayDeque<Integer> pFull(int n) {
        ArrayDeque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            s.push(i);
        return s;
    }

    static linkedlist.DaftarMahasiswa lFull(int n) {
        linkedlist.DaftarMahasiswa d = new linkedlist.DaftarMahasiswa();
        for (int i = 0; i < n; i++)
            d.tambahDiAkhir("N" + i, "M" + i, 80);
        return d;
    }

    public static void main(String[] a) {
        // pemanasan JIT
        for (int i = 0; i < 6; i++) {
            qFull(20000);
            tFull(2000);
            pFull(20000);
            lFull(20000);
        }
        int[] S = { 1000, 10000, 100000 };
        System.out.println("QUEUE (ms, total n operasi)");
        for (int n : S)
            System.out.printf("n=%d enqueue=%.3f dequeue=%.3f%n", n,
                    bench(x -> null, (t, k) -> {
                        queue.CustomerQueue c = new queue.CustomerQueue();
                        for (int i = 0; i < k; i++)
                            c.enqueue("P" + i);
                        sink = c;
                    }, n),
                    bench(Benchmark::qFull, (c, k) -> {
                        for (int i = 0; i < k; i++)
                            c.dequeue();
                    }, n));
        System.out.println("PURE STACK ArrayDeque (ms)");
        for (int n : S)
            System.out.printf("n=%d push=%.3f pop=%.3f%n", n,
                    bench(x -> null, (t, k) -> {
                        ArrayDeque<Integer> s = new ArrayDeque<>();
                        for (int i = 0; i < k; i++)
                            s.push(i);
                        sink = s;
                    }, n),
                    bench(Benchmark::pFull, (s, k) -> {
                        for (int i = 0; i < k; i++)
                            s.pop();
                    }, n));
        System.out.println("TEXTEDITOR STACK (ms)");
        for (int n : new int[] { 1000, 5000, 10000, 20000 })
            System.out.printf("n=%d tambahTeks=%.3f undo=%.3f redo=%.3f%n", n,
                    bench(x -> null, (t, k) -> {
                        stack.TextEditor e = new stack.TextEditor();
                        for (int i = 0; i < k; i++)
                            e.tambahTeks("a");
                        sink = e;
                    }, n),
                    bench(Benchmark::tFull, (e, k) -> {
                        for (int i = 0; i < k; i++)
                            e.undo();
                    }, n),
                    bench(Benchmark::tUndone, (e, k) -> {
                        for (int i = 0; i < k; i++)
                            e.redo();
                    }, n));
        System.out.println("LINKEDLIST (ms)");
        for (int n : S)
            System.out.printf("n=%d tambahDiAkhir(n kali)=%.3f \nhapusDepan(n kali)=%.3f%n", n,
                    bench(x -> null, (t, k) -> {
                        sink = lFull(k);
                    }, n),
                    bench(Benchmark::lFull, (d, k) -> {
                        for (int i = 0; i < k; i++)
                            d.hapusDepan();
                    }, n));
        System.out.println("LINKEDLIST satu operasi pada node TERAKHIR md, rata-rata 200 pengulangan");
        for (int n : S) {
            linkedlist.DaftarMahasiswa d = lFull(n);
            String last = "N" + (n - 1);
            for (int i = 0; i < 50; i++) {
                d.cariMahasiswa(last);
                d.updateNilai(last, 90);
            }
            long s = System.nanoTime();
            for (int i = 0; i < 200; i++)
                sink = d.cariMahasiswa(last);
            double cari = (System.nanoTime() - s) / 200 / 1e3;
            s = System.nanoTime();
            for (int i = 0; i < 200; i++)
                d.updateNilai(last, 90 + (i % 5));
            double upd = (System.nanoTime() - s) / 200 / 1e3;
            // hapus node terakhir lalu tambah lagi (ukur gabungan hapus saja)
            double del = 0;
            for (int i = 0; i < 20; i++) {
                d.tambahDiAkhir("Z" + i, "Z", 1);
                long t0 = System.nanoTime();
                d.hapusMahasiswa("Z" + i);
                del += (System.nanoTime() - t0) / 1e3;
            }
            del /= 20;
            long s2 = System.nanoTime();
            for (int i = 0; i < 200; i++) {
                d.tambahDiAkhir("Y" + i, "Y", 1);
            }
            double tail = (System.nanoTime() - s2) / 200 / 1e3;
            System.out.printf("n=%d cari=%.2f update=%.2f \nhapusTerakhir=%.2f tambahDiAkhir=%.3f%n", n, cari, upd, del,
                    tail);
        }
    }
}
