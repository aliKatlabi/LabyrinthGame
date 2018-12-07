/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ali pc
 */
public enum Direction {
    
      DOWN(1,0), LEFT(0, -1), UP(-1, 0), RIGHT(0, 1);
    
    Direction(int i, int j){
        this.i = i;
        this.j = j;
    }
    
    public final int i,j;
    
}
