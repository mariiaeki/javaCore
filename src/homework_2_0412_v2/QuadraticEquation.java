package homework_2_0412_v2;

public class QuadraticEquation {
    public static void main(String[] args) {
        Equation eq = new Equation(2,6,7);
        System.out.println(eq);
        eq.solver();
    }
}
