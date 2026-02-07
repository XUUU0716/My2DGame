package main;

import entity.Player;
import tile.TileManager;

import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    private final int originalTileSize=16;
    private final int scale=3;  /* because 16x16 pixel per tile is to small for modern computer resolution  */

    public final int tileSize=originalTileSize*scale;  /* 48x48 tiles  */

    public final int maxScreenCol=16;  /* 16 tiles horizontal */
    public final int maxScreenRow=12;  /* 12 tiles vertical  */

    public final int screenWidth=tileSize*maxScreenCol;    /* 768 pixels */
    public final int screenHeight=tileSize*maxScreenRow;   /* 576 pixels */


    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;

    // FPS
    int FPS=60;

    KeyHandler keyH=new KeyHandler();
    Thread gameThread;
    public Player player=new Player(this,keyH);
    TileManager tileM=new TileManager(this);
    /* constructor */
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread=new Thread(this);
        gameThread.start();
    }

   /* @Override
    public void run() {
        double drawInterval=1000000000/FPS; //1/60 and convert to ns
        double nextDrawTime=System.nanoTime()+drawInterval;
        while(gameThread!=null)
        {
            update();
            repaint();
            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime<0)remainingTime=0;
                Thread.sleep((long)remainingTime);      // convert to millisecond
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
    @Override
    public void run()
    {
        double drawInterval=1000000000/FPS;
        long lastTime=System.nanoTime();
        long currentTime;
        double delta=0;
        while(gameThread!=null)
        {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
