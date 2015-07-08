package pl.wojciechm.viacom.contest.superdigit;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by wojciechm on 2015-07-08.
 */
public class MainTest {

    @Test
    public void testShortInt() {
        assertEquals(6, Main.calculateSuperDigit("123"));
    }

    @Test(expected = NumberFormatException.class)
    public void testNonInteger() {
        Main.calculateSuperDigit("123a");
    }

    @Test
    public void testVeryLongInteger() {
        assertEquals(7, Main.calculateSuperDigit("" +
                "11111111111111111111111111111111111111111111111111" + // 50 * 1
                "11111111111111111111111111111111111111111111111")); // 47 * 1
    }

    @Test
    public void testNegativeInteger() {
        assertEquals(-6, Main.calculateSuperDigit("-123"));
    }

    @Test
    public void testOverflow() {
        int piece = Integer.MAX_VALUE;
        int change = 4;
        piece += change;
        BigInteger result = BigInteger.ZERO;
        BigInteger modified = Main.overflowTest(piece, result);
        int corrected = Main.overflowCorrectPiece(piece);
        assertNotEquals(result, modified);
        assertEquals(change, corrected);
        assertEquals(Integer.MAX_VALUE, modified.intValue());
    }

    @Test
    public void testNoOverflow() {
        int piece = 0;
        int change = 4;
        piece += change;
        BigInteger result = BigInteger.ZERO;
        BigInteger modified = Main.overflowTest(piece, result);
        int corrected = Main.overflowCorrectPiece(piece);
        assertEquals(result, modified);
        assertEquals(piece, corrected);
    }
}