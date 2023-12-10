package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class Plateform {
    // class attributes
    private static final int NB_COLONNE = 12;
    
    /** 
     * Create a tab of string from the different parameters of student. 
     * @param line
     * @return String[]
     */
    public static String[] remplirCase(String line){
        String[] tab = new String[NB_COLONNE];
        String[] split = line.split(";");
        for(int i = 0; i < NB_COLONNE; i ++){
            if(i >= split.length || split[i].equals("")){
                tab[i] = "";
            }else{
                tab[i] = split[i];
            }
        }
        return tab;
    }

    
    /** 
     * Create teenager from the data of the tab.
     * @param tab
     * @return Teenager
     */
    public static Teenager createTeenager(String[] tab) throws WrongFormatException{
        try{
            String firstName = tab[0];
            String name = tab[1];
            if(tab[9].charAt(0) != 'f' && tab[9].charAt(0) != 'm' && tab[9].charAt(0) != 'o'){
                throw new WrongFormatException();
            }
            char gender = tab[9].charAt(0);
            LocalDate dateOfBirth = LocalDate.parse(tab[3]);
            Country country = Country.valueOf(tab[2]);
            HashMap<CriterionName,Criterion> requirements = new HashMap<CriterionName,Criterion>();
            requirements.put(CriterionName.HOBBIES, new Criterion(tab[4],CriterionName.HOBBIES));
            requirements.put(CriterionName.GUEST_ANIMAL_ALLERGY, new Criterion(tab[5], CriterionName.GUEST_ANIMAL_ALLERGY));
            requirements.put(CriterionName.HOST_HAS_ANIMAL, new Criterion(tab[6], CriterionName.HOST_HAS_ANIMAL));
            requirements.put(CriterionName.GUEST_FOOD, new Criterion(tab[7],CriterionName.GUEST_FOOD));
            requirements.put(CriterionName.HOST_FOOD, new Criterion(tab[8], CriterionName.HOST_FOOD));
            requirements.put(CriterionName.PAIR_GENDER, new Criterion(tab[10], CriterionName.PAIR_GENDER));
            requirements.put(CriterionName.HISTORY, new Criterion(tab[11], CriterionName.HISTORY));
            requirements.put(CriterionName.GENDER, new Criterion(tab[9], CriterionName.GENDER));
            return new Teenager(firstName, name, gender, dateOfBirth, country, requirements);
        }catch(DateTimeParseException e){
            throw new WrongFormatException();
        }
        
    }

    public static ArrayList<Teenager> importTeenagerFromCSV(String fileName){
        ArrayList<Teenager> teenagerList = new ArrayList<Teenager>();
        try{
            FileReader fichier = new FileReader(fileName);  
            BufferedReader br = new BufferedReader(fichier);
            String currentLine = br.readLine();
            currentLine = br.readLine();
            Teenager t;
             
            String[] split;
            

            while(currentLine != null){
                split = Plateform.remplirCase(currentLine);    
                t = createTeenager(split);
                teenagerList.add(t);
                currentLine = br.readLine();
            }
            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
        }
        return teenagerList;
    }

    public static void serializationTeenagerList(ArrayList<Couple> lt){
        try{
            FileOutputStream file = new FileOutputStream(".."+File.separator + "bin" + File.separator + "Teenagers.bin");
            ObjectOutputStream object = new ObjectOutputStream(file);
            for(int i = 0; i < lt.size(); i ++){
                object.writeObject(lt.get(i));
            }
            object.close();
        }catch(IOException e){
            e.printStackTrace();
        }    
    }

    
    public static ArrayList<Couple> deserialisationFile(String fileName){
        ArrayList<Couple> coupleList = new ArrayList<Couple>();
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream object = new ObjectInputStream(file);
            Couple t;
            while((t = (Couple) object.readObject()) != null){
                coupleList.add(t);
            }
            object.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return coupleList;
    }

    public static File exportResults(List<Arete<Teenager>> results,String Filename){
        File file = new File(Filename);
        String separator = ";";
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            bf.append("ID;FIRSTNAME;NAME;COUNTRY;ID2;FIRSTNAME2;NAME2;COUNTRY2");
            bf.newLine();
            for(int i=0;i<results.size();i++){
                bf.append(results.get(i).getExtremite1().getID()+"");
                bf.append(separator);
                bf.append(results.get(i).getExtremite1().getFirstName());
                bf.append(separator);
                bf.append(results.get(i).getExtremite1().getName());
                bf.append(separator);
                bf.append(results.get(i).getExtremite1().getCountry()+"");
                bf.append(separator);
                bf.append(results.get(i).getExtremite2().getID()+"");
                bf.append(separator);
                bf.append(results.get(i).getExtremite2().getFirstName());
                bf.append(separator);
                bf.append(results.get(i).getExtremite2().getName());
                bf.append(separator);
                bf.append(results.get(i).getExtremite2().getCountry()+"");
                bf.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public static ArrayList<Teenager> selectionPays(List<Teenager> promo, Country pays){
        ArrayList<Teenager> liste = new ArrayList<>();
        for(Teenager t : promo){
            if(t.getCountry() == pays){
                liste.add(t);
            }
        }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        return liste;
    }


     public static List<Arete<Teenager>> affectionCountry(ArrayList<Teenager> teens, Country country1, Country country2){
        History history = new History();
        ArrayList<Teenager> host= selectionPays(teens, country1);
        ArrayList<Teenager> guest=selectionPays(teens, country2);
        GrapheNonOrienteValue<Teenager> graphe = AffectationUtil.creationGraphe(host,guest,  history);
        CalculAffectation<Teenager> ca = new CalculAffectation<>(graphe, guest, host);
       return ca.calculerAffectation();
     }

}