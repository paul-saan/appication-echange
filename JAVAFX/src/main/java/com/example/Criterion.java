package com.example;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**class description
 * @author : <a href = mailto:'paul.milleville.etu@univ-lille.fr'>Paul Milleville</a>
 * @version 0.0.0.0.1
 */

public class Criterion implements Serializable{
    private String value;
    private CriterionName label;

    /**<i> Create an instance of Criterion </i> 
     * @param value
    */
    public Criterion(String value, CriterionName critere){
        this.value = value;
        this.label = critere;
    }

    
    /** 
     * @return String
     */
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return value;
    }


    /** 
     * @return boolean
     */
    // Not used 
    public boolean isValidDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd" );
        dateFormat.setLenient(false);
        java.util.Date date = null;
        try {
            date = dateFormat.parse(this.value);
            return true;
        } catch (ParseException ex) {
            // la chaîne ne correspond pas à une date valide
            ex.printStackTrace();
            return false;
        }
    }

    /**<i> Return true if parameters is correct for the instance</i> */
    public boolean isValid(){
        try{
            return (label.getType() == 'B' && (value.equals("yes") || value.equals("no")) 
            || (label.getType() == 'N' && value.matches("[0-9]+")) // à modifier si on veut des entiers négatifs ou des doubles
            || (label.getType() =='D' && isValidDate()) //
            || (label.getType() == 'T' && value!=null));
        }catch(NullPointerException e ){
            return false;
        }   

    }
}
