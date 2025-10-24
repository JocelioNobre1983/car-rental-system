package model.services;

import model.entities.CarRental;
import model.entities.Invoice;


import java.time.Duration;

public class RentalService {
    private static final Double PRICE_PER_HOUR = 10.0;
    private static Double PRICE_PER_DAY = 130.0;

    private TaxService taxService;

    public RentalService() {
    }

    public RentalService(TaxService taxService) {
        this.taxService = taxService;
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public void TaxService(TaxService taxService) {
        this.taxService = taxService;
    }

    ////METHOD RESPONSIBLE FOR GENERATING THE INVOICE IN THE CARRENTAL CLASS
    public void processInvoice(CarRental carRental){
            //INITIAL VALIDATIONS
            try{
            if(carRental == null){
                throw new IllegalArgumentException("CarRental object cannot be null.");
            }
            if(carRental.getStart() == null || carRental.getFinish() == null){
                throw new IllegalArgumentException("Start and end dates cannot be null.");
            }

            if(taxService == null){
                throw new IllegalArgumentException("Tax service (TaxService) has not been configured.");
            }
            //HOURS AND DAYS CALCULATOR
            long diff = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
            if(diff <= 0.0){
                throw new IllegalArgumentException("The return date must be after the pickup date.");
            }

            Double horas = Math.ceil(diff / 60.0);
            Double basicPayment;
            if (horas <= 12.0) {
                basicPayment = horas * PRICE_PER_HOUR;
            } else {
                basicPayment = Math.ceil(horas / 24) * PRICE_PER_DAY;

            }

            Double tax = taxService.tax(basicPayment);
            carRental.setInvoice(new Invoice(basicPayment, tax));
            } catch(IllegalArgumentException e){
                System.out.println("Erro: " + e);
            }
    }
}
