package com.vcu.groupr.relicrepository;

import java.io.Serializable;
import java.util.Objects;

//REQUIRED: artifact name. OPTIONAL: artifact type, description, age, location found, date found, price, URL for selling website
public class Artifact implements Serializable {
    private String name;
    private String type;
    private String description;
    private int age;
    private String location;
    private String date;
    private double price;
    private String url;

    public Artifact(){

    }

    public Artifact(String name, String type, String description, int age, String location, String date, double price, String url) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.age = age;
        this.location = location;
        this.date = date;
        this.price = price;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artifact artifact = (Artifact) o;
        return age == artifact.age &&
                Double.compare(artifact.price, price) == 0 &&
                Objects.equals(name, artifact.name) &&
                Objects.equals(type, artifact.type) &&
                Objects.equals(description, artifact.description) &&
                Objects.equals(location, artifact.location) &&
                Objects.equals(date, artifact.date) &&
                Objects.equals(url, artifact.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, description, age, location, date, price, url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
