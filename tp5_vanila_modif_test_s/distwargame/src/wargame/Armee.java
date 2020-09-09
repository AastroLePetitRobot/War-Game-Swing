package wargame;

import wargame.gui.Tile;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareLayout;
import wargame.gui.square.SquareTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;


public class Armee {

    private int TailleArmee ;
    private Joueur joueur ;

    /**
     * @return joueur
     * getterjoueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    
    /** 
     * @return int
     * gettertailleArmee
     */
    public int getTailleArmee() {
        return TailleArmee ;
    }
    
    
    /** 
     * @param joueur
     * setter joueur 
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur ;
    }

    
    
    /** 
     * @param TailleArmee
     * 
     * setter tailleArmee
     */
    public void setTailleArmee( int TailleArmee) {
        this.TailleArmee = TailleArmee ;
    }

    
    /** 
     * @param TailleArmee
     * @param joueur
     * @return 
     * constructeur de armee 
     */
    public Armee ( int TailleArmee ,Joueur joueur){
        this.TailleArmee = TailleArmee ;
        this.joueur = joueur ;
    }


    
    /** 
     * @return String
     * permet de print armee ( simplifie le debug)
     */
    @Override
    public String toString() {
        return " Armee de taille : " + this.TailleArmee ;
    }
}

