package model.services;

import model.entities.CarRental;

public class RentalService {
    private static final Double PRICE_PER_HOUR = 10.0;
    private static Double PRICE_PER_DAY = 130.0;

    private BrazilTaxService taxService;

    public BrazilTaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(BrazilTaxService taxService) {
        this.taxService = taxService;
    }

    ////METHOD RESPONSIBLE FOR GENERATING THE INVOICE IN THE CARRENTAL CLASS
    public void processInvoice(CarRental carRental){

        carRental.getInvoice();
    }
}
