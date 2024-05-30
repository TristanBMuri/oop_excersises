package Week5;

public class Circle extends Shape {
    private int diameter;

    public Circle(int x, int y, int diameter) {
        super(x, y);
        this.diameter = diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getPerimeter() {
        return (int) (Math.PI * diameter);
    }

    public int getArea() {
        int radius = diameter / 2;
        return (int) (Math.PI * radius * radius);
    }
}
