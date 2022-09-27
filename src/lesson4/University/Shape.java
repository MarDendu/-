package lesson4.University;

import java.util.Objects;

public abstract class Shape {

    private String colour;
    public  Shape(String colour){
        this.colour=colour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public abstract void draw();

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(colour, shape.colour);
    }

    public int hashCode() {
        return Objects.hash(colour);
    }
}