/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isproject;

/**
 *
 * @author user
 */
public class GameEngine {

    private CheckerBoard cb;

    public GameEngine() {
        cb = new CheckerBoard(8);
        /////////////////////////////testing
        cb.printBoard();
        cb.movePiece(2, 0, 4, 2);
        cb.printBoard();
        if (cb.cutPiece(5, 1, 4, 2)) {
            System.out.println("done");
            cb.printBoard();
        } else {
            System.out.println("false");
        }
        cb.cutPiece(2, 2, 3, 3);
        System.out.println("R"+cb.pieceCount('R')+" B"+cb.pieceCount('B'));
        cb.cutPiece(5, 3, 4, 4);
        System.out.println("R"+cb.pieceCount('R')+" B"+cb.pieceCount('B'));
        cb.cutPiece(2, 6, 3, 5);
        System.out.println("R"+cb.pieceCount('R')+" B"+cb.pieceCount('B'));
        cb.cutPiece(5, 5, 4, 4);
        System.out.println("R"+cb.pieceCount('R')+" B"+cb.pieceCount('B'));
        cb.cutPiece(7,1,6,2);
        cb.printBoard();
        System.out.println("R"+cb.pieceCount('R')+" B"+cb.pieceCount('B'));
        ////////////////////////////////*/
    }
}
