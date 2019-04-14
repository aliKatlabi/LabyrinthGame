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
    /**
     * 
     * @param d take the direction
     * @return the position of the next step
     */
    public Position translate(Direction d){
        return new Position(i + d.i, j + d.j);
    }
    @Override
    public boolean equals(Object that){
        if(that==null) return false;
        if(this==that) return true;
        if(that instanceof Position) return this.i==((Position)that).i&&this.j==((Position)that).j;
        
        return false;
}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.i;
        hash = 47 * hash + this.j;
        return hash;
    }
}
