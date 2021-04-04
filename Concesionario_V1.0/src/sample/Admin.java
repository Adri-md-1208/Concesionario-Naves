package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    File clientFile = new File("Clients.dat");
    List <Client> clientList = new ArrayList<>();
    File offerFile = new File("Offers.dat");
    List <Offer> offerList = new ArrayList<>();
    File transactionFile = new File("Transactions.dat");
    List <Transaction> transactionList= new ArrayList<>();
    File shipFile = new File("Ships.dat");
    List <Nave> shipList= new ArrayList<>();

    FileManager fileManager = new FileManager(clientFile,clientList,offerFile,offerList,transactionFile,transactionList,shipFile,shipList);

    public int evaluarTipoUsuario(Client client) throws IOException, ClassNotFoundException {

        if (client.isFraud() || client.getWarnings() >= 2) {
            return 0; //Fraude o advertencias = No entra
        } else if (client.isPiracy()) {
            return 1; //Piratería = Solo cargueros
        } else if (!client.isLicense() && (client.getSpecie().equals("Kromagg"))) {
            return 2; //Sin licencia especial = No kromagg
        }else{
            return 3; //Sin limitaciones
            }
        }


    public List<Offer> getPublishedOffers() throws IOException, ClassNotFoundException {
        fileManager.readOffers();
        for (int n=0; n<=offerList.size(); n++){
            if(!offerList.get(n).isPublished()){
                offerList.remove(n);
            }

        }
        return offerList;
    }

    public List<Offer> getUnpublishedOffers() throws IOException, ClassNotFoundException {
        fileManager.readOffers();
        for (int n=0; n<=offerList.size(); n++){
            if(offerList.get(n).isPublished()){
                offerList.remove(n);
            }

        }
        return offerList;
    }

    public void addOffer(Offer offer) throws IOException {
        if (offer.isPublished()){
            offer.setPublished(false);
        }
        fileManager.writeOffer(offer);
    }

    public void deleteOffer(Offer offer) throws IOException {
        fileManager.deleteOffer(offer);
    }

    public void modifyOfferVisibility(Offer offer) throws IOException, ClassNotFoundException {
        fileManager.modifyOfferVisibility(offer,true);
    }

    public void addFraudSanction(Client client) throws IOException, ClassNotFoundException {
        fileManager.modifyFraudClient(client,true);
    }
    public void addPiracySanction(Client client) throws IOException, ClassNotFoundException {
        fileManager.modifyPiracyClient(client,true);
    }
    public void deleteFraudSanction(Client client) throws IOException, ClassNotFoundException {
        fileManager.modifyFraudClient(client,false);
    }
    public void deletePiracySanction(Client client) throws IOException, ClassNotFoundException {
        fileManager.modifyPiracyClient(client,false);
    }

    public void addTransaction(Transaction transaction) throws IOException {
        fileManager.writeTransaction(transaction);
    }

    public List<Transaction> getIndividualTransaction(Client client) throws IOException, ClassNotFoundException {
        fileManager.readTransactions();
        for (int n=0;n<=transactionList.size(); n++){
            if(transactionList.get(n).getPurchaser().getId()!=client.getId() && transactionList.get(n).getSeller().getId()!=client.getId()){
                transactionList.remove(n);
            }
        }
        return transactionList;
    }

    public void addClient(Client client) throws IOException {
        fileManager.writeClient(client);
    }

    public List<Client> getClientList() throws IOException, ClassNotFoundException {
        fileManager.readClients();
        return clientList;
    }

    public void addShip(Nave ship) throws IOException {
        fileManager.writeShip(ship);
    }
    public List<Nave> getShipList() throws IOException, ClassNotFoundException {
        fileManager.readShips();
        return shipList;
    }
    }


