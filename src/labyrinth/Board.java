/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinth;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Position;
import model.World;
import model.WorldItem;
import res.Resource;

/**
 *
 * @author Ali pc
 */
public class Board extends javax.swing.JPanel implements ActionListener{
    private World world;
    private Image dragon,player,path,destination,wall;
    private final int[] resolution ={32,64,128};
    private int scale=128;
    private Timer timer;
    private JFrame JF;
    public Board (World w, JFrame JF) throws IOException {
        int r=2;
        this.JF=JF;
        
        world   =       w;
        
        dragon  =       Resource.loadImage("res/worldElements/dragon"+resolution[r]+".png");
        player  =       Resource.loadImage("res/worldElements/player"+resolution[r]+".png");
        path    =       Resource.loadImage("res/worldElements/path"+resolution[r]+".jpg");
        destination=    Resource.loadImage("res/worldElements/dest"+resolution[r]+".png");
        wall=    Resource.loadImage("res/worldElements/wall"+resolution[r]+".png");
        timer= new Timer(world.getWorldBuilder().getDifficulty().getSpeed(),this);
        this.timer.start();
        
      
    }

    
    public void refresh(){
        
        if(world.isWorldLoaded()){
            
            Dimension maxd =new Dimension(scale*world.getWorldBuilder().getCols(),scale*world.getWorldBuilder().getRows());
            Dimension frame =new Dimension(scale*5,scale*5);
            setPreferredSize(frame);
            setSize(maxd);
            setMaximumSize(maxd);
            this.timer.stop();
            timer= new Timer(world.getWorldBuilder().getDifficulty().getSpeed(),this);
            timer.start();
            
           
            repaint();
       }
    
    }
    
   
    @Override
    
    protected void paintComponent(Graphics g){
        
        
        Graphics2D gr = (Graphics2D)g;
        Position playerPos= world.getWorldBuilder().getPlayer();
        Position dragonPos= world.getWorldBuilder().getDragon();
        Image img = null;
        int i,j;
        
        for( i=0;i< world.getWorldBuilder().getRows();i++){
            for(j=0;j< world.getWorldBuilder().getCols();j++){
       
                WorldItem wi =  world.getWorldBuilder().getWorldItemMatrix()[i][j];
                
                switch(wi){
                    
                    case TREE:          img=wall; break;
                    case PATH:          img=path;break;       
                    case DESTINATION:   img=destination;break;
                    default:
                        
                        break; 
                }
                
                if(playerPos.i==i&&playerPos.j==j){img=player;}
                if(dragonPos.i==i&&dragonPos.j==j){img=dragon;}
                if (img == null) continue;
                

                gr.drawImage(img , j*scale, i*scale, scale,scale, null);
                setLocation(scale-(playerPos.j*120),scale-(playerPos.i*120));
                            
                
            }
       
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rnd = new Random();
        int range = world.dragonHunch().size();
        int randomIndex =rnd.nextInt(range);
        
        world.getWorldBuilder().moveDragon((model.Direction) world.dragonHunch().get(randomIndex));
          if(world.getWorldBuilder().isEaten()){
                   
                   JOptionPane.showMessageDialog(JF, "Dead", "Game over", JOptionPane.INFORMATION_MESSAGE);
                   world.loadNewWorld(world.getWorldBuilder().getDifficulty().getMap());
               }
        repaint();
        
        
    }
 
}
