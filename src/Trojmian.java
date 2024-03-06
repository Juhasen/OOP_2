public class Trojmian {
    public static void main(String[] args) {
        if (args.length == 0 || args.length > 3) {
            System.out.println("Zła ilość argumentów");
            return;
        }

        Double[] array = new Double[3];
        for (int i = 0; i < args.length; i++) {
            array[i] = Double.parseDouble(args[i]);
        }
        for (int i = args.length; i < 3; i++) {
            array[i] = 0.0;
        }
        double a = array[0];
        double b = array[1];
        double c = array[2];

        System.out.println("a = " + a + ", b = " + b + ", c = " + c);

        double delta = Math.pow(b, 2) - 4 * a * c;
        System.out.println("Delta = " + delta);

        if (delta > 0) {
            System.out.println("Równanie ma dwa pierwiastki:");
            double x1 = (-b - Math.sqrt(delta)) / (2 * a);
            double x2 = (-b + Math.sqrt(delta)) / (2 * a);
            System.out.println("x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            System.out.println("Równanie ma jeden pierwiastek:");
            double x0 = -b / (2 * a);
            System.out.println("x0 = " + x0);
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-delta) / (2 * a);
            System.out.println("Równanie ma dwa pierwiastki zespolone:");
            System.out.println("x1 = " + realPart + " + " + "i" + imaginaryPart);
            System.out.println("x2 = " + realPart + " - " + "i" + imaginaryPart);
        }
    }
}