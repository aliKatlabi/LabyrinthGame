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
public class Position {
    public int i, j;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }    
    
    public Position translate(Direction d){
        return new Position(i + d.i, j + d.j);
    }
    
}
