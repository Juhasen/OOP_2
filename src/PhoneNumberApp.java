import java.util.TreeMap;

class NrTelefoniczny implements Comparable<NrTelefoniczny> {
    private int nrKierunkowy;
    private int nrTelefonu;

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
    public void setNrKierunkowy(int nrKierunkowy) {
        this.nrKierunkowy = nrKierunkowy;
    }
    public void setNrTelefonu(int nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    @Override
    public int compareTo(NrTelefoniczny other) {
        if (this.nrKierunkowy < other.nrKierunkowy) {
            return -1;
        } else if (this.nrKierunkowy > other.nrKierunkowy) {
            return 1;
        } else {
            if (this.nrTelefonu < other.nrTelefonu) {
                return -1;
            } else if (this.nrTelefonu > other.nrTelefonu) {
                return 1;
            }
        }
        return 0;
    }
}

abstract class Wpis {
    public abstract void opis();
}

class Osoba extends Wpis {
    private final String imie;
    private final String nazwisko;
    final Address address;

    public Osoba(String imie, String nazwisko, Address address) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.address = address;
    }

    @Override
    public void opis() {
        System.out.println("Osoba: " + imie + " " + nazwisko + " " + address.getUlica() + " " + address.getMiasto() + " " + address.getKodPocztowy() + " " + address.getKraj() + " " + address.getNrTelefonu().getNrKierunkowy() + " " + address.getNrTelefonu().getNrTelefonu());
    }
}

class Address {
    private final String ulica;
    private final String miasto;
    private final String kodPocztowy;
    private final String kraj;
    final NrTelefoniczny nrTelefonu;
    public Address(String ulica, String miasto, String kodPocztowy, String kraj, NrTelefoniczny nrTelefonu) {
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.kraj = kraj;
        this.nrTelefonu = nrTelefonu;
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
    public NrTelefoniczny getNrTelefonu() {
        return nrTelefonu;
    }
}

class Firma extends Wpis{
    private final String nazwa;
    protected final Address address;

    public Firma(String nazwa, Address address) {
        this.nazwa = nazwa;
        this.address = address;
    }

    @Override
    public void opis() {
       System.out.println("Firma: " + nazwa + " " + address.getUlica() + " " + address.getMiasto() + " " + address.getKodPocztowy() + " " + address.getKraj() + " " + address.getNrTelefonu().getNrKierunkowy() + " " + address.getNrTelefonu().getNrTelefonu());
    }
}


public class PhoneNumberApp {
    public static void main(String[] args) {
        Osoba osoba = new Osoba("Jan", "Kowalski", new Address("Kwiatowa", "Krakow", "30-000", "Polska", new NrTelefoniczny(48, 123456789)));
        Osoba osoba2 = new Osoba("Mateusz", "Nowak", new Address("Łąkowa","Warszawa", "34-123", "Polska", new NrTelefoniczny(48, 987654321)));
        Osoba osoba3 = new Osoba("Krzysztof", "Urbaniak", new Address("Lekowa", "Poznań", "32-654", "Polska", new NrTelefoniczny(48, 151541549)));
        Firma firma = new Firma("MlekPol", new Address("Razowa", "Gdańsk", "32-012", "Polska", new NrTelefoniczny(48, 156566589)));
        Firma firma2 = new Firma("Piekarenka", new Address("Pszenna", "Wrocław", "23-784", "Polska", new NrTelefoniczny(48, 120922625)));
        Firma firma3 = new Firma("Kwiaciarenka", new Address("Kwiatowa", "Lublin", "30-123", "Polska", new NrTelefoniczny(48, 147258369)));
        TreeMap<NrTelefoniczny, Wpis> ksiazkaTelefoniczna = new TreeMap<>();
        ksiazkaTelefoniczna.put(osoba.address.nrTelefonu, osoba);
        ksiazkaTelefoniczna.put(osoba2.address.nrTelefonu, osoba2);
        ksiazkaTelefoniczna.put(osoba3.address.nrTelefonu, osoba3);
        ksiazkaTelefoniczna.put(firma.address.nrTelefonu, firma);
        ksiazkaTelefoniczna.put(firma2.address.nrTelefonu, firma2);
        ksiazkaTelefoniczna.put(firma3.address.nrTelefonu, firma3);
        for (Wpis wpis : ksiazkaTelefoniczna.values()) {
            wpis.opis();
        }
    }
}
