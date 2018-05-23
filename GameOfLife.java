/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author nikoletad
 */
public class GameOfLife extends JFrame implements ActionListener {

    boolean[][] oldStage = new boolean[50][50];
    JPanel buts = new JPanel();
    JPanel grid = new JPanel();
    Cell[][] cells = new Cell[50][50];
    JButton jbStart = new JButton("Start");
    JButton jbStep = new JButton("Step");
    JButton jbClear = new JButton("Clear");
    JButton jbStop = new JButton("Stop");
    Timer timer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameOfLife a = new GameOfLife();
    }

    public GameOfLife() {
        super("Game of Life");
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        grid.setLayout(new GridLayout(50, 50));
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setSize(5, 5);
                grid.add(cells[i][j]);
            }
        }
        jbStart.addActionListener(this);
        jbStart.setActionCommand("start");
        jbStep.addActionListener(this);
        jbStep.setActionCommand("step");
        jbClear.addActionListener(this);
        jbClear.setActionCommand("clear");
        jbStop.addActionListener(this);
        jbStop.setActionCommand("stop");
        jbStop.setVisible(false);
        buts.add(jbStart);
        buts.add(jbStop);
        buts.add(jbStep);
        buts.add(jbClear);
        add(buts, BorderLayout.SOUTH);
        add(grid);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("start".equals(e.getActionCommand())) {
            jbStart.setVisible(false);
            jbStop.setVisible(true);
            int delay = 250; //milliseconds
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    saveOldStage();
                    for (int i = 0; i < 50; i++) {
                        for (int j = 0; j < 50; j++) {
                            calculateNextStage(i, j);
                        }
                    }
                    repaint();
                }
            };
            timer = new Timer(delay, taskPerformer);
            timer.start();
        } else if ("stop".equals(e.getActionCommand())) {
            jbStop.setVisible(false);
            jbStart.setVisible(true);
            timer.stop();
        } else if ("step".equals(e.getActionCommand())) {
            if (timer.isRunning()) {
                jbStop.setVisible(false);
                jbStart.setVisible(true);
                timer.stop();
            }
            saveOldStage();
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    calculateNextStage(i, j);
                }
            }
        } else if ("clear".equals(e.getActionCommand())) {
            if (timer.isRunning()) {
                jbStop.setVisible(false);
                jbStart.setVisible(true);
                timer.stop();
            }
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (cells[i][j].isAlive()) {
                        cells[i][j].setAlive(false);
                    }
                }
            }
        }
        this.repaint();
    }

    public void saveOldStage() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                oldStage[i][j] = cells[i][j].isAlive();
            }
        }

    }

    public void calculateNextStage(int x, int y) {
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

    public boolean validCell(int x, int y) {
        return (x >= 0 && y >= 0 && x < 50 && y < 50);
    }

    public boolean aliveForNextStage(boolean alive, int a) {// receives current status of the cell and number of alive neighbours as argument, returns if the cell will be alive for next stage
        if (alive) {
            return (a == 2 || a == 3);
        } else {
            return a == 3;
        }
    }
}
