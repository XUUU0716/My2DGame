import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale=3;  /* because 16x16 pixel per tile is to small for modern computer resolution  */

    final int tileSize=originalTileSize*scale;  /* 48x48 tiles  */

    final int maxScreenCol=16;  /* 16 tiles horizontal */
    final int maxScreenRow=12;  /* 12 tiles vertical  */

    final int screenWidth=tileSize*maxScreenCol;    /* 768 pixels */
    final int screenHeight=tileSize*maxScreenRow;   /* 576 pixels */

    Thread gameThread;
    /* constructor */
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void run() {

    }
}
