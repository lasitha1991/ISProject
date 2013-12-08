/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class CheckerBoard {

    private char[][] checkersBoard;
    private int boardSize;
    private char typeR, typeB, empty;
    private List<Chip> typeRList;
    private List<Chip> typeBList;

    public CheckerBoard(int size) {
        boardSize = size;
        checkersBoard = new char[boardSize][boardSize];
        typeR = 'r';
        typeB = 'b';
        empty = '_';
        typeRList = new LinkedList<Chip>();
        typeBList = new LinkedList<Chip>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    checkersBoard[i][j] = empty;    //EMPTY cell
                }
            }
        }
        for (int i = 0; i < (boardSize / 2) - 1; i++) {
            int j;
            if (i % 2 == 0) {
                j = 0;
            } else {
                j = 1;
            }
            while (j < boardSize) {
                checkersBoard[i][j] = typeR;    //RED piece
                typeRList.add(new Chip(j, i));
                j += 2;
            }
        }
        for (int i = boardSize - 1; i >= (boardSize) / 2 + 1; i--) {
            int j;
            if (i % 2 == 0) {
                j = 0;
            } else {
                j = 1;
            }
            while (j < boardSize) {
                checkersBoard[i][j] = typeB;     //BLACK piece
                typeBList.add(new Chip(j, i));
                j += 2;
            }
        }
    }

    public void printBoard() {
        System.out.println("*********************************");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print(checkersBoard[i][j] + " " + i + "," + j + "\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
        System.out.println("*********************************");
    }

    public boolean movePiece(int sRow, int sCol, int dRow, int dCol) {
        if (isMoveable(sRow, sCol, dRow, dCol)) {
            char tmpType = checkersBoard[sRow][sCol];
            checkersBoard[sRow][sCol] = empty;
            checkersBoard[dRow][dCol] = tmpType;
            if (tmpType == typeR) {
                Chip tmpChip = typeRList.get(typeRList.indexOf(new Chip(sCol, sRow)));
                tmpChip.setCol(dCol);
                tmpChip.setRow(dRow);
            }
            if (tmpType == typeB) {
                Chip tmpChip = typeBList.get(typeBList.indexOf(new Chip(sCol, sRow)));
                tmpChip.setCol(dCol);
                tmpChip.setRow(dRow);
            }

            if (tmpType == typeR && dRow == boardSize - 1) {
                checkersBoard[dRow][dCol] = Character.toUpperCase(typeR);     //uppercase typeR to represent RED QUEEN 
                typeRList.get(typeRList.indexOf(new Chip(dCol, dRow))).setIsKing(true);
            }
            if (tmpType == typeB && dRow == 0) {
                checkersBoard[dRow][dCol] = Character.toUpperCase(typeB);     //uppercase typeB to represent BLACK QUEEN
                typeBList.get(typeBList.indexOf(new Chip(dCol, dRow))).setIsKing(true);
            }
            return true;
        }
        return false;
    }

    public boolean isMoveable(int sRow, int sCol, int dRow, int dCol) {
        if (dRow >= boardSize || dRow < 0) {
            return false;
        }
        if (dCol >= boardSize || dCol < 0) {
            return false;
        }
        if (checkersBoard[sRow][sCol] == empty) {
            return false;
        } else if ((checkersBoard[dRow][dCol] == empty)) {
            if (sCol == dCol) {
                return false;
            }
            char tmpType = checkersBoard[sRow][sCol];
            if (tmpType == typeR) {
                if (sRow - dRow >= 0) {
                    return false;
                }
            }
            if (tmpType == typeB) {
                if (sRow - dRow <= 0) {
                    return false;
                }
            }
            if (Math.abs(dCol - sCol) > 2 || Math.abs(dRow - sRow) > 2) {
                //not allowing too long moves
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cutPiece(int attackerRow, int attackerCol, int victimRow, int victimCol) {
        char tmpAttacker, tmpVictim;
        if (checkersBoard[attackerRow][attackerCol] == empty || checkersBoard[victimRow][victimCol] == empty) {
            return false;
        } else {
            tmpAttacker = checkersBoard[attackerRow][attackerCol];
            tmpVictim = checkersBoard[victimRow][victimCol];
        }
        if (tmpAttacker == tmpVictim) {
            return false;
        } else {
            if (attackerRow > victimRow) {
                if (isMoveable(attackerRow, attackerCol, victimRow - 1, victimCol - 1)) {
                    checkersBoard[victimRow][victimCol] = empty;
                    if (tmpVictim == typeR) {
                        typeRList.get(typeRList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    if (tmpVictim == typeB) {
                        typeBList.get(typeBList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    movePiece(attackerRow, attackerCol, victimRow - 1, victimCol - 1);
                    return true;
                }
                if (isMoveable(attackerRow, attackerCol, victimRow - 1, victimCol + 1)) {
                    checkersBoard[victimRow][victimCol] = empty;
                    if (tmpVictim == typeR) {
                        typeRList.get(typeRList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    if (tmpVictim == typeB) {
                        typeBList.get(typeBList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    movePiece(attackerRow, attackerCol, victimRow - 1, victimCol + 1);
                    return true;
                }
            }
            if (attackerRow < victimRow) {
                if (isMoveable(attackerRow, attackerCol, victimRow + 1, victimCol - 1)) {
                    checkersBoard[victimRow][victimCol] = empty;
                    if (tmpVictim == typeR) {
                        typeRList.get(typeRList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    if (tmpVictim == typeB) {
                        typeBList.get(typeBList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    movePiece(attackerRow, attackerCol, victimRow + 1, victimCol - 1);
                    return true;
                }
                if (isMoveable(attackerRow, attackerCol, victimRow + 1, victimCol + 1)) {
                    checkersBoard[victimRow][victimCol] = empty;
                    if (tmpVictim == typeR) {
                        typeRList.get(typeRList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    if (tmpVictim == typeB) {
                        typeBList.get(typeBList.indexOf(new Chip(victimCol, victimRow))).setOnBoard(false);
                    }
                    movePiece(attackerRow, attackerCol, victimRow + 1, victimCol + 1);
                    return true;
                }
            }
        }
        return false;
    }

    public int pieceCount(char type) {
        int count = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    if (isUsed(type, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public boolean isUsed(char type, int row, int col) {
        if (checkersBoard[row][col] == Character.toLowerCase(type) || checkersBoard[row][col] == Character.toUpperCase(type)) {
            return true;
        }
        return false;
    }

    public boolean isUsedByNormalPiece(char type, int row, int col) {
        if (checkersBoard[row][col] == Character.toLowerCase(type)) {
            return true;
        }
        return false;
    }

    public boolean isUsedByQueen(char type, int row, int col) {
        if (checkersBoard[row][col] == Character.toUpperCase(type)) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return boardSize;
    }

    public List<Chip> getTypeRList() {
        return typeRList;
    }

    public List<Chip> getTypeBList() {
        return typeBList;
    }

    public int calcHeuristicValue(char type) {
        HashMap<Character, Integer> map = calcBoardHeuristicValue3();
        int heuristicValueR = map.get(typeR);
        int heuristicValueB = map.get(typeB);
        if (Character.toLowerCase(type) == Character.toLowerCase(typeR)) {
            return heuristicValueR - heuristicValueB;
        } else if (Character.toLowerCase(type) == Character.toLowerCase(typeB)) {
            return heuristicValueB - heuristicValueR;
        }
        return -1;
    }

    private HashMap calcBoardHeuristicValue3() {
        int heuristicValueR = 0;
        int heuristicValueB = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    if (isUsedByNormalPiece(typeR, i, j)) {
                        heuristicValueR += 5 + i;
                    }
                    if (isUsedByQueen(typeR, i, j)) {
                        heuristicValueR += 5 + i + 2;
                    }
                }
            }
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    if (isUsed(typeB, i, j)) {
                        heuristicValueB += 5 + (7 - i);
                    }
                    if (isUsedByQueen(typeB, i, j)) {
                        heuristicValueB += 5 + (7 - i) + 2;
                    }
                }
            }
        }
        HashMap<Character, Integer> map;
        map = new HashMap<Character, Integer>();
        map.put(typeB, heuristicValueB);
        map.put(typeR, heuristicValueR);
        return map;
    }

    private HashMap calcBoardHeuristicValue2() {
        int heuristicValueR = 0;
        int heuristicValueB = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    if (isUsedByNormalPiece(typeR, i, j)) {
                        if (i < 4) {
                            heuristicValueR += 5;
                        } else {
                            heuristicValueR += 7;
                        }
                    }
                    if (isUsedByQueen(typeR, i, j)) {
                        heuristicValueR += 10;
                    }
                }
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    if (isUsed(typeB, i, j)) {
                        if (i > 3) {
                            heuristicValueB += 5;
                        } else {
                            heuristicValueB += 7;
                        }
                    }
                    if (isUsedByQueen(typeB, i, j)) {
                        heuristicValueB += 10;
                    }
                }
            }
        }
        HashMap<Character, Integer> map;
        map = new HashMap<Character, Integer>();
        map.put(typeB, heuristicValueB);
        map.put(typeR, heuristicValueR);
        return map;
    }
    
    private HashMap calcBoardHeuristicValue4() {               
        HashMap<Character, Integer> map;
        map = this.calcBoardHeuristicValue2();
        float heuristicValueR = map.get(typeR);
        float heuristicValueB = map.get(typeB);
        heuristicValueR=heuristicValueR*10/this.pieceCount(typeR);
        heuristicValueB=heuristicValueB*10/this.pieceCount(typeB);
        map.put(typeB, (int)heuristicValueB);
        map.put(typeR, (int)heuristicValueR);
        return map;
        
    }
}
