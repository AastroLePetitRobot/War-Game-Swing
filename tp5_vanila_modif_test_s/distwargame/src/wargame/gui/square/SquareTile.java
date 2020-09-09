package wargame.gui.square;

import wargame.gui.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.lang.Math;

public class SquareTile extends Tile {

    
    /** 
     * @param row
     * @param text
     * @param columns
     * @param image
     * @return 
     */
    public SquareTile(int row,String text,int columns, BufferedImage image) {
        super(row,text,columns, image);
    }

    
     

    @Override
    protected void recomputeBorder() {
        this.getBounds(this.boundingBox);
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        this.border = new Polygon(
                new int[] {0, width, width, 0},
                new int[] {0, 0, height, height},
                4
        );
    }
}
