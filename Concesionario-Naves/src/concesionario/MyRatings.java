package concesionario;

import java.util.ArrayList;
import java.util.List;

public class MyRatings {

    public boolean seeRatings(Client client){
        List<String> comentarios = client.getComments();
        System.out.println("Los comentarios que han dejado en tu perfil son los siguientes:\n");
        if (comentarios.isEmpty()) {
        } else {
            for (String comentario : comentarios) {
                System.out.println("- " + comentario.substring(1,comentario.length()));
            }
        }
        System.out.println("");
        List<Integer> valoraciones = new ArrayList<>();
        for(String comentario : client.getComments()){
            int valoracion = Integer.parseInt(comentario.substring(0,1));
            valoraciones.add(valoracion);
        }
        double media = 0;
        if (valoraciones.isEmpty()) {
            System.out.println("No tienes valoraciones ni comentarios\n");
        } else {
            for (Integer rating : valoraciones) {
                media += rating;
            }
            media = media / valoraciones.size();
            System.out.println("La media de las valoraciones que te han dejado es de: " + media +"\n");
        }
        return true;
    }
}
