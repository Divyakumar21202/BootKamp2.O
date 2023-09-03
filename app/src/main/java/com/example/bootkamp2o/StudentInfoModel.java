package com.example.bootkamp2o;

public class StudentInfoModel {

    StudentInfoModel(){

    }
    String ID;
    String Images;
    String NAME;

    public StudentInfoModel(String ID, String images, String NAME, String ABOUT) {
        this.ID = ID;
        Images = images;
        this.NAME = NAME;
        this.ABOUT = ABOUT;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setImages(String images) {
        Images = images;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setABOUT(String ABOUT) {
        this.ABOUT = ABOUT;
    }

    public String getImages() {
        return Images;
    }

    String ABOUT;

    public String getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getABOUT() {
        return ABOUT;
    }



}
