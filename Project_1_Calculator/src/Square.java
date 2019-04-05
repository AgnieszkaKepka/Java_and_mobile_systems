public class Square extends Figure implements Print {

    static boolean inputText;
    private double a;

    Square(double a){
        try{
            if(a<=0)
                throw new NumberFormatException("side length must to be a positive number");
            else{
                this.a = a;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error "+e);
        }


    }

    @Override
    double calculateArea() {
        return a*a;
    }

    @Override
    double calculatePerimeter() {
        return 4*a;
    }

    @Override
    public void print() {
        System.out.println("Square:");
        System.out.println("Perimeter: " +calculatePerimeter());
        System.out.println("Area: " +calculateArea());

    }

}
