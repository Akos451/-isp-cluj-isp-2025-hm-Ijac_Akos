package isp.lab10.racedemo;

import java.awt.*;
import javax.swing.*;

public class Semaphore extends JFrame {
    private SemaphorePanel semaphorePanel;
    private SemaphoreThread semaphoreThread;

    public Semaphore() {
        this.setTitle("Semaphore");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 500);
        this.setLocation(50, 100);

        semaphorePanel = new SemaphorePanel();
        this.getContentPane().add(semaphorePanel);
        this.pack();
        this.setSize(200, 500);
        this.setVisible(true);

        // Exercise 1: Create and start the custom semaphore thread
        semaphoreThread = new SemaphoreThread(semaphorePanel);
        semaphoreThread.start();
    }

    // Exercise 3: Allows the main race to wait until this thread turns green
    public void waitForGreen() throws InterruptedException {
        if (semaphoreThread != null) {
            semaphoreThread.join();
        }
    }

    public static void main(String[] args) {
        new Semaphore();
    }
}

class SemaphorePanel extends JPanel {
    private Color circleColor = Color.GRAY;

    public void setColor(Color color) {
        this.circleColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(circleColor);
        int diameter = Math.min(getWidth(), getHeight()) - 40;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        g.fillOval(x, y, diameter, diameter);
    }
}

class SemaphoreThread extends Thread {
    private SemaphorePanel semaphorePanel;

    public SemaphoreThread(SemaphorePanel semaphorePanel) {
        this.semaphorePanel = semaphorePanel;
    }

    @Override
    public void run() {
        try {
            // Red Phase
            semaphorePanel.setColor(Color.RED);
            Thread.sleep(2000);

            // Yellow Phase
            semaphorePanel.setColor(Color.YELLOW);
            Thread.sleep(1000);

            // Green Phase
            semaphorePanel.setColor(Color.GREEN);
        } catch (InterruptedException e) {
            System.err.println("Semaphore thread interrupted.");
        }
    }
}