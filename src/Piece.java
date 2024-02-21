enum boardSquares {
    A1, B1, C1, D1, E1, F1, G1, H1,
    A2, B2, C2, D2, E2, F2, G2, H2,
    A3, B3, C3, D3, E3, F3, G3, H3,
    A4, B4, C4, D4, E4, F4, G4, H4,
    A5, B5, C5, D5, E5, F5, G5, H5,
    A6, B6, C6, D6, E6, F6, G6, H6,
    A7, B7, C7, D7, E7, F7, G7, H7,
    A8, B8, C8, D8, E8, F8, G8, H8
}

enum boardFiles {
    A, B, C, D, E, F, G, H
}

public class Piece {
    public long bitBoard;
    public piecesName pieceName;
//    public static long allWhite = 0B1111111111111111L;
    public static long allWhite = 0B1000000001111111111111110L;
    public static long allBlack = 0B1111111111111111000000000000000000000000000000000000000000000000L;
    public static long allPieces = allWhite | allBlack;

    public Piece(piecesName pieceName) {
        setBitBoard(pieceName);
        this.pieceName = pieceName;
    }

    public void setBitBoard(piecesName pieceName) {
        switch (pieceName){
            case whitePawns -> bitBoard = 0B1111111100000000L;
//            case whiteRooks -> bitBoard = 0B10000001L;
            case whiteRooks -> bitBoard = 0B10000000010000000L;
            case whiteKnights -> bitBoard = 0B1000010L;
            case whiteBishops -> bitBoard = 0B100100L;
            case whiteQueens -> bitBoard = 0B1000L;
            case whiteKing -> bitBoard = 0B10000L;

            case blackPawns -> bitBoard = 0B11111111000000000000000000000000000000000000000000000000L;
            case blackRooks -> bitBoard = 0B1000000100000000000000000000000000000000000000000000000000000000L;
            case blackKnights -> bitBoard = 0B100001000000000000000000000000000000000000000000000000000000000L;
            case blackBishops -> bitBoard = 0B10010000000000000000000000000000000000000000000000000000000000L;
            case blackQueens -> bitBoard = 0B100000000000000000000000000000000000000000000000000000000000L;
            case blackKing -> bitBoard = 0B1000000000000000000000000000000000000000000000000000000000000L;
        }
    }

    public long maskRank(int a) {
        return (0B1111_1111L << a * 8);
    }
    public long clearRank(int a) {
        return ~(0B1111111111111111111111111111111111111111111111111111111111111111L & maskRank(a));
    }

    public long maskFile(boardFiles a) {
        return (0B100000001000000010000000100000001000000010000000100000001L << a.ordinal());
    }
    public long clearFile(boardFiles a) {
        return ~(0B1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111L & maskFile(a));
    }

    public void updateAll(){
        allPieces = allWhite | allBlack;
    }
}

class KingWhite extends Piece{

    public KingWhite() {
        super(piecesName.whiteKing);
    }

    public long getValidMoves(){
        long spot1 = bitBoard << 7;
        long spot2 = bitBoard << 8;
        long spot3 = bitBoard << 9;
        long spot4 = bitBoard << 1;
        long spot5 = bitBoard >> 7;
        long spot6 = bitBoard >> 8;
        long spot7 = bitBoard >> 9;
        long spot8 = bitBoard >> 1;

        return (spot1 | spot2 | spot3 | spot4 | spot5 | spot7 | spot8 | spot6) & (~allWhite);
    }
}

class KingBlack extends Piece{

    public KingBlack() {
        super(piecesName.blackKing);
    }

    public long getValidMoves() {
        long spot1 = bitBoard << 7;
        long spot2 = bitBoard << 8;
        long spot3 = bitBoard << 9;
        long spot4 = bitBoard << 1;
        long spot5 = bitBoard >> 7;
        long spot6 = bitBoard >> 8;
        long spot7 = bitBoard >> 9;
        long spot8 = bitBoard >> 1;

        return (spot1 | spot2 | spot3 | spot4 | spot5 | spot7 | spot8 | spot6) & (~allBlack);
    }
}

