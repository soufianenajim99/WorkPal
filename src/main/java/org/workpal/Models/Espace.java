package org.workpal.Models;

public class Espace {
    private int id;
    private String name;
    private String location;

    public Espace(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Espace() {
    }

    public Espace(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Espace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
