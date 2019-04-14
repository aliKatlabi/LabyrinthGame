/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;
import res.Difficulty;
import res.Resource;



public final  class WorldBuilder {
    private int cols; 
    private int rows;
    
    private ArrayList<String> stringMap; 
    private WorldItem[][] WorldItemMatrix;
    
    
    private Position  player;
    private Position  dragon;
    
    private int numSteps=0;
    
    private Difficulty diff;
    
    public WorldBuilder(Difficulty D){
        
        player = new Position(0, 0);
        dragon = new Position(0, 0);
        
        
        stringMap = new ArrayList<>();
        diff=D;
        
        readMap(); //reading into arrylist of strings (map rows) -- stringMap
        
        cols  =   stringMap.get(1).length();
        rows  =   stringMap.size();
         
        WorldItemMatrix = new WorldItem[rows][cols];
        
        build();
        
    }
    
    public final void rebuild(){
        
        WorldItemMatrix = new WorldItem[rows][cols];
        build();
    }
    /**
     * building the WorldItemMatrix based on stringMap(read map from text file)
     */
     protected final void build(){
         
         
         
        for(int i=0; i<rows; i++){
            
            String line = stringMap.get(i);
            
            for(int j=0 ;j<cols;j++){
                 
                switch (line.charAt(j)){
                    case '#': 
                        
                        WorldItemMatrix[i][j] = WorldItem.PATH; break;    
                    case '$':
                        
                        WorldItemMatrix[i][j] = WorldItem.TREE; break;    
                    case '&':
                        
                        dragon=new Position(i,j);
                        
                        WorldItemMatrix[i][j] = WorldItem.DRAGON; break;
                    case 'p':
                        
                        player=new Position(i,j);
                        
                        WorldItemMatrix[i][j] = WorldItem.PLAYER; break;
                    case 'd':
                        
                        WorldItemMatrix[i][j] = WorldItem.DESTINATION; break;
                        
                    default: 
                        
                        WorldItemMatrix[i][j] = WorldItem.PATH; break;
                }
            }
   
        
        }
    
    }
     
     /**
      * read the text map into the stringMap
      */
     protected void readMap(){
         
        
        String line;
        
        try (Scanner sc = new Scanner(Resource.loadResource("res/maps/map"+(diff.getMap())+".txt"))) {
            
            while(sc.hasNextLine()) {
                
                line = sc.nextLine();
                stringMap.add(line);
            }
            
            sc.close();
        }
         
         
         
    }
     
     /**
      * move the item represent the player in WorldItemMatrix
      * @param d direction
      * @return true if it is moved 
      */
     public boolean movePlayer(Direction d){
         
        Position curr = player;
        Position next = curr.translate(d);
        
       if (isFree(next) && !isDragon(next)) {
            
            player = next;
            
            WorldItem tmp= WorldItemMatrix[next.i][next.j];
            WorldItemMatrix[next.i][next.j]=WorldItemMatrix[curr.i][curr.j];
            WorldItemMatrix[curr.i][curr.j]=tmp;
            
            numSteps++;
            return true;
        } 
      return false;
    }
     /**
      * move the item represent the dragon in WorldItemMatrix
      * @param d direction
      * @return true if it is moved 
      */
     public boolean moveDragon(Direction d){
        Position curr = dragon;
        Position next = curr.translate(d);
        
        if (  isFree(next) ) {
            
            dragon = next;
            
            WorldItem tmp= WorldItemMatrix[next.i][next.j];
            WorldItemMatrix[next.i][next.j]=WorldItemMatrix[curr.i][curr.j];
            WorldItemMatrix[curr.i][curr.j]=tmp;
          
            return true;
        } 
        return false;
    }
    
 
     /**
      * check if position given represented by a path item which means it is free
      * @param p Position
      * @return true if it is free
      */
    public boolean          isFree(Position p){
        
        if (!isValidPosition(p)) return false;
         
        WorldItem li = WorldItemMatrix[p.i][p.j];
        
        return ( li == WorldItem.PATH  );
    
    }
    /**
     * 
     * @return true if the dragon near the player
     */
    public boolean          isEaten(){
        
        int pos[]={dragon.i,dragon.j,player.i,player.j};
        
        return  pos[0]==pos[2]-1&&pos[1]==pos[3]||
                pos[0]==pos[2]+1&&pos[1]==pos[3]||
                pos[0]==pos[2]&&pos[1]==pos[3]+1||
                pos[0]==pos[2]&&pos[1]==pos[3]-1;
        
    }
    /**
     * 
     * @param p
     * @return true if the given position is not out of world limits
     */
    public boolean          isValidPosition(Position p){
        
         return (p.i >= 0 && p.j >= 0 && p.i < rows && p.j < cols);
    }
    /**
     * 
     * @param p Position
     * @return true if in the given position there is a dragon
     */
    public boolean          isDragon(Position p){
         
         if (!isValidPosition(p)) return false;
         
         WorldItem li = WorldItemMatrix[p.i][p.j];
         
         return (li == WorldItem.DRAGON );
     }
    /**
     * 
     * @param p Position
     * @return true if in the given position there is a player
     */
     public boolean          isPlayer(Position p){
         
         if (!isValidPosition(p)) return false;
         
         WorldItem li = WorldItemMatrix[p.i][p.j];
         
         return (li == WorldItem.PLAYER );
     }
     /**
      * 
      * @param p Position
      * @return true if the given position is destination point
      */
     public boolean          isDestination(Position p){
         
         if (!isValidPosition(p)) return false;
         
         WorldItem li = WorldItemMatrix[p.i][p.j];
         
         return (li == WorldItem.DESTINATION );
     }
     /**
      * 
      * @param d
      * @return true if the destination is reached
      */
     public boolean IsReached(Direction d){
          Position curr = player;
          Position next = curr.translate(d);
        
       if (isDestination(next) && !isDragon(next)) {
           return true;
       }
         return false;
     }
     

     /*
     getters
     */
    public WorldItem[][]    getWorldItemMatrix() {
        return WorldItemMatrix;
    }
    
    

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Position getPlayer() {
        return player;
    }

    public Position getDragon() {
        return dragon;
    }

    public Difficulty getDifficulty() {
        return diff;
    }

    public int getNumSteps() {
        return numSteps;
    }
    
    
    
    
    
    
    
}
