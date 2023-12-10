package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ulille.but.sae2_02.graphes.Arete;



public class Interface {
    
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Teenager> teens= Plateform.importTeenagerFromCSV("/home/juju/S2/S2.01.02/F3/doc/adosAleatoires.csv");

    public static Country[] choixPays(){
        System.out.println("\n");
        Country[] pays = new Country[2];
        String host = "";
        while(!paysExist(host)){
            System.out.print("Veuillez rentrer le nom du pays hote en MAJUSCULE : ");
            host = scan.next();
        }
        String guest = "";
        while(!paysExist(guest)){
            System.out.print("Veuillez rentrer le nom du pays guest en MAJUSCULE : ");
            guest = scan.next();
        }
        pays[0] = Country.valueOf(host);
        pays[1] = Country.valueOf(guest);
        return pays;
    }

     public static boolean paysExist(String pays){
        for(Country c : Country.values()){
            if(c.name().equals(pays)){
                return true;
            }
        }
        return false;
    }

    private static char ecouterChar(){
        System.out.print("Veuillez entrer 1 caractère : ");
        String c = scan.next();
        while(c.length() != 1){
            System.out.print("Veuillez entrer 1 caractère : ");
            c = scan.next();
        }
        return c.charAt(0);
    }

    private static void afficheEtudiants(){
        for (Teenager t : teens) {
            System.out.println(t);
        }
    }

    

    private static void commandes(){
        System.out.println(" 1 : Afficher la liste des étudiants");
        System.out.println(" 2 : Afficher la liste des affectations (en fonction des pays que vous allez choisir)");
        System.out.println(" 3 : Exporter la liste des affectations");
        System.out.println(" 4 : Quitter");
    }

    private static void interfaceAffectation(Country[] pays) throws InterruptedException{
        List<Arete<Teenager>> results;
            results=Plateform.affectionCountry(teens, pays[0], pays[1]);
            commandes();
            char c = ecouterChar();
                if(c=='1'){
                    afficheEtudiants();
                    Thread.sleep(1000);
                    interfaceAffectation(pays);
                }else if(c=='2'){
                    System.out.println(results);
                    Thread.sleep(1000);
                    interfaceAffectation(pays);
                }else if(c=='3'){
                    System.out.println("Entrez le nom du fichier de l'export des résultats ( avec le .csv )");
                    String file = scan.next();
                    Plateform.exportResults(results, file);
                    Thread.sleep(1000);
                    interfaceAffectation(pays);
                }else{
                    System.out.println("Au revoir ....");
                    Thread.sleep(1000);
               }
            
    }

    public static void main(String[] args){
       // ArrayList<Teenager> teens= Plateform.importTeenagerFromCSV("/home/juju/S2/S2.01.02/F3/doc/adosAleatoires.csv");
        //ArrayList<Teenager> test1 = Plateform.selectionPays(teens,Country.FRANCE);
        //ArrayList<Teenager> test2 = Plateform.selectionPays(teens, Country.ITALY);
        //Plateform.exportResults(Plateform.affectionCountry(teens, Country.FRANCE, Country.ITALY), "la.csv");
        System.out.println("Dans un premier temps choisissez les deux pays pour l'affectations");
        Country[] pays = choixPays();
        try{
            interfaceAffectation(pays);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
    

