import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is a class
 * Created 2021-10-18
 *
 * @author Magnus Silverdal
 */
public class Controller implements Runnable, ActionListener {
    private Thread thread;
    private boolean running = false;
    private int fps = 60;
    private int ups = 60;
    private int width = 1024;
    private int height = 512;
    private int scale = 4;
    private double magnitude = 1;
    private JFrame frame;
    private String title = "Game of life";
    private ScreenRenderer view;
    private SimulationModel model;

    public Controller() {
        view = new ScreenRenderer(width, height, scale);
        model = new SimulationModel(width, height, scale);
        model.randomizeCellGrid();
        // Frame data
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.add(view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();

    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double nsFPS = 1000000000.0 / fps;
        double nsUPS = 1000000000.0 / ups;
        double deltaFPS = 0;
        double deltaUPS = 0;
        int frames = 0;
        int updates = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            deltaFPS += (now - lastTime) / nsFPS;
            deltaUPS += (now - lastTime) / nsUPS;
            lastTime = now;

            while(deltaUPS >= 1) {
                //System.out.println(model.getCellArray().get(0).getX());
                model.update(magnitude);
                view.clear();
                ArrayList<cell> array = model.getCellArray();
                view.draw(array);
                //System.out.println((int) Math.floor(array.get(0).getX()));

                updates++;
                deltaUPS--;
            }

            while (deltaFPS >= 1) {
                view.render();
                frames++;
                deltaFPS--;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(this.title + "  |  " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}