class KnightsWhite extends Piece{

    public KnightsWhite() {
        super(piecesName.whiteKnights);
    }

    public long getValidMoves(boardSquares loc) {
        long localisation = bitBoard & (long) Math.pow(2, loc.ordinal());
        long fileA = clearFile(boardFiles.A);
        long fileH = clearFile(boardFiles.H);
        long fileAB = fileA & clearFile(boardFiles.B);
        long fileGH = fileH & clearFile(boardFiles.G);

        long spot1 = (localisation & fileAB) << 6;
        long spot2 = (localisation & fileA) << 15;
        long spot3 = (localisation & fileH) << 17;
        long spot4 = (localisation & fileGH) << 10;
        long spot5 = (localisation & fileGH) >> 6;
        long spot6 = (localisation & fileH) >> 15;
        long spot7 = (localisation & fileA) >> 17;
        long spot8 = (localisation & fileAB) >> 10;

        return (spot1 | spot2 | spot3 | spot4 | spot5 | spot7 | spot8 | spot6) & (~allWhite);
    }
}

class KnightsBlack extends Piece{

    public KnightsBlack() {
        super(piecesName.blackKnights);
    }

    public long getValidMoves(boardSquares loc) {
        long localisation = bitBoard & (long) Math.pow(2, loc.ordinal());
        long fileA = clearFile(boardFiles.A);
        long fileH = clearFile(boardFiles.H);
        long fileAB = fileA & clearFile(boardFiles.B);
        long fileGH = fileH & clearFile(boardFiles.G);

        long spot1 = (localisation & fileAB) << 6;
        long spot2 = (localisation & fileA) << 15;
        long spot3 = (localisation & fileH) << 17;
        long spot4 = (localisation & fileGH) << 10;
        long spot5 = (localisation & fileGH) >> 6;
        long spot6 = (localisation & fileH) >> 15;
        long spot7 = (localisation & fileA) >> 17;
        long spot8 = (localisation & fileAB) >> 10;

        return (spot1 | spot2 | spot3 | spot4 | spot5 | spot7 | spot8 | spot6) & (~allBlack);
    }
}

class PawnsWhite extends Piece{

    public PawnsWhite() {
        super(piecesName.whitePawns);
    }

    public long getValidMoves(boardSquares loc) {
        long localisation = bitBoard & (long) Math.pow(2, loc.ordinal());
        long singleStep = (localisation << 8) & (~allPieces);
        long doubleStep = (singleStep << 8) & (~allPieces);

        return (singleStep | doubleStep ) & (~allPieces);
    }
}

class PawnsBlack extends Piece{

    public PawnsBlack() {
        super(piecesName.blackPawns);
    }

    public long getValidMoves(boardSquares loc) {
        long localisation = bitBoard & (long) Math.pow(2, loc.ordinal());
        long singleStep = (localisation >> 8) & (~allPieces);
        long doubleStep = (singleStep >> 8) & (~allPieces);

        return (singleStep | doubleStep ) & (~allPieces);
    }

}

class BishopsWhite extends Piece{

    public BishopsWhite() {
        super(piecesName.whiteBishops);
    }
}

class BishopsBlack extends Piece{

    public BishopsBlack() {
        super(piecesName.blackBishops);
    }
}

class RooksWhite extends Piece{

    public RooksWhite() {
        super(piecesName.whiteRooks);
    }
}

class RooksBlack extends Piece{

    public RooksBlack() {
        super(piecesName.blackRooks);
    }
}

class QueensWhite extends Piece{

    public QueensWhite() {
        super(piecesName.whiteQueens);
    }
}

class QueensBlack extends Piece{

    public QueensBlack() {
        super(piecesName.blackQueens);
    }
}