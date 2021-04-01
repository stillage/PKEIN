package com.example.pkein;

import java.util.Date;

class uploadinfo {
    public String imageName;
    public String imageURL;
    public String imageAlamat;
    public String imageTanggal;
    public String imageEmail;
    public String imageDetail;

    public uploadinfo() {
    }

    public uploadinfo(String imageName, String imageAlamat, String imageTanggal, String imageEmail,String imageDetail,String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
        this.imageAlamat = imageAlamat;
        this.imageTanggal = imageTanggal;
        this.imageEmail = imageEmail;
        this.imageDetail = imageDetail;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageAlamat() {
        return imageAlamat;
    }

    public void setImageAlamat(String imageAlamat) {
        this.imageAlamat = imageAlamat;
    }

    public String getImageTanggal() {
        return imageTanggal;
    }

    public void setImageTanggal(String imageTanggal) {
        this.imageTanggal = imageTanggal;
    }

    public String getImageEmail() {
        return imageEmail;
    }

    public void setImageEmail(String imageEmail) {
        this.imageEmail = imageEmail;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
