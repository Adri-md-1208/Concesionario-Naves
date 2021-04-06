package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Offer implements Serializable {
    private String description;
    private double prize;
    private Date dateLimit;
    private boolean isPublished;
    private boolean isBought;
    private List<Nave> naves;

    public Offer(String description, double prize, Date dateLimit, boolean isPublished, boolean isBought, List<Nave> naves) {
        this.description = description;
        this.prize = prize;
        this.dateLimit = dateLimit;
        this.isPublished = isPublished;
        this.isBought = isBought;
        this.naves = naves;
    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public String getDescription() {
        return description;
    }

    public double getPrize() {
        return prize;
    }

    public Date getLimit() {
        return dateLimit;
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
