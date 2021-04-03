package sample;

import java.io.Serializable;
import java.util.Date;

public class Offer implements Serializable {
    private String description;
    private double prize;
    private Date limit;
    private boolean isPublished;
    private boolean isBought;
    private Nave[] naves;

    public Nave[] getNaves() {
        return naves;
    }

    public void setNaves(Nave[] naves) {
        this.naves = naves;
    }

    public String getDescription() {
        return description;
    }

    public double getPrize() {
        return prize;
    }

    public Date getLimit() {
        return limit;
    }

    public double getPower(){
        return 0;
    } //Parámetro Nave en los paréntesis

    public double getAbsorbDamage(){
        return 0;
    } //Parámetro Nave en los paréntesis

    public boolean isPublished() {
        return isPublished;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    };
}
