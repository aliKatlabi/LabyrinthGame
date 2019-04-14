/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res;

/**
 *
 * @author Ali pc
 */
public class Difficulty {
    
    private int speed;
    private int map;
    private Boolean smartDragon;
    
    public Difficulty(){
        
    }
    public Difficulty(int speed,int map,boolean smart){
         this.speed=speed;
         this.map=map;
         this.smartDragon=smart;
    }
    public int getSpeed() {
        return speed;
    }

    public int getMap() {
        return map;
    }
    /**
     * 
     * @return true if the value martDragon checked as true
     */
    public Boolean isSmartDragon() {
        return smartDragon;
    }
    /**
     * 
     * @param map
     * @return create new difficulty object labeled as easy
     */
    public Difficulty easy(int map){
        
        return new Difficulty(900,map,false);
    }
    /**
     * 
     * @param map
     * @return create new difficulty object labeled as mideum
     */
    public Difficulty mideum(int map){
        
        
        return new Difficulty(500,map,false);
    }
    /**
     * 
     * @param map
     * @return create new difficulty object labeled as hard
     */
    public Difficulty hard(int map){
       
        return new Difficulty(400,map,true);
    }
    
}
