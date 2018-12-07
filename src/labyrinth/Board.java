/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinth;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import model.Position;
import model.World;
import model.WorldBuilder;
import model.WorldItem;
import res.Resource;

/**
 *
 * @author Ali pc
 */
public class Board extends javax.swing.JPanel implements ActionListener{
    
    private Image dragon,tree,player,path,destination;
    private final int[] resolution ={32,64,128};
    private int scale=64;
    private int x;
    private int y;
    private Timer timer;
    private WorldBuilder WB;
    Position playerPos;
    Position dragonPos;
    World world;
    public Board (World w) throws IOException {
        
        world = w;
        WB =w.getWorldBuilder();
        
       
        dragon  =       Resource.loadImage("res/worldElements/dragon"+resolution[1]+".png");
        tree    =       Resource.loadImage("res/worldElements/tree"+resolution[1]+".png");
        player  =       Resource.loadImage("res/worldElements/player"+resolution[1]+".png");
        path    =       Resource.loadImage("res/worldElements/path"+resolution[1]+".jpg");
        
        
        timer=new Timer(999,this);
        timer.start();
        
   
        
    }

    
    
    public void refresh(){
        
        Dimension dim =new Dimension(scale*WB.getCols(),scale*WB.getRows());
        setPreferredSize(dim);
        setSize(dim);
        setMaximumSize(dim);
        repaint();
    
    
    }
    @Override
    
    protected void paintComponent(Graphics g){
        
        
        Graphics2D gr = (Graphics2D)g;
        playerPos=WB.getPlayer();
        dragonPos=WB.getDragon();
        Image img = null;
        int i,j;
        
        for( i=0;i<WB.getRows();i++){
            for(j=0;j<WB.getCols();j++){
                WorldItem wi = WB.getWorldItemMatrix()[i][j];
                switch(wi){
                    
                    case TREE:   img=tree; break;
                    case PATH:   img=path;break;       
                   
                    default:
                        
                        break; 
                }
                
                if(playerPos.i==i&&playerPos.j==j){img=player;}
                if(dragonPos.i==i&&dragonPos.j==j){img=dragon;}
                if (img == null) continue;
                
//                if(img==dragon){
//                    gr.drawImage(img , j, i, scale,scale, null);
//                }else{
                gr.drawImage(img , j*scale, i*scale, 62,62, null);}
            //}
       
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rnd = new Random();
        
        int range = world.dragonHunsh().size();
        
       
        int randomIndex =rnd.nextInt(range);
        
        WB.moveDragon((model.Direction) world.dragonHunsh().get(randomIndex));
      
        repaint();
        
        
    }
 
}
