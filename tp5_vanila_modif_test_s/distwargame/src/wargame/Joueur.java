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

public class Joueur {

    private int id ;
    private Color couleur ;
    private ArrayList<Armee> listArmee = new ArrayList<>();
    
    
    /** 
     * @return int
     */
    public int getId() {
        return this.id;
    }

    
    /** 
     * @return Color
     */
    public Color getCouleur() {
        return this.couleur;
    }

    
    /** 
     * @param couleur
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur ;
    }

    
    /** 
     * @return ArrayList<Armee>
     */
    public ArrayList<Armee> getlistArmee() {
        return this.listArmee ;

    } 

    
    /** 
     * @param id
     * @param couleur
     * @return 
     * un joueur a une id , une couleur et une list d'armee 
     */
    public Joueur ( int id , Color couleur ){
        this.id = id ;
        this.couleur = couleur ;
        this.listArmee.add(new Armee(1,this)); 
        this.listArmee.add(new Armee(1,this)); 
        this.listArmee.add(new Armee(2,this)); 
        this.listArmee.add(new Armee(2,this)); 
        this.listArmee.add(new Armee(3,this)); 
        this.listArmee.add(new Armee(3,this)); 
        this.listArmee.add(new Armee(4,this)); 
        this.listArmee.add(new Armee(4,this)); 
        this.listArmee.add(new Armee(5,this)); 
        this.listArmee.add(new Armee(5,this));  

    }

    
    /** 
     * @return Armee
     * on prend une armee dans notre list d'armee aleatoirement et on la place 
     * si on a plus d'armee on return null 
     */
    public Armee PlaceArmee (){
        int ind = (int)(Math.random()*this.getlistArmee().size()) ;
        Armee armee = this.getlistArmee().get(ind);
        if (this.getlistArmee().size() == 0 ){
            return null ;
        }else {
            this.getlistArmee().remove(ind);
            return armee ;
        }
    }

}

