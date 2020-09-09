package wargame;

import wargame.gui.Tile;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareLayout;
import wargame.gui.square.SquareTile;
import wargame.Main;
import wargame.Armee;
import wargame.Joueur;
import wargame.MainIHM;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

/**
 * la classe voisinage sert a reperer tout les voisins autour d'une tile centre 
 * elle sert aussi d'update pour les tile conquises ou bien les tile qui recoivent du renfort 
 */
public class Voisinage {

    private ArrayList<Tile> listVoisinEnnemi = new ArrayList<>();
    private ArrayList<Tile> listVoisinAllie = new ArrayList<>();
    private ArrayList<Tile> carte = new ArrayList<>();
    
    /** 
     * @param centre
     * @return 
     */
    // Le constructeur de voisinage est un constructeur qui remplie une arraylist de tout les voisins autour d'une tile centre

    public Voisinage(Tile centre) {
        if ( centre.getRow() - 1 >= 0 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() - 1][centre.getColumn()].getOccuper_Rouge()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow() - 1][centre.getColumn()].getOccuper_Bleu()  ) )){
            listVoisinEnnemi.add(MainIHM.listeTiles[centre.getRow() - 1][centre.getColumn()]); 
        }
        if ( centre.getRow() + 1 <= 7 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() + 1][centre.getColumn()].getOccuper_Rouge()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow() + 1][centre.getColumn()].getOccuper_Bleu()  ) ) ){
            listVoisinEnnemi.add(MainIHM.listeTiles[centre.getRow() + 1][centre.getColumn()]);
        }
        if ( centre.getColumn() - 1 >= 0 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() ][centre.getColumn()- 1].getOccuper_Rouge()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow()][centre.getColumn() - 1].getOccuper_Bleu()  ) )){
            listVoisinEnnemi.add(MainIHM.listeTiles[centre.getRow()][centre.getColumn() - 1]);
        }
        if ( centre.getColumn() + 1 <= 7 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() ][centre.getColumn() + 1].getOccuper_Rouge()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow()][centre.getColumn() + 1].getOccuper_Bleu()  ) )){
            listVoisinEnnemi.add(MainIHM.listeTiles[centre.getRow()][centre.getColumn() + 1]);
        }


        if ( centre.getRow() - 1 >= 0 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() - 1][centre.getColumn()].getOccuper_Bleu()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow() - 1][centre.getColumn()].getOccuper_Rouge()  ) )){
            listVoisinAllie.add(MainIHM.listeTiles[centre.getRow() - 1][centre.getColumn()]);
            
        }
        if ( centre.getRow() + 1 <= 7 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() + 1][centre.getColumn()].getOccuper_Bleu()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow() + 1][centre.getColumn()].getOccuper_Rouge()  ) ) ){
            listVoisinAllie.add(MainIHM.listeTiles[centre.getRow() + 1][centre.getColumn()]);
        }
        if ( centre.getColumn() - 1 >= 0 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() ][centre.getColumn()- 1].getOccuper_Bleu()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow()][centre.getColumn() - 1].getOccuper_Rouge()  ) )){
            listVoisinAllie.add(MainIHM.listeTiles[centre.getRow()][centre.getColumn() - 1]);
        }
        if ( centre.getColumn() + 1 <= 7 && (( centre.getOccuper_Bleu() && MainIHM.listeTiles[centre.getRow() ][centre.getColumn() + 1].getOccuper_Bleu()  ) ||  ( centre.getOccuper_Rouge() && MainIHM.listeTiles[centre.getRow()][centre.getColumn() + 1].getOccuper_Rouge()  ) )){
            listVoisinAllie.add(MainIHM.listeTiles[centre.getRow()][centre.getColumn() + 1]);
        }
        
    }


    
    /** 
     * getter listennemi
     * @return ArrayList<Tile>
     */
    public ArrayList<Tile> getListEnnemi(){
        return this.listVoisinEnnemi ;
    } 

    
    /** 
     * getter listallie
     * @return ArrayList<Tile>
     */
    public ArrayList<Tile> getListAllie(){
        return this.listVoisinAllie ;
    } 

    
    /** 
     * @param centre
     * On update les voisins que ce soit des ennemi inferieur on set leurs couleur et leur proprietaire au joueur de centre 
     *  ou bien des allie inferieur on set leur taille armee a taille armee + 1 
     */
    public void majVoisinage(Tile centre) {
        // tant que listennemi n'est pas vide on fait une boucle 
        for (Tile ennemi : this.getListEnnemi()) {
            // si notre armee est superieur a l'armee de l'ennemi elle devient la notre 
            if (centre.getArmeeTile().getTailleArmee() > ennemi.getArmeeTile().getTailleArmee() ) {
                ennemi.getArmeeTile().setJoueur(centre.getArmeeTile().getJoueur());
                System.out.println("j'attack voisin ennemi");
                ennemi.getArmeeTile().getJoueur().setCouleur(centre.getArmeeTile().getJoueur().getCouleur());
                if (ennemi.getOccuper_Bleu()){
                    ennemi.setOccuper_rouge(true);
                    ennemi.setOccuper_bleu(false);
                }else if(ennemi.getOccuper_Rouge()){
                    ennemi.setOccuper_rouge(false);
                    ennemi.setOccuper_bleu(true);
                }
            ennemi.setTextBackgroundColor(centre.getArmeeTile().getJoueur().getCouleur());
            ennemi.repaint();  
            }
            //si l'armee ennemi est superieur a notre armee , notre armee devient la sienne 
            if (ennemi.getArmeeTile().getTailleArmee() > centre.getArmeeTile().getTailleArmee() ) {
                centre.getArmeeTile().setJoueur(ennemi.getArmeeTile().getJoueur());
                System.out.println("l'ennemi m'attack");
                centre.getArmeeTile().getJoueur().setCouleur(ennemi.getArmeeTile().getJoueur().getCouleur());
                if (centre.getOccuper_Bleu()){
                    centre.setOccuper_rouge(true);
                    centre.setOccuper_bleu(false);
                }else if (centre.getOccuper_Rouge()){
                    centre.setOccuper_rouge(false);
                    centre.setOccuper_bleu(true);
                }
            centre.setTextBackgroundColor(ennemi.getArmeeTile().getJoueur().getCouleur());
            centre.repaint();   
            }
            

            
        }
        // tant que listennemi n'est pas vide on fait une boucle
        for (Tile allie : this.getListAllie()) {
            // si notre armee a comme voisin un allie avec une armee plus faible on augmente son effectif de 1 
            if (centre.getArmeeTile().getTailleArmee() > allie.getArmeeTile().getTailleArmee() ) {
                allie.getArmeeTile().setTailleArmee(allie.getArmeeTile().getTailleArmee()+1);
                int temp = allie.getArmeeTile().getTailleArmee() ;
                allie.setTextContent("  "+temp+"  ");
                System.out.println("j'aide voisin allie");
            }
            allie.repaint();  
            }
    }
    

}

