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
public class Address {

    private String houseNo;
    private String moo;
    private String soi;
    private String road;
    private String subdistrict;
    private String district;
    private String province;

    private String address;

    public Address() {
    }

    public Address(String houseNo, String moo, String soi, String road, String subdistrict, String district, String province) {
        this.houseNo = houseNo;
        this.moo = moo;
        this.soi = soi;
        this.road = road;
        this.subdistrict = subdistrict;
        this.district = district;
        this.province = province;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getMoo() {
        return moo;
    }

    public void setMoo(String moo) {
        this.moo = moo;
    }

    public String getSoi() {
        return soi;
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }
}
