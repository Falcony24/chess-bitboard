import java.util.ArrayList;

public class Game {
    private PawnsWhite pawnsW = new PawnsWhite();
    private PawnsBlack pawnsB = new PawnsBlack();
    private KnightsWhite knightsW = new KnightsWhite();
    private KnightsBlack knightsB = new KnightsBlack();
    private BishopsWhite bishopsW = new BishopsWhite();
    private BishopsBlack bishopsB = new BishopsBlack();
    private RooksWhite rooksW = new RooksWhite();
    private RooksBlack rooksB = new RooksBlack();
    private QueensWhite queensW = new QueensWhite();
    private QueensBlack queensB = new QueensBlack();
    private KingWhite kingW = new KingWhite();
    private KingBlack kingB = new KingBlack();

    private char[] board = new char[64];

    public void buildBoard(){
        ArrayList<Piece> a = new ArrayList<>(12);
        a.add(pawnsW);
        a.add(pawnsB);
        a.add(knightsW);
        a.add(kingB);
        a.add(bishopsW);
        a.add(bishopsB);
        a.add(rooksW);
        a.add(rooksB);
        a.add(queensW);
        a.add(queensB);
        a.add(kingW);
        a.add(kingB);

        String bits;
        char p;
        char[] ps = {'K', 'Q', 'B', 'N', 'R', 'P','k', 'q', 'b', 'n', 'r', 'p'};
        for (Piece b: a) {
            bits = Long.toBinaryString(b.bitBoard);
            p = ps[b.pieceName.ordinal()];
            for(int i = 0; i < bits.length(); i++){
                if(bits.charAt(i) == '1'){
                    board[bits.length() - i - 1] = p;
                }
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            if(i % 8 == 0 ){
                System.out.println();
            }
            System.out.print(board[i] + " ");
        }
    }
}
