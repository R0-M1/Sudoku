public class Case {
    Coordoner coord;
    int val;

    public Case(int x,int y, int val){
        this.coord.setX(x);
        this.coord.setY(y);
        this.val = val;
    }
    int getX(){
        return this.coord.getX();
    }
    int getY(){
        return this.coord.getY();
    }

    int getVal(){
        return this.val;
    }
    
}
