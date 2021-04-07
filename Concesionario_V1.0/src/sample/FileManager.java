package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    File clientFile;
    List<Client> clientList;
    File offerFile;
    List<Offer> offerList;
    File transactionFile;
    List <Transaction> transactionList;
    File shipFile;
    List <Nave> shipList;

    public FileManager(File clientFile, List<Client> clientList, File offerFile, List<Offer> offerList, File transactionFile, List<Transaction> transactionList, File shipFile, List<Nave> shipList) {
        this.clientFile = clientFile;
        this.clientList = clientList;
        this.offerFile = offerFile;
        this.offerList = offerList;
        this.transactionFile = transactionFile;
        this.transactionList = transactionList;
        this.shipFile = shipFile;
        this.shipList = shipList;
    }

    //Para añadir nuevos clientes
    public void writeClient(Client client) throws IOException {
        //Por si se quieren añadir nuevos clientes


        ObjectOutputStream writer;
        if (!clientFile.exists()) {
            clientFile.createNewFile();
        }
        if (clientFile.length() == 0) {
            writer = new ObjectOutputStream(new FileOutputStream(clientFile));
        } else {
            writer = new ObjectOutputStreamMod(new FileOutputStream(clientFile, true));
        }
        writer.writeObject(client);
    }


    // FICHERO CLIENTES
    public void readClients() throws IOException, ClassNotFoundException {
        if (clientFile.exists()) {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(clientFile));
            try {
                while (true) {
                    Client client = (Client) reader.readObject();
                    clientList.add(client);
                }
            } catch (EOFException e) {

            }
        }
    }

    public void deleteClient(Client clientToRemove) throws IOException {
        clientList.remove(clientToRemove);
        clientFile.delete();
        for (Client clientToAdd : clientList) {
            writeClient(clientToAdd);
        }
    }

    public void modifyFraudClient(Client client, Boolean status) throws IOException, ClassNotFoundException {
        readClients();
        int index=0;
        while (client.getId()!=clientList.get(index).getId()){
            index=+1;
        }
        clientList.get(index).setFraud(status);
        clientFile.delete();
        for (Client clientToAdd : clientList) {
            writeClient(clientToAdd);
        }

    }

    public void modifyPiracyClient(Client client, Boolean status) throws IOException, ClassNotFoundException {
        readClients();
        int index=0;
        while (client.getId()!=clientList.get(index).getId()){
            index=+1;
        }
        clientList.get(index).setPiracy(status);
        clientFile.delete();
        for (Client clientToAdd : clientList) {
            writeClient(clientToAdd);
        }

    }

    public void modifyWarningsClient(Client client, int status) throws IOException, ClassNotFoundException {
        readClients();
        int index=0;
        while (client.getId()!=clientList.get(index).getId()){
            index=+1;
        }
        clientList.get(index).setWarnings(status);
        clientFile.delete();
        for (Client clientToAdd : clientList) {
            writeClient(clientToAdd);
        }

    }





    //FICHERO OFERTAS
    public void writeOffer(Offer offer) throws IOException {
        ObjectOutputStream writer;
        if (offerFile.length() == 0) {
            writer = new ObjectOutputStream(new FileOutputStream(offerFile));
        } else {
            writer = new ObjectOutputStreamMod(new FileOutputStream(offerFile, true));
        }
        writer.writeObject(offer);
    }

    public void readOffers() throws IOException, ClassNotFoundException {
        if (clientFile.exists()) {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(offerFile));
            try {
                offerList.clear();
                while (true) {
                    Offer offer = (Offer) reader.readObject();
                    offerList.add(offer);
                }
            } catch (EOFException e) {

            }
        }
    }

    public void modifyOfferVisibility(Offer offer, boolean status) throws IOException, ClassNotFoundException {
        offerList.get(offerList.indexOf(offer)).setPublished(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Offer offerToAdd : offerList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(offerFile));
                writer.writeObject(offerToAdd);
                firstTime=false;
            }
            else {
                writeOffer(offerToAdd);
            }
        }
    }

    public void deleteOffer(Offer offerToRemove) throws IOException, ClassNotFoundException {
        offerList.clear();
        readOffers();
        offerList.remove(offerToRemove);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Offer offerToAdd : offerList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(offerFile));
                writer.writeObject(offerToAdd);
                firstTime=false;
            }
            else {
                writeOffer(offerToAdd);
            }
        }
    }


    //FIHCERO TRANSACCIONES
    public void writeTransaction(Transaction transaction) throws IOException {
        ObjectOutputStream writer;
        if (transactionFile.length() == 0) {
            writer = new ObjectOutputStream(new FileOutputStream(transactionFile));
        } else {
            writer = new ObjectOutputStreamMod(new FileOutputStream(transactionFile, true));
        }
        writer.writeObject(transaction);
    }

    public void readTransactions() throws IOException, ClassNotFoundException {
        if (transactionFile.exists()) {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(transactionFile));
            try {
                while (true) {
                    Transaction transaction = (Transaction) reader.readObject();
                    transactionList.add(transaction);
                }
            } catch (EOFException e) {

            }
        }
    }

    //FICHERO NAVES
    public void writeShip(Nave nave) throws IOException {
        ObjectOutputStream writer;
        if (shipFile.length() == 0) {
            writer = new ObjectOutputStream(new FileOutputStream(shipFile));
        } else {
            writer = new ObjectOutputStreamMod(new FileOutputStream(shipFile, true));
        }
        writer.writeObject(nave);
    }

    public void readShips() throws IOException, ClassNotFoundException {
        if (shipFile.exists()) {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(shipFile));
            try {
                while (true) {
                    Nave nave = (Nave) reader.readObject();
                    shipList.add(nave);
                }
            } catch (EOFException e) {

            }
        }
    }
    public void changePropietario(Nave nave, Client newPropietario) throws IOException {
        shipList.get(shipList.indexOf(nave)).setPropietario(newPropietario);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Nave naveToAdd : shipList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(shipFile));
                writer.writeObject(naveToAdd);
                firstTime=false;
            }
            else {
                writeShip(naveToAdd);
            }
        }
    }

}
