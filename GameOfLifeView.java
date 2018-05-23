/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author nikoletad
 */
public class GameOfLifeView extends JFrame implements ActionListener {

    JPanel buts;
    JPanel grid;
    JButton jbStart;
    JButton jbStep;
    JButton jbClear;
    JButton jbStop;
    CellView gridbtns[][];
    Timer timer;
    GameOfLifeControler golc;

    public GameOfLifeView(GameOfLifeControler gol) {
        super("Game Of Life");
        this.golc = gol;
        buts = new JPanel();
        grid = new JPanel();
        jbStart = new JButton("Start");
        jbStep = new JButton("Step");
        jbClear = new JButton("Clear");
        jbStop = new JButton("Stop");
        gridbtns = new CellView[50][50];
        //timer = new Timer(250, this);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(700, 700));
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        grid.setLayout(new GridLayout(50, 50));
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                gridbtns[i][j] = new CellView(gol, i, j);
                gridbtns[i][j].setPreferredSize(new Dimension(5, 5));
                grid.add(gridbtns[i][j]);
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
        String btnsrc = e.getActionCommand();
        switch (btnsrc) {
            case "start":
                jbStart.setVisible(false);
                jbStop.setVisible(true);
                int delay = 250; //milliseconds
                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        golc.loadNextStep();
                        repaint();
                    }
                };
                timer = new Timer(delay, taskPerformer);
                timer.start();
                break;
            case "stop":
                jbStop.setVisible(false);
                jbStart.setVisible(true);
                timer.stop();
                break;
            case "step":
                if (timer != null && timer.isRunning()) {
                    jbStop.setVisible(false);
                    jbStart.setVisible(true);
                    timer.stop();
                }
                golc.loadNextStep();
                repaint();
                break;
            case "clear":
                if (timer != null && timer.isRunning()) {
                    jbStop.setVisible(false);
                    jbStart.setVisible(true);
                    timer.stop();
                }
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        if (golc.accesCell(i, j).isAlive()) {
                            golc.accesCell(i, j).setAlive(false);
                        }
                    }
                }
                break;
            default:
                break;
        }
        this.repaint();
    }
}
