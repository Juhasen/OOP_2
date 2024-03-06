public class Substring {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new ArithmeticException("Zła ilość argumentów! Wymagane 3 argumenty: łańcuch znaków oraz dwie liczby.");
        }
        try {
            String text = args[0];
            int start = Integer.parseInt(args[1]);
            if (start > text.length() - 1) {
                throw new ArithmeticException("Pierwsza liczba musi być mniejsza od długości łańcucha znaków.");
            }
            if (start < 0) {
                throw new ArithmeticException("Liczby muszą być dodatnie.");
            }

            int end = Integer.parseInt(args[2]);
            if (start > end) {
                throw new ArithmeticException("Pierwsza liczba musi być mniejsza od drugiej.");
            }
            if (end > text.length() - 1) {
                end = text.length() - 1;
            }
            System.out.println(text.substring(start, end + 1));
        } catch (Exception e) {
            System.out.println("Błąd konwersji!");
            System.out.print(e.getMessage());
        }
    }
}

