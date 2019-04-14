/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import res.Difficulty;

public final  class World {
    
    private WorldBuilder worldB =null;
   
    
    private ArrayList <WorldBuilder> worlds;
    private Difficulty diff;
    public World(){
        
        readAllWorlds();
        loadNewWorld(0);
     
    }

    
    ///////////////////////////
    /**
     * based on the map(level) it will load the corresponding world
     * @param map 
     */
    public void loadNewWorld(int map){
        readAllWorlds();
        worldB      = worlds.get(map);
        worldB.rebuild();
     
    }
    /**
     * 
     * @return true if the world is loaded
     */
    public boolean isWorldLoaded(){ return worldB != null; }
    
    /**
     * build the worlds based on the mapId and difficulty,then put them into array of worlds  
     */
    protected void readAllWorlds(){
        
        worlds = new ArrayList<>();
        
        for(int mapID=0;mapID<6;mapID++){
            
            if(mapID<2){
                
                WorldBuilder world=new WorldBuilder(new Difficulty(400,mapID,false));
                worlds.add(world);
            }
            if(mapID>=2 && mapID<4){
                
                WorldBuilder world=new WorldBuilder(new Difficulty(200,mapID,true));
                worlds.add(world);
            }
            if(mapID>=4 && mapID<6){
               
                WorldBuilder world=new WorldBuilder(new Difficulty(100,mapID,true));
                worlds.add(world);
            }
        }
    }
  ///////////////////////////
    
    /**
     * 
     * @param d moving the player one step
     * @return true if player is moved
     */
    public boolean playerStep(Direction d){
        
        return worldB.movePlayer(d);
    }
    /**
     * 
     * @param d moving the dragon one step
     * @return true if dragon is moved
     */
    public boolean dragonStep(Direction d){
        
        return worldB.moveDragon(d);
    }
    /**
     * 
     * @return ArrayList of open paths based on searchAndDestroy function
     */
    public  ArrayList dragonHunch(){
        
        Position dr = worldB.getDragon();
        
        Direction[] next=  {Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};
        ArrayList<Direction> openNext = new ArrayList<>();
        int j=0;
        
        
        for(int i=0;i<4;i++){
            Position nextPos =dr.translate(next[i]);
            if(worldB.isFree(nextPos)){
                
                if(worldB.getWorldItemMatrix()[nextPos.i][nextPos.j]==WorldItem.PATH){
                    if(searchAndDestroy(next[i])){
                        openNext.add(next[i]);
                        j++;
                    }
            }
            }
        }
        if(openNext.isEmpty()){
          
            openNext.add(Direction.LEFT);
        }
       
         return openNext;
    }
    /**
     * check if a given direction will lead the dragon to the player
     * @param dir
     * @return true if the direction is good 
     */
    public Boolean searchAndDestroy(Direction dir){
        
        Position p  = worldB.getPlayer();
        Position dr = worldB.getDragon();
        
        if(worldB.getDifficulty().isSmartDragon()){
            
            if(p.i>=dr.i  && dir==Direction.DOWN){
                return true;
            } 
             if(p.i<dr.i && dir==Direction.UP){
                return true;
            }
              if(p.j>dr.j && dir==Direction.RIGHT){
                return true;
            }
            return dr.j>=p.j && dir==Direction.LEFT;
        }
        return true;
    }
    
    /*
    getters
    */
    public WorldBuilder getWorldBuilder() {
        
        return worldB;
    }
    public ArrayList<WorldBuilder> getWorlds() {
        return worlds;
    }

    public Difficulty getDifficulty() {
        return diff;
    }
    
   
}
