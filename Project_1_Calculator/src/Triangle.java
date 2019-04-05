import static java.lang.StrictMath.sqrt;

public class Triangle extends Figure implements Print {

    static boolean inputText;
    private double a,b,c;
    Triangle(double a, double b, double c){
        try {
            if (a <= 0 || b <= 0 || c <= 0)
                throw new NumberFormatException("side length must to be a positive number");
            else {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("error"+e);
        }
    }

    double calculatePerimeter(){
        return a+b+c;
    }
    double calculateArea(){
        double p = calculatePerimeter();
        p=p/2;
        double Area=0;
        try{
            if((p-c)==0)
                throw new ArithmeticException("sections of the given length do not form a triangle");
            else
            {
                Area=sqrt((p*(p-a)*(p-b)*(p-c)));
            }
        }
        catch (ArithmeticException e) {
            System.out.println("error " + e);
        }
        return (Area);
    }

    @Override
    public void print() {
        System.out.println("Triangle:");
        System.out.println("Perimeter: " +calculatePerimeter());
        System.out.println("Area: " +calculateArea());

    }
}




