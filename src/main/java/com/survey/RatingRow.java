/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Sekwa
 */
public class RatingRow extends JPanel {
    private String question;
    private ButtonGroup group;
    private JRadioButton[] options;
    
    public RatingRow(String question) {
        this.question = question;

        // Panel layout: horizontal
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(new JLabel(question));
        
        //Radio button group of 5
        group = new ButtonGroup();
        options = new JRadioButton[5];

        for (int i = 0; i < 5; i++) {
            options[i] = new JRadioButton(String.valueOf(i + 1));
            group.add(options[i]);
            this.add(options[i]);
        }
    }
    
    //Returns selected rating
    public int getSelectedRating() {
        for (int i = 0; i < 5; i++) {
            if (options[i].isSelected()) {
                return i + 1;
            }
        }
        return -1;  //No selection made
    }

    public String getQuestion() {
        return question;
    }

}
