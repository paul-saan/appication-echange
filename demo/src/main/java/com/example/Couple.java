package com.example;

public class Couple {
    private Teenager first;
    private Teenager second;
    private double weight;

    public Couple(Teenager first,Teenager second, History affect){
        this.first = first;
        this.second = second;
        this.weight = AffectationUtil.weight(first, second, affect);
    }

    public Teenager getFirst() {
        return first;
    }

    public void setFirst(Teenager first) {
        this.first = first;
    }

    public Teenager getSecond() {
        return second;
    }

    public void setSecond(Teenager second) {
        this.second = second;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    

}
