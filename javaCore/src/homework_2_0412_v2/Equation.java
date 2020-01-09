package homework_2_0412_v2;

public class Equation {

    private Coefficient coef;

    public Equation(double first_coef, double second_coef, double third_coef) {
        this.coef = new Coefficient(first_coef, second_coef,third_coef);
    }

    private double calculateDiscriminant(){
        return Math.pow(coef.getSecond_coef(), 2) - 4 * coef.getFirst_coef() * coef.getThird_coef();
    }

    protected void solver() {

        double discriminant = calculateDiscriminant();

        if (discriminant < 0) {
            System.out.println("None");
        } else if (discriminant == 0) {
            double x = -coef.getFirst_coef() / (2 * coef.getFirst_coef());
            System.out.println("x1 = x2 = " + x);
        } else {
            double x1 = (-coef.getSecond_coef() + Math.sqrt(discriminant)) / (2 * coef.getFirst_coef());
            double x2 = (-coef.getSecond_coef() - Math.sqrt(discriminant)) / (2 * coef.getFirst_coef());
            System.out.println("x1 = " + x1 + " ; " + "x2 = " + x2);
        }
    }

    @Override
    public String toString() {
        return "Equation{" +
                "coef=" + coef +
                '}';
    }
}
