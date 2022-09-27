package lesson4.University;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;


public class Matrica {
    private double[][] m;
    private int rowsQuantity;
    private int columnsQuantity;
    private int tmp;

    public Matrica( int rowsQuantity , int columnsQuantity) {
        m = new double[rowsQuantity][columnsQuantity];
        this.rowsQuantity = rowsQuantity;
        this.columnsQuantity = columnsQuantity;
    }

    public Matrica (double[][] matrica) {
        this.m = matrica;
        rowsQuantity = matrica.length;
        columnsQuantity = Matriac[0].length;
    }

    public Matrica (Matrica a) {
        this (a.getRowsQuantity(), a.getColumnsQuantity());
        for (int row = 0; row < rowsQuantity; row++) {
            for (int col = 0; col < columnsQuantity; col++) {
                this.setElement(row, col, a.getElement(row, col));
            }
        }
    }

    public int getRowsQuantity() {
        return rowsQuantity;
    }

    public int getColumnsQuantity() {
        return columnsQuantity;
    }

    public void setElement( int row , int column, double value) {
        m[row][column] = value;
    }

    public void fillRandom() {
        for (int row = 0; row < rowsQuantity; row++) {
            for (int col = 0; col < columnsQuantity; col++) {
                this.setElement(row, col, Math.random());
            }
        }
    }

    public void fillUnit() {
        if (rowsQuantity == columnsQuantity) {
            for (int row = 0; row < rowsQuantity; row++) {
                for (int col = 0; col < columnsQuantity; col++) {
                    if (row == col) {
                        this.setElement(row, col, 1.0);
                    } else {
                        this.setElement(row, col, 0.0);
                    }
                }
            }
        }
    }

    public double getElement(int row, int column) {
        return m [row] [column];
    }

    private Matrica getMinor(int rm, int cm) {
        Matrica Matrica = new Matrica(this.getRowsQuantity() - 1, this.getColumnsQuantity() - 1);
        int r = 0, c = 0;
        for (int i = 0; i < this.getRowsQuantity(); i++, r++) {
            for (int j = 0; j < this.getColumnsQuantity(); j++, c++) {
                if (i != rm && j != cm) {
                    Matrica.setElement(r, c, this.getElement(i, j));
                } else if (j == cm) {
                    c--;
                }
            }
            if (i == rm) {
                r--;
            }
            c = 0;
        }
        return Matrica;
    }

    public double getDeterminant() {
        double det = 0.0;
        if (this.getRowsQuantity() == this.getColumnsQuantity()) {
            if (this.getColumnsQuantity() == 1) {
                det = this.getElement(0, 0);
            } else {
                for (int j = 0; j < this.getColumnsQuantity(); j++) {
                    det += Math.pow(-1.0, j) * this.getElement(0, j) * this.getMinor(0, j).getDeterminant();
                }
            }
        }
        return det;
    }

    public Matrica add(Matrica a) {
        if (rowsQuantity != a.getRowsQuantity() || columnsQuantity != a.getColumnsQuantity()) return new Matrica(0, 0);
        Matrica tmp = new Matrica(rowsQuantity, columnsQuantity);
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < columnsQuantity; j++) {
                tmp.setElement(i, j, this.getElement(i, j) + a.getElement(i, j));
            }
        }
        return tmp;
    }

    public Matrica add(Number a) {
        Matrica tmp = new Matrica(rowsQuantity, columnsQuantity);
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < columnsQuantity; j++) {
                tmp.setElement(i, j, this.getElement(i, j) + a.doubleValue());
            }
        }
        return tmp;
    }

    public Matrica subtract(Matrica a) {
        return this.add(a.multiply(-1));
    }

    public Matrica subtract(Number a) {
        return this.add(-a.doubleValue());
    }

    public Matrica multiply(Matrica a) {
        if (this.getColumnsQuantity() != a.getRowsQuantity()) return new Matrica(0, 0);
        Matrica tmp = new Matrica(rowsQuantity, a.getColumnsQuantity());
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < a.getColumnsQuantity(); j++) {
                for (int k = 0; k < columnsQuantity; k++) {
                    tmp.setElement(i, j, tmp.getElement(i, j) + this.getElement(i, k) * a.getElement(k, j));
                }
            }
        }
        return tmp;
    }

    public Matrica multiply(double a) {
        Matrica tmp = new Matrica(rowsQuantity, columnsQuantity);
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < columnsQuantity; j++) {
                tmp.setElement(i, j, this.getElement(i, j) * a);
            }
        }
        return tmp;
    }

    public Matrica divide(double a) {
        return a != 0 ? this.multiply(1 / a) : null;
    }

    public Matrica transpose() {
        Matrica tmp = new Matrica(columnsQuantity, rowsQuantity);
        for (int i = 0; i < columnsQuantity; i++) {
            for (int j = 0; j < rowsQuantity; j++) {
                tmp.setElement(i, j, this.getElement(j, i));
            }
        }
        return tmp;
    }

    public void print(String matricaName) {
        System.out.println("Matrica " + matricaName + ":");
        for (int i = 0; i < this.getRowsQuantity(); i++) {
            for (int j = 0; j < this.getColumnsQuantity(); j++) {
                System.out.printf(Locale.ROOT, "%1.4f    ", this.getElement(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void toFile(FileWriter fw, String matricaName) throws IOException {
        fw.write("Matrica " + matricaName + ":\n");
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < columnsQuantity; j++) {
                fw.write(this.getElement(i, j) + "\t");
            }
            fw.write("\n");
        }
        fw.write("\n");
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrica matrica = (Matrica) o;

        if (rowsQuantity != matrica.rowsQuantity) return false;
        if (columnsQuantity != matrica.columnsQuantity) return false;
        return Arrays.deepEquals(m, matrica.m);
    }


    public int hashCode() {
        int result = Arrays.deepHashCode(m);
        result = 31 * result + rowsQuantity;
        result = 31 * result + columnsQuantity;
        return result;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getRowsQuantity(); i++) {
            for (int j = 0; j < this.getColumnsQuantity(); j++) {
                sb.append(this.getElement(i, j));
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}