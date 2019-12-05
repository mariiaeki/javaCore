package homework_2_0412_v2;

public class Coefficient {
    private double first_coef;
    private double second_coef;
    private double third_coef;

    public Coefficient(double first_coef, double second_coef, double third_coef) {
        this.first_coef = first_coef;
        this.second_coef = second_coef;
        this.third_coef = third_coef;
    }

    public double getFirst_coef() {
        return first_coef;
    }

    public double getSecond_coef() {
        return second_coef;
    }

    public double getThird_coef() {
        return third_coef;
    }

    @Override
    public String toString() {
        return "Coefficient{" +
                "first_coef=" + first_coef +
                ", second_coef=" + second_coef +
                ", third_coef=" + third_coef +
                '}';
    }
}
