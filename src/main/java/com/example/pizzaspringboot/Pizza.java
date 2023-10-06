package com.example.pizzaspringboot;

public class Pizza {
    int id;
    String dressing;

    public Pizza(int id, String dressing) {
        this.id = id;
        this.dressing = dressing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDressing() {
        return dressing;
    }

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }
}
