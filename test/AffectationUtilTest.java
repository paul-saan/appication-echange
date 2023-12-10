import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AffectationUtilTest {
    Criterion c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,spTech,culSc,scRea,culTech,tech,male,female;
    Teenager teen1,teen2,teen3,teen4,adonia,bellatrix,callista,xolag,yak,zander;
    History affect = new History();
    HashMap<CriterionName,Criterion> map1 = new HashMap<>();
    HashMap<CriterionName,Criterion> map2 = new HashMap<>();
    HashMap<CriterionName,Criterion> map3 = new HashMap<>();
    HashMap<CriterionName,Criterion> map4 = new HashMap<>();
    HashMap<CriterionName,Criterion> map5 = new HashMap<>();

    HashMap<CriterionName,Criterion> a = new HashMap<>();
    HashMap<CriterionName,Criterion> b = new HashMap<>();
    HashMap<CriterionName,Criterion> c = new HashMap<>();
    HashMap<CriterionName,Criterion> x = new HashMap<>();
    HashMap<CriterionName,Criterion> y = new HashMap<>();
    HashMap<CriterionName,Criterion> z = new HashMap<>();

    
    @BeforeEach
    void initialisation(){
        //ANIMAL
        c1=new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        c2=new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        c3=new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        c4=new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        //HISTORIQUE
        c5=new Criterion("same", CriterionName.HISTORY);
        c6=new Criterion("other", CriterionName.HISTORY);
        //FOOD
        c7=new Criterion("nonuts", CriterionName.HOST_FOOD);
        c8=new Criterion("nonuts", CriterionName.GUEST_FOOD);
        c9=new Criterion("vegetarian", CriterionName.HOST_FOOD);
        c10=new Criterion("vegetarian", CriterionName.GUEST_FOOD);
        //HOOBIE
        c11= new Criterion("sport,cinema,music", CriterionName.HOBBIES);
        c12= new Criterion("cinema,theatre", CriterionName.HOBBIES);
        c13= new Criterion("sport,videogames", CriterionName.HOBBIES);
        c14= new Criterion("book,cinema", CriterionName.HOBBIES);
        spTech= new Criterion("sport,technologie", CriterionName.HOBBIES);
        culSc= new Criterion("culture,science", CriterionName.HOBBIES);
        scRea= new Criterion("science,reading", CriterionName.HOBBIES);
        culTech= new Criterion("culture,technologie", CriterionName.HOBBIES);
        tech= new Criterion("technologie", CriterionName.HOBBIES);

        
        map1.put(CriterionName.GUEST_ANIMAL_ALLERGY,c1);
        map1.put(CriterionName.HOST_HAS_ANIMAL,c4);
        map1.put(CriterionName.HOBBIES,c11);

        map2.put(CriterionName.GUEST_ANIMAL_ALLERGY,c2);
        map2.put(CriterionName.HOST_HAS_ANIMAL,c3);
        map2.put(CriterionName.HOBBIES,c12);

        map3.put(CriterionName.GUEST_ANIMAL_ALLERGY,c1);
        map3.put(CriterionName.HOST_HAS_ANIMAL,c3);
        map3.put(CriterionName.HOBBIES,c13);
        
        map4.put(CriterionName.GUEST_ANIMAL_ALLERGY,c2);
        map4.put(CriterionName.HOST_HAS_ANIMAL,c4);
        map4.put(CriterionName.HOBBIES,c14);

        a.put(CriterionName.GUEST_ANIMAL_ALLERGY,c2);
        a.put(CriterionName.HISTORY,c6);
        a.put(CriterionName.HOBBIES,spTech);

        b.put(CriterionName.GUEST_ANIMAL_ALLERGY,c1);
        b.put(CriterionName.HISTORY,c6);
        b.put(CriterionName.HOBBIES,culSc);
        
        c.put(CriterionName.GUEST_ANIMAL_ALLERGY,c2);
        c.put(CriterionName.HISTORY,c6);
        c.put(CriterionName.HOBBIES,scRea);

        x.put(CriterionName.HOST_HAS_ANIMAL,c4);
        x.put(CriterionName.HISTORY,c6);
        x.put(CriterionName.HOBBIES,culTech);

        y.put(CriterionName.HOST_HAS_ANIMAL,c3);
        y.put(CriterionName.HISTORY,c6);
        y.put(CriterionName.HOBBIES,scRea);
        
        z.put(CriterionName.HOST_HAS_ANIMAL,c4);
        z.put(CriterionName.HISTORY,c6);
        z.put(CriterionName.HOBBIES,tech);

        teen1=new Teenager("Matys", "Lepretre", 'M', LocalDate.now(), Country.FRANCE, map1);
        teen2=new Teenager("Paul", "Milleville", 'M', LocalDate.now(), Country.BRAZIL, map2);
        teen3=new Teenager("Julien", "Bouin", 'M', LocalDate.now(), Country.ITALY, map3);
        teen4=new Teenager("Fille", "pour de la diversité", 'F', LocalDate.now(), Country.FRANCE, map4);

        adonia = new Teenager("Adonia", "A", 'F', LocalDate.now(), Country.FRANCE, a);
        bellatrix= new Teenager("Bellatrix", "B", 'F', LocalDate.now(), Country.FRANCE, b);
        callista = new Teenager("Calista", "C", 'F', LocalDate.now(), Country.FRANCE, c);
        xolag= new Teenager("Xolag", "X", 'M', LocalDate.now(), Country.ITALY, x);
        yak = new Teenager("Yak", "Y", 'M', LocalDate.now(), Country.ITALY, y);
        zander= new Teenager("Zander", "Z", 'M', LocalDate.now(), Country.ITALY, z);
    }

    @Test
    void testWeightV1(){
        assertEquals(1099,AffectationUtil.weight(teen1, teen2, affect));
        assertEquals(1099,AffectationUtil.weight(teen1, teen3, affect));
        assertEquals(99,AffectationUtil.weight(teen1, teen4, affect));
        assertEquals(99,AffectationUtil.weight(teen2, teen1, affect));
        assertEquals(100,AffectationUtil.weight(teen2, teen3, affect));
        assertEquals(99,AffectationUtil.weight(teen2, teen4, affect));
        assertEquals(99,AffectationUtil.weight(teen3, teen1, affect));
        assertEquals(1100,AffectationUtil.weight(teen3, teen2, affect));
        assertEquals(100,AffectationUtil.weight(teen3, teen4, affect));
        assertEquals(99,AffectationUtil.weight(teen4, teen1, affect));
        assertEquals(99,AffectationUtil.weight(teen4, teen2, affect));
        assertEquals(100,AffectationUtil.weight(teen4, teen3, affect));

        assertEquals(99, AffectationUtil.weight(adonia, xolag, affect));
        assertEquals(100, AffectationUtil.weight(adonia, yak, affect));
        assertEquals(99, AffectationUtil.weight(adonia, zander, affect));

        assertEquals(99, AffectationUtil.weight(bellatrix, xolag, affect));
        assertEquals(1099, AffectationUtil.weight(bellatrix, yak, affect));
        assertEquals(100, AffectationUtil.weight(bellatrix, zander, affect));

        assertEquals(100, AffectationUtil.weight(callista, xolag, affect));
        assertEquals(98, AffectationUtil.weight(callista, yak, affect));
        assertEquals(100, AffectationUtil.weight(callista, zander, affect));
    }
}
