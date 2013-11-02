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
        cb.printBoard();
        ////////////////////////////////*/
    }
}
