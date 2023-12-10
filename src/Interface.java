import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fr.ulille.but.sae2_02.graphes.Arete;



public class Interface {
    
    private static Scanner scan = new Scanner(System.in);
    private static String filename =  System.getProperty("user.dir") + File.separator + "doc" + File.separator + "adosAleatoires.csv";
    private static String configurationFile=System.getProperty("user.dir") +File.separator+"doc"+File.separator+"configuration.csv";
   
    private static String Header = "FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY";

    
    /** 
     * @return Country[]
     */
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

     
     /** 
      * @param pays
      * @return boolean
      */
     public static boolean paysExist(String pays){
        for(Country c : Country.values()){
            if(c.name().equals(pays)){
                return true;
            }
        }
        return false;
    }

    public static void clear(){
        final String ESC = "\033[";
        System.out.print (ESC + "2J");
        System.out.print (ESC + "0;0H");
        System.out.flush();
	}

    
    /** 
     * @return char
     */
    private static char ecouterChar(){
        System.out.print("Veuillez entrer 1 caractère : ");
        String c = scan.next();
        while(c.length() != 1){
            System.out.print("Veuillez entrer 1 caractère : ");
            c = scan.next();
        }
        return c.charAt(0);
    }


    
    /** 
     * @param liste1
     * @param liste2
     */
    public static void afficheEtudiants(ArrayList<Teenager> liste1, ArrayList<Teenager> liste2) {
        Iterator<Teenager> it1 = liste1.iterator();
        Iterator<Teenager> it2 = liste2.iterator();

        while (it1.hasNext() || it2.hasNext()) {
            if (it1.hasNext()) {
                Teenager temp= it1.next();
                System.out.print(temp.getID()+" "+temp.getName()+" "+ temp.getFirstName()+" "+temp.getCountry());
            }
          

            System.out.print("\t \t"); // Ajouter une tabulation entre les éléments

            if (it2.hasNext()) {
                Teenager temp= it2.next();
                System.out.print(temp.getID()+" "+temp.getName()+" "+ temp.getFirstName()+" "+temp.getCountry());
            }
     

            System.out.println(); // Passer à la ligne
        }
          System.out.println(liste1.size()+"étudiants hote");
          System.out.println(liste2.size()+"étudiants guest");
    }


    public static void defPond(){
        System.out.println("Changement pondérations :");
        System.out.print("Indiquez la pondération d'un critère rédhibitoire : ");
        try{
            int n = scan.nextInt();
            if(n > 1000){
                n = 1000;
            }
            AffectationUtil.redhibitoire = n;
        }catch(Exception e){

        }
        System.out.print("Indiquez la pondération d'un critère préférentiel : ");
        try{
            int n = scan.nextInt();
            if(n > 1000){
                n = 1000;
            }
            AffectationUtil.pref = n;
        }catch(Exception e){

        }
        System.out.print("Indiquez la pondération du poids de base : ");
        try{
            int n = scan.nextInt();
            if(n > 1000){
                n = 1000;
            }
            AffectationUtil.initialWeight = n;
        }catch(Exception e){

        }
    }

    private static boolean compatibleSizeOfCountry(Country[] pays){
         ArrayList<Teenager> teenagers = Plateform.importTeenagerFromCSV(filename);
        ArrayList<Teenager> country1 = Plateform.selectionPays(teenagers, pays[0]);
        ArrayList<Teenager> country2 = Plateform.selectionPays(teenagers, pays[1]);
        if(country1.size()!=country2.size()){
            return false;
        }else{
            return true;
        }
    }

    private static List<Arete<Teenager>> listAffectations(Country[] pays){
        List<Arete<Teenager>> results;
         ArrayList<Teenager> teenagers = Plateform.importTeenagerFromCSV(filename);
        if(!compatibleSizeOfCountry(pays)){
            return null;
        }else{
            results=Plateform.affectionCountry(teenagers, pays[0], pays[1]);
            return results;
        }
    }

