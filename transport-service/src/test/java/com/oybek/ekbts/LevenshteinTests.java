package com.oybek.ekbts;

import com.oybek.ekbts.algorithms.Levenshtein;
import junit.framework.TestCase;
import org.junit.Test;

public class LevenshteinTests extends TestCase {
    @Test
    public void testTest() {
        assertEquals( "test", "test" );
    }

    @Test
    public void testNullCases() {
        assertEquals( Levenshtein.calc( "", "" ) , 0 );
        assertEquals( Levenshtein.calc( "A", "A" ) , 0 );
    }

    @Test
    public void testInsertCases() {
        assertEquals( Levenshtein.calc( "A", "AB" ) , 1 );
        assertEquals( Levenshtein.calc( "", "AAAA" ) , 4 );
    }

    @Test
    public void testTrickyCases() {
        assertEquals( Levenshtein.calc( "ABBA", "ABABA" ) , 1 );
        assertEquals( Levenshtein.calc( "ABC", "XYZ" ) , 3 );
        assertEquals( Levenshtein.calc( "ABC", "BCA" ) , 2 );
    }

    @Test
    public void testCycleShift() {
        assertEquals( Levenshtein.calc( "ABCDEF", "BCDEFA" ) , 2 );
    }

    @Test
    public void testRealCases() {
        assertEquals( Levenshtein.calc( "горьково", "Горького" ) , 2 );
        assertEquals( Levenshtein.calc( "грьково", "Горького" ) , 3 );
    }

    @Test
    public void testOfLesha() {
        assertEquals( Levenshtein.calc( "lord666", "orda777" ), 5 );
        assertEquals( Levenshtein.calc( "aaaaaa", "aaabaaa" ), 1 );
    }
}
