import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter the rental data: ");
        System.out.print("Car model: ");
        String model = sc.nextLine();
        System.out.print("Withdrawal of the car (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("return of the car (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
        CarRental carRental = new CarRental(start, finish, new Vehicle(model));

        RentalService rentalService = new RentalService(new BrazilTaxService());
        rentalService.processInvoice(carRental);

                System.out.println();
        System.out.println("INVOICE:");
        System.out.printf("Basic payment: $%.2f\n", carRental.getInvoice().getBasicPayment());
        System.out.printf("Tax: $%.2f\n", carRental.getInvoice().getTax());
        System.out.printf("Total payment: $%.2f", carRental.getInvoice().totalPayment());
        sc.close();
    }
}