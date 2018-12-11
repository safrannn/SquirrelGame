import java.util.Scanner;

public class Squirrel extends Entity implements Movable{
    public int pointsCollected;
    public int totalNutsEaten;
    public Maze maze = new Maze();
    
    public Squirrel(){
        this.symbol = '@';
        this.row = 0;
        this.column = 0;
    }
    
    //prompts the user to enter the initial location of the squirrel in maze
    @Override 
    public void create(){
        System.out.println("Enter the Squirrel position (row , column):");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.next();
        
        //check input format
        if (!input.matches("\\d{1,2},\\d{1,2}")){
            System.out.println("Invalid format, please try again.");
            create();
        }

        //turn input string into row number and column number
        //and set Squirrel's initial value
        this.row = Integer.parseInt(input.split(",")[0].trim());
        this.column = Integer.parseInt(input.split(",")[1].trim());

        //if wall hit, then ask the user to input a new set of row and column
        //until the user provides a position that squirrel can start from
        if (!Maze.available(this.row,this.column)|| 
                this.row < 0 || this.row > Maze.Max_Maze_Row || 
                this.column < 0 || this.column > Maze.Max_Maze_Column){
            System.out.println("Position not available. Try again!");
            create();
        }
        System.out.println("User input accepted.");  
    }
    
    //change position
    @Override
    public void move(char direction){
        switch(direction){
            case 'u':
                this.row--;
                if(this.row < 0 || !maze.available(this.row,this.column)){
                    System.out.println("Position not available. Try again!");
                    this.row++;
                }
                 break;
            case 'd':
                this.row++;
                if(this.row >20|| !maze.available(this.row,this.column)){
                    System.out.println("Position not available. Try again!");
                    this.row--;
                }
                break;
            case 'l' :
                this.column--;
                if(this.column < 0 || !maze.available(this.row,this.column)){
                    System.out.println("Position not available. Try again!");
                    this.column++;
                }
                break;
            case 'r':
                this.column++;
                if(this.column > 50|| !maze.available(this.row,this.column)){
                    System.out.println("Position not available. Try again!");
                    this.column--;
                }
                break;
        }
    }
}
