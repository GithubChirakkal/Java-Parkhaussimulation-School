import java.util.Scanner;

class Parkhaus {
    int gesamtPlaetze;
    int freiePlaetze;
    double minutenPreis;
    boolean[] tickets; // Array für Speicherung von einem Ticket

    // Konstruktor für das Parkhaus
    public Parkhaus(int gesamtPlaetze, double minutenPreis) {
        this.gesamtPlaetze = gesamtPlaetze;
        this.freiePlaetze = gesamtPlaetze;
        this.minutenPreis = minutenPreis;
        this.tickets = new boolean[gesamtPlaetze]; // Array für Ticket-Status
    }

    // Einfahrt: Gibt true zurück wenn Einfahrt erfolgreich
    public boolean einfahrt(int ticketId, int parkdauer) {
        if (freiePlaetze > 0 && ticketId >= 0 && ticketId < gesamtPlaetze) {
            double kosten = parkdauer * minutenPreis;
            System.out.println("Ticket " + ticketId + " erstellt. Parkdauer: " + parkdauer + " Minuten. Kosten: " + kosten + " Euro.");
            freiePlaetze--; // Einen Platz belegen
            return true;
        } else {
            System.out.println("Keine freien Plätze verfügbar oder ungültige Ticket-ID.");
            return false;
        }
    }

    // Bezahlung: Gibt true zurck wenn Bezahlung erfolgreich
    public boolean bezahlen(int ticketId, double betrag) {
        if (ticketId >= 0 && ticketId < gesamtPlaetze) {
            double kosten = minutenPreis;
            if (betrag >= kosten) {
                tickets[ticketId] = true; // Ticket als bezahlt markieren
                System.out.println("Ticket " + ticketId + " bezahlt. Vielen Dank!");
                return true;
            } else {
                System.out.println("Unzureichender Betrag. Bitte zahlen Sie mindestens " + kosten + " Euro.");
                return false;
            }
        } else {
            System.out.println("Ungültige Ticket-ID.");
            return false;
        }
    }

    // Ausfahrt: Gibt true zurück wenn Ausfahrt erfolgreich
    public boolean ausfahrt(int ticketId) {
        if (ticketId >= 0 && ticketId < gesamtPlaetze && tickets[ticketId]) {
            freiePlaetze++; // Einen Platz freigeben
            tickets[ticketId] = false; // Ticket ungültig machen
            System.out.println("Ticket " + ticketId + " akzeptiert. Gute Fahrt!");
            return true;
        } else {
            System.out.println("Ticket " + ticketId + " nicht bezahlt oder ungültig.");
            return false;
        }
    }

    // Anzeige der freien Plätze
    public void anzeigenFreiePlaetze() {
        System.out.println("Freie Plätze: " + freiePlaetze + " von " + gesamtPlaetze);
    }
}

public class ParkhausSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parkhaus parkhaus = new Parkhaus(10, 0.05); // 10 Plätze 0,05 Euro pro Minute

        while (true) {
            System.out.println("\n1. Einfahrt\n2. Bezahlen\n3. Ausfahrt\n4. Freie Plätze anzeigen\n5. Beenden");
            int wahl = scanner.nextInt();

            if (wahl == 1) {
                System.out.print("Ticket-ID eingeben (0-9): ");
                int ticketId = scanner.nextInt();
                System.out.print("Parkdauer in Minuten: ");
                int parkdauer = scanner.nextInt();
                parkhaus.einfahrt(ticketId, parkdauer);
            } else if (wahl == 2) {
                System.out.print("Ticket-ID eingeben (0-9): ");
                int ticketId = scanner.nextInt();
                System.out.print("Betrag eingeben: ");
                double betrag = scanner.nextDouble();
                parkhaus.bezahlen(ticketId, betrag);
            } else if (wahl == 3) {
                System.out.print("Ticket-ID eingeben (0-9): ");
                int ticketId = scanner.nextInt();
                parkhaus.ausfahrt(ticketId);
            } else if (wahl == 4) {
                parkhaus.anzeigenFreiePlaetze();
            } else if (wahl == 5) {
                System.out.println("Simulation beendet.");
                break;
            } else {
                System.out.println("Ungültige Auswahl.");
            }
        }

        scanner.close();
    }
}
