import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CriterionTest {
    private Criterion cnull,
                      c1_1,c1_2,c1_3,c1_4,c1_5,
                      c2_1,c2_2,c2_3,date1;
    
    @BeforeEach
    public void initialization(){ 
        cnull = new Criterion(null, null);
        c1_1 = new Criterion("yes",CriterionName.valueOf("GUEST_ANIMAL_ALLERGY"));
        c1_2 = new Criterion("no",CriterionName.valueOf("GUEST_ANIMAL_ALLERGY"));
        c1_3 = new Criterion("yes",null);
        c1_4 = new Criterion("no",null);
        c1_5 = new Criterion(null,CriterionName.valueOf("GUEST_ANIMAL_ALLERGY"));

        c2_1 = new Criterion("Criterion 123", CriterionName.valueOf("GUEST_FOOD"));
        c2_2 = new Criterion(null, CriterionName.valueOf("GUEST_FOOD"));
        c2_3 = new Criterion("Criterion 321", null);

      
    }

    @Test
    public void isValidTest(){
        assertFalse(cnull.isValid());
        assertTrue(c1_1.isValid());
        assertTrue(c1_2.isValid());
        assertFalse(c1_3.isValid());
        assertFalse(c1_4.isValid());
        assertFalse(c1_5.isValid());
        assertTrue(c2_1.isValid());
        assertFalse(c2_2.isValid());
        assertFalse(c2_3.isValid());
    }

    
}
