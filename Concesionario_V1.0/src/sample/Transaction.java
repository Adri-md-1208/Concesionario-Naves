package sample;

import java.util.Date;

public class Transaction {
    private Client purchaser;
    private Client seller;
    private Offer offer;
    private Date time;

    public Date getTime() {
        return time;
    }

    public Offer getOffer() {
        return offer;
    }

    public Client getPurchaser() {
        return purchaser;
    }

    public Client getSeller() {
        return seller;
    }
}
