/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard;

import com.github.somprasongd.jthaismartcard.model.NhsoInfo;
import com.github.somprasongd.jthaismartcard.model.PersonalInfo;

/**
 *
 * @author sompr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        jThaiSmartcard ts = new jThaiSmartcard();
        PersonalInfo moi = ts.read();
        if (moi != null) {
            System.out.println(moi.getThaiName());
            System.out.println(moi.getEngName());
            System.out.println(moi.getAddress());
            System.out.println(moi.getDateOfIssue());
            System.out.println(moi.getDateOfExpiry());
        }
        NhsoInfo nhso = moi.getNhsoInfo();
        if (nhso != null) {
            System.out.println(nhso.getMainRights());
            System.out.println(nhso.getMainHospitalsName());
        }
    }
}
