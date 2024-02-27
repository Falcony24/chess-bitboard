public class Main {
    public static void main(String[] args) {
        Game a = new Game();
//        a.printBoard(a.buildBoard());
//        a.printAttackedBoard(a.rooksW.getValidMoves(boardSquares.A1));
        a.rooksW.getValidMoves(boardSquares.B2);
//        a.bishopsB.getValidMoves(boardSquares.B2);
    }
}
