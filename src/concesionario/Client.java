package concesionario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private List<Nave> navesVenta;
    private List<String> comentarios;
    private Date ultimoAcceso;
    private boolean suscritoDestructor;
    private boolean suscritoCaza;
    private boolean suscritoCarguero;
    private boolean suscritoEstacionEspacial;

    public Client(String name, String planet, String specie, int id, String nick, String password, String email, boolean license, int warnings, boolean piracy, boolean fraud, List<Nave> navesVenta, List<String> comentarios) {
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
        this.navesVenta = navesVenta;
        this.comentarios = comentarios;
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

    public List<String> getComments() {
        return comentarios;
    }
    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }
    public void setUltimoAcceso(Date ultimoAcceso){ this.ultimoAcceso = ultimoAcceso;}

    public void setSuscritoDestructor(boolean suscritoDestructor) {
        this.suscritoDestructor = suscritoDestructor;
    }

    public void setSuscritoCaza(boolean suscritoCaza) {
        this.suscritoCaza = suscritoCaza;
    }

    public void setSuscritoCarguero(boolean suscritoCarguero) {
        this.suscritoCarguero = suscritoCarguero;
    }

    public void setSuscritoEstacionEspacial(boolean suscritoEstacionEspacial) {
        this.suscritoEstacionEspacial = suscritoEstacionEspacial;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public boolean isSuscritoDestructor() {
        return suscritoDestructor;
    }

    public boolean isSuscritoCaza() {
        return suscritoCaza;
    }

    public boolean isSuscritoCarguero() {
        return suscritoCarguero;
    }

    public boolean isSuscritoEstacionEspacial() {
        return suscritoEstacionEspacial;
    }
}