    private static void afficheAffectations(List<Arete<Teenager>> affectations){
        for (Arete<Teenager> arete : affectations) {
            System.out.println(arete.getExtremite1().getID()+" "+arete.getExtremite1().getName()+" "+arete.getExtremite1().getFirstName()+" "+arete.getExtremite1().getCountry()+ "\t       "+arete.getExtremite2().getID()+" "+arete.getExtremite2().getName()+" "+arete.getExtremite2().getFirstName()+" "+arete.getExtremite2().getCountry());
        }
    }

    

    private static void commandes(){
        System.out.println(" 1 : Afficher la liste des étudiants");
        System.out.println(" 2 : Afficher la liste des affectations (en fonction des pays que vous avez choisis)");
        System.out.println(" 3 : Exporter la liste des affectations");
        System.out.println(" 4 : Définir la pondération");
        System.out.println(" 5 : Supprimer un étudiant");
        System.out.println(" 6 : Ajouter un étudiant");
        System.out.println(" 7 : Quitter");
    }

    private static void interfaceAffectation(Country[] pays) throws InterruptedException{
        List<Arete<Teenager>> results;
        ArrayList<Teenager> teenagers = Plateform.importTeenagerFromCSV(filename);
        results=listAffectations(pays);
        ArrayList<Teenager> country1 = Plateform.selectionPays(teenagers, pays[0]);
        ArrayList<Teenager> country2 = Plateform.selectionPays(teenagers, pays[1]);
        clear();
        commandes();
        char c = ecouterChar();
        if(c=='1'){
            clear();
            afficheEtudiants(country1,country2);
            Thread.sleep(10000);
            interfaceAffectation(pays);
        }else if(c=='2'){
            clear();
            if(results==null){
                System.out.println("Le nombre d'étudiants doit être identique pour les deux pays");
                System.out.println("Il est possible de modifier ça dans l'interface de gestion des étudiants");
            }else{
                afficheAffectations(results);
            }
            Thread.sleep(10000);
            interfaceAffectation(pays);
        }else if(c=='3'){
            clear();
            if(results==null){
                System.out.println("Le nombre d'étudiants doit être identique pour les deux pays");
                System.out.println("Il est possible de modifier ça dans l'interface de gestion des étudiants");
                Thread.sleep(10000);
                interfaceAffectation(pays);
            }else{
                System.out.println("Entrez le nom du fichier de l'export des résultats ( avec le .csv )");
            String file = scan.next();
            Plateform.exportResults(results, file);
            Thread.sleep(10000);
            interfaceAffectation(pays);
            }
            
        }else if(c=='4'){
            clear();
            if(results==null){
                System.out.println("Le nombre d'étudiants doit être identique pour les deux pays");
                System.out.println("Il est possible de modifier ça dans l'interface de gestion des étudiants");
                Thread.sleep(10000);
                interfaceAffectation(pays);
            }else{
                defPond();
            interfaceAffectation(pays);
            }
            
        }else if(c=='5'){
              clear();
            System.out.println("Voici la liste des étudiants : \n \n \n \n");
            afficheEtudiants(country1, country2);
            Thread.sleep(10000);
            System.out.println("Quel étudiant voulez vous supprimez ?");
            System.out.println("Veuillez rentrez son ID : ");
            String ID = scan.next();
            String supp = suppEtu(country1, country2, ID);
            if(supp!=null){
                suppEtuCSV(supp, filename);
                System.out.println("L'étudiant a bien été supprimé.");
                Thread.sleep(10000);
                interfaceAffectation(pays);
            }else{
                System.out.println("L'étudiant n'a pas bien été supprimé.");
                System.out.println("Cela peut etre du au fait qu'il n'existe pas ou que vous ayez fait une faute");
                Thread.sleep(10000);
                interfaceAffectation(pays);
            }
            
        }else if(c=='6'){
          clear();
          newEtu();
          interfaceAffectation(pays);
        }
        else if(c=='7'){
              clear();
            System.out.println("Au revoir ....");
            Thread.sleep(10000);
        }else{
            clear();
            System.out.println("Ce n'est pas une bonne commande");
            Thread.sleep(10000);
            interfaceAffectation(pays);
        }
            
    }

