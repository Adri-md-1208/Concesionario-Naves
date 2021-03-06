package concesionario;

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
            if (nave.getPropietario().getEmail().equals(client.getEmail())){
                navesVenta.add(nave);
            }
        }
        if (navesVenta.isEmpty()) {
            System.out.println("No tienes ninguna nave para vender\n");
            return true;
        }
        for (Nave nave : navesVenta) {
            System.out.println(contador + ". " + nave.getNumeroRegistro() + " (Número de Registro) del tipo " + nave.getClass().getSimpleName());
            contador++;
        }
        //Leer opcion elegida y guardarlo en Offer oferta.
        List<String> navesOferta = seleccionarNaves(navesVenta);
        if (navesOferta==null) {
            return true;
        }
        //Pedir una descripción, un precio y una ¿¿¿fecha límite???
        System.out.println("Introduzca el precio de su oferta");
        Scanner sc = new Scanner(System.in);
        double precio=0;
        boolean precioVálido = false;
        while (!precioVálido) {
            if (sc.hasNextDouble()) {
                precio = sc.nextDouble();
                precioVálido = true;
            } else {
                System.out.println("Este precio no es posible");
                sc = new Scanner(System.in);
            }
        }
        System.out.println("Introduzca una descripción para su oferta");
        sc = new Scanner(System.in);
        String descripcion = sc.nextLine();
        System.out.println("¿En qué mes quiere que se acabe su oferta? -- Introduzca un número del mes entre 1 y 12");
        int mes = 0;
        sc = new Scanner(System.in);
        boolean correct = false;
        while (!correct) {
            mes = sc.nextInt();
            if (mes>=1 && mes<=12) {
                correct = true;
            }
        }
        System.out.println("¿En qué día quiere que se acabe su oferta? -- Introduzca un número del dia entre 1 y 28");
        int dia = 0;
        correct = false;
        sc = new Scanner(System.in);
        while (!correct) {
            dia = sc.nextInt();
            if (dia>=1 && dia<=28) {
                correct = true;
            }
        }
        System.out.println("¿En qué año quiere que se acabe su oferta? -- Introduzca un año de cuatro cifras posterior o igual al actual");
        Integer año = 0;
        correct = false;
        sc = new Scanner(System.in);
        while (!correct) {
            año = sc.nextInt();
            if (año >= 2021 && año.toString().length() == 4) {
                correct = true;
            }
        }
        int year = (año.intValue()) - 1900;
        Date fechaLímite = new Date(year, mes, dia);
        Calendar calendario = new GregorianCalendar();

        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),calendario.get(Calendar.SECOND));

        //Para terminar, se crea la oferta al completo y se añade a la lista de ofertas sin publicar para que el admin la revise.
        Offer finalOffer = new Offer(descripcion, precio, fechaLímite, false, false, navesOferta,fechaPublicacion);
        admin.addOffer(finalOffer);
        System.out.println("Se ha enviado la oferta al admin para que se revise, en breves será publicada\nVolviendo al menú principal...\n");
        return true;
    }

    private List<String> seleccionarNaves(List<Nave> navesVenta) {
        Set<Integer> chosenNaves = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        int i = 0;
        int numNave=0;
        List<String> navesOferta = new ArrayList<>();
        while (!stop) {
        System.out.println("\nIntroduzca el número de la nave que desea vender -- 0 para cancelar");
        numNave = sc.nextInt();
        if (numNave==0) {
            return null;
        }
        while (numNave < 1 || numNave > (navesVenta.size())) {
            numNave = sc.nextInt();
        }
        System.out.println("Has seleccionado la nave " + numNave);
        if (!chosenNaves.contains(numNave)) {
            chosenNaves.add(numNave);
            navesOferta.add(i, navesVenta.get(numNave-1).getNumeroRegistro());
            i++;
        } else {
            System.out.println("\nEsta nave ya ha sido seleccionada\n");
        }
        System.out.println("¿Desea seleccionar más naves? -- Pulse S para confirmar y N para denegar");
        String option = sc.next();
        while (!option.equals("S") && !option.equals("N")) {
            option = sc.next();
        }
        if (option.equals("N")) {
            stop = true;
        }
    }
        return navesOferta;
    }
}