package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp,KeyHandler keyH)
    {
        this.gp=gp;
        this.keyH=keyH;

        this.screenX=this.gp.screenWidth/2-this.gp.tileSize/2;
        this.screenY=this.gp.screenHeight/2-this.gp.tileSize/2;
        this.setDefaultValues();
        this.getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=4;
        direction="down";
    }

    public void getPlayerImage()
    {
        try{
            this.up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            this.up2= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            this.down1= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            this.down2= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            this.left1= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            this.left2= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            this.right1= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            this.right2= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void update()
    {
        if(keyH.rightPressed==true || keyH.downPressed==true || keyH.upPressed==true || keyH.leftPressed==true)
        {if(keyH.upPressed==true)
        {
            this.direction="up";
            this.worldY-=speed;
        }else if(keyH.downPressed==true)
        {
            this.direction="down";
            this.worldY+=speed;
        }else if(keyH.rightPressed==true)
        {
            this.direction="right";
            this.worldX+=speed;
        }else if(keyH.leftPressed==true)
        {
            this.direction="left";
            this.worldX-=speed;
        }
            spriteCounter++;
            if(spriteCounter>10)
            {
                if(spriteNum==1)
                {
                    spriteNum=2;
                }else if(spriteNum==2)
                {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }

    }
    public void draw(Graphics2D g2)
    {
/*        g2.setColor(Color.white);
        g2.fillRect(this.x,this.y,gp.tileSize,gp.tileSize);*/
        BufferedImage image=null;
        if(direction=="up")
        {
            if(spriteNum==1)
            {
                image=this.up1;
            }else if(spriteNum==2)
            {
                image=this.up2;
            }
        }else if(direction=="down")
        {
            if(spriteNum==1)
            {
                image=this.down1;
            }else if(spriteNum==2)
            {
                image=this.down2;
            }
        }else if(direction=="right")
        {
            if(spriteNum==1)
            {
                image=this.right1;
            }else if(spriteNum==2)
            {
                image=this.right2;
            }
        }else if(direction=="left")
        {
            if(spriteNum==1)
            {
                image=this.left1;
            }else if(spriteNum==2)
            {
                image=this.left2;
            }
        }
        g2.drawImage(image,screenX,screenY, gp.tileSize,gp.tileSize,null);
    }
}
