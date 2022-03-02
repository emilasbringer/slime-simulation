public class cell {
    private float x,y;
    private float angle;
    private boolean isTrail;
    private float strength;

    public cell(float x,float y,float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public cell(float x,float y, float strength, boolean isTrail) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.isTrail = isTrail;
        this.strength = strength;
    }

    public float getAngle() {
        return angle;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setAngle(float angle) {this.angle = angle;}

    public void alterAngle(float inputAngle) {
        this.angle = (float) (angle + inputAngle);
    }
}
