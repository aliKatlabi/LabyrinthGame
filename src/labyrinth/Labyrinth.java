/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.WindowConstants;
import model.Direction;
import model.World;

/**
 *
 * @author Ali pc
 */
public class Labyrinth extends javax.swing.JFrame {

    
  
   
    public Labyrinth()throws IOException{
        
        World world;
        
        Board board= new Board(world=new World());
        
        setTitle("Labyrinth game");
       
        this.setResizable(false);
        
        setBackground(Color.darkGray);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        add( board,BorderLayout.CENTER);
        
     
       addKeyListener(new KeyAdapter(){
           
       @Override
       public void keyPressed(KeyEvent ke){
           super.keyPressed(ke);
           
           int kc = ke.getKeyCode();
           
           Direction d = null;
           
           switch (kc){
          
              case KeyEvent.VK_A:    d = Direction.LEFT;     break;
              case KeyEvent.VK_D:    d = Direction.RIGHT;    break;
              case KeyEvent.VK_W:    d = Direction.UP;       break;
              case KeyEvent.VK_S:    d = Direction.DOWN;     break;
              default:  break;
       }
           if (d != null && world.playerStep(d)){
           
       }
           board.repaint();
       }
       }
   );
       
       
       
        setVisible(true);
        setLocationRelativeTo(board);
        setLocation(10, 10);
       
        board.refresh();
        pack();
        
    }
    
   
    
     public static void main(String[] args) {
        
        
        try {
           Labyrinth game = new Labyrinth();
        } catch (IOException ex) {
            Logger.getLogger(Labyrinth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
