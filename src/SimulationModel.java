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
            if (array.get(i).getAngle() > Math.PI*2) {
                array.get(i).alterAngle((float) -(Math.PI*2));
            }
            if (array.get(i).getAngle() < 0) {
                array.get(i).alterAngle((float) (Math.PI*2));
            }

            array.get(i).setX(array.get(i).getX() + (float) (magnitude * Math.cos(array.get(i).getAngle())));
            array.get(i).setY(array.get(i).getY() + (float) (magnitude * Math.sin(array.get(i).getAngle())));

            if (array.get(i).getX() < 1) {
                if (array.get(i).getAngle() > Math.PI) {array.get(i).setAngle((float) (array.get(i).getAngle()+(Math.PI/2)));}
                if (array.get(i).getAngle() < Math.PI) {array.get(i).setAngle((float) (array.get(i).getAngle()-(Math.PI/2)));}
                array.get(i).setX(1);
            }
            if (array.get(i).getX() > width/scale-1) {
                if (array.get(i).getAngle() > 0 && array.get(i).getAngle() < Math.PI/2) {array.get(i).setAngle((float) (array.get(i).getAngle()+(Math.PI/2)));}
                if (array.get(i).getAngle() > (Math.PI + Math.PI/2)) {array.get(i).setAngle((float) (array.get(i).getAngle()-(Math.PI/2)));}
                array.get(i).setX(width/scale-1);
            }
            if (array.get(i).getY() < 1) {
                // PI < Angle < 2PI
                if (array.get(i).getAngle() > (Math.PI/2 + Math.PI)) {array.get(i).setAngle((float) (0 + ((2*Math.PI) - array.get(i).getAngle()) ) );}
                if (array.get(i).getAngle() < (Math.PI/2 + Math.PI)) {array.get(i).setAngle((float) (array.get(i).getAngle()-(Math.PI/2)));}
                array.get(i).setY(1);
            }
            if (array.get(i).getY() > height/scale-1) {
                // 0 < Angle < PI
                if (array.get(i).getAngle() > Math.PI/2) {array.get(i).setAngle((float) (Math.PI + (Math.PI - array.get(i).getAngle())));}
                if (array.get(i).getAngle() < Math.PI/2) {array.get(i).setAngle(array.get(i).getAngle()-(array.get(i).getAngle()*2));}
                array.get(i).setY(height/scale-1);
            }
        }
    }

    public void randomizeCellGrid() {
        cellArray.add(0, new cell((width/scale)/2,(height/scale)/2, (float) ((Math.PI/4)) ));
        cellArray.add(1, new cell((width/scale)/2,(height/scale)/2, 3) );
        cellArray.add(2, new cell((width/scale)/2,(height/scale)/2, 2) );
        cellArray.add(3, new cell((width/scale)/2,(height/scale)/2, 5) );
        cellArray.add(4, new cell((width/scale)/2,(height/scale)/2, -3) );

    }


    public ArrayList<cell> getCellArray() {
        return cellArray;
    }

    public ArrayList<cell> getCellGridArrayTemporary() {
        return cellGridArrayTemporary;
    }
}
