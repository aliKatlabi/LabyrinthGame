/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;
import res.Resource;



public class WorldBuilder {
    private int cols; 
    private int rows;
    
    private ArrayList<String> stringMap; 
    private WorldItem[][] WorldItemMatrix;
    
    
    public Position  player = new Position(0, 0);
    public Position  dragon = new Position(0, 0);
    
    public int numSteps=0;
    
    public WorldBuilder(){
        
        stringMap = new ArrayList<>();
        
        build();
        
    
    }
    
    protected final void build(){
        
        //user readLevel()
        //translate levelMatrix into worlditems
        readLevel();
        
        WorldItemMatrix = new WorldItem[rows][cols];

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
                        
                        WorldItemMatrix[i][j] = WorldItem.PATH; break;
                    case 'p':
                        
                        player=new Position(i,j);
                        
                        WorldItemMatrix[i][j] = WorldItem.PATH; break;
                        
                    default: 
                        
                        WorldItemMatrix[i][j] = WorldItem.PATH; break;
                }
            }
   
        
        }
    
    }
    
    protected void readLevel(){
    
    //read level text file into levelMatrix /above\
    //set cols and rows numbers
        
        String line;
        
        try (Scanner sc = new Scanner(Resource.loadResource("res/maps/map1.txt"))) {
            
            while(sc.hasNextLine()) {
                
                line = sc.nextLine();
                stringMap.add(line);
            }
        }
         
         cols=stringMap.get(1).length();
         rows=stringMap.size();
    }
    
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
    
     
     
    public boolean isFree(Position p){
        
        if (!isValidPosition(p)) return false;
         
        WorldItem li = WorldItemMatrix[p.i][p.j];
        
        return ( li == WorldItem.PATH);
    
    }
    
    public boolean isDestination(Position p){
        
        if (!isValidPosition(p)) return false;
    
        WorldItem li = WorldItemMatrix[p.i][p.j];
        
        return (li == WorldItem.DESTINATION);
    }
    
     public boolean isValidPosition(Position p){
        
         return (p.i >= 0 && p.j >= 0 && p.i < rows && p.j < cols);
    }
     public Boolean isDragon(Position p){
         
         if (!isValidPosition(p)) return false;
         
         WorldItem li = WorldItemMatrix[p.i][p.j];
         
         return (li == WorldItem.DRAGON );
     }
    public WorldItem[][] getWorldItemMatrix() {
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
    
    
    
    
    
    
    
}
