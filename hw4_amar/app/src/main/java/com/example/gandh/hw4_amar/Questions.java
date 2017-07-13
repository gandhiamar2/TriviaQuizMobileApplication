package com.example.gandh.hw4_amar;

import java.io.Serializable;

/**
 * Created by gandh on 2/10/2017.
 */

public class Questions implements Serializable{
    int id;
    String text, image;
    Choices choices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }
}
