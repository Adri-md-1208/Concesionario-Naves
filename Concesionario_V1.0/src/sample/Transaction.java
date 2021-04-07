package sample;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private String purchaser;
    private String seller;
    private Offer offer;
    private Date time;

    public Offer getOffer() {
        return offer;
    }

    public Date getTime() {
        return time;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public String getSeller() {
        return seller;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
