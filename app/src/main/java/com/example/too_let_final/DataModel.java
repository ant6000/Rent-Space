package com.example.too_let_final;

public class DataModel {

    String bath,bed,description,dining,imageUrl,kitchen,location,rent;

    public DataModel() {
    }

    public DataModel(String bath, String bed, String description, String dining, String imageUrl, String kitchen, String location, String rent) {
        this.bath = bath;
        this.bed = bed;
        this.description = description;
        this.dining = dining;
        this.imageUrl = imageUrl;
        this.kitchen = kitchen;
        this.location = location;
        this.rent = rent;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }
}
