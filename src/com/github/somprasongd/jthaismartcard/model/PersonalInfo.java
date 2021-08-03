/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.somprasongd.jthaismartcard.model;

import com.github.somprasongd.jthaismartcard.util.DateUtils;
import static com.github.somprasongd.jthaismartcard.util.ImageUtils.changeImageScale;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sompr
 */
public class PersonalInfo {

    private String version;
    private String pid;
    private Name thaiName;
    private Name engName;
    private String bod;
    private String sex;
    private String cardNumberOrRequest;
    private String cardOwner;
    private String issuerCode;
    private String dateOfIssue;
    private String dateOfExpiry;
    private String cardTtype;
    private byte[] facesImage;
    private Address address;
    private String facesImageId;
    private byte[] officerSignatureImage;
    private boolean oldCard = false;
    private NhsoInfo nhsoAppletInfo;
    private String cid;
    private String atr;
    private int cardType = 20;
    public final int CARD_UNKNOWN = 20;
    public final int CARD_ST_DP66_B = 24;
    public final int CARD_JCOP_31 = 26;

    public Address getAddress() {
        return address;
    }

    public String getFormattedDateOfExpiry() {
        if (this.dateOfExpiry != null) {
            return DateUtils.yyyymmddToFormattedFullThaiDate(this.dateOfExpiry);
        }
        return "";
    }

    public String getFormattedDateOfIssue() {
        if (this.dateOfIssue != null) {
            return DateUtils.yyyymmddToFormattedFullThaiDate(this.dateOfIssue);
        }
        return "";
    }

    public String getSexDesc() {
        if (this.sex.equals("1")) {
            return "ªÒÂ";
        }
        if (this.sex.equals("2")) {
            return "Ë­Ô§";
        }
        return "";
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBod() {
        return this.bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getFormattedBod() {
        if (this.bod != null) {
            return DateUtils.yyyymmddToFormattedFullThaiDate(this.bod);
        }
        return "";
    }

    public String getCardNumberOrRequest() {
        return this.cardNumberOrRequest;
    }

    public void setCardNumberOrRequest(String cardNumberOrRequest) {
        this.cardNumberOrRequest = cardNumberOrRequest;
    }

    public String getCardOwner() {
        return this.cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardTtype() {
        return this.cardTtype;
    }

    public void setCardTtype(String cardTtype) {
        this.cardTtype = cardTtype;
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

    public Name getEngName() {
        return this.engName;
    }

    public void setEngName(Name engName) {
        this.engName = engName;
    }

    public byte[] getFacesImage() {
        return facesImage;
    }

    public void setFacesImage(byte[] facesImage) {
        this.facesImage = facesImage;
    }

    public Image getFacesImage(int width, int heigth) throws IOException, Exception {
        if (this.facesImage == null) {
            return null;
        }
        BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(this.facesImage));
        Image image = ImageIO.read(changeImageScale(is, width, heigth));
        return image;
    }

    public String getFacesImageId() {
        return this.facesImageId;
    }

    public void setFacesImageId(String facesImageId) {
        this.facesImageId = facesImageId;
    }

    public String getIssuerCode() {
        return this.issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public byte[] getOfficerSignatureImage() {
        return this.officerSignatureImage;
    }

    public void setOfficerSignatureImage(byte[] officerSignatureImage) {
        this.officerSignatureImage = officerSignatureImage;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Name getThaiName() {
        return this.thaiName;
    }

    public void setThaiName(Name thaiName) {
        this.thaiName = thaiName;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isOldCard() {
        return this.oldCard;
    }

    public void setOldCard(boolean oldCard) {
        this.oldCard = oldCard;
    }

    public NhsoInfo getNhsoInfo() {
        return this.nhsoAppletInfo;
    }

    public void setNhsoInfo(NhsoInfo nhsoInfo) {
        this.nhsoAppletInfo = nhsoInfo;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getCardType() {
        return this.cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getAtr() {
        return this.atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }

    public int getAgeYear() {
        return DateUtils.getAgeYear(getBod());
    }

    public int getAgeMonth() {
        return DateUtils.getAgeMonth(getBod());
    }

    @Override
    public String toString() {
        return thaiName.toString() + " " + pid;
    }
}
