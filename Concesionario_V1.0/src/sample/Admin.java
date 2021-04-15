package sample;

import java.io.*;
import java.util.*;

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
        List <Offer> publishedOfferList = new ArrayList<>();
        for (Offer offerToClone: offerList) {
            publishedOfferList.add(offerToClone);
        }
        int n=1;
        int range= publishedOfferList.size();
        while(range>=n){
            if(!publishedOfferList.get(range-n).isPublished()){
                publishedOfferList.remove(range-n);
            }
            n++;
        }
        return publishedOfferList;
    }

    public List<Offer> getUnpublishedOffers() throws IOException, ClassNotFoundException {
        fileManager.readOffers();
        List <Offer> unpublishedOfferList = new ArrayList<>();
        for (Offer offerToClone: offerList) {
            unpublishedOfferList.add(offerToClone);
        }
        int n=1;
        int range= unpublishedOfferList.size();
        while(range>=n){
            if(unpublishedOfferList.get(range-n).isPublished()){
                unpublishedOfferList.remove(range-n);
            }
            n++;
        }
        return unpublishedOfferList;
    }

    public void addOffer(Offer offer) throws IOException {
        if (offer.isPublished()){
            offer.setPublished(false);
        }
        fileManager.writeOffer(offer);
    }

    public void deleteOffer(Offer offer) throws IOException, ClassNotFoundException {
        fileManager.deleteOffer(offer);
    }

    public void modifyOfferVisibility(Offer offer,Boolean status) throws IOException, ClassNotFoundException {
        fileManager.modifyOfferVisibility(offer,status);
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
        transactionList.clear();
        fileManager.readTransactions();
        List<Transaction> individualTransactionList = new ArrayList<>();
        for (Transaction transactionToAdd : transactionList){
            individualTransactionList.add(transactionToAdd);
        }
        int n=1;
        int range= individualTransactionList.size();
        while(range>=n){
            if(!individualTransactionList.get(range-n).getPurchaser().equals(client.getEmail())&&!individualTransactionList.get(range-n).getSeller().equals(client.getEmail())){
                individualTransactionList.remove(range-n);
            }
            n++;
        }
        return individualTransactionList;
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
        shipList.clear();
        fileManager.readShips();
        return shipList;
    }

    public Nave searchShip(String numeroRegistro) throws IOException, ClassNotFoundException {
     List<Nave> listToSearch= getShipList();
     for(Nave searchedNave : listToSearch){
         if(searchedNave.getNumeroRegistro().equals(numeroRegistro)){
             return searchedNave;
         }
     }
     return null;
    }

    public void changePropietario(Nave nave, Client newPropietario) throws IOException, ClassNotFoundException {
        /*getClientList();
        List<Client> clientListToSearch= new ArrayList<>();
        for (Client clientToAdd : clientList){
            clientListToSearch.add(clientToAdd);
        }
        Client newPropietario=null;
        for (Client clientToSearch : clientListToSearch){
            if(clientToSearch.getEmail().equals(emailNewPropietario)){
                newPropietario=clientToSearch;
            }
        }*/
        fileManager.changePropietario(nave,newPropietario);
    }

    public void addSuscriptor(String tipo, Client newSuscriptor) throws IOException, ClassNotFoundException {
        fileManager.addSuscriptor(tipo,newSuscriptor);
    }

    public void addComment(Client client, String comment) throws IOException, ClassNotFoundException {
        fileManager.addComment(client,comment);
    }

    public void actualizarUltimoAcceso(Client cliente) throws IOException, ClassNotFoundException{
        Calendar calendario = new GregorianCalendar();
        Date fechaActual = new Date(calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),calendario.get(Calendar.SECOND));
        fileManager.modifyDateClient(cliente,fechaActual);
    }

    public void setSuscriptor(String tipo, Client cliente, Boolean estado)throws IOException, ClassNotFoundException{
        switch (tipo){
            case "Estacion Espacial":
                fileManager.modifySuscEE(cliente,estado);
                break;
            case "Destructor":
                fileManager.modifySuscDest(cliente, estado);
                break;
            case "Caza":
                fileManager.modifySuscCaza(cliente, estado);
                break;
            case "Carguero":
                fileManager.modifySuscCarg(cliente, estado);
                break;
        }
    }
}

