package isp.lab10.racedemo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.*;

public class CarRace {
    public static void main(String[] args) {
        // Step 1: Open the traffic manager window
        Semaphore initialSemaphore = new Semaphore();

        // Step 2: Initialize car configuration window layouts
        JFrame frame = new JFrame("Car Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocation(270, 100);

        CarPanel carPanel = new CarPanel();
        frame.add(carPanel);
        frame.setVisible(true);

        // Instantiate threads
        Car redCar = new Car("Red car", carPanel);
        Car blueCar = new Car("Blue car", carPanel);
        Car greenCar = new Car("Green car", carPanel);
        Car yellowCar = new Car("Yellow car", carPanel);
        RaceTimer masterTrackTimer = new RaceTimer();

        // Directly references your external PlaySound.java file
        PlaySound backgroundAudio = new PlaySound();

        try {
            // Exercise 3: Block execution operations until safety semaphore goes green
            initialSemaphore.waitForGreen();

            System.out.println("🚦 Green Light Active! Starting race engines...");

            // Exercise 4 & 6: Play soundtrack (using professor's playSound method) and start clock
            backgroundAudio.playSound();
            masterTrackTimer.start();

            // Execute track threads concurrently
            redCar.start();
            blueCar.start();
            greenCar.start();
            yellowCar.start();

            // Wait for car threads to finish crossing the line
            redCar.join();
            blueCar.join();
            greenCar.join();
            yellowCar.join();

            // Exercise 6: Kill active clocks once final completion tokens match constraints
            masterTrackTimer.stopTimer();
            masterTrackTimer.join();

            // Exercise 4: Shut down sound effects loops safely
            backgroundAudio.stopSound();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main setup coordination flow interrupted unexpectedly.");
        }
    }
}

class Car extends Thread {
    private String carName;
    private CarPanel carPanel;
    private int distanceTraveled = 0;
    private final int finishLine = 400;
    private long startTime;

    public Car(String carName, CarPanel carPanel) {
        this.carName = carName;
        this.carPanel = carPanel;
    }

    @Override
    public void run() {
        this.startTime = System.currentTimeMillis();
        Random rand = new Random();

        while (distanceTraveled < finishLine) {
            int speedDelta = rand.nextInt(10) + 1;
            distanceTraveled += speedDelta;

            carPanel.updateCarPosition(carName, distanceTraveled);

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.err.println(carName + " interrupted.");
                return;
            }
        }

        long totalDuration = System.currentTimeMillis() - startTime;
        // Exercise 5: Report to synchronized tracking engine
        carPanel.carFinished(carName, totalDuration);
    }
}

class CarPanel extends JPanel {
    private Map<String, Integer> carPositions = new HashMap<>();
    private Map<String, Color> carColors = new HashMap<>();
    private final List<String> standings = new ArrayList<>();
    private final Map<String, Long> durations = new HashMap<>();

    public CarPanel() {
        this.setLayout(null);

        carPositions.put("Red car", 0);
        carColors.put("Red car", Color.RED);

        carPositions.put("Blue car", 0);
        carColors.put("Blue car", Color.BLUE);

        carPositions.put("Green car", 0);
        carColors.put("Green car", Color.GREEN);

        carPositions.put("Yellow car", 0);
        carColors.put("Yellow car", Color.YELLOW);
    }

    public void updateCarPosition(String name, int xPosition) {
        carPositions.put(name, xPosition);
        repaint();
    }

    // Exercise 5: Synchronized token registry processing prevents data racing
    public synchronized void carFinished(String name, long raceDuration) {
        if (!standings.contains(name)) {
            standings.add(name);
            durations.put(name, raceDuration);
        }

        if (standings.size() == 4) {
            displayFinalStandings();
        }
    }

    private void displayFinalStandings() {
        StringBuilder sb = new StringBuilder("🏁 Race Concluded! Final Leaderboard Standings: 🏁\n\n");
        for (int i = 0; i < standings.size(); i++) {
            String car = standings.get(i);
            sb.append(String.format("%d. %s - Time: %.2f seconds\n",
                    (i + 1), car, (durations.get(car) / 1000.0)));
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Podium Metrics Results", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int yOffset = 40;

        for (Map.Entry<String, Integer> entry : carPositions.entrySet()) {
            String name = entry.getKey();
            int xPos = entry.getValue();

            g.setColor(Color.BLACK);
            g.drawString(name, 20, yOffset - 5);

            g.setColor(carColors.get(name));
            g.fillOval(20 + xPos, yOffset, 30, 30);

            g.setColor(Color.DARK_GRAY);
            g.drawLine(430, yOffset - 10, 430, yOffset + 35);

            yOffset += 90;
        }
    }
}

// Exercise 6: Timer Thread
class RaceTimer extends Thread {
    private long elapsedTime = 0;
    private boolean running = true;

    public void stopTimer() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(10);
                elapsedTime += 10;
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println("⏱️ Total track execution duration timer stopped at: " + elapsedTime + " ms");
    }
}