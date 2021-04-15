package concesionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuscriptionManager {

    public void showOffers(Client client)throws IOException, ClassNotFoundException{
        Admin admin = new Admin();
        List<Offer> offerList = admin.getPublishedOffers();
        List<Nave> shipList = new ArrayList<>();
        Date ultimoAcceso = client.getUltimoAcceso();
        int contador=0;

        if(client.isSuscritoCarguero()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("Carguero") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(client.getEmail())){
                       contador+=1;
                    }
                }
            }
        }
        if(client.isSuscritoDestructor()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("Destructor") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(client.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
        if(client.isSuscritoCaza()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("Caza") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(client.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
        if(client.isSuscritoEstacionEspacial()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("EstacionEspacial") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(client.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
            System.out.println("Han publicado " + contador + " ofertas nuevas de naves a las que estaba suscrito desde su último inicio de sesión. ¡Revise el mercado!\n");
    }
}