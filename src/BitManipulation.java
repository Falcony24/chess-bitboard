public class BitManipulation {
    static long getBit(long bitboard, int square) {return (bitboard & (1L << square));}
    static long setBit(long bitboard, int square) {return (bitboard | (1L << square));}
    static long popBit(long bitboard, int square) {return (getBit(bitboard, square) != 0 ? (bitboard ^ (1L << square)) : 0);}
    static int countBits(long bitboard) {
        int count = 0;

        while (bitboard != 0) {
            count++;
            bitboard &= bitboard - 1;
        }
        return count;
    }
    static int getLS1BIndex(long bitBoard) {return countBits((bitBoard & -bitBoard) - 1);}
}
