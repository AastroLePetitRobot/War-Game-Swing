package wargame;

import wargame.gui.Tile;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareLayout;
import wargame.gui.square.SquareTile;
import wargame.Armee;
import wargame.Joueur;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math;

/**
 * @author Franck Vandewiele
 *
 */
public class Main {
    public static Joueur J0  = new Joueur(0, Color.red);
    public static Joueur J1  = new Joueur(1, Color.blue);
    public static BufferedImage image = null;
    public static BufferedImage base = null;
    public static BufferedImage eau = null;
    public static BufferedImage montagne = null;
    public static BufferedImage desert = null;
    public static BufferedImage plaine = null;
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        MainIHM main = new MainIHM();
    }
}



