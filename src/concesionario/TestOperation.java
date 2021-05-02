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
        navesOferta1.add(admin.getShipList().get(0).getNumeroRegistro());
        navesOferta1.add(admin.getShipList().get(2).getNumeroRegistro());
        Date fechaLímite1 = new Date(2025,3,25);
        Offer oferta1 = new Offer("Test1", 2500, fechaLímite1, false, false, navesOferta1,fechaPublicacion);
        admin.addOffer(oferta1);

        //Creamos Oferta 2
        ArrayList<String> navesOferta2 = new ArrayList<>();
        navesOferta2.add(admin.getShipList().get(1).getNumeroRegistro());
        navesOferta2.add(admin.getShipList().get(4).getNumeroRegistro());
        Date fechaLímite2 = new Date(2024,4,24);
        Offer oferta2 = new Offer("Test2", 5000, fechaLímite2, false, false, navesOferta2,fechaPublicacion);
        admin.addOffer(oferta2);

        //Creamos Oferta 3
        ArrayList<String> navesOferta3 = new ArrayList<>();
        navesOferta3.add(admin.getShipList().get(6).getNumeroRegistro());
        Date fechaLímite3 = new Date(2030,3,23);
        Offer oferta3 = new Offer("Test3", 7777, fechaLímite3, false, false, navesOferta3,fechaPublicacion);
        admin.addOffer(oferta3);

        //Creamos Oferta 4
        ArrayList<String> navesOferta4 = new ArrayList<>();
        navesOferta4.add(admin.getShipList().get(3).getNumeroRegistro());
        Date fechaLímite4 = new Date(2026,1,24);
        Offer oferta4 = new Offer("Test4", 4, fechaLímite2, false, false, navesOferta4,fechaPublicacion);
        admin.addOffer(oferta2);

        //Compramos Oferta 1


        //Compramos Oferta 2


    }

}

