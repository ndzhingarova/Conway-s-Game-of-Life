/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author nikoletad
 */

public class Cell extends JButton implements ActionListener{
    private boolean alive;
    private int value;

    public Cell() {
        alive = false;
        value = 0;
        setBackground(Color.WHITE);
        this.addActionListener(this);
        repaint();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean Alive) {
        this.alive = Alive;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    value++;
    value%=2;
    switch(value){
        case 0:
            alive = false;
            break;
        case 1:
            alive = true;
            break;
        default:
    }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        if(isAlive())   
            setBackground(Color.DARK_GRAY);
        else    
            setBackground(Color.WHITE);
    }
    
    
    
}
