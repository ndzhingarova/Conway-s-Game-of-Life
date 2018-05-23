/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author nikoletad
 */
public class MVCmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GameOfLifeModel g = new GameOfLifeModel();
        GameOfLifeControler c = new GameOfLifeControler(g);
        GameOfLifeView v = new GameOfLifeView(c);
    }

}
