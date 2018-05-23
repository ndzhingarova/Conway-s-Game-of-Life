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
public class CellModel {

    private boolean alive;

    public CellModel() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean Alive) {
        this.alive = Alive;
    }
}
