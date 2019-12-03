package homework_1;

public class T_1_Quadratic_Equation {
    public static void main(String[] args) {
        solver(2, 5, -7); // > 0
        solver(1, -6, 9); // = 0
        solver(9, -6, 2); // < 0
        solver(-1, 7, -10);
        solver(-1.4, 2, 0.3);
    }

    private static void solver(double a, double b, double c) {

        double d = Math.pow(b, 2) - 4 * a * c;

        if (d < 0) {
            System.out.println("Корней на множестве действительных чисел нет");
        } else if (d == 0) {
            double x = -b / (2 * a);
            System.out.println("x1 = x2 = " + x);
        } else {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("x1 = " + x1 + " ; " + "x2 = " + x2);
        }
    }

}
