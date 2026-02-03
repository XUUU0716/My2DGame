package main;

import entity.Player;

import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale=3;  /* because 16x16 pixel per tile is to small for modern computer resolution  */

    final int tileSize=originalTileSize*scale;  /* 48x48 tiles  */

    final int maxScreenCol=16;  /* 16 tiles horizontal */
    final int maxScreenRow=12;  /* 12 tiles vertical  */

    final int screenWidth=tileSize*maxScreenCol;    /* 768 pixels */
    final int screenHeight=tileSize*maxScreenRow;   /* 576 pixels */

    // FPS
    int FPS=60;

    KeyHandler keyH=new KeyHandler();
    Thread gameThread;
    Player player=new Player(this,keyH);

    // player default position
    int  playerX=100,playerY=100;
    int playerSpeed=4;
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
        if(keyH.upPressed==true)
        {
            playerY-=playerSpeed;
        }else if(keyH.downPressed==true)
        {
            playerY+=playerSpeed;
        }else if(keyH.rightPressed==true)
        {
            playerX+=playerSpeed;
        }else if(keyH.leftPressed==true)
        {
            playerX-=playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
