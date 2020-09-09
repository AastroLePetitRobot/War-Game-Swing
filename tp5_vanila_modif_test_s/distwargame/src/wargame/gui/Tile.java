package wargame.gui;
import wargame.Joueur;
import wargame.Armee;
import wargame.Main ;
import wargame.Voisinage;
import wargame.MainIHM;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;




/**
 * Base class of graphical tiles
 * This software is released under the GNU GPLv3 license
 *
 * @author Franck Vandewiele
 */
public abstract class Tile extends JComponent implements MouseListener {

    private int row ;
    private int column ;
    private String text = "";
    protected BufferedImage tile;
    private BufferedImage scaledTile;
    private boolean highlightable = true;
    private Color highlightColor = new Color(255, 0, 0, 64);

    protected Polygon border = new Polygon();
    protected Rectangle boundingBox = new Rectangle();
    private boolean isHighlighted = false;
    private int previousHeight = 0;
    private int previousWidth = 0;
    private Color textBackgroundColor = new Color(64, 64, 64);
    public static int tour_joueur = 0;
    public int clique = 0 ;
    private boolean occuper_bleu = false ;
    private boolean occuper_rouge = false ;
    private Armee armeeTile ;
    private boolean bleu_fini = false;
    private boolean rouge_fini = false;
    private static ArrayList<Tile> carte = new ArrayList<>();

    
    /** 
     * @param row
     * @param text
     * @param column
     * @param image
     * @return 
     */
    /*
     * public Tile(String text, BufferedImage image) { this.text = text;
     * this.recomputeBorder(); this.addMouseListener(this); this.setTile(image);
     * Font f = new Font(Font.SANS_SERIF, Font.BOLD, 10); this.setFont(f);
     * this.setCursor(new Cursor(Cursor.HAND_CURSOR)); this.setLayout(new
     * FlowLayout()); }
     */
    public Tile(int row, String text, int column, BufferedImage image) {
        this.column = column;
        this.row = row;
        this.text = column + text + row;
        this.recomputeBorder();
        this.addMouseListener(this);
        this.setTile(image);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        this.setFont(f);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setLayout(new FlowLayout());

    }

    
    /** 
     * @return boolean
     * bool pour simplifier la map 
     */
    public boolean getOccuper_Bleu() {
        return this.occuper_bleu;
    }

    
    /** 
     * @return boolean
     * bool pour simplifier la map
     */
    public boolean getOccuper_Rouge() {
        return this.occuper_rouge;
    }

    
    /** 
     * @param armeeTile
     *
     */
    public void setArmeeTile(Armee armeeTile) {
        this.armeeTile = armeeTile;
    }

    
    /** 
     * @return Armee
     */
    public Armee getArmeeTile() {
        return this.armeeTile;
    }

    
    /** 
     * @param occuper_bleu
     */
    public void setOccuper_bleu(boolean occuper_bleu) {
        this.occuper_bleu = occuper_bleu;
    }

    
    /** 
     * @param occuper_rouge
     */
    public void setOccuper_rouge(boolean occuper_rouge) {
        this.occuper_rouge = occuper_rouge;
    }

    
    /** 
     * @return int
     * get ligne 
     */
    public int getRow() {
        return this.row;
    }

    
    /** 
     * @return int
     * get colonne 
     */
    public int getColumn() {
        return this.column;
    }

    
    /** 
     * @param p
     */
    /**
     * Provides a shape-specific border computation of the attribute border
     */
    protected abstract void recomputeBorder();

    
    /** 
     * @param p
     * @return boolean
     */
    @Override
    public boolean contains(Point p) {
        return border.contains(p);
    }

    
    /** 
     * @param x
     * @param y
     * @return boolean
     */
    @Override
    public boolean contains(int x, int y) {
        return border.contains(x, y);
    }

    
    /** 
     * @param d
     */
    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        this.recomputeBorder();
    }

    
    /** 
     * @param w
     * @param h
     */
    @Override
    public void setSize(int w, int h) {
        super.setSize(w, h);
        this.recomputeBorder();
    }

    
    /** 
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        this.recomputeBorder();
    }

    
    /** 
     * @param r
     */
    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r.x, r.y, r.width, r.height);
    }

    
    /** 
     * @param mouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.setHighlighted(true);
    }

    
    /** 
     * @param mouseEvent
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setHighlighted(false);
    }

    
    /** 
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // si ce n'est pas de l'eau
        if (!this.getImage().equals(Main.eau)) {
            //tour du joueur 0 et tile pas encore clique 
            if (this.tour_joueur == 0 && clique == 0) {
                this.setTextBackgroundColor(Color.red);
                Armee armeeJ0 = Main.J0.PlaceArmee();
                // on test si il y a une armee 
                if (armeeJ0 == null) {
                    System.out.println("plus de jetons rouge");
                    bleu_fini = true;
                 // si c'est une montagne 
                } else if (this.getImage().equals(Main.montagne)) {
                    System.out.println(" *****  TOUR DU JOUEUR J0  *****");
                    setArmeeTile(armeeJ0);
                    armeeJ0.setTailleArmee(3);
                    this.setTextContent("  3  ");
                    System.out.println("MONTAGNE");
                    this.repaint();
                } else {
                    // si c'est une tile "normal"
                    System.out.println(" *****  TOUR DU JOUEUR J0  *****");
                    setArmeeTile(armeeJ0);
                    this.setTextContent("  " + armeeJ0.getTailleArmee() + "  ");
                    this.repaint();
                }
                // on gere le tour et si la case a deja ete clique ou non 
                this.tour_joueur = 1;
                this.clique = 1;
                System.out.println(Main.J0.getlistArmee());
                // on set sur la tile quel est bien rouge grace a un bool ( utile pour le score plus tard ) 
                this.setOccuper_rouge(true);
                System.out.println("colone" + this.getColumn());
                System.out.println("ligne" + this.getRow());
                // on gere le voisinnage 
                Voisinage voisins = new Voisinage(this);
                System.out.println("Voisins du ennemi du rouge : " + voisins.getListEnnemi());
                System.out.println("Voisins allie du rouge : " + voisins.getListAllie());
                System.out.println("size restante : "+Main.J0.getlistArmee().size());
                System.out.println("size restante : "+Main.J1.getlistArmee().size());
                // si plus personne n'a d'armee 
                if (Main.J0.getlistArmee().size() == 0 && Main.J1.getlistArmee().size() == 0) {
                    System.out.println("TEST FIN");
                    carte = carte();
                    System.out.println(score(carte));
                }else{
                // si il a encore des armee on update 
                voisins.majVoisinage(this);
                }
            // si c'est le tour du joueur 1 et tile pas deja clique 
            } else if (this.tour_joueur == 1 && clique == 0) {
                this.setTextBackgroundColor(Color.blue);
                Armee armeeJ1 = Main.J1.PlaceArmee();
                // on test si il y a une armee 
                if (armeeJ1 == null) {
                    System.out.println("plus de jetons bleu ");
                    rouge_fini = true;
                // si c'est une montagne 
                } else if (this.getImage().equals(Main.montagne)) {
                    System.out.println(" *****  TOUR DU JOUEUR J0  *****");
                    setArmeeTile(armeeJ1);
                    armeeJ1.setTailleArmee(3);
                    this.setTextContent("  3  ");
                    System.out.println("MONTAGNE");
                    this.repaint();
                // si c'est une tile "normal"
                } else {
                    System.out.println(" *****  TOUR DU JOUEUR J0  *****");
                    setArmeeTile(armeeJ1);
                    this.setTextContent("  " + armeeJ1.getTailleArmee() + "  ");
                    this.repaint();
                }
                // on gere le tour et si la case a deja ete clique ou non 
                this.tour_joueur = 0;
                this.clique = 1;
                System.out.println(Main.J1.getlistArmee());
                // on set sur la tile quel est bien bleu grace a un bool 
                this.setOccuper_bleu(true);
                System.out.println("Colone : " + this.getColumn());
                System.out.println("Ligne : " + this.getRow());
                // on gere le voisinnage 
                Voisinage voisins = new Voisinage(this);
                System.out.println("Voisins ennemi du bleu : " + voisins.getListEnnemi());
                System.out.println("Voisins allie du bleu : " + voisins.getListAllie());
                System.out.println("size restante : "+Main.J0.getlistArmee().size());
                System.out.println("size restante : "+Main.J1.getlistArmee().size());
                // si plus personne n'a d'armee 
                if (Main.J0.getlistArmee().size() == 0  && Main.J1.getlistArmee().size() == 0) {
                    System.out.println("FIN DU JEU ");
                    carte = carte();
                    System.out.println(score(carte));
                }else{
                // si on a encore des armee on update 
                voisins.majVoisinage(this);
                }
            }
            
        }
        
    }

    
    /** 
     * @param mouseEvent
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    
    /** 
     * @return ArrayList<Tile>
     * creation de la carte 
     */
    public static ArrayList<Tile> carte() {

        for (int row = 0; row < MainIHM.height; row++) {
            for (int column = 0; column < MainIHM.width; column++) {
                carte.add(MainIHM.listeTiles[row][column]);
            }
        }
        return carte;
    }

    
    /** 
     * @param carte
     * @return String
     * permet de calculer le score final en fonction d'une map et de deux fonction occuper rouge / bleu bool qui me
     * permettent de faire un affichage simple du score 
     */
    public static String score( ArrayList<Tile> carte ) {
        int score_bleu = 0 ;
        int score_rouge = 0 ;
        for (int ligne = 0 ; ligne < MainIHM.height  ; ligne++) {
            for (int colonne = 0 ; colonne < MainIHM.width ; colonne++) {
                if(MainIHM.listeTiles[ligne][colonne].getOccuper_Bleu()){
                    score_bleu = score_bleu + 1 ;
                }else if(MainIHM.listeTiles[ligne][colonne].getOccuper_Rouge()){
                    score_rouge = score_rouge + 1 ;
                }
            }
        }
                return new String ("Score du joueur rouge : "+score_rouge+" Score du joueur bleu : "+score_bleu);
    }
    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getImage(){
        return this.tile ;
    }

    
    /** 
     * @param g2D
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    /**
     *
     * Draws additional content inside the tile (default implementation : some text in a circle)
     *
     * @param g2D Graphic context
     */
    protected void drawContents(Graphics2D g2D) {
        if (this.text.length() == 0) return;

        FontMetrics fontMetrics = getFontMetrics(this.getFont());

        Rectangle iconR = new Rectangle();
        Rectangle textR = new Rectangle();

        SwingUtilities.layoutCompoundLabel(
                null,
                fontMetrics,
                text,
                null,
                SwingUtilities.CENTER,
                SwingUtilities.CENTER,
                SwingUtilities.BOTTOM,
                SwingUtilities.CENTER,
                this.getBounds(),
                iconR,
                textR,
                0
        );

        Point loc = getLocation();

        g2D.setColor(Color.white);
        int margin = 5;
        g2D.fillOval(
                textR.x - loc.x - margin,
                textR.y - loc.y - textR.width / 2 + fontMetrics.getHeight() / 2 + this.boundingBox.height / 4 - margin,
                textR.width + 2 * margin,
                textR.width + 2 * margin
        );

        g2D.setColor(this.textBackgroundColor);
        margin = 3;
        g2D.fillOval(
                textR.x - loc.x - margin,
                textR.y - loc.y - textR.width / 2 + fontMetrics.getHeight() / 2 + this.boundingBox.height / 4 - margin,
                textR.width + 2 * margin,
                textR.width + 2 * margin
        );

        g2D.setColor(Color.white);

        g2D.drawString(
                text,
                textR.x - loc.x,
                textR.y - loc.y + fontMetrics.getAscent() + this.boundingBox.height / 4
        );
    }

    private void rescaleTile() {
        AffineTransform transform = AffineTransform.getScaleInstance(((double) this.boundingBox.width) / this.tile.getWidth(), ((double) this.boundingBox.height) / this.tile.getHeight());
        BufferedImage scaled = new BufferedImage(this.boundingBox.width, this.boundingBox.height, BufferedImage.TYPE_INT_ARGB);
        AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

        this.scaledTile = scaleOp.filter(this.tile, scaled);
        this.scaledTile = new BufferedImage(this.boundingBox.width, this.boundingBox.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2D = this.scaledTile.createGraphics();
        g2D.setClip(this.border);
        g2D.drawImage(scaled, 0, 0, null);
    }

    
    /** 
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) (g.create());
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (this.previousHeight != this.boundingBox.height || this.previousWidth != this.boundingBox.width) {
            this.rescaleTile();
            this.previousWidth = this.boundingBox.width;
            this.previousHeight = this.boundingBox.height;
        }

        g2D.drawImage(this.scaledTile, 0, 0, null);

        this.drawContents(g2D);

        // highlights the tile if hovered by mouse
        if (this.isHighlighted && this.highlightable) {
            g2D.setColor(this.highlightColor);
            g2D.fillPolygon(this.border);
        }

        // draw border
        g2D.setColor(Color.black);
        g2D.drawPolygon(border);

        g2D.dispose();
    }

    
    /** 
     * @param g
     */
    @Override
    protected void paintBorder(Graphics g) {
        // no border, thanks
    }

    /**
     * Changes the color used to render the circle around the (text) content
     * @param color
     */
    public void setTextBackgroundColor(Color color) {
        this.textBackgroundColor = color;
    }

    /**
     * sets if this tile is highlightable
     * @param highlightable
     */
    public void setHighlightable(boolean highlightable) {
        this.highlightable = highlightable;
    }

    /**
     * sets the color used to render a highlighted tile. Should be a RGBA color
     * @param color the new (RGBA) color
     */
    public void setHighlightColor(Color color) {
        this.highlightColor = color;
    }

    /**
     * forces a tile to be highlighted or not
     * @param highlighted
     */
    public void setHighlighted(boolean highlighted) {
        this.isHighlighted = highlighted;
        this.repaint();
    }

    /**
     * sets new text content for the tile
     * @param text
     */
    public void setTextContent(String text) {
        this.text = text;
        this.invalidate();
        this.repaint();
    }

    /**
     * sets a new image background for the tile
     * @param image
     */
    public void setTile(BufferedImage image) {
        this.tile = image;
        this.previousHeight = -1;
        this.previousWidth = -1;
    }
}
