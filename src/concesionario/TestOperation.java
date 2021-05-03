package concesionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestOperation {

    public void testOperation() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();

        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),calendario.get(Calendar.SECOND));

        //Creamos Oferta 1
        ArrayList<String> navesOferta1 = new ArrayList<>();
        navesOferta1.add(admin.getShipList().get(0).getNumeroRegistro());   //Client1
        navesOferta1.add(admin.getShipList().get(2).getNumeroRegistro());   //Client1
        Date fechaLímite1 = new Date(2025,3,25);
        Offer oferta1 = new Offer("Test1", 2500, fechaLímite1, false, false, navesOferta1,fechaPublicacion);
        admin.addOffer(oferta1);

        //Creamos Oferta 2
        ArrayList<String> navesOferta2 = new ArrayList<>();
        navesOferta2.add(admin.getShipList().get(1).getNumeroRegistro());   //Client2
        navesOferta2.add(admin.getShipList().get(4).getNumeroRegistro());   //Client2
        Date fechaLímite2 = new Date(2024,4,24);
        Offer oferta2 = new Offer("Test2", 5000, fechaLímite2, false, false, navesOferta2,fechaPublicacion);
        admin.addOffer(oferta2);

        //Creamos Oferta 3
        ArrayList<String> navesOferta3 = new ArrayList<>();
        navesOferta3.add(admin.getShipList().get(6).getNumeroRegistro());   //Client4
        Date fechaLímite3 = new Date(2030,3,23);
        Offer oferta3 = new Offer("Test3", 7777, fechaLímite3, false, false, navesOferta3,fechaPublicacion);
        admin.addOffer(oferta3);

        //Creamos Oferta 4
        ArrayList<String> navesOferta4 = new ArrayList<>();
        navesOferta4.add(admin.getShipList().get(3).getNumeroRegistro());   //Client3
        Date fechaLímite4 = new Date(2026,1,24);
        Offer oferta4 = new Offer("Test4", 4, fechaLímite2, false, false, navesOferta4,fechaPublicacion);
        admin.addOffer(oferta2);

        //Revisamos las ofertas
        admin.modifyOfferVisibility(oferta1, true);
        admin.modifyOfferVisibility(oferta2, true);
        admin.modifyOfferVisibility(oferta3, true);
        admin.modifyOfferVisibility(oferta4, true);

        //Compramos Oferta 1
        Transaction transaction1 = new Transaction(admin.getClientList().get(3).getEmail(), admin.getClientList().get(0).getEmail(), oferta1, fechaPublicacion);
        admin.addTransaction(transaction1);
        //Comentario y Valoración 1
        String valoracion1 = "4Fue puntual y bastante majo, pero tardaba en responder";
        admin.addComment(admin.getClientList().get(0), valoracion1);

        //Compramos Oferta 2
        Transaction transaction2 = new Transaction(admin.getClientList().get(4).getEmail(), admin.getClientList().get(1).getEmail(), oferta2, fechaPublicacion);
        admin.addTransaction(transaction2);
        //Comentario y Valoración 2
        String valoracion2 = "3Experiencia bastante mejorable";
        admin.addComment(admin.getClientList().get(1), valoracion2);

        //Compramos Oferta 3
        Transaction transaction3 = new Transaction(admin.getClientList().get(0).getEmail(), admin.getClientList().get(4).getEmail(), oferta3, fechaPublicacion);
        admin.addTransaction(transaction3);
        //Comentario y Valoración 3
        String valoracion3 = "5Inmejorable, muy recomendable";
        admin.addComment(admin.getClientList().get(4), valoracion3);
    }

}

