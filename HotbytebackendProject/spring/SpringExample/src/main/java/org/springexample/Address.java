package org.springexample;

public class Address {

    int hno;
    String city;
    String pcode;

    Address()
    {


    }

    public Address(int hno, String city, String pcode) {
        super();
        this.hno = hno;
        this.city = city;
        this.pcode = pcode;
    }

    public int getHno() {
        return hno;
    }

    public void setHno(int hno) {
        this.hno = hno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    @Override
    public String toString() {
        return "Address [hno=" + hno + ", city=" + city + ", pcode=" + pcode + "]";
    }






}
