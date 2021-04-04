package sample;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String planet;
    private String specie;
    private int id;
    private String nick;
    private String password;
    private String email;
    private boolean license;
    private int warnings;
    private boolean piracy;
    private boolean fraud;
    private Nave navesVenta;


    public Client(String name, String planet, String specie, int id, String nick, String password, String email, boolean license, int warnings, boolean piracy, boolean fraud, Nave naves) {
        this.name = name;
        this.planet = planet;
        this.specie = specie;
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.email = email;
        this.license = license;
        this.warnings = warnings;
        this.piracy = piracy;
        this.fraud = fraud;
        this.navesVenta = naves;
    }


    public String getName() {
        return name;
    }
    public String getNick() {
        return nick;
    }
    public String getPassword() {
        return password;
    }

    public String getPlanet() {
        return planet;
    }

    public String getSpecie() {
        return specie;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLicense() {
        return license;
    }

    public boolean isPiracy() {
        return piracy;
    }

    public boolean isFraud() {
        return fraud;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    public void setPiracy(boolean piracy) {
        this.piracy = piracy;
    }

    public void setFraud(boolean fraud) {
        this.fraud = fraud;
    }

    public void setNavesVenta(Nave navesVenta) {
        this.navesVenta = navesVenta;
    }

    public Nave getNavesVenta() {
        return navesVenta;
    }
}
