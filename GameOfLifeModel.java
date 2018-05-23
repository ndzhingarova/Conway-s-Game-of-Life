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
public class GameOfLifeModel {

    boolean[][] oldStage;
    CellModel[][] cells;

    public GameOfLifeModel() {
        oldStage = new boolean[50][50];
        cells = new CellModel[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                cells[i][j] = new CellModel();
            }
        }
    }

    public CellModel[][] getCells() {
        return cells;
    }

    public void setCells(CellModel[][] cells) {
        this.cells = cells;
    }
    
    

    public CellModel getSpecificCell(int x, int y){
        return cells[x][y];
    }

    private void saveOldStage() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                oldStage[i][j] = cells[i][j].isAlive();
            }
        }

    }

    private void calculateNextStage(int x, int y) {
        int aliveNeighbours = 0;
        if (validCell(x - 1, y - 1) && oldStage[x - 1][y - 1]) {
            aliveNeighbours++;
        }
        if (validCell(x - 1, y) && oldStage[x - 1][y]) {
            aliveNeighbours++;
        }
        if (validCell(x - 1, y + 1) && oldStage[x - 1][y + 1]) {
            aliveNeighbours++;
        }
        if (validCell(x + 1, y - 1) && oldStage[x + 1][y - 1]) {
            aliveNeighbours++;
        }
        if (validCell(x + 1, y) && oldStage[x + 1][y]) {
            aliveNeighbours++;
        }
        if (validCell(x + 1, y + 1) && oldStage[x + 1][y + 1]) {
            aliveNeighbours++;
        }
        if (validCell(x, y - 1) && oldStage[x][y - 1]) {
            aliveNeighbours++;
        }
        if (validCell(x, y + 1) && oldStage[x][y + 1]) {
            aliveNeighbours++;
        }

        boolean cellOldAliveStatus = oldStage[x][y];
        cells[x][y].setAlive(aliveForNextStage(cellOldAliveStatus, aliveNeighbours));
    }

    private boolean validCell(int x, int y) {
        return (x >= 0 && y >= 0 && x < 50 && y < 50);
    }

    private boolean aliveForNextStage(boolean alive, int a) {// receives current status of the cell and number of alive neighbours as argument, returns if the cell will be alive for next stage
        if (alive) {
            return (a == 2 || a == 3);
        } else {
            return a == 3;
        }
    }

    public void nextStep() {
        saveOldStage();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                calculateNextStage(i, j);
            }
        }
    }
}
