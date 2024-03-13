import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Zła ilość argumentów");
        }

        int range = 0;

        try {
            range = Integer.parseInt(args[0]);
            if (range < 1) {
                throw new IllegalArgumentException("Zbyt mały zakres");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Random rand = new Random();
        int target = rand.nextInt(range + 1);
        int attempt = 0;
        Scanner scanner = new Scanner(System.in);
        Scanner charScanner = new Scanner(System.in);

        boolean isGuessed = false;

        while (!isGuessed) {
            try {
                System.out.print("Podaj liczbę z zakresu <0; " + range + ">:");
                int guess;
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                } else {
                    scanner.next();
                    throw new IllegalStateException("Nie podałeś liczby!");
                }
                attempt++;
                if (guess == target) {
                    System.out.println("Wygrałeś!!! Trafiłeś za " + attempt + " razem.");
                    while (true) {
                        System.out.print("Chcesz zacząć od nowa? [y/n] >");
                        char answer = charScanner.next().charAt(0);
                        if (answer == 'y') {
                            target = rand.nextInt(range + 1);
                            attempt = 0;
                            System.out.println();
                            System.out.println("Nowa gra :D");
                            break;
                        } else if (answer == 'n') {
                            System.out.println("Gra zakończona :(");
                            scanner.close();
                            charScanner.close();
                            isGuessed = true;
                            break;
                        }
                        else {
                            throw new IllegalArgumentException("Podałeś zły znak! Spróbuj jeszcze raz.");
                        }
                    }
                }
                else if (guess > range || guess < 0) {
                    throw new IllegalArgumentException("Liczba spoza zakresu!");
                }
                else if (guess > target) {
                    System.out.println("Za duża liczba!");
                }
                else {
                    System.out.println("Za mała liczba!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int checkInput(char answer) {
        if (answer == 'y') {
            return 1;
        } else if (answer == 'n') {
            return 2;
        } else {
            return 0;
        }
    }
}
