/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinth;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import model.Direction;
import model.World;
/**
 *
 * @author Ali pc
 */
public final   class Labyrinth extends javax.swing.JFrame {
    
    private  Board board;
    private final World world;
    public JLabel gameStatLabel;
    public Labyrinth()throws IOException{
       
        
       world= new World();
       
       board= new Board(world,this);
    
       setTitle("Labyrinth game");
       setSize(600, 600);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       URL url = Labyrinth.class.getClassLoader().getResource("res/worldElements/dragon128.png");
       
       buildMenus();
       
        
        setLayout(new BorderLayout());
        add( board ,BorderLayout.CENTER); 
        
        setLocation(10, 10);
        gameStatLabel = new JLabel("");
        add(gameStatLabel, BorderLayout.NORTH);
        
        //gameStatLabel.setText("steps: "+world.getWorldBuilder().getNumSteps());
       
       
       
       addKeyListener(new KeyAdapter(){
           
       @Override
       public void keyPressed(KeyEvent ke){
           super.keyPressed(ke);
           if (!world.isWorldLoaded()) return;
           int kc = ke.getKeyCode();
           
           Direction d = null;
           
           switch (kc){
          
              case KeyEvent.VK_A:      d = Direction.LEFT;     break;
              case KeyEvent.VK_D:      d = Direction.RIGHT;    break;
              case KeyEvent.VK_W:      d = Direction.UP;       break;
              case KeyEvent.VK_S:      d = Direction.DOWN;     break;
              case KeyEvent.VK_ESCAPE: world.loadNewWorld(world.getWorldBuilder().getDifficulty().getMap());break;
           }
           
           
           if (d != null && world.playerStep(d)){
               
                if(world.getWorldBuilder().IsReached(d)){
                   JOptionPane.showMessageDialog(Labyrinth.this, "Congratulations", "Game over", JOptionPane.INFORMATION_MESSAGE);
                  
                   world.loadNewWorld(world.getWorldBuilder().getDifficulty().getMap());
                }
           }
           
       
       board.repaint();
       }
       } );
       
       world.loadNewWorld(0);
       board.refresh();
       
       setResizable(false);
       pack();
       setVisible(true);
       
    }
    
    /**
     * build menus
     */
    public void buildMenus(){
        
        JMenuBar menuB = new JMenuBar();
        
        JMenu game=new JMenu("Game");
        
        JMenu diff= new JMenu("Difficulty");
        game.add(diff);
        
        JMenu easy   =new JMenu("Easy");
        JMenu mideum =new JMenu("mideum");
        JMenu hard   =new JMenu("Hard");
        
        diff.add(easy);
        diff.add(mideum);
        diff.add(hard);
        
        JMenuItem JMI0 = new JMenuItem(new AbstractAction("map-"+ 1) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        world.loadNewWorld(0);
                        board.refresh();
                    } } );
        easy.add(JMI0);
        JMenuItem JMI1 = new JMenuItem(new AbstractAction(("map-"+ 2)) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      
                        world.loadNewWorld(1);
                        board.refresh();  
                    } } );
            
        easy.add(JMI1);
        JMenuItem JMI2 = new JMenuItem(new AbstractAction(("map-"+ 3)) {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.loadNewWorld(2);
                board.refresh();  
            } } );

        mideum.add(JMI2);

        JMenuItem JMI3 = new JMenuItem(new AbstractAction("map-"+ 4) {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.loadNewWorld(3);
                board.refresh();  
            } } );


         mideum.add(JMI3);
         JMenuItem JMI4 = new JMenuItem(new AbstractAction("map-"+ 5) {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.loadNewWorld(4);
                board.refresh();  } } );


         hard.add(JMI4);
         JMenuItem JMI5 = new JMenuItem(new AbstractAction("map-"+ 6) {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.loadNewWorld(5);
                board.refresh();  } } );

         hard.add(JMI5);

         menuB.add(game);
         add(menuB);
         setJMenuBar(menuB);
         
         JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
         game.add(menuGameExit);
    }
    
    
    public static void main(String[] args) {
        
        
        try {
           Labyrinth game = new Labyrinth();
        } catch (IOException ex) {
            Logger.getLogger(Labyrinth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
