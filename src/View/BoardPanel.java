package View;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import Logic.Chip;

public class BoardPanel extends JPanel {

    Image boardImg, blackchipImg, redchipImg, crownImg;
    Chip[] blackchips;
    Chip[] redchips;
    int redMax, blackMax;
    Logic.CheckerBoard checkersBoard;

    public BoardPanel(Logic.CheckerBoard cb) {
        checkersBoard = cb;
        redMax = 12;
        blackMax = 12;;
        ImageIcon boardIcon = new ImageIcon(this.getClass().getResource("board.png"));
        boardImg = boardIcon.getImage();
        ImageIcon blackchipIcon = new ImageIcon(this.getClass().getResource("blackchip.png"));
        blackchipImg = blackchipIcon.getImage();
        ImageIcon redchipIcon = new ImageIcon(this.getClass().getResource("redchip.png"));
        redchipImg = redchipIcon.getImage();
        ImageIcon crownIcon = new ImageIcon(this.getClass().getResource("crown.png"));
        crownImg = crownIcon.getImage();
        blackchips = new Chip[blackMax];
        redchips = new Chip[redMax];
        updateChipInfo();
//        for(int i = 0; i < redMax; i++){
//            redchips[i] = new Chip(i%4,i/4);
//            blackchips[i] = new Chip(i%4,7-i/4);
//        }

    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(boardImg, 0, 0, null);
        updateChipInfo();
        for (int i = 0; i < redMax; i++) {
            //place red chips on the board
            g2d.drawImage(redchipImg, pixelXPos(redchips[i].getCol(), redchips[i].getRow()), pixelYPos(redchips[i].getRow()), null);
            //if the chip is a king, overlap with a crown
            if (redchips[i].isKing()) {
                g2d.drawImage(crownImg, pixelXPos(redchips[i].getCol(), redchips[i].getRow()), pixelYPos(redchips[i].getRow()), null);
            }
            //System.out.print(redchips[i].row+","+redchips[i].col+" ");
        }
        //System.out.println("");
        for (int i = 0; i < blackMax; i++) {
            //place black chips on the board
            g2d.drawImage(blackchipImg, pixelXPos(blackchips[i].getCol(), blackchips[i].getRow()), pixelYPos(blackchips[i].getRow()), null);
            //if the chip is a king, overlap with a crown
            if (blackchips[i].isKing()) {
                g2d.drawImage(crownImg, pixelXPos(blackchips[i].getCol(), blackchips[i].getRow()), pixelYPos(blackchips[i].getRow()), null);
            }
            //System.out.print(blackchips[i].row+","+blackchips[i].col+" ");
        }
        //System.out.println("");
    }

    public int pixelXPos(int col, int row) { // (col,row) left,top
        return (row % 2 != 1) ? col * 140 + 20 : col * 140 + 90;
    }

    public int pixelYPos(int row) { // (col,row) left,top
        return row * 70 + 20;
    }

    public void updateChipInfo() {
        Chip tmpChip;
        redMax = 0;
        blackMax = 0;
        redchips = new Chip[12];
        blackchips = new Chip[12];
        for (int row = 0; row < checkersBoard.getSize(); row++) {
            for (int col = 0; col < checkersBoard.getSize(); col++) {
                tmpChip = new Chip(col / 2, row);
                if (checkersBoard.isUsed('R', row, col)) {
                    tmpChip.setOnBoard(true);
                    if (checkersBoard.isUsedByQueen('R', row, col)) {
                        tmpChip.setIsKing(true);
                    }
                    redchips[redMax++] = tmpChip;
                }
                if (checkersBoard.isUsed('B', row, col)) {
                    tmpChip.setOnBoard(true);
                    if (checkersBoard.isUsedByQueen('B', row, col)) {
                        tmpChip.setIsKing(true);
                    }
                    blackchips[blackMax++] = tmpChip;
                }
            }
        }
    }
}

