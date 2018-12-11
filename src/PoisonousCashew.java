
import java.util.Random;

public class PoisonousCashew extends Nut{
    public static final int TOTAL_POISONOUS_NUTS = 5;

    public PoisonousCashew(){
        this.name = "Poisonous Cashew";
        this.symbol = 'C';
        this.Nutrition_Points= -15;
    }
    
    @Override
    public void create(){
        Random rand = new Random();
        row = rand.nextInt(20);
        column = rand.nextInt(50);
        if (Maze.maze[row][column] != null){
            create();
        }
    }
}
