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
public class CellView extends JButton implements ActionListener {

    int x;
    int y;
    GameOfLifeControler golc;
    
    public CellView(GameOfLifeControler g, int x, int y) {
        golc = g;
        this.x = x;
        this.y = y;
        this.addActionListener(this);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        golc.accesCell(x, y).setAlive(!golc.accesCell(x, y).isAlive());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (golc.accesCell(x, y).isAlive()) {
            setBackground(Color.DARK_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
    }
}
