import java.util.ArrayList;
import java.util.Arrays;

public class SlidersAttack {
    // rook relevant occupancy bits
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

    // find magic number
    long findMagic(int square, int relevant_bits) {
        // define occupancies array
        U64 occupancies[4096];

        // define attacks array
        U64 attacks[4096];

        // define used indicies array
        U64 used_attacks[4096];

        // mask piece attack
        U64 mask_attack = bishop ? mask_bishop_attacks(square) : mask_rook_attacks(square);

        // occupancy variations
        int occupancy_variations = 1 << relevant_bits;

        // loop over the number of occupancy variations
        for(int count = 0; count < occupancy_variations; count++) {
            // init occupancies
            occupancies[count] = set_occupancy(count, relevant_bits, mask_attack);

            // init attacks
            attacks[count] = bishop ? bishop_attacks_on_the_fly(square, occupancies[count]) :
                    rook_attacks_on_the_fly(square, occupancies[count]);
        }

        // test magic numbers
        for(int random_count = 0; random_count < 100000000; random_count++)
        {
            // init magic number candidate
            U64 magic = random_fewbits();

            // skip testing magic number if inappropriate
            if(count_bits((mask_attack * magic) & 0xFF00000000000000ULL) < 6) continue;

            // reset used attacks array
            memset(used_attacks, 0ULL, sizeof(used_attacks));

            // init count & fail flag
            int count, fail;

            // test magic index
            for (count = 0, fail = 0; !fail && count < occupancy_variations; count++) {
                // generate magic index
                int magic_index = (int)((occupancies[count] * magic) >> (64 - relevant_bits));

                // if got free index
                if(used_attacks[magic_index] == 0ULL)
                // assign corresponding attack map
                used_attacks[magic_index] = attacks[count];

                // otherwise fail on  collision
            else if(used_attacks[magic_index] != attacks[count]) fail = 1;
            }

            // return magic if it works
            if(!fail) return magic;
        }

        // on fail
        printf("***Failed***\n");
        return 0ULL;
    }
}
