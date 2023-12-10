import java.time.LocalDate;
import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class AffectationUtil {
    
    /** Calcule le poids de l’arête entre host et visitor.
     * Poids a une base de 100
    * Si non compatible alors poids -1000
    * On ajoute le nombre de Hobbies en commun
    * Si Bonus d'age alors -1
    * Si Bonus de Gender -1 sinon +1 si contrainte non respecté
    * Modifie avec l'historique fourni en parametre (voir fonction (Teenager)history)
    * @param host un Teenager
    * @param visitor un Teenager
    * @param affect de type Affectation
    * @return poids pour l'association de host et de teenager.
    */
    public static double weight (Teenager host, Teenager visitor, History affect) {
        double weight=100;
        if(!host.compatibleWithGuest(visitor)){
            weight+=1000;
        }
        weight -= host.nbHobbies(visitor);
        weight += affect.history(visitor, host);
        if(host.affinityWithAge(visitor)){
            weight-=1;
        }
        if( visitor.getRequirements() != null){
            if(visitor.getRequirements().get(CriterionName.GENDER) != null){
                if(visitor.getRequirements().get(CriterionName.GENDER).getValue().equals(String.valueOf(host.getGender()))){
                    weight-=1;
                }
                else{
                    weight+=1;
                }
            } 
        }
        if( host.getRequirements() != null){
            if(host.getRequirements().get(CriterionName.GENDER) != null){
                if(host.getRequirements().get(CriterionName.GENDER).getValue().equals(String.valueOf(visitor.getGender()))){
                    weight-=1;
                }
                else{
                    weight+=1;
                }
            }  
        }
            
        return weight;
    }

    /**
     * Méthode de création de Graphe à l'aide d'une ArrayList de Teenager représentant les invité et une autre reptrésentant les hotes avec un historique d'échange
     * @param guest
     * @param host
     * @param affect
     * @return
     */
    public static GrapheNonOrienteValue<Teenager> creationGraphe(ArrayList<Teenager> guest, ArrayList<Teenager> host, History affect){
        ArrayList<Couple> liaisions = new ArrayList<>();
        for (Teenager teenager : guest) {
            for (Teenager teenager2 : host) {
                liaisions.add(new Couple(teenager, teenager2, affect));
            }
        }
        GrapheNonOrienteValue<Teenager> graphe = new GrapheNonOrienteValue<Teenager>();
        for (Couple liaison : liaisions) {
            graphe.ajouterSommet(liaison.getFirst());
            graphe.ajouterSommet(liaison.getSecond());
            graphe.ajouterArete(liaison.getFirst(), liaison.getSecond(), liaison.getWeight());
        }
        return graphe;
    }

/*    public static void main(String[] args) {
        HashMap<CriterionName,Criterion> a = new HashMap<>();
        HashMap<CriterionName,Criterion> b = new HashMap<>();
        HashMap<CriterionName,Criterion> c = new HashMap<>();
        HashMap<CriterionName,Criterion> x = new HashMap<>();
        HashMap<CriterionName,Criterion> y = new HashMap<>();
        HashMap<CriterionName,Criterion> z = new HashMap<>();
        Criterion c1=new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion c2=new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion c3=new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion c4=new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion c5=new Criterion("same", CriterionName.HISTORY);
        Criterion c6=new Criterion("other", CriterionName.HISTORY);
        Criterion c7=new Criterion("nonuts", CriterionName.HOST_FOOD);
        Criterion c8=new Criterion("nonuts", CriterionName.GUEST_FOOD);
        Criterion c9=new Criterion("vegetarian", CriterionName.HOST_FOOD);
        Criterion c10=new Criterion("vegetarian", CriterionName.GUEST_FOOD);
        Criterion spTech= new Criterion("sport,technologie", CriterionName.HOBBIES);
        Criterion culSc= new Criterion("culture,science", CriterionName.HOBBIES);
        Criterion scRea= new Criterion("science,reading", CriterionName.HOBBIES);
        Criterion culTech= new Criterion("culture,technologie", CriterionName.HOBBIES);
        Criterion tech= new Criterion("technologie", CriterionName.HOBBIES);

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

        ArrayList<Teenager> guest = new ArrayList<>();
        ArrayList<Teenager> host = new ArrayList<>();
        guest.add(new Teenager("Adonia", "A", 'F', LocalDate.now(), Country.FRANCE, a));
        guest.add(new Teenager("Bellatrix", "B", 'F', LocalDate.now(), Country.FRANCE, b));
        guest.add(new Teenager("Calista", "C", 'F', LocalDate.now(), Country.FRANCE, c));
        host.add(new Teenager("Xolag", "X", 'M', LocalDate.now(), Country.ITALY, x));
        host.add(new Teenager("Yak", "Y", 'M', LocalDate.now(), Country.ITALY, y));
        host.add(new Teenager("Zander", "Z", 'M', LocalDate.now(), Country.ITALY, z));
        GrapheNonOrienteValue<Teenager> graphe = creationGraphe(guest,host);
        CalculAffectation<Teenager> ca = new CalculAffectation<>(graphe, guest, host);
        System.out.println(ca.calculerAffectation());

    }*/



    public static void main(String[] args) {
        History history = new History();
        ArrayList<Teenager> guest = new ArrayList<>();
        ArrayList<Teenager> host = new ArrayList<>();

        Teenager p = new Teenager("Patrice", "P", 'M', LocalDate.now(), Country.BELGIUM);
        Teenager s = new Teenager("Sofia", "S", 'F', LocalDate.now(), Country.BELGIUM);
        Teenager m = new Teenager("Mila", "M", 'F', LocalDate.now(), Country.BELGIUM);
        Teenager h = new Teenager("Henry", "H", 'M', LocalDate.now(), Country.BELGIUM);
        Teenager k = new Teenager("Klein", "K", 'M', LocalDate.now(), Country.GERMANY);
        Teenager f = new Teenager("Frantz", "F", 'M', LocalDate.now(), Country.GERMANY);
        Teenager l = new Teenager("Ludwig", "L", 'M', LocalDate.now(), Country.GERMANY);
        Teenager e = new Teenager("Emily", "E", 'F', LocalDate.now(), Country.GERMANY);

        p.addCriterion(CriterionName.HISTORY, "same");
        s.addCriterion(CriterionName.HISTORY, "same");
        m.addCriterion(CriterionName.HISTORY, "same");
        h.addCriterion(CriterionName.HISTORY, "other");
        k.addCriterion(CriterionName.HISTORY, "other");
        f.addCriterion(CriterionName.HISTORY, "same");
        l.addCriterion(CriterionName.HISTORY, "other");
        e.addCriterion(CriterionName.HISTORY, "");

        history.teenagerAffectation(p, f);
        history.teenagerAffectation(s, k);
        history.teenagerAffectation(m, e);
        history.teenagerAffectation(h, l);

        guest.add(p);
        guest.add(s);
        guest.add(m);
        guest.add(h);
        host.add(f);
        host.add(k);
        host.add(e);
        host.add(l);

        GrapheNonOrienteValue<Teenager> graphe = creationGraphe(guest,host, history);
        CalculAffectation<Teenager> ca = new CalculAffectation<>(graphe, guest, host);
        System.out.println(ca.calculerAffectation());
    }
}