package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesCreator {

    List<Client> clientList = new ArrayList<>();

    Admin admin = new Admin();

    public void ClientCreator() throws IOException {
        Client client1 = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "programar",
                "ala@gmail.com", true, 0, false, false, null);
        Client client2 = new Client("Guille", "Mars",
                "Kromagg", 8510, "Guija777", "Hola",
                "Biggest@gmail.com", false, 1, true, false, null);
        Client client3 = new Client("Nico", "Jupiter",
                "Alien", 0256, "Nisco", "0123",
                "NRC@gmail.com", true, 2, false, false, null);
        Client client4 = new Client("Adrian", "Saturn",
                "Human", 0023, "MoralesDato", "3366",
                "AMD@gmail.com", false, 0, false, false, null);
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        clientList.add(client4);
        for (Client clientToAdd : clientList){
            admin.addClient(clientToAdd);
        }
    }

    public void ShipCreator() throws IOException, ClassNotFoundException {

        Client client1 = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "programar",
                "ala@gmail.com", true, 0, false, false, null);
        Client client2 = new Client("Guille", "Mars",
                "Kromagg", 8510, "Guija777", "Hola",
                "Biggest@gmail.com", false, 1, true, false, null);
        Client client3 = new Client("Nico", "Jupiter",
                "Alien", 0256, "Nisco", "0123",
                "NRC@gmail.com", true, 2, false, false, null);
        Client client4 = new Client("Adrian", "Saturn",
                "Human", 0023, "MoralesDato", "3366",
                "AMD@gmail.com", false, 0, false, false, null);

        //Propulsiones
        Propulsion propulsion1 = new Propulsion(Propulsion.TipoPropulsor.Compresor_de_traza, 2000);
        Propulsion propulsion2 = new Propulsion(Propulsion.TipoPropulsor.Motor_de_curvatura, 1500);
        Propulsion propulsion3 = new Propulsion(Propulsion.TipoPropulsor.Motor_FTL, 950);
        Propulsion propulsion4 = new Propulsion(Propulsion.TipoPropulsor.Motor_iónico, 2100);
        Propulsion propulsion5 = new Propulsion(Propulsion.TipoPropulsor.Velas_solares, 1750);
        Propulsion propulsion6 = new Propulsion(Propulsion.TipoPropulsor.Compresor_de_traza, 2400);

        //Array de propulsiones
        Propulsion[] motor1=new Propulsion[2];
        Propulsion[] motor2=new Propulsion[1];
        Propulsion[] motor3=new Propulsion[2];
        Propulsion[] motor4=new Propulsion[1];
        motor1[0]=propulsion1;
        motor1[1]=propulsion2;
        motor2[0]=propulsion3;
        motor3[0]=propulsion5;
        motor3[1]=propulsion4;
        motor4[0]=propulsion4;


        //Defensas
        Defensa defensa1 = new Escudo(1000, 3000);
        Defensa defensa2 = new Blindaje("Hierro", 8000, 3500);
        Defensa defensa3 = new Escudo(1000, 3000);
        Defensa defensa4 = new Blindaje("Acero",450, 960);

        //Array de defensas
        Defensa[] defensas1 = new Defensa[1];
        Defensa[] defensas2 = new Defensa[1];
        Defensa[] defensas3 = new Defensa[2];
        defensas1[0]=defensa1;
        defensas2[0]=defensa4;

        //Armas
        Arma arma1 = new Arma(Arma.TipoArma.Cañón_Blaster, 2500);
        Arma arma2 = new Arma(Arma.TipoArma.Misiles_termonucleares, 4000);
        Arma arma3 = new Arma(Arma.TipoArma.PEM, 1500);
        Arma arma4 = new Arma(Arma.TipoArma.Rayos_láser, 1800);

        //Array de armas
        Arma[] armas1 = new Arma[2];
        Arma[] armas2 = new Arma[2];
        armas1[0]= arma1;
        armas1[1]=arma2;
        armas2[0]=arma4;


        //Creadores de naves
        Creator destCreator = new DestructorCreator();
        Creator estCreator = new EstacionEspacialCreator();
        Creator cargCreator = new CargueroCreator();
        Creator cazCreator = new CazaCreator();

        List<Nave> navesList = new ArrayList<>();
        Nave nave1 = destCreator.crearNave("A89742QOP", client1, motor1, 1000, null, 5000, defensas1, armas1, 0, null);
        Nave nave2 = cargCreator.crearNave("C5689POO", client2, motor2, 560, null, 8900, null , null , 0, null);
        Nave nave3 = cazCreator.crearNave("X9089BNV", client2, motor3, 1, null, 50, defensas2, armas2, 0, null);
        Nave nave4 = cazCreator.crearNave("A8900HJK", client3, motor3, 1, null, 50, defensas2, armas2, 0, null);
        Nave nave5 = cazCreator.crearNave("Z0011QWE", client2, motor3, 1, null, 50, defensas2, armas2, 0, null);
        navesList.add(nave4);
        navesList.add(nave5);
        Nave nave6 = cargCreator.crearNave("Q2009PPO", client4, motor4, 5600, null, 8590, defensas1, null, 0, navesList);

        List<Nave> navesToAdd = new ArrayList<>();
        Nave[] client1Naves = new Nave[1];
        Nave[] client2Naves = new Nave[3];
        Nave[] client3Naves = new Nave[1];
        Nave[] client4Naves = new Nave[1];
        client1Naves[0]=nave1;
        client2Naves[0]=nave2;
        client2Naves[1]=nave3;
        client2Naves[2]=nave5;
        client3Naves[0]=nave4;
        client4Naves[0]=nave6;

        navesToAdd.add(nave1);
        navesToAdd.add(nave2);
        navesToAdd.add(nave3);
        navesToAdd.add(nave4);
        navesToAdd.add(nave5);
        navesToAdd.add(nave6);

        for (Nave nave : navesToAdd){
            admin.addShip(nave);
        }





    }
}
