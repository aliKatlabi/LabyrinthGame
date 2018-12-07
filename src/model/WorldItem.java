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
public enum WorldItem {
    
    DRAGON('&'), DESTINATION('D'), TREE('#'), PATH('$') , PLAYER('p');
    
    public final char representation;
     
    WorldItem(char rep){ 
        
        representation = rep; 
    }
    
   
    
}
