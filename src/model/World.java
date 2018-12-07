/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class World {
    
    WorldBuilder worldB;
    
    
    
    public World(){
        
       worldB = new WorldBuilder();
       
    }

  
    public boolean playerStep(Direction d){
        
        return worldB.movePlayer(d);
    }
    
    public boolean dragonStep(Direction d){
        
        return worldB.moveDragon(d);
    }
    
    public  ArrayList dragonHunsh(){
        
        Position dr = worldB.getDragon();
        
        Direction[] next=  {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        ArrayList<Direction> openNext = new ArrayList<>();
        int j=0;
        for(int i=0;i<4;i++){
            Position nextPos =dr.translate(next[i]);
            if(worldB.isFree(nextPos)){
                
                if(worldB.getWorldItemMatrix()[nextPos.i][nextPos.j]==WorldItem.PATH){
                    
                    openNext.add(next[i]);
                j++;
            }
            }
        }
         return openNext;
    }

    public WorldBuilder getWorldBuilder() {
        
        return worldB;
    }
    
    
    
    
}
