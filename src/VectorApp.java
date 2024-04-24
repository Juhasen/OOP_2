import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class WektoryRoznejDlugosciException extends Exception {
    private final int lenA;
    private final int lenB;

    public WektoryRoznejDlugosciException(int lenA, int lenB) {
        this.lenA = lenA;
        this.lenB = lenB;
    }

    @Override
    public String getMessage() {
        return "Długość pierwszego wektora to " + lenA + " a drugiego to " + lenB;
    }
}

public class VectorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Podaj pierwszy wektor(A):");
                ArrayList<Double> vectorA = readVector(scanner);
                System.out.print("Podaj pierwszy wektor(B):");
                ArrayList<Double> vectorB = readVector(scanner);

                if (vectorA.size() != vectorB.size()) {
                    throw new WektoryRoznejDlugosciException(vectorA.size(), vectorB.size());
                }

                ArrayList<Double> result = addVectors(vectorA, vectorB);

                zapiszDoPliku(result);

                System.out.println("Wektory zostały dodane i zapisane do pliku addedVectors.txt");
                break;
            } catch (WektoryRoznejDlugosciException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Podane wektory mają różną długość. Spróbuj ponownie.");
            } catch (InputMismatchException e) {
                System.out.println("Podano zły format liczby. Spróbuj ponownie.");
            }
        }
    }

    private static ArrayList<Double> readVector(Scanner scanner) {
        ArrayList<Double> vector = new ArrayList<>();
        String number = scanner.nextLine();
        String[] numbers = number.split(" ");
        for (String s : numbers) {
            try {
                vector.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                throw new InputMismatchException();
            }
        }
        return vector;
    }


    private static ArrayList<Double> addVectors(ArrayList<Double> vectorA, ArrayList<Double> vectorB) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < vectorA.size(); i++) {
            result.add(vectorA.get(i) + vectorB.get(i));
        }
        return result;
    }

    private static void zapiszDoPliku(ArrayList<Double> vector) throws IOException {
        try (FileWriter file = new FileWriter("/home/krystian/IdeaProjects/PO2/.idea/addedVectors.txt")) {
            for (Double number : vector) {
                file.write(number + " ");
            }
        }
    }
}
