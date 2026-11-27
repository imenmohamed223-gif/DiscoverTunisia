package com.example.discovertunisia;

public class Site {
    public String nom, note, description, categorie, type, lieu;
    public int image;
    public double lat, lng;
    public boolean favori;

    public Site(String nom, String note, String description, String categorie,
                String type, String lieu, int image, double lat, double lng) {
        this.nom = nom;
        this.note = note;
        this.description = description;
        this.categorie = categorie;
        this.type = type;
        this.lieu = lieu;
        this.image = image;
        this.lat = lat;
        this.lng = lng;
    }
}