    private static String suppEtu(ArrayList<Teenager> country1,ArrayList<Teenager> country2,String ID){
        Iterator<Teenager> it1 = country1.iterator();
        Iterator<Teenager> it2 = country2.iterator();
        if(!isNumeric(ID)){
            return null;
        }

        while (it1.hasNext() || it2.hasNext()) {
            if (it1.hasNext()) {
                Teenager temp= it1.next();
                if(temp.getID()==Integer.valueOf(ID)){
                    country1.remove(temp);
                    return temp.getName();
                }
            }
            if (it2.hasNext()) {
                Teenager temp= it2.next();
                if(temp.getID()==Integer.valueOf(ID)){
                    country2.remove(temp);
                    return temp.getName();
                }
            }
        }
        return null;
    }

     private static void suppEtuCSV(String name,String filename){
          List<String> fichier = new ArrayList<>();
        fichier.add("FORENAME;NAME;COUNTRY;BIRTH_DATE;HOBBIES;GUEST_ANIMAL_ALLERGY;HOST_HAS_ANIMAL;GUEST_FOOD;HOST_FOOD;GENDER;PAIR_GENDER;HISTORY");
        try( BufferedReader br = new BufferedReader(new FileReader(new File(filename)))){
            br.readLine();
            while(br.ready()){
                String currentLine=br.readLine();
                String[] tab = currentLine.split(";");
                if(!tab[1].equals(name)){
                   fichier.add(currentLine);
                }
            }
        }catch(IOException e ){
            e.printStackTrace();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))){
            for(String s : fichier){
                bw.write(s);
                bw.newLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void suppEtuWithFileConfiguration(String filenameConfiguration){
        String critere="";
        String value="";
        try( BufferedReader br = new BufferedReader(new FileReader(new File(filenameConfiguration)))){
            br.readLine();
            String[] split = br.readLine().split(";");
            critere=split[0].toUpperCase();
            value=split[1].toLowerCase();
        }catch(IOException e ){
            e.printStackTrace();
        }
        try( BufferedReader br = new BufferedReader(new FileReader(new File(Interface.filename)))){
           String[] header = Header.split(";");
           int idx=0;
           for(int i=0;i<header.length;i++){
                if(header[i].equals(critere)){
                    idx=i;
                }
           }
           br.readLine();
           while(br.ready()){
            String currentline= br.readLine();
            String[] lineSeparated=currentline.split(";");
            if(lineSeparated[idx].equals(value)){
                suppEtuCSV(lineSeparated[1], Interface.filename);
            }
           }
        }catch(IOException e ){
            e.printStackTrace();
        }
    }

     private static void newEtu(){
        List<String> liste = new ArrayList<>();
        System.out.println("Ajouter étudiant :");
        String[] header = Header.split(";");
        for(int i = 0; i < header.length; i++){
            System.out.print("Veuillez la valeur qui coresspond au critère " + header[i] + ": ");
            String s = scan.next();
            if(s.equals("null")){
                s = "";
            }
            liste.add(s);
        }
        String newEtu = "";
        for(String s : liste){
            newEtu += s + ";";
        }
        newEtu = newEtu.substring(0, newEtu.length()-1);
        File file = new File(filename);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(newEtu);
            bw.newLine();
        }catch(Exception e){
            System.out.println("Erreur dans l'écriture du fichier");
        }
    }

     public static boolean isNumeric(String s){
        if (s == null || s.equals("")) {
            return false;
        }
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        suppEtuWithFileConfiguration(configurationFile);
        System.out.println("Dans un premier temps choisissez les deux pays pour l'affectations");
        Country[] pays = choixPays();
         try{
            interfaceAffectation(pays);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
       
        

    }
}
    

