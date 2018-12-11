public abstract class Entity{
    public char symbol;
    public int row;
    public int column;
    public Entity entity;
    
    public Entity(){}
    
    public abstract void create();
    
    //This method puts an entity at location (newRow, newCol) in the maze
    public Entity put(int newRow, int newCol){
        entity.row = newRow;
        entity.column = newCol;
        return entity;
    }
}
