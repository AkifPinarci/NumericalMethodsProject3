public class project3 {
    public static void main(String[] args) {
        // bisection(0, 0.5, 1);
        // bisection(0.5, 2, 1);
        // bisection(2, 4, 1);
        // secant(0, 1);
        // secant(2, 1);
        // secant(4, 1);
        // false_position(0, 0.5, 1);
        // false_position(0.5, 2, 1);
        // false_position(2, 4, 1);
        // modifiedSecant(0.5, 1);
        modifiedSecant(1.5, 1);
        // modifiedSecant(3.5, 1);
        // newton_raphson(1.0, 1);
        // newton_raphson(2.0, 1);
        // newton_raphson(3.0, 1);
        // bisection(120, 140, 2);
        // secant(120, 2);
        // false_position(120, 140, 2);
        // modifiedSecant(120, 2);
        // newton_raphson(120, 2);
        System.out.println("Error: ");
    }

    public static double cosh(double x) {
        double e = 2.718;
        double result = ((Math.pow(e, x) + Math.pow(e, -x))) / 2;
        return result;
    }

    public static double sinh(double x) {
        double e = 2.718;
        double result = ((Math.pow(e, x) - Math.pow(e, -x))) / 2;
        return result;
    }

    public static double evalPoly(double value, int problem) {
        // f(x) = 2x^3 – 11.7x^2 + 17.7x – 5 there is a root between [0, 0.5], [0.5, 2],
        // and [2, 4]
        if (problem == 1) {
            double result = -5;
            result += (2 * value * value * value);
            result += (-11.7 * value * value);
            result += (17.7 * value);

            return result;
        }
        // f(x) = x + 10 - xcosh(50/x)
        else {
            double result = 10;
            result += (value);
            result -= (value * cosh(50 / value));
            return result;
        }
    }

    public static double evalDerivative(double value, int problem) {
        // f'(x) = 6x^2 - 23.4x + 17.7
        if (problem == 1) {
            double result = 17.7;
            result += (6 * value * value);
            result += (-23.4 * value);

            return result;
        }
        // 50sinh(50/x)/x -cosh(50/x) + 1
        else {
            double result = 1;
            result += ((50 * sinh(50 / value)) / value);
            result -= (cosh(50 / value));
            return result;
        }
    }

    public static void bisection(double a, double b, int problem) {
        double c = (a + b) / 2;
        double cn = 0;
        double error;
        int count = 0;
        while (count < 101) {
            if ((evalPoly(a, problem) * evalPoly(c, problem)) > 0) {
                a = c;
            } else {
                b = c;
            }
            cn = c;
            c = (a + b) / 2;
            error = (c-cn)/c;
            System.out.println("Error: " + error);
            // System.out.println(") Error: "+ evalPoly(c, problem));
            if (Math.abs(error) < 0.001) {
                System.out.println("Solution: " + c);
                break;
            }
            count += 1;
        }
    }

    public static void secant(double x0, int problem) {
        int count = 0;
        double xi = x0 + 0.1;
        double error;
        double temp;
        while (count < 101) {

            error = (xi - x0)/xi;
            System.out.println("Error: " + error);
            if (Math.abs(error) < 0.001) {
                System.out.println("Solution: " + xi);
                break;
            } else {
                temp = xi;
                xi = xi - ((xi - x0) / (evalPoly(xi, problem) - evalPoly(x0, problem))) * evalPoly(xi, problem);
                x0 = temp;
            }
            count++;
        }

    }

    public static void modifiedSecant(double xi, int problem) {
        int count = 0;
        double delta = 0.1;
        double xii = xi
                - evalPoly(xi, problem) * ((delta * xi) / (evalPoly(xi + delta * xi, problem) - evalPoly(xi, problem)));
        double temp;
        double error = 1;
        while (count < 101) {
            if(error != 0) {
                System.out.println("Error: " + error);
            }
            if (Math.abs(error) < 0.001 && error != 0) {
                System.out.println("Solution: " + xii);
                break;
            } else {
                temp = xii;
                xii = xi - evalPoly(xi, problem)
                * ((delta * xi) / (evalPoly(xi + delta * xi, problem) - evalPoly(xi, problem)));
                xi = temp;
            }
            error = (xii - xi)/xii;
            count++;
        }
    }

    public static void false_position(double a, double b, int problem) {
        double c = (b - evalPoly(b, problem) * ((a - b) / (evalPoly(a, problem) - evalPoly(b, problem))));
        double cn = 0;
        double error;
        int count = 0;
        while (count < 101) {
            if ((evalPoly(a, problem) * evalPoly(c, problem)) > 0) {
                a = c;
            } else {
                b = c;
            }
            cn = c;
            c = (b - evalPoly(b, problem) * ((a - b) / (evalPoly(a, problem) - evalPoly(b, problem))));
            error = (c-cn)/c;
            System.out.println("Error: "+ error);
            if (Math.abs(error) < 0.001) {
                System.out.println("Solution: " + c);
                break;
            }
            count += 1;
        }
    }

    public static void newton_raphson(double xi, int problem) {
        int count = 0;
        double temp;
        double error = 1;
        double xii = xi - (evalPoly(xi, problem) / evalDerivative(xi, problem));
        while (count < 101) {
            error = (xii-xi)/xii; 
            if(error != 0){
                System.out.println("Error: "+ error);
            }
            if (Math.abs(error) < 0.001 && error != 0) {
                System.out.println("Solution: " + xi);
                break;
            } else {
                temp = xii;
                xii = xi - ((evalPoly(xi, problem)) / (evalDerivative(xi, problem)));
                xi = temp;
            }
            count++;
        }
    }
}