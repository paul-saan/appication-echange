package com.example;

public class CoupleTeen {
    private String firstname1;
    private String firstname2;
    private String country1;
    private String country2;
	private String name1;
    private String name2;
	private String ID1;
	private String ID2;


	public CoupleTeen(String firstname1, String firstname2, String country1, String country2,String name1,String name2,String ID1,String ID2) {
		this.firstname1 = firstname1;
		this.firstname2 = firstname2;
		this.country1 = country1;
		this.country2 = country2;
		this.name1=name1;
		this.name2=name2;
		this.ID1=ID1;
		this.ID2=ID2;
	}
    
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getFirstname1() {
		return firstname1;
	}

	public String getID1() {
		return ID1;
	}

	public void setID1(String iD1) {
		ID1 = iD1;
	}

	public String getID2() {
		return ID2;
	}

	public void setID2(String iD2) {
		ID2 = iD2;
	}

	public void setFirstname1(String firstname1) {
		this.firstname1 = firstname1;
	}

	public String getFirstname2() {
		return firstname2;
	}

	public void setFirstname2(String firstname2) {
		this.firstname2 = firstname2;
	}

	public String getCountry1() {
		return country1;
	}

	public void setCountry1(String country1) {
		this.country1 = country1;
	}

	public String getCountry2() {
		return country2;
	}

	public void setCountry2(String country2) {
		this.country2 = country2;
	}

    

    
}
