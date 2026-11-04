public class GuardedCalculator {

    public static double calculate(double a, double b, char op)
            throws DivideByZeroException {

        switch (op) {
            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                if (b == 0) {
                    throw new DivideByZeroException("Cannot divide by zero");
                }
                return a / b;

            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}