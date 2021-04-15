package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

    public void modifyFraudClient(Client client, Boolean status) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setFraud(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifyPiracyClient(Client client, Boolean status) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setPiracy(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifyWarningClient(Client client,Boolean delete) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        if(delete) {
            clientList.get(clientList.indexOf(clientToModify)).setWarnings(0);
        } else{
            clientList.get(clientList.indexOf(clientToModify)).setWarnings(clientList.get(clientList.indexOf(clientToModify)).getWarnings() + 1);
        }
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void addComment(Client cliente, String comment) throws IOException, ClassNotFoundException {
        clientList.clear();
        readClients();
        Client clientToModify = null;
        for (Client clientToSearch : clientList) {
            if (cliente.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;
            }
        }
        //clientToModify = clientList.get(clientList.indexOf(cliente));
        List<String> commentList = clientToModify.getComments();
        commentList.add(comment);
        clientList.get(clientList.indexOf(clientToModify)).setComentarios(commentList);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifyDateClient(Client client, Date date) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setUltimoAcceso(date);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifySuscCarg(Client client, Boolean status) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setSuscritoCarguero(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifySuscCaza(Client client, Boolean status) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;
            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setSuscritoCaza(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifySuscEE(Client client, Boolean status) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setSuscritoEstacionEspacial(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
        }
    }

    public void modifySuscDest(Client client, Boolean status) throws IOException, ClassNotFoundException{
        clientList.clear();
        readClients();
        Client clientToModify= null;
        for (Client clientToSearch : clientList) {
            if (client.getEmail().equals(clientToSearch.getEmail())) {
                clientToModify = clientToSearch;

            }
        }
        clientList.get(clientList.indexOf(clientToModify)).setSuscritoDestructor(status);
        //offerFile.delete();
        Boolean firstTime = true;
        for(Client clientToAdd : clientList){
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(clientFile));
                writer.writeObject(clientToAdd);
                firstTime=false;
            }
            else {
                writeClient(clientToAdd);
            }
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
        if (offerFile.exists()) {
            if (offerFile.length() != 0) {
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
        Offer offerToDelete = null;
        for (Offer offerToSearch : offerList) {
            if (offerToRemove.getDescription().equals(offerToSearch.getDescription())) {
                offerToDelete = offerToSearch;

            }
        }
        offerList.remove(offerToDelete);
        //offerFile.delete();
        Boolean firstTime = true;
        if(offerList.isEmpty()){
            BufferedWriter bw = new BufferedWriter(new FileWriter(offerFile));
            bw.write("");
            bw.close();
        }
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

    public void addSuscriptor(String tipo, Client newSuscriptor) throws IOException, ClassNotFoundException {
        shipList.clear();
        readShips();
        List <Nave> shipsOfType = new ArrayList<>();
        List <Nave> shipList2 = new ArrayList<>();
        for (Nave shipToClone : shipList){
            shipList2.add(shipToClone);
        }
        for (Nave shipSearched: shipList2){
            if (shipSearched.getClass().getSimpleName().equals(tipo)){
                shipsOfType.add(shipSearched);
                shipList.remove(shipList.get(shipList.indexOf(shipSearched)));
            }
        }
        Boolean firstTime = true;
        List<String> newSuscriptorsList = new ArrayList<>();
        if (!shipsOfType.isEmpty()){
            newSuscriptorsList= shipsOfType.get(0).getSuscriptores();
        }
        for (Nave naveToModify : shipsOfType) {
            try {
                newSuscriptorsList.add(newSuscriptor.getNick());
                naveToModify.setSuscriptores(newSuscriptorsList);
                shipList.add(naveToModify);
            }
            catch(Exception nullList){
                List<String> suscriptorsList = new ArrayList<>();
                suscriptorsList.add(newSuscriptor.getNick());
                naveToModify.setSuscriptores(suscriptorsList);
                shipList.add(naveToModify);
            }

        }
        for (Nave naveToAdd : shipList) {
            if (firstTime) {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(shipFile));
                writer.writeObject(naveToAdd);
                firstTime = false;
            } else {
                writeShip(naveToAdd);
            }
        }


    }

    /*public void removeSuscriptor(String tipo, Client suscriptor) throws IOException, ClassNotFoundException {
        shipList.clear();
        readShips();

        for(Nave shipSearched : shipList){
            shipSearched.getSuscriptores().
            for (Client clientSearched : shipSearched.getSuscriptores(){
                if (clientSearched.)
            }
        }

    }*/

}
