import java.util.ArrayList;

/**
 * This is a class
 * Created 2021-10-18
 *
 * @author Magnus Silverdal
 */
public class SimulationModel {
    private ScreenRenderer view;
    private int width;
    private int height;
    private int scale;
    private ArrayList<cell> cellArray;;
    private ArrayList<cell> cellGridArrayTemporary ;


    public SimulationModel(int w, int h, int s) {
        this.width = w;
        this.height = h;
        this.scale = s;
        this.cellArray = new ArrayList<cell>();
        this.cellGridArrayTemporary = new ArrayList<cell>();
    }

    public void update(double magnitude) {
        ArrayList<cell> array = cellArray;
        for (int i = 0; i < array.size(); i++) {
            array.get(i).setX(array.get(i).getX() + (float) (magnitude * Math.cos(array.get(i).getAngle())));
            array.get(i).setY(array.get(i).getY() + (float) (magnitude * Math.sin(array.get(i).getAngle())));
            if (array.get(i).getX() < 1) {
                array.get(i).reverseAngle();
                array.get(i).setX(1);
                System.out.println("ANGLE X = " + array.get(i).getAngle());
            }
            if (array.get(i).getX() > width/scale-1) {
                array.get(i).reverseAngle();
                array.get(i).setX(width/scale-2);
                System.out.println("ANGLE X = " + array.get(i).getAngle());
            }
            /*if (array.get(i).getY() < 1 || array.get(i).getY() > height/scale-1) {
                array.get(i).setY(array.get(i).getY() + (float) (magnitude*5 * Math.sin(array.get(i).getAngle())));
                System.out.println("ANGLE Y = " + array.get(i).getAngle());
            }*/
        }
    }

    public void randomizeCellGrid() {
        System.out.println("Randomizing cells...");

        cellArray.add(0, new cell((width/scale)/2,(height/scale)/2, 0) );
    }


    public ArrayList<cell> getCellArray() {
        return cellArray;
    }

    public ArrayList<cell> getCellGridArrayTemporary() {
        return cellGridArrayTemporary;
    }
}
