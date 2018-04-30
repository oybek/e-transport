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
    public void testSimpleCases() {
        assertEquals( Levenshtein.calc( "", "" ) , 0 );
        assertEquals( Levenshtein.calc( "", "AAAA" ) , 4 );
        assertEquals( Levenshtein.calc( "A", "A" ) , 0 );
        assertEquals( Levenshtein.calc( "A", "AB" ) , 1 );
        assertEquals( Levenshtein.calc( "AB", "A" ) , 1 );
        assertEquals( Levenshtein.calc( "AA", "BB" ) , 2 );
    }

    @Test
    public void testTrickyCases() {
        assertEquals( Levenshtein.calc( "ABBA", "ABABA" ) , 1 );
        assertEquals( Levenshtein.calc( "ABC", "XYZ" ) , 3 );
        assertEquals( Levenshtein.calc( "ABC", "BCA" ) , 2 );
        assertEquals( Levenshtein.calc( "ABCDEF", "BCDEFA" ) , 2 ); // cycle shift
    }

    @Test
    public void testRealCases() {
        assertEquals( Levenshtein.calc( "горьково", "Горького" ) , 2 );
        assertEquals( Levenshtein.calc( "грьково", "Горького" ) , 3 );
    }
}
