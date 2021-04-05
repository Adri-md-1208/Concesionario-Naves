package sample;

import java.io.IOException;
import java.util.*;

public class CreateOffer {

    List<Nave> navesVenta = new ArrayList<>();
    private int contador = 1;

    public boolean crearOferta(Client client) throws IOException, ClassNotFoundException {
        System.out.println("¡Es el momento de vender tus naves!");
        System.out.println("Vamos a crear tu oferta de naves, primero dinos que naves quieres vender\n");
        Admin admin = new Admin();
        List<Nave> naves = admin.getShipList();
        for (Nave nave : naves) {
            if (nave.getPropietario().equals(client)){
                navesVenta.add(nave);
            }
        }
        if (navesVenta.isEmpty()) {
            System.out.println("No tienes ninguna nave para vender\n");
            return true;
        }
        for (Nave nave : navesVenta) {
            System.out.println(contador + ". " + nave.getNumeroRegistro() + " (Número de Registro) del tipo " + nave.getClass().getName());
            contador++;
        }
        //Leer opcion elegida y guardarlo en Offer oferta.
        Nave[] navesVentaArray = (Nave[]) navesVenta.toArray();
        Nave [] navesOferta = seleccionarNaves(navesVentaArray);

        //Pedir una descripción, un precio y una ¿¿¿fecha límite???
        System.out.println("Introduzca el precio de su oferta");
        Scanner sc = new Scanner(System.in);
        double precio = sc.nextDouble();
        System.out.println("Introduzca una descripción para su oferta");
        String descripcion = sc.next();
        System.out.println("¿En qué mes quiere que se acabe su oferta? -- Introduzca el número del mes entre 1 y 12");
        int mes = Calendar.MONTH;
        boolean correct = false;
        while (!correct) {
            mes = sc.nextInt();
            if (mes>=1 && mes<=12) {
                correct = true;
            }
        }
        System.out.println("¿En qué día quiere que se acabe su oferta? -- Introduzca el número del dia entre 1 y 28");
        int dia = Calendar.DAY_OF_MONTH;
        correct = false;
        while (!correct) {
            dia = sc.nextInt();
            if (dia>=1 && dia<=28) {
                correct = true;
            }
        }
        System.out.println("¿En qué año quiere que se acabe su oferta? -- Introduzca un año de cuatro cifras posterior o igual al actual");
        Integer año = Calendar.YEAR;
        correct = false;
        while (!correct) {
            año = sc.nextInt();
            if (año >= Calendar.YEAR && año.toString().length() == 4) {
                correct = true;
            }
        }
        Date fechaLímite = new Date(dia, mes, año);

        //Para terminar, se crea la oferta al completo y se añade a la lista de ofertas sin publicar para que el admin la revise.
        Offer finalOffer = new Offer(descripcion, precio, fechaLímite, false, false, navesOferta);
        admin.addOffer(finalOffer);
        return true;
    }

    private Nave[] seleccionarNaves(Nave[] navesVenta) {
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        int i = 0;
        int numNave=0;
        Nave[] navesOferta = new Nave[5];
        while (!stop) {
        System.out.println("\nIntroduzca el número de la oferta que desea vender");
        numNave = sc.nextInt();
        while (numNave < 1 && numNave > (navesVenta.length - 1)) {
            numNave = sc.nextInt();
        }
        System.out.println("Has seleccionado la nave " + numNave);
        navesOferta[i] = navesVenta[numNave];
        i++;
        System.out.println("¿Desea seleccionar más naves? -- Pulse S para confirmar y N para denegar");
        String option = "0";
        while (option != "S" || option != "N") {
            option = sc.next();
        }
        if (option == "N") {
            stop = true;
        }
    }
        return navesOferta;
    }

}
