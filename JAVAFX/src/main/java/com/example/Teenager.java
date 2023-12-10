package com.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/** Class description
 * @author : <a href = 'mailto:matys.lepretre.etu@univ-lille.fr'> Matys Lepretre</a>
 * @version 0.0.0.0.1
 */
public class Teenager implements Serializable {

    /* Documentation attributes */
    private static int increment = 0;
    public static int getIncrement() {
        return increment;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    private final int ID;
    private String firstName;
    private String name;
    private char gender;
    private final LocalDate DATE_OF_BIRTH;
    private Country country;
    private HashMap<CriterionName, Criterion> requirements  ;

    /* --------------------- */
    /** <i> return an instance of Teenager </i>
     * @param firstName name gender dateOfBirth country requirements
    */
    public Teenager(String firstName, String name, char gender, LocalDate dateOfBirth, Country country,
        HashMap<CriterionName,Criterion> requirements) {
        this.ID = increment;
        increment += 1;
        this.firstName = firstName;
        this.name = name;
        this.gender = gender;
        this.DATE_OF_BIRTH = dateOfBirth;
        this.country = country;
        this.requirements = requirements;
    }

    public Teenager(String firstName, String name, char gender, LocalDate dateOfBirth, Country country){
        this(firstName,name,gender,dateOfBirth,country, new HashMap<CriterionName,Criterion>());
    }

    public boolean affinityWithAge(Teenager teen){
        long differenceInMonths = ChronoUnit.MONTHS.between(this.DATE_OF_BIRTH, teen.DATE_OF_BIRTH);
        long differenceInYears = differenceInMonths / 12;
        long differenceInMonthsRemainder = differenceInMonths % 12;
        
        if (differenceInYears < 1 || (differenceInYears == 1 && differenceInMonthsRemainder <= 6)) {
            return true;
        } else {
            return false;
        }
    }

    public String toStringCourt() {
        return firstName +" "+ name;
    }
    
    public String toString() {
        return firstName +" "+ name + "[" + country + "," + gender + "," + DATE_OF_BIRTH + requirements+"]";
    }

    public char getGender() {
        return gender;
    }
    public LocalDate getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }
    /** 
     * @return Country
     */
    public Country getCountry() {
        return country;
    }
    
    /** 
     * @return HashMap<CriterionName, Criterion>
     */
    public HashMap<CriterionName, Criterion> getRequirements() {
        return requirements;
    }

    public void addCriterion(CriterionName crit , String value){
        Criterion criterion = new Criterion(value, crit);
        requirements.put(crit, criterion);
    }

    

    public boolean equalsCriterion(CriterionName criterionName, String value){
        if(!this.requirements.containsKey(criterionName)){
            return false;
        }
        return this.requirements.get(criterionName).getValue().equals(value);
    }
    
    /** 
     * @param other
     * @return boolean
     */
    public boolean compatibleWithGuest(Teenager other){
        return isCompatibleWithAnimalAllergy(other) /*&& isCompatibleWithFoodAllergy(other)*/;
    }
    
    
    /** 
     * @param other
     * @return boolean
     */
    public boolean compatibleWithGuestGrapheV1(Teenager other){
        return isCompatibleWithAnimalAllergy(other);
    }

    /** 
     * @param other
     * @return boolean
     */
    public boolean isCompatibleWithAnimalAllergy(Teenager other){
        if(this.requirements.get(CriterionName.GUEST_ANIMAL_ALLERGY)==null ||other.requirements.get(CriterionName.HOST_HAS_ANIMAL)==null)return true;
        return (this.requirements.get(CriterionName.GUEST_ANIMAL_ALLERGY).getValue().equals("yes") && other.requirements.get(CriterionName.HOST_HAS_ANIMAL).getValue().equals("no"))
        || (this.requirements.get(CriterionName.GUEST_ANIMAL_ALLERGY).getValue().equals("no"));
    }

    
    /** 
     * @param other
     * @return boolean
     */
    public boolean isCompatibleWithFoodAllergy(Teenager other){
        if(this.getRequirements().get(CriterionName.GUEST_FOOD)==null)return true;
        String[] tabGuestFoodConstraint, tabHostFood;
        tabGuestFoodConstraint = SplitAndSortString(this.getRequirements().get(CriterionName.GUEST_FOOD).getValue());
        tabHostFood = SplitAndSortString(other.getRequirements().get(CriterionName.GUEST_FOOD).getValue());
        
        for(int i = 0; i < tabGuestFoodConstraint.length; i ++){
            if(!tabGuestFoodConstraint[i].equals(tabHostFood[i])){
                return false;
            }
        }
        return true;
    }   

    
    /** 
     * @param other
     * @return boolean
     */
    public boolean isCompatibleWithCountry(Teenager other){
        // A faire par MATYS
        //Creer une fonction qui regarde les valeurs de deux tableau pour cette fonction et IsCompatibleWithFoodAllergy
        if(this.getCountry() == Country.FRANCE || other.getCountry() == Country.FRANCE){
            return this.nbHobbies(other) > 0;            
        }
        return false;
    }


    
    /** 
     * @param other
     * @return double
     */
    public double nbHobbies(Teenager other){
        if(this.getRequirements().get(CriterionName.HOBBIES)==null)return 0.0;
        if(this.getRequirements().get(CriterionName.HOBBIES).getValue()==null)return 0;
        String[] HobbiesTab = SplitAndSortString(this.getRequirements().get(CriterionName.HOBBIES).getValue());
        String[] HobbiesOtherTab = SplitAndSortString(other.getRequirements().get(CriterionName.HOBBIES).getValue());
        double n = 0.0;

        for(int i = 0; i < HobbiesTab.length; i++){
            for(int j = 0; j < HobbiesOtherTab.length; j++){
                if(HobbiesTab[i].equals(HobbiesOtherTab[j])){
                    n ++;
                }
            }
        }
        return n;
    }


    
    /** 
     * @param txt
     * @return String[]
     */
    public String[] SplitAndSortString(String txt){
        String[] tab = txt.split(",");
        Arrays.sort(tab);
        return tab;
    }
    
    public void purgeInvalidrequirement(){
        Iterator<Map.Entry<CriterionName, Criterion>> it = requirements.entrySet().iterator();
        while (it.hasNext()) {
            if (!it.next().getValue().isValid()){
                it.remove();
            }
        }
    }
}
