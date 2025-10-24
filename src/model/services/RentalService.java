package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private static final Double PRICE_PER_HOUR = 10.0;
    private static Double PRICE_PER_DAY = 130.0;

    private BrazilTaxService taxService = new BrazilTaxService();

    public BrazilTaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(BrazilTaxService taxService) {
        this.taxService = taxService;
    }

    ////METHOD RESPONSIBLE FOR GENERATING THE INVOICE IN THE CARRENTAL CLASS
    public void processInvoice(CarRental carRental){

        //HOURS AND DAYS CALCULATOR
        long diff = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        Double horas = Math.ceil(diff / 60.0);

        Double basicPayment;
        if(horas <= 12.0){
            basicPayment = horas * PRICE_PER_HOUR;
        } else {
            basicPayment = Math.ceil(horas / 24) * PRICE_PER_DAY;

        }

        double tax = taxService.tax(basicPayment);
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
