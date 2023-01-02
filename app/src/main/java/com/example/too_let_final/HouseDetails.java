package com.example.too_let_final;

public class HouseDetails {
    String bed,bath,kitchen,dining,rent,description,location,imageUrl;

    public HouseDetails() {
    }

    public HouseDetails(String bed, String bath, String kitchen, String dining, String rent, String description, String location, String imageUrl) {
        this.bed = bed;
        this.bath = bath;
        this.kitchen = kitchen;
        this.dining = dining;
        this.rent = rent;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
