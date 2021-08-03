/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard.model;

import com.github.somprasongd.jthaismartcard.util.DateUtils;

/**
 *
 * @author sompr
 */
public class NhsoInfo {

    private String version;
    private String mainRights;
    private String subRights;
    private String mainHospitalsName;
    private String subHospitalsName;
    private String paidType;
    private String dateOfIssue;
    private String dateOfExpiry;
    private String updateDate;
    private String changeHospitalAmount;

    public String getChangeHospitalAmount() {
        return this.changeHospitalAmount;
    }

    public String getFormattedDateOfExpirey() {
        if ((this.dateOfExpiry != null) && (!this.dateOfExpiry.trim().isEmpty())) {
            return DateUtils.yyyymmddToFormattedFullThaiDateOrEmpty(this.dateOfExpiry);
        }
        return "";
    }

    public String getFormattedDateOfIssue() {
        if ((this.dateOfIssue != null) && (!this.dateOfIssue.trim().isEmpty())) {
            return DateUtils.yyyymmddToFormattedFullThaiDateOrEmpty(this.dateOfIssue);
        }
        return "";
    }

    public String getFormattedUpdateDate() {
        if ((this.updateDate != null) && (!this.updateDate.trim().isEmpty())) {
            return DateUtils.yyyymmddToFormattedFullThaiDateOrEmpty(this.updateDate);
        }
        return "";
    }

    public void setChangeHospitalAmount(String changeHospitalAmount) {
        this.changeHospitalAmount = changeHospitalAmount;
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getDateOfIssue() {
        return this.dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getMainHospitalsName() {
        return this.mainHospitalsName;
    }

    public void setMainHospitalsName(String mainHospitalsName) {
        this.mainHospitalsName = mainHospitalsName;
    }

    public String getMainRights() {
        return this.mainRights;
    }

    public void setMainRights(String mainRights) {
        this.mainRights = mainRights;
    }

    public String getPaidType() {
        return this.paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public String getSubHospitalsName() {
        return this.subHospitalsName;
    }

    public void setSubHospitalsName(String subHospitalsName) {
        this.subHospitalsName = subHospitalsName;
    }

    public String getSubRights() {
        return this.subRights;
    }

    public void setSubRights(String subRights) {
        this.subRights = subRights;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return mainRights + " " + mainHospitalsName;
    }
}
