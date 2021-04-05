package sample;

import java.util.Date;

public class Transaction {
    private Client purchaser;
    private Client seller;
    private Offer offer;
    private Date time;

    public Offer getOffer() {
        return offer;
    }

    public Date getTime() {
        return time;
    }

    public Client getPurchaser() {
        return purchaser;
    }

    public Client getSeller() {
        return seller;
    }

    public void setPurchaser(Client purchaser) {
        this.purchaser = purchaser;
    }

    public void setSeller(Client seller) {
        this.seller = seller;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
