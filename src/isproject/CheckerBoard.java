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
                checkersBoard[i][j] = '_';    //EMPTY cell
            } 
        }
        for (int i = 0; i < (boardSize - 2) / 2; i++) {
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
                System.out.print(checkersBoard[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("*********************************");
    }

    public boolean movePiece(int sRow, int sCol, int dRow, int dCol) {
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
            if (tmpAttacker == 'E' || tmpAttacker == 'V') {
                if (attackerRow > victimRow) {
                    if (victimCol - 1 >= 0 && victimRow - 1 >= 0 && checkersBoard[victimRow - 1][victimCol - 1] == '_') {
                        checkersBoard[victimRow][victimCol] = '_';
                        movePiece(attackerRow, attackerCol, victimRow - 1, victimCol - 1);
                        return true;
                    }
                    if (victimCol + 1 < boardSize && victimRow - 1 >= 0 && checkersBoard[victimRow - 1][victimCol - 1] == '_') {
                        checkersBoard[victimRow][victimCol] = '_';
                        movePiece(attackerRow, attackerCol, victimRow - 1, victimCol + 1);
                        return true;
                    }
                }
                if (attackerRow < victimRow) {
                    if (victimCol - 1 >= 0 && victimRow + 1 < boardSize && checkersBoard[victimRow + 1][victimCol - 1] == '_') {
                        checkersBoard[victimRow][victimCol] = '_';
                        movePiece(attackerRow, attackerCol, victimRow + 1, victimCol - 1);
                        return true;
                    }
                    if (victimCol + 1 < boardSize && victimRow + 1 < boardSize && checkersBoard[victimRow + 1][victimCol - 1] == '_') {
                        checkersBoard[victimRow][victimCol] = '_';
                        movePiece(attackerRow, attackerCol, victimRow + 1, victimCol + 1);
                        return true;
                    }

                }
            }
            if (attackerRow > victimRow && tmpAttacker == 'B') {
                if (attackerCol > victimCol && victimCol - 1 >= 0 && victimRow - 1 >= 0 && checkersBoard[victimRow - 1][victimCol - 1] == '_') {
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow - 1, victimCol - 1);
                    return true;
                }
                if (attackerCol < victimCol && victimCol + 1 < boardSize && victimRow - 1 >= 0 && checkersBoard[victimRow - 1][victimCol - 1] == '_') {
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow - 1, victimCol + 1);
                    return true;
                }
            }
            if (attackerRow < victimRow && tmpAttacker == 'R') {
                if (attackerCol > victimCol && victimCol - 1 >= 0 && victimRow + 1 < boardSize && checkersBoard[victimRow + 1][victimCol - 1] == '_') {
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow + 1, victimCol - 1);
                    return true;
                }
                if (attackerCol < victimCol && victimCol + 1 < boardSize && victimRow + 1 < boardSize && checkersBoard[victimRow + 1][victimCol - 1] == '_') {
                    checkersBoard[victimRow][victimCol] = '_';
                    movePiece(attackerRow, attackerCol, victimRow + 1, victimCol + 1);
                    return true;
                }
            }
        }
        return false;
    }
}
