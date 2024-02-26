public class Main {
    public static void main(String[] args) {
        Game a = new Game();
//        a.printBoard(a.buildBoard());
        a.printAttackedBoard(a.rooksW.getValidMoves(boardSquares.A1));
    }
}
