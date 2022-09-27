package lesson4.University;
import java.io.FileWriter;
import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("matrica.txt");

        Matrix a = new Matrix(4, 4);
        a.fillRandom();
        a.print("A (Случайно заполненная матрица)");
        a.toFile(fw, "A (Случайно заполненная матрица)");

        Matrix b = new Matrix (new double[][]{{3, 2, 1, 1}, {1, 1, 2, 3}, {3, 2, 1, 1},{1, 1, 2, 3}});
        b.print("B (Заполненаная вручная матрица");
        b.toFile(fw, "B (Заполненаная вручная матрица)");

        Matrix c = a.add(b);
        c.print("C = A + B");
        c.toFile(fw, "C = A + B");

        Matrix d = c.multiply(2);
        d.print("D = C * 2");
        d.toFile(fw, "D = C * 2");

        Matrix m = c.divide(2);
        m.print("M = C / 2");
        m.toFile(fw, "M = C / 2");

        Matrix e = c.multiply(d);
        e.print("E = C * D");
        e.toFile(fw, "E = C * D");

        fw.close();

    }
}