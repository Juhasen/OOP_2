import java.util.*;

class NrTelefoniczny implements Comparable<NrTelefoniczny> {
    private final int nrKierunkowy;
    private final int nrTelefonu;

    public NrTelefoniczny(int nrKierunkowy, int nrTelefonu) {
        this.nrKierunkowy = nrKierunkowy;
        this.nrTelefonu = nrTelefonu;
    }

    public int getNrKierunkowy() {
        return nrKierunkowy;
    }

    public int getNrTelefonu() {
        return nrTelefonu;
    }

    @Override
    public int compareTo(NrTelefoniczny other) {
        if (this.nrKierunkowy == other.nrKierunkowy) {
            return Integer.compare(this.nrTelefonu, other.nrTelefonu);
        }
        return Integer.compare(this.nrKierunkowy, other.nrKierunkowy);
    }
}

abstract class Wpis {
    public abstract void opis();
}

class Address {
    private final String ulica;
    private final String miasto;
    private final String kodPocztowy;
    private final String kraj;

    public Address(String ulica, String miasto, String kodPocztowy, String kraj) {
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.kraj = kraj;
    }

    public String getUlica() {
        return ulica;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getKraj() {
        return kraj;
    }

    public String toString() {
        return ulica + " " + miasto + " " + kodPocztowy + " " + kraj;
    }
}

class Osoba extends Wpis {
    private final String imie;
    private final String nazwisko;
    final Address address;

    final NrTelefoniczny nrTelefonu;

    public Osoba(String imie, String nazwisko, Address address, NrTelefoniczny nrTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.address = address;
        this.nrTelefonu = nrTelefonu;
    }

    @Override
    public void opis() {
        System.out.println("Osoba: " + imie + " " + nazwisko + " " + address.getUlica() + " " + address.getMiasto() + " " +
                address.getKodPocztowy() + " " + address.getKraj() + " " +  "+" + nrTelefonu.getNrKierunkowy() + " " + nrTelefonu.getNrTelefonu());
    }
}
class Firma extends Wpis {
    private final String nazwa;
    protected final Address address;
    final NrTelefoniczny nrTelefonu;

    public Firma(String nazwa, Address address, NrTelefoniczny nrTelefonu) {
        this.nazwa = nazwa;
        this.address = address;
        this.nrTelefonu = nrTelefonu;
    }

    @Override
    public void opis() {
        System.out.println("Firma: " + nazwa + " " + address.getUlica() + " " +
                address.getMiasto() + " " + address.getKodPocztowy() + " " + address.getKraj() + " " +  "+" + nrTelefonu.getNrKierunkowy() + " " + nrTelefonu.getNrTelefonu());
    }
}


public class PhoneNumberApp {
    public static void main(String[] args) {
        Osoba osoba = new Osoba("Jan", "Kowalski", new Address("Kwiatowa", "Krakow", "30-000", "Polska"), new NrTelefoniczny(48, 123456789));
        Osoba osoba2 = new Osoba("Mateusz", "Nowak", new Address("Łąkowa", "Warszawa", "34-123", "Polska"), new NrTelefoniczny(48, 987654321));
        Osoba osoba3 = new Osoba("Krzysztof", "Urbaniak", new Address("Lekowa", "Poznań", "32-654", "Polska"), new NrTelefoniczny(48, 151541549));
        Osoba osoba4 = new Osoba("Krystian", "Kowalski", new Address("Kwiatowa", "Krakow", "30-000", "Polska"), new NrTelefoniczny(48, 123546789));

        Firma firma = new Firma("MlekPol", new Address("Razowa", "Gdańsk", "32-012", "Polska"), new NrTelefoniczny(48, 156566589));
        Firma firma2 = new Firma("Piekarenka", new Address("Pszenna", "Wrocław", "23-784", "Polska"), new NrTelefoniczny(48, 120922625));
        Firma firma3 = new Firma("Kwiaciarenka", new Address("Kwiatowa", "Lublin", "30-123", "Polska"), new NrTelefoniczny(48, 147258369));

        TreeMap<NrTelefoniczny, Wpis> ksiazkaTelefoniczna = new TreeMap<>();

        ksiazkaTelefoniczna.put(osoba.nrTelefonu, osoba);
        ksiazkaTelefoniczna.put(osoba2.nrTelefonu, osoba2);
        ksiazkaTelefoniczna.put(osoba3.nrTelefonu, osoba3);
        ksiazkaTelefoniczna.put(firma.nrTelefonu, firma);
        ksiazkaTelefoniczna.put(firma2.nrTelefonu, firma2);
        ksiazkaTelefoniczna.put(firma3.nrTelefonu, firma3);

        System.out.println("Książka telefoniczna: " + ksiazkaTelefoniczna.size() + " wpisów");
        displayTreeMap(ksiazkaTelefoniczna);

        removeIdenticAddress(ksiazkaTelefoniczna);

        System.out.println("\nKsiążka telefoniczna po usunięciu powtarzających się adresów: " + ksiazkaTelefoniczna.size() + " wpisów");
        displayTreeMap(ksiazkaTelefoniczna);
    }
    private static void displayTreeMap(TreeMap<NrTelefoniczny, Wpis> ksiazkaTelefoniczna) {
        for (Map.Entry<NrTelefoniczny, Wpis> entry : ksiazkaTelefoniczna.entrySet()) {
            entry.getValue().opis();
        }
    }

    private static void removeIdenticAddress(TreeMap<NrTelefoniczny, Wpis> ksiazkaTelefoniczna) {
        Set<String> uniqueKeys = new HashSet<>();
        ksiazkaTelefoniczna.entrySet().removeIf(e -> !uniqueKeys.add(e.getValue() instanceof Osoba ? ((Osoba) e.getValue()).address.getUlica() : ((Firma) e.getValue()).address.getUlica()));
    }
}

