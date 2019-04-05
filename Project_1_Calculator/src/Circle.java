import static java.lang.Math.PI;

public class Circle extends Figure implements Print {

    static boolean inputText;
    private double r;

    Circle(double r) {
        try {
            if (r < +0)
                throw new NumberFormatException("radius must be a positive number");
            else {
                this.r = r;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error!" + e);
        }
    }

    double calculatePerimeter(){
        return 2*PI*r;
    }

    double calculateArea(){
        return PI*r*r;

    }

    @Override
    public void print() {
        System.out.println("Circle:");
        System.out.println("Perimeter: " +calculatePerimeter());
        System.out.println("Area: " +calculateArea());

    }
}

