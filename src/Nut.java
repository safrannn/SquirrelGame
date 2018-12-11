
import java.util.Random;

public class Nut extends Entity{
    public static final int TOTAL_NUTS = 5;
    public int Nutrition_Points;
    protected String name;
    
    public Nut(){
        this.row = 0;
        this.column = 0;
        name = "";
    }
    
    //randomly generated the location of the nut
    @Override
    public void create(){
        Random rand = new Random();
        row = rand.nextInt(20);
        column = rand.nextInt(50);
        if (Maze.maze[row][column] != null){
            create();
        }
        double nutType = Math.random() ;
        if (nutType < 0.5){
            this.name = "Almond";
        }else{
            this.name = "Peanut";
        }
    }
}
