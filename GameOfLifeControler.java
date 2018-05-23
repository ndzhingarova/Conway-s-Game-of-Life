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
public class GameOfLifeControler {
    
    GameOfLifeModel m;

    public GameOfLifeControler(GameOfLifeModel m) {
        this.m = m;
    }
    
    public void loadNextStep(){
        m.nextStep();
    }
    
    public CellModel accesCell(int x, int y){
        return m.getSpecificCell(x, y);
    }
    
    
}
