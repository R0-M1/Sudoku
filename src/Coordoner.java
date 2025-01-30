public class Coordoner {
    int x;
    int y;

    public Coordoner(int x, int y) {
        this.x = x;
        this.y = y;

    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void rotate() {
        int a = this.x;
        int b = this.y;
        this.y = a;
        this.x = b;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
