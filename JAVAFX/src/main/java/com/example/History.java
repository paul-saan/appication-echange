package com.example;

import java.util.HashMap;
import java.util.Map;

public class History {
    private Map<Teenager,Teenager> tab_affectation;

    public History(){
        this.tab_affectation = new HashMap<Teenager,Teenager>();
    }

    public Map<Teenager, Teenager> getTab_affectation() {
        return tab_affectation;
    }

    @Override
    public String toString() {
        return "Historique [tab_affectation=" + tab_affectation + "]";
    }

    public Teenager getCurrentTeenager(Teenager t){
       return this.tab_affectation.get(t);
    }

    public void teenagerAffectation(Teenager t1,Teenager t2){
        this.tab_affectation.put(t1, t2);
    }

    public void teenagerDesAffectation(Teenager t){
        this.tab_affectation.remove(t);
    }

    public boolean isAffecter(Teenager t){
        return this.tab_affectation.containsKey(t);
    }

    /**
     * Permet de savoir ce que l'on doit enlever en fonction des containte posé d'historique
     * Si dans l'historique il y a eu un antécédent entre les deux :
     *  - 1000 si l'un des deux à la motion other
     *  - -99 si les deux on same
     *  - -50 si uniquement un des deux à same
     * Sinon on renvois par défault 0 
     * @param visitor
     * @param host
     * @return
     */
    public int history(Teenager visitor, Teenager host){
        if(this.isAffecter(host)){
            if(this.getCurrentTeenager(host).equals(visitor)){
                if(visitor.equalsCriterion(CriterionName.HISTORY, "other") || host.equalsCriterion(CriterionName.HISTORY, "other")){
                    return 1000;
                }
                if(visitor.equalsCriterion(CriterionName.HISTORY, "same") && host.equalsCriterion(CriterionName.HISTORY, "same")){
                    return -99;
                }
                if(visitor.equalsCriterion(CriterionName.HISTORY, "same") || host.equalsCriterion(CriterionName.HISTORY, "same")){
                    return -50;
                }
            }
        }
        return 0;
    }


}
