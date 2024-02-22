public class Game {
    public PawnsWhite pawnsW = new PawnsWhite();
    public PawnsBlack pawnsB = new PawnsBlack();
    public KnightsWhite knightsW = new KnightsWhite();
    public KnightsBlack knightsB = new KnightsBlack();
    public BishopsWhite bishopsW = new BishopsWhite();
    public BishopsBlack bishopsB = new BishopsBlack();
    public RooksWhite rooksW = new RooksWhite();
    public RooksBlack rooksB = new RooksBlack();
    public QueensWhite queensW = new QueensWhite();
    public QueensBlack queensB = new QueensBlack();
    public KingWhite kingW = new KingWhite();
    public KingBlack kingB = new KingBlack();

    private char[] board = new char[64];

    public char[] buildBoard() {
        char[] board = new char[64];

        long bitBoard = Piece.allPieces;
        while (bitBoard != 0) {
            int index = Long.numberOfTrailingZeros(bitBoard);
            board[63 - index] = '1';
            bitBoard ^= 1L << index;
        }
        return board;
    }

    public void printBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.print(board[i] + " ");
        }
    }

    private char[] buildAttackedBoard(long bitBoard)
    {
        char[] board = new char[64];
        String bits = Long.toBinaryString(bitBoard);
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                board[bits.length() - i - 1] = '1';
            }
        }
        return board;
    }

    public void printAttackedBoard(long bitBoard) {
        char[] board = buildAttackedBoard(bitBoard);

        for (int i = 0; i < board.length; i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.print(board[i] + " ");
        }
    }
}
