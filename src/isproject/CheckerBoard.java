/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isproject;

/**
 *
 * @author user
 */
public class CheckerBoard {

    private char[][] checkersBoard;
    private int boardSize;

    public CheckerBoard(int size) {
        boardSize = size;
        checkersBoard = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) {
                    checkersBoard[i][j] = '_';    //EMPTY cell
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
                checkersBoard[i][j] = 'R';    //RED piece
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
                checkersBoard[i][j] = 'B';     //BLACK piece
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
            checkersBoard[sRow][sCol] = '_';
            checkersBoard[dRow][dCol] = tmpType;
            if (tmpType == 'R' && dRow == boardSize - 1) {
                checkersBoard[dRow][dCol] = 'E';     //E to represent RED QUEEN
            }
            if (tmpType == 'B' && dRow == 0) {
                checkersBoard[dRow][dCol] = 'V';     //V to represent BLACK QUEEN
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
        if (checkersBoard[sRow][sCol] == '_') {
            return false;
        } else if ((checkersBoard[dRow][dCol] == '_')) {
            if (sCol == dCol) {
                return false;
            }
            char tmpType = checkersBoard[sRow][sCol];
            if (tmpType == 'R') {
                if (sRow - dRow >= 0) {
                    return false;
                }
            }
            if (tmpType == 'B') {
                if (sRow - dRow <= 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean cutPiece(int attackerRow, int attackerCol, int victimRow, int victimCol) {
        char tmpAttacker, tmpVictim;
        if (checkersBoard[attackerRow][attackerCol] == '_' || checkersBoard[victimRow][victimCol] == '_') {
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
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow - 1, victimCol - 1);
                    return true;
                }
                if (isMoveable(attackerRow, attackerCol, victimRow-1, victimCol+1)){
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow - 1, victimCol + 1);
                    return true;
                }
            }
            if (attackerRow < victimRow) {
                if (isMoveable(attackerRow, attackerCol, victimRow+1, victimCol-1)){                        
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow + 1, victimCol - 1);
                    return true;
                }
                if (isMoveable(attackerRow, attackerCol, victimRow+1, victimCol+1)){
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow + 1, victimCol + 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    public int pieceCount(char type){
        int count=0;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if((i+j)%2==0 && checkersBoard[i][j]==type)
                    count++;
            }
        }
        return count;
    }
}
