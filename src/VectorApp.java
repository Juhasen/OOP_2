import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class WektoryRoznejDlugosciException extends Exception {
    private final int dlugosc1;
    private final int dlugosc2;

    public WektoryRoznejDlugosciException(int dlugosc1, int dlugosc2) {
        super("Wektory mają różną długość!");
        this.dlugosc1 = dlugosc1;
        this.dlugosc2 = dlugosc2;
    }

    public int getDlugosc1() {
        return dlugosc1;
    }

    public int getDlugosc2() {
        return dlugosc2;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Długość pierwszego wektora to " + dlugosc1 + " a drugiego to " + dlugosc2;
    }
}

public class VectorApp {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj pierwszy wektor (wciśnij Enter, aby zakończyć):");
        ArrayList<Integer> wektor1 = wczytajWektor(scanner);

        System.out.println("Podaj drugi wektor (wciśnij Enter, aby zakończyć):");
        ArrayList<Integer> wektor2 = wczytajWektor(scanner);

        try {
            if (wektor1.size() != wektor2.size()) {
                throw new WektoryRoznejDlugosciException(wektor1.size(), wektor2.size());
            }

            ArrayList<Integer> suma = dodajWektory(wektor1, wektor2);

            zapiszWektorDoPliku(suma, "wynik.txt");

            System.out.println("Wektory zostały dodane. Wynik zapisano w pliku wynik.txt.");
        } catch (WektoryRoznejDlugosciException e) {
            System.out.println(e.getMessage());
            System.out.println("Spróbuj ponownie podać wektory.");
            main(args);
        }
    }

    public static ArrayList<Integer> wczytajWektor(Scanner scanner) {
        ArrayList<Integer> wektor = new ArrayList<>();
        String liczbaTekst = scanner.nextLine();
        while (scanner.hasNext()) {
            try {
                int liczba = Integer.parseInt(liczbaTekst);
                wektor.add(liczba);
                System.out.println(liczba);
            } catch (NumberFormatException e) {
                break;
            }
        }
        return wektor;
    }


    private static ArrayList<Integer> dodajWektory(ArrayList<Integer> wektor1, ArrayList<Integer> wektor2) {
        ArrayList<Integer> suma = new ArrayList<>();
        for (int i = 0; i < wektor1.size(); i++) {
            suma.add(wektor1.get(i) + wektor2.get(i));
        }
        return suma;
    }

    private static void zapiszWektorDoPliku(ArrayList<Integer> wektor, String nazwaPliku) throws IOException {
        File plik = new File(nazwaPliku);
        FileWriter writer = new FileWriter(plik);

        for (Integer element : wektor) {
            writer.write(element + " ");
        }

        writer.close();
    }
}

