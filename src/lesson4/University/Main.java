package lesson4.University;

public class Main  {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        Circle circle1=new Circle("Черный",8,9,40);
        Rectangle rectangle=new Rectangle("Черный",6,7,8,9);
        Circle circle2=new Circle("",8,9,40);
        shapes[0] =circle1;
        shapes[1] = rectangle;
        shapes[2] =circle2;
        for(Shape shape: shapes){
            shape.draw();
        }
        System.out.println(circle1.equals(circle2));
    }
}