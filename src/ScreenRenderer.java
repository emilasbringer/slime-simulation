import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

/**
 * This is a class
 * Created 2021-10-18
 *
 * @author Magnus Silverdal
 */

/**
 * A system for drawing pixelgraphics to the screen using native Java.
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public class ScreenRenderer extends Canvas {
    private int WIDTH;
    private int HEIGHT;
    private int scale;

    private Screen screen;
    private BufferedImage image;
    private SimulationModel model;

    public ScreenRenderer(int width, int height, int scale) {
        // Screen data
        this.WIDTH = width;
        this.HEIGHT = height;
        this.scale = scale;
        image = new BufferedImage(WIDTH/scale, HEIGHT/scale, BufferedImage.TYPE_INT_RGB);
        screen = new Screen(((DataBufferInt) image.getRaster().getDataBuffer()).getData(),image.getWidth(), image.getHeight());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
    }

    public void draw(ArrayList<cell> array) {
        for (int i = 0; i < array.size(); i++) {
            screen.draw( (int) Math.floor(array.get(i).getX()),(int) Math.floor(array.get(i).getY()),0xFFFFFF);
        }
    }

    public void clear() {
        for (int x = 0; x < WIDTH/scale; x++) {
            for (int y = 0; y < HEIGHT/scale; y++) {
                screen.draw(x,y,0x000000);
            }
        }
    }
}
