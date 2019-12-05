package homework_2_0412;

public class Equation {

    private double first_coef;
    private double second_coef;
    private double third_coef;

    public Equation(double first_coef, double second_coef, double third_coef) {
        this.first_coef = first_coef;
        this.second_coef = second_coef;
        this.third_coef = third_coef;
    }


    public static void main(String[] args) {
        Equation eq = new Equation(2 ,5,-7);
        System.out.println(eq);
        eq.solver();

//        solver(2, 5, -7); // > 0
//        solver(1, -6, 9); // = 0
//        solver(9, -6, 2); // < 0
//        solver(-1, 7, -10);
//        solver(-1.4, 2, 0.3);
    }

    private double calculateDiscriminant(){
        return Math.pow(second_coef, 2) - 4 * first_coef * third_coef;
    }

    private void solver() {

        double discriminant = calculateDiscriminant();

        System.out.println("Discriminant " + discriminant);

        if (discriminant < 0) {
            System.out.println("None");
        } else if (discriminant == 0) {
            double x = -second_coef / (2 * first_coef);
            System.out.println("x1 = x2 = " + x);
        } else {
            double x1 = (-second_coef + Math.sqrt(discriminant)) / (2 * first_coef);
            double x2 = (-second_coef - Math.sqrt(discriminant)) / (2 * first_coef);
            System.out.println("x1 = " + x1 + " ; " + "x2 = " + x2);
        }
    }

    @Override
    public String toString() {
        return "Equation{" +
                "first_coef=" + first_coef +
                ", second_coef=" + second_coef +
                ", third_coef=" + third_coef +
                '}';
    }
}
