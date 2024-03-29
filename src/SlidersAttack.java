import java.util.ArrayList;
import java.util.Arrays;

public class SlidersAttack {
    private char piece;

    public SlidersAttack(char piece) {
        this.piece = piece;
    }

    protected static final ArrayList<Integer> rooksRelevantBits = new ArrayList<>(Arrays.asList(
            12, 11, 11, 11, 11, 11, 11, 12,
            11, 10, 10, 10, 10, 10, 10, 11,
            11, 10, 10, 10, 10, 10, 10, 11,
            11, 10, 10, 10, 10, 10, 10, 11,
            11, 10, 10, 10, 10, 10, 10, 11,
            11, 10, 10, 10, 10, 10, 10, 11,
            11, 10, 10, 10, 10, 10, 10, 11,
            12, 11, 11, 11, 11, 11, 11, 12
    ));

    // bishop relevant occupancy bits
    protected static final ArrayList<Integer> bishopsRelevantBits = new ArrayList<>(Arrays.asList(
            6, 5, 5, 5, 5, 5, 5, 6,
            5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 7, 7, 7, 7, 5, 5,
            5, 5, 7, 9, 9, 7, 5, 5,
            5, 5, 7, 9, 9, 7, 5, 5,
            5, 5, 7, 7, 7, 7, 5, 5,
            5, 5, 5, 5, 5, 5, 5, 5,
            6, 5, 5, 5, 5, 5, 5, 6
    ));

    public long maskRook(boardSquares square)
    {
        long attacks = 0L;
        int f, r;

        int tr = square.ordinal() / 8;
        int tf = square.ordinal() % 8;

        for (r = tr + 1; r <= 6; r++) attacks |= (1L << (r * 8 + tf));
        for (r = tr - 1; r >= 1; r--) attacks |= (1L << (r * 8 + tf));
        for (f = tf + 1; f <= 6; f++) attacks |= (1L << (tr * 8 + f));
        for (f = tf - 1; f >= 1; f--) attacks |= (1L << (tr * 8 + f));

        return attacks;
    }

    public long maskBishop(boardSquares square)
    {
        long attacks = 0L;
        int f, r;

        int tr = square.ordinal() / 8;
        int tf = square.ordinal() % 8;

        for (r = tr + 1, f = tf + 1; r <= 6 && f <= 6; r++, f++) attacks |= (1L << (r * 8 + f));
        for (r = tr + 1, f = tf - 1; r <= 6 && f >= 1; r++, f--) attacks |= (1L << (r * 8 + f));
        for (r = tr - 1, f = tf + 1; r >= 1 && f <= 6; r--, f++) attacks |= (1L << (r * 8 + f));
        for (r = tr - 1, f = tf - 1; r >= 1 && f >= 1; r--, f--) attacks |= (1L << (r * 8 + f));

        return attacks;
    }

    long setOccupancy(int index, int bitsInMask, long attackMask)
    {
        long occupancy = 0L;

        for (int i = 0; i < bitsInMask; i++){
            int square = BitManipulation.getLS1BIndex(attackMask);
            BitManipulation.popBit(attackMask, square);

            if ((index & (1 << i)) != 0) occupancy |= (1L << square);
        }
        return occupancy;
    }
    long findMagic(boardSquares square) {
        long[] occupancies = new long[4096];
        long[] attacks = new long[4096];
        long[] usedAttacks = new long[4096];
        long maskAttack;
        int occupancyVariations;
        int relevantBits;

        if(piece == 'r' || piece == 'R') {
            maskAttack = maskRook(square);
            relevantBits = rooksRelevantBits.get(square.ordinal());
            occupancyVariations = 1 << relevantBits;
        }
        else {
            maskAttack = maskBishop(square);
            relevantBits = bishopsRelevantBits.get(square.ordinal());
            occupancyVariations = 1 << relevantBits;
        }


        for(int i = 0; i < occupancyVariations; i++) {
            occupancies[i] = setOccupancy(i, relevantBits, maskAttack);

            System.out.println(occupancies[i]);
            // init attacks
//            attacks[i] = bishop ? bishop_attacks_on_the_fly(square, occupancies[count]) :
//                    rook_attacks_on_the_fly(square, occupancies[count]);
        }
//
//        // test magic numbers
//        for(int random_count = 0; random_count < 100000000; random_count++)
//        {
//            // init magic number candidate
//            U64 magic = random_fewbits();
//
//            // skip testing magic number if inappropriate
//            if(count_bits((mask_attack * magic) & 0xFF00000000000000ULL) < 6) continue;
//
//            // reset used attacks array
//            memset(used_attacks, 0ULL, sizeof(used_attacks));
//
//            // init count & fail flag
//            int count, fail;
//
//            // test magic index
//            for (count = 0, fail = 0; !fail && count < occupancy_variations; count++) {
//                // generate magic index
//                int magic_index = (int)((occupancies[count] * magic) >> (64 - relevant_bits));
//
//                // if got free index
//                if(used_attacks[magic_index] == 0ULL)
//                // assign corresponding attack map
//                used_attacks[magic_index] = attacks[count];
//
//                // otherwise fail on  collision
//            else if(used_attacks[magic_index] != attacks[count]) fail = 1;
//            }
//
//            // return magic if it works
//            if(!fail) return magic;
//        }
//
//        // on fail
//        printf("***Failed***\n");
        return 0L;
    }
}
