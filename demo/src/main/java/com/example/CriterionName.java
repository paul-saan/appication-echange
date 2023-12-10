package com.example;



/**Class description
 * @author : <a href ='julien.bouin.etu@univ-lille.fr'> Bouin Julien </a>
 * @version 0.0.0.0.1
 */


public enum CriterionName {

    GUEST_ANIMAL_ALLERGY('B'),HOST_HAS_ANIMAL('B'),GUEST_FOOD('T'),HOST_FOOD('T'),HOBBIES('T'),GENDER('T'),PAIR_GENDER('T'),HISTORY('T');

    private final char TYPE;
    /** <i> Create an instance of a TYPE
     * @param type
     */
    private CriterionName(char type){
        this.TYPE=type;
    }
    
    public char getType(){
        return this.TYPE;
    }
}
