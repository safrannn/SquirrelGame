import java.io.IOException;
import java.util.Scanner;

public class HungrySquirrelGame {

    public static void main(String[] args) throws IOException {
        //Call the create method of the Maze class to create the maze. 
        Maze maze = new Maze();
        Maze.display();
        
        //Create a Squirrel object. This creates the squirrel and puts the 
        //squirrel in the maze based on the user input.
        Squirrel sql = new Squirrel();
        sql.create();
        Maze.maze[sql.row][sql.column] = (Squirrel)sql;
        
        //Instantiate an array of Nut objects and determine and create the type 
        //of nut (almond or peanut).
        Nut[] nuts = new Nut[5];
        for (int i = 0; i < Nut.TOTAL_NUTS; i++){
            Nut nut = new Nut();
            nut.create();
            if (nut.name.equals("Almond")){
                nuts[i] = new Almond();
                maze.maze[nut.row][nut.column] = (Almond)nuts[i];
            }else if (nut.name.equals("Peanut")){
                nuts[i] = new Peanut();
                maze.maze[nut.row][nut.column] = (Peanut)nuts[i];
            }   
        }
        
        //create poisonous cashew
        Nut[] poisonousNuts = new Nut[5];
        for (int i = 0; i < PoisonousCashew.TOTAL_POISONOUS_NUTS; i++){
            PoisonousCashew pnut = new PoisonousCashew();
            pnut.create();
            poisonousNuts[i] = pnut;
            maze.maze[pnut.row][pnut.column] = pnut;
        }
        
        //Accept user input to move the squirrel.
        //For every move the full maze with all the entities should be displayed.
        System.out.println("Enter commands u,d,l,r to move Up, Down, Left, "
                + "and Right:");
        
        // if user lands on a nut
        checkNut(maze, sql);
        if (sql.pointsCollected < 0){
            System.out.println("Squirrel died. Total points of " 
                    + sql.pointsCollected + ".");
            System.out.println("Thank you for playing this game");
            return;
        }
                
        //Display the maze with all the entities created.
        maze.display();
        
        System.out.print("Enter command:");
        Scanner keyboard = new Scanner(System.in);
        while (keyboard.hasNext()){
            String inputCmd = keyboard.next();
            if (inputCmd.length() != 1 || !inputCmd.matches("[udlr]")){
                System.out.println("Invalid command, please try again");
                continue;
            }
            maze.maze[sql.row][sql.column] = null;
            sql.move(inputCmd.charAt(0));
            
            checkNut(maze, sql);
            if (sql.pointsCollected < 0){
                System.out.println("Squirrel died. Total points of " 
                        + sql.pointsCollected + ".");
                System.out.println("Thank you for playing this game");
                return;
            }
          
            maze.maze[sql.row][sql.column] = (Squirrel)sql;
                       
            maze.display();
            
            if (sql.totalNutsEaten == Nut.TOTAL_NUTS){
                System.out.println("Squirrel successfully collected all the nuts."
                        + " Total points " + sql.pointsCollected + ".");
                System.out.println("Thank you for playing this game");
                return;
            }else{
                System.out.print("Enter command:");
            }
        }
        
    }
    
    private static void checkNut(Maze maze, Squirrel sql){
        if (maze.maze[sql.row][sql.column] instanceof Almond){
            sql.pointsCollected += 5;
            sql.totalNutsEaten ++;
            maze.maze[sql.row][sql.column] = null;
            System.out.println("!!! Squirrel ate Almond and gained 5 points "
                + "(Total " + sql.pointsCollected + " points) !!!");
        }else if (maze.maze[sql.row][sql.column] instanceof Peanut){
            sql.pointsCollected += 10;
            sql.totalNutsEaten ++;
            maze.maze[sql.row][sql.column] = null;
            System.out.println("!!! Squirrel ate Peanut and gained 10 points "
                + "(Total " + sql.pointsCollected + " points) !!!");
        }else if (maze.maze[sql.row][sql.column] instanceof PoisonousCashew){
            sql.pointsCollected -= 15;
            sql.totalNutsEaten ++;
            maze.maze[sql.row][sql.column] = null;
            System.out.println("!!! Squirrel ate Poisonous Cashew and lost 15 "
                    + "points (Total " + sql.pointsCollected + " points) !!!");
        }
    }
    
}
