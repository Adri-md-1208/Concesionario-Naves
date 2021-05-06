import concesionario.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SuscriptionManagerTest {

    @BeforeAll
    static void beforeAll() {
        File naves = new File("Ships.dat");
        File  clientes = new File("Clients.dat");
        File transacciones = new File("Transactions.dat");
        File ofertas = new File("Offers.dat");
        naves.delete();
        clientes.delete();
        transacciones.delete();
        ofertas.delete();
    }

    @Test
    void showOfferTest() throws IOException, ClassNotFoundException, InterruptedException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addClient(test.crearCliente1Test());
        admin.addClient(test.crearCliente2Test());
        Client cliente1=admin.getClientList().get(0);
        Client cliente2=admin.getClientList().get(1);
        admin.setSuscriptor("Destructor",cliente1,true);  //Suscribimos a cada cliente a un tipo de nave
        admin.setSuscriptor("Caza",cliente1,true);
        //admin.actualizarUltimoAcceso(cliente1);   //Ponemos el último inicio de sesión antes de crear una oferta
        //admin.actualizarUltimoAcceso(cliente2);
        cliente1=admin.getClientList().get(0);
        cliente2=admin.getClientList().get(1);
        test.crearOferta();  //Creamos las ofertas
        admin.modifyOfferVisibility(admin.getUnpublishedOffers().get(0),true); //Publicamos una oferta con destructor y caza
        List<Offer> offerList = admin.getPublishedOffers();
        List<Nave> shipList = new ArrayList<>();
        Date ultimoAcceso = new Date(2021,2,5);   //cliente1.getUltimoAcceso();
        int contador=0;

        if(cliente1.isSuscritoCarguero()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("Carguero") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(cliente1.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
        if(cliente1.isSuscritoDestructor()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("Destructor") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(cliente1.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
        if(cliente1.isSuscritoCaza()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("Caza") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(cliente1.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
        if(cliente1.isSuscritoEstacionEspacial()){
            for(Offer offerToSearch : offerList){
                List<String> numsReg = offerToSearch.getNaves();
                for(String numReg : numsReg){
                    if(admin.searchShip(numReg).getClass().getSimpleName().equals("EstacionEspacial") && offerToSearch.getFechaPublicacion().after(ultimoAcceso)
                            && !admin.searchShip(numReg).getPropietario().getEmail().equals(cliente1.getEmail())){
                        contador+=1;
                    }
                }
            }
        }
        assertEquals(contador,2);  //Devuelve el 1 de la oferta nueva que hay de destructor
    }
}
