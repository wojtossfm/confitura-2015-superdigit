package pl.wojciechm.viacom.contest.superdigit;

import java.math.BigInteger;

public class Main {

    /**
     * Test whether the integer overflowed. Extracting this reduced readability,
     * but it increased testability.
     * @param piece Current partial sum used for calculating the superdigit
     * @param result Current result sum for calculating the superdigit
     * @return unchanged sum if no overflow occured and otherwise a new sum instance
     */
    public static BigInteger overflowTest(int piece, BigInteger result) {
        return (piece < 0) ? result.add(new BigInteger(Integer.toString(Integer.MAX_VALUE))) : result;
    }

    public static int overflowCorrectPiece(int piece) {
        return piece < 0 ? (piece - Integer.MIN_VALUE) + 1 : piece;
    }

    /**
     * Calculate the superdigit of the supplied integer.
     * If the input value is negative then the resulting superdigit will also be negative.
     * @param input String containing an integer
     * @return The calculated superdigit for the supplied value
     */
    public static int calculateSuperDigit(String input) {
        BigInteger result = BigInteger.ZERO;
        BigInteger inputInteger = new BigInteger(input);
        boolean negative = false;
        while (input.length() > 1) {
            result = BigInteger.ZERO;
            //System.out.println("Working on " + input);
            int piece = 0;
            for(int index = 0; index < input.length(); index++) {
                char digit = input.charAt(index);
                if (digit == '-') {
                    negative = true;
                    continue;
                }
                piece += Integer.parseInt(""+ digit);
                BigInteger modified = Main.overflowTest(piece, result);
                // The object changed and so an overflow occured
                if (modified != result) {
                    piece = Main.overflowCorrectPiece(piece);
                    result = modified;
                }
            }
            result = result.add(new BigInteger(Integer.toString(piece)));
            input = result.toString();
        }
        return negative ? -result.intValue() : result.intValue();
    }

    public static void main(String[] args) {
        int result = 0;
        if (args.length != 1) {
            System.out.println("Program must be called with exactly one integer value as an argument");
            System.exit(1);
        }
        try {
            result = Main.calculateSuperDigit(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Program called with a non integer value");
            System.exit(1);
        }
        System.out.println(String.format("The superdigit is %d", result));
    }
}
