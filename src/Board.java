import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;

import javax.swing.ImageIcon;

import javax.swing.JPanel;

public class Board extends JPanel {
    
    Image boardImg, blackchipImg, redchipImg, crownImg;
    Chip[] blackchips;
    Chip[] redchips;
    
    public Board() {
        ImageIcon boardIcon = new ImageIcon(this.getClass().getResource("board.png"));
        boardImg = boardIcon.getImage();
        ImageIcon blackchipIcon = new ImageIcon(this.getClass().getResource("blackchip.png"));
        blackchipImg = blackchipIcon.getImage();
        ImageIcon redchipIcon = new ImageIcon(this.getClass().getResource("redchip.png"));
        redchipImg = redchipIcon.getImage();
        ImageIcon crownIcon = new ImageIcon(this.getClass().getResource("crown.png"));
        crownImg = crownIcon.getImage();
        blackchips = new Chip[12];
        redchips = new Chip[12];
        for(int i = 0; i < 12; i++){
            redchips[i] = new Chip(i%4,i/4);
            blackchips[i] = new Chip(i%4,7-i/4);
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(boardImg, 0, 0, null);
        updateChipInfo();
         for(int i = 0;i<12;i++){
                //place red chips on the board
                g2d.drawImage(redchipImg, pixelXPos(redchips[i].col, redchips[i].row), pixelYPos(redchips[i].row), null);
                //if the chip is a king, overlap with a crown
                if(redchips[i].isKing)
                    g2d.drawImage(crownImg, pixelXPos(redchips[i].col, redchips[i].row), pixelYPos(redchips[i].row), null);
                
                //place black chips on the board
                g2d.drawImage(blackchipImg, pixelXPos(blackchips[i].col,blackchips[i].row), pixelYPos(blackchips[i].row), null);
                //if the chip is a king, overlap with a crown
                if(blackchips[i].isKing)
                    g2d.drawImage(crownImg, pixelXPos(blackchips[i].col,blackchips[i].row), pixelYPos(blackchips[i].row), null);
            }
    }
    
    public int pixelXPos (int col, int row){ // (col,row) left,top
        return (row%2==1) ? col * 140 + 20: col * 140 + 90;
    }
    
    public int pixelYPos (int row){ // (col,row) left,top
        return row * 70 + 20;
    }
    
    public void updateChipInfo(){
        
    }
            
}

class Chip{
    int col, row;
    boolean onBoard, isKing;
    
    public Chip(int col, int row){
        this.col = col;
        this.row = row;
        onBoard = true;
        isKing = false;
    }
}
