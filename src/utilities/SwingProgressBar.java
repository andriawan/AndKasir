/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author andriawan
 */
public class SwingProgressBar extends JPanel{
    
    JProgressBar pbar;
    static final int MIN = 0;
    static final int MAX = 100;

    public SwingProgressBar() {
        pbar = new JProgressBar();
        pbar.setMinimum(MIN);
        pbar.setMaximum(MAX);
        add(pbar);
    }
    
    public void updateBar(int newValue){
        pbar.setValue(newValue);
    }
    
    public static void main(String[] args) {
        final SwingProgressBar it = new SwingProgressBar();
        JFrame fr = new JFrame("progress");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setContentPane(it);
        fr.pack();
        fr.setVisible(true);
        for (int i = MIN; i <= MAX; i++) {
            final int percent = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        it.updateBar(percent);
                    }
                });
                Thread.sleep(100);
            } catch (InterruptedException e) {
                ;
            }
        }
            
    }
}
            

