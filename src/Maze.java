import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    public static final int Max_Maze_Row = 20;
    public static final int Max_Maze_Column = 50;
    public static Entity[][] maze = new Entity[20][50];
    
    public Maze(){
        create("Maze.txt");      
    }
    
    //This method reads the file passed to the method and initializes a 
    //2-dimentional array with the maze in the file.
    public static void create(String filename){
        File inputMaze = new File(filename);
        Scanner mazetxt = null;
        //check if Maze.txt exist
        try {
            mazetxt = new Scanner(inputMaze);
        } catch (FileNotFoundException ex) {
            System.out.println("File " + filename + " does not exist.");
        }
        
        //initialize a two dimentsional array
        int i = 0;
        while(mazetxt.hasNextLine()){
            String line = mazetxt.nextLine();
            //if '*', create a wall object
            for (int j = 0; j < Max_Maze_Column; j++){
                if (line.charAt(j) == '*'){
                    Wall wall = new Wall();
                    wall.row = i;
                    wall.column = j;
                    maze[i][j] = wall;
                }
            }
            i++;
        }     
    }
    
    //This method displays the maze structure and the containing entities.
    public static void display(){
        for (int i = 0; i < Max_Maze_Row ; i++){
            for (int j = 0; j < Max_Maze_Column ; j++){
                if (maze[i][j] instanceof Wall){
                    System.out.print('*');
                }else if (maze[i][j] instanceof Almond){
                    System.out.print('A');
                }else if (maze[i][j] instanceof Peanut){
                    System.out.print('P');
                }else if (maze[i][j] instanceof PoisonousCashew){
                    System.out.print('C');
                }else if (maze[i][j] instanceof Squirrel){
                    System.out.print('@');
                }else{
                    System.out.print(' ');
                }
            }
            System.out.print("\n");
        }
    }
    
    //This method takes a row and a column and determines if the location is a 
    //blank space. If it is, it returns true; otherwise, it returns false.
    public static boolean available(int row, int col){
        return !(maze[row][col] instanceof Wall);
    }
}

