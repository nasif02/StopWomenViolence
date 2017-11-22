package com.ewu.bug.swv.model;

import java.io.Serializable;

/**
 * Created by Xplo on 12/23/2015.
 */
public class Contact implements Serializable {

    private String name;
    private String number;

    public Contact() {
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
