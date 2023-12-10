import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeenagerTest {
    Criterion c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22;
    Teenager teen1,teen2,teen3,teen4,teen5,teen6;
    HashMap<CriterionName,Criterion> map1 = new HashMap<>();
    HashMap<CriterionName,Criterion> map2 = new HashMap<>();
    HashMap<CriterionName,Criterion> map3 = new HashMap<>();
    HashMap<CriterionName,Criterion> map4 = new HashMap<>();

    HashMap<CriterionName,Criterion> map5 = new HashMap<>();
    HashMap<CriterionName,Criterion> map6 = new HashMap<>();

    
    @BeforeEach
    void initialisation(){
        c1=new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        c2=new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        c3=new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        c4=new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        c5=new Criterion("same", CriterionName.HISTORY);
        c6=new Criterion("other", CriterionName.HISTORY);
        c7=new Criterion("nonuts", CriterionName.HOST_FOOD);
        c8=new Criterion("nonuts", CriterionName.GUEST_FOOD);
        c9=new Criterion("vegetarian", CriterionName.HOST_FOOD);
        c10=new Criterion("vegetarian", CriterionName.GUEST_FOOD);
        c11= new Criterion("sport,cinema,music", CriterionName.HOBBIES);
        c12= new Criterion("cinema,theatre", CriterionName.HOBBIES);
        c13= new Criterion("sport,videogames", CriterionName.HOBBIES);
        c14= new Criterion("book,cinema", CriterionName.HOBBIES);

        c15=new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        c16=new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        c17=new Criterion("vegetarian", CriterionName.GUEST_FOOD);
        c18= new Criterion("sport,cinema,music", CriterionName.HOBBIES);

        c19=new Criterion("Cyril", CriterionName.GUEST_ANIMAL_ALLERGY);
        c20=new Criterion("Patrick", CriterionName.HOST_HAS_ANIMAL);
        c21=new Criterion("blala", CriterionName.GUEST_FOOD);
        c22= new Criterion("blobo", CriterionName.HOBBIES);
        
        map1.put(CriterionName.GUEST_ANIMAL_ALLERGY,c1);
        map1.put(CriterionName.HOST_HAS_ANIMAL,c4);
        map1.put(CriterionName.HISTORY,c5);
        map1.put(CriterionName.HOST_FOOD,c7);
        map1.put(CriterionName.GUEST_FOOD,c8);
        map1.put(CriterionName.HOBBIES,c11);

        map2.put(CriterionName.GUEST_ANIMAL_ALLERGY,c2);
        map2.put(CriterionName.HOST_HAS_ANIMAL,c3);
        map2.put(CriterionName.HISTORY,c5);
        map2.put(CriterionName.HOST_FOOD,c7);
        map2.put(CriterionName.GUEST_FOOD,c8);
        map2.put(CriterionName.HOBBIES,c12);

        map3.put(CriterionName.GUEST_ANIMAL_ALLERGY,c1);
        map3.put(CriterionName.HOST_HAS_ANIMAL,c3);
        map3.put(CriterionName.HISTORY,c6);
        map3.put(CriterionName.HOST_FOOD,c9);
        map3.put(CriterionName.GUEST_FOOD,c10);
        map3.put(CriterionName.HOBBIES,c13);
        
        map4.put(CriterionName.GUEST_ANIMAL_ALLERGY,c2);
        map4.put(CriterionName.HOST_HAS_ANIMAL,c4);
        map4.put(CriterionName.HISTORY,c6);
        map4.put(CriterionName.HOST_FOOD,c9);
        map4.put(CriterionName.GUEST_FOOD,c10);
        map4.put(CriterionName.HOBBIES,c14);

        map5.put(CriterionName.GUEST_ANIMAL_ALLERGY,c15);
        map5.put(CriterionName.HOST_HAS_ANIMAL,c16);
        map5.put(CriterionName.GUEST_FOOD,c17);
        map5.put(CriterionName.HOBBIES,c18);


        map6.put(CriterionName.GUEST_ANIMAL_ALLERGY,c19);
        map6.put(CriterionName.HOST_HAS_ANIMAL,c20);
        map6.put(CriterionName.GUEST_FOOD,c21);
        map6.put(CriterionName.HOBBIES,c22);

        teen1=new Teenager("Matys", "Lepretre", 'M', LocalDate.now(), Country.FRANCE, map1);
        teen2=new Teenager("Paul", "Milleville", 'M', LocalDate.now(), Country.BRAZIL, map2);
        teen3=new Teenager("Julien", "Bouin", 'M', LocalDate.now(), Country.ITALY, map3);
        teen4=new Teenager("Fille", "pour de la diversité", 'F', LocalDate.now(), Country.FRANCE, map4);

        teen5= new Teenager("Jean-Christophe", "Charles", 'H', LocalDate.now(), Country.FRANCE, map5);
        teen6 = new Teenager("Francois", "Berléan", 'H', LocalDate.now(), Country.FRANCE, map6);
    }

    @Test
    void testCompatibilityAnimalAllergies(){
        assertTrue(teen1.isCompatibleWithAnimalAllergy(teen1));
        assertTrue(teen2.isCompatibleWithAnimalAllergy(teen4));
        assertFalse(teen3.isCompatibleWithAnimalAllergy(teen2));
        assertTrue(teen4.isCompatibleWithAnimalAllergy(teen1));
    }

    @Test
    void testCompatibleFoodAllergy(){
        assertTrue(teen1.isCompatibleWithFoodAllergy(teen2));
        assertFalse(teen2.isCompatibleWithFoodAllergy(teen3));
        assertTrue(teen3.isCompatibleWithFoodAllergy(teen4));
        assertFalse(teen4.isCompatibleWithFoodAllergy(teen1));
    }

    @Test
    void testCompatibleWithGuest(){
        assertTrue(teen1.compatibleWithGuest(teen1));
        assertFalse(teen2.compatibleWithGuest(teen3));
        assertTrue(teen3.compatibleWithGuest(teen4));
        assertFalse(teen4.compatibleWithGuest(teen1));
    }
    @Test
    void testCompatibleCountry(){
        assertTrue(teen1.isCompatibleWithCountry(teen2));
        assertFalse(teen4.isCompatibleWithCountry(teen3));
    }

    @Test
    void testPurgeInvalidRequirement(){
        teen5.purgeInvalidrequirement();
        assertTrue(map5.containsValue(c15));
        assertTrue(map5.containsValue(c16));
        assertTrue(map5.containsValue(c17));
        assertTrue(map5.containsValue(c18));
        teen6.purgeInvalidrequirement();
        assertFalse(map6.containsValue(c19));
        assertFalse(map6.containsValue(c20));
        assertTrue(map6.containsValue(c21));
        assertTrue(map6.containsValue(c22));
    }
}