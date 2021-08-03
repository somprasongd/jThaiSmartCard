/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard.model;

/**
 *
 * @author sompr
 */
public class Name {

    private String title;
    private String firstName;
    private String midName;
    private String lastName;

    public Name() {
    }

    public Name(String title, String firstName, String midName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMidName() {
        return midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.title + this.firstName + (this.midName.isEmpty() ? "" : " " + this.midName) + " " + this.lastName;
    }
}
