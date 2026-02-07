package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile [] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        this.tile=new Tile[10];
        this.mapTileNum=new int [this.gp.maxWorldRow][this.gp.maxWorldCol];
        loadMap("/maps/world01.txt");
        getTileImage();
    }
    public void getTileImage()
    {
        try{
            this.tile[0]=new Tile();
            this.tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            this.tile[1]=new Tile();
            this.tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            this.tile[2]=new Tile();
            this.tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            this.tile[3]=new Tile();
            this.tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            this.tile[4]=new Tile();
            this.tile[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            this.tile[5]=new Tile();
            this.tile[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath)
    {
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(row<this.gp.maxWorldRow)
            {
                String line=br.readLine();
                while(col<this.gp.maxWorldCol)
                {
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[row][col]=num;
                    col++;
                }
                col=0;
                row++;
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2)
    {
        int worldCol=0;
        int worldRow=0;
        while(worldCol<this.gp.maxWorldCol && worldRow<this.gp.maxWorldRow)
        {
            int tileNum=mapTileNum[worldRow][worldCol];
            int worldX=worldCol*this.gp.tileSize;
            int worldY=worldRow*this.gp.tileSize;
            int screenX=worldX-this.gp.player.worldX+this.gp.player.screenX;
            int screenY=worldY-this.gp.player.worldY+this.gp.player.screenY;
            g2.drawImage(this.tile[tileNum].image,screenX,screenY,this.gp.tileSize,this.gp.tileSize,null);
            worldCol++;
            if(worldCol==gp.maxWorldCol)
            {
                worldCol=0;
                worldRow++;
            }
        }
    }
}
