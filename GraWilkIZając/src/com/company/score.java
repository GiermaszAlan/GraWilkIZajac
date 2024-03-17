package com.company;

import java.io.Serializable;

public class score implements Serializable {
    private String name;
    private String time;
    private int points;

    public score(String name, String time, int points){
        this.name = name;
        this.time = time;
        this.points = points;
    }

    @Override
    public String toString(){
        return "name: "+ name + " time: " + time + " points: " + points;
    }
}
