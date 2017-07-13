package com.example.gandh.hw4_amar;

import android.view.View;

import java.io.Serializable;

/**
 * Created by gandh on 2/10/2017.
 */

public class Choices implements Serializable {

    String Choice[];

    public String getSanswer() {
        return sanswer;
    }

    public void setSanswer(String sanswer) {
        this.sanswer = sanswer;
    }

    int selected;
    int answer;
    String sanswer;

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String[] getChoice() {
        return Choice;
    }

    public void setChoice(String[] choice) {
        Choice = choice;
    }
}
