import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAffetationVersion2 {
    Teenager p,s,m,h,k,f,l,e;
    History history = new History();
    
    @BeforeEach
    void initialisation(){
        p = new Teenager("Patrice", "P", 'M', LocalDate.now(), Country.BELGIUM);
        s = new Teenager("Sofia", "S", 'F', LocalDate.now(), Country.BELGIUM);
        m = new Teenager("Mila", "M", 'F', LocalDate.now(), Country.BELGIUM);
        h = new Teenager("Henry", "H", 'M', LocalDate.now(), Country.BELGIUM);
        k = new Teenager("Klein", "K", 'M', LocalDate.now(), Country.GERMANY);
        f = new Teenager("Frantz", "F", 'M', LocalDate.now(), Country.GERMANY);
        l = new Teenager("Ludwig", "L", 'M', LocalDate.now(), Country.GERMANY);
        e = new Teenager("Emily", "E", 'F', LocalDate.now(), Country.GERMANY);

        //Patrice
        p.addCriterion(CriterionName.HISTORY, "same");
        //SOFIA
        s.addCriterion(CriterionName.HISTORY, "same");
        //MILA
        m.addCriterion(CriterionName.HISTORY, "same");
        //HENRY
        h.addCriterion(CriterionName.HISTORY, "other");
        //KLEIN
        k.addCriterion(CriterionName.HISTORY, "other");
        //FRANTZ
        f.addCriterion(CriterionName.HISTORY, "same");
        //LUDWIG
        l.addCriterion(CriterionName.HISTORY, "other");
        //EMILY
        e.addCriterion(CriterionName.HISTORY, "");

        history.teenagerAffectation(p, f);
        history.teenagerAffectation(s, k);
        history.teenagerAffectation(m, e);
        history.teenagerAffectation(h, l);
    }

    @Test
    void testWeightV2Exemple1(){
        //Ici on met 1 de moins pour compenser le bonus d'affinité de l'age
        assertEquals(100-1,AffectationUtil.weight(p, k, history));
        assertEquals(1-1,AffectationUtil.weight(p, f, history));
        assertEquals(100-1,AffectationUtil.weight(p, l, history));
        assertEquals(100-1,AffectationUtil.weight(p, e, history));

        assertEquals(1100-1,AffectationUtil.weight(s, k, history));
        assertEquals(100-1,AffectationUtil.weight(s, f, history));
        assertEquals(100-1,AffectationUtil.weight(s, l, history));
        assertEquals(100-1,AffectationUtil.weight(s, e, history));

        assertEquals(100-1,AffectationUtil.weight(m, k, history));
        assertEquals(100-1,AffectationUtil.weight(m, f, history));
        assertEquals(100-1,AffectationUtil.weight(m, l, history));
        assertEquals(50-1,AffectationUtil.weight(m, e, history));

        assertEquals(100-1,AffectationUtil.weight(h, k, history));
        assertEquals(100-1,AffectationUtil.weight(h, f, history));
        assertEquals(1100-1,AffectationUtil.weight(h, l, history));
        assertEquals(100-1,AffectationUtil.weight(h, e, history));
        
        
    }

    @Test
    void testWeightV2Exemple2(){

        //Ici on ajoute les Hobbies de chaque Teenager
        p.addCriterion(CriterionName.HOBBIES, "culture,science");
        s.addCriterion(CriterionName.HOBBIES, "science,reading");
        m.addCriterion(CriterionName.HOBBIES, "culture,technologie");
        h.addCriterion(CriterionName.HOBBIES, "technologie");
        k.addCriterion(CriterionName.HOBBIES, "culture,technologie");
        f.addCriterion(CriterionName.HOBBIES, "science,reading");
        l.addCriterion(CriterionName.HOBBIES, "sport,technologie");
        e.addCriterion(CriterionName.HOBBIES, "culture,science");
        
        //Ici on met 1 de moins pour compenser le bonus d'affinité de l'age

        assertEquals(99-1,AffectationUtil.weight(p, k, history));
        assertEquals(0-1,AffectationUtil.weight(p, f, history));
        assertEquals(100-1,AffectationUtil.weight(p, l, history));
        assertEquals(98-1,AffectationUtil.weight(p, e, history));

        assertEquals(1100-1,AffectationUtil.weight(s, k, history));
        assertEquals(98-1,AffectationUtil.weight(s, f, history));
        assertEquals(100-1,AffectationUtil.weight(s, l, history));
        assertEquals(99-1,AffectationUtil.weight(s, e, history));

        assertEquals(98-1,AffectationUtil.weight(m, k, history));
        assertEquals(100-1,AffectationUtil.weight(m, f, history));
        assertEquals(99-1,AffectationUtil.weight(m, l, history));
        assertEquals(49-1,AffectationUtil.weight(m, e, history));

        assertEquals(99-1,AffectationUtil.weight(h, k, history));
        assertEquals(100-1,AffectationUtil.weight(h, f, history));
        assertEquals(1099-1,AffectationUtil.weight(h, l, history));
        assertEquals(100-1,AffectationUtil.weight(h, e, history));  
    }
}
