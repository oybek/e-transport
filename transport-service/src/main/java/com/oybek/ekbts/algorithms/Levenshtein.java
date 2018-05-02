package com.oybek.ekbts.algorithms;

public class Levenshtein {
    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static int calc(String s1, String s2, int n, int m) {
        if( m == 0 && n == 0 )
            return 0;

        if( m == 0 && n > 0 )
            return n;

        if( m > 0 && n == 0 )
            return m;

        return min(
                calc(s1, s2, n, m-1) + 1
                , calc(s1, s2, n-1, m) + 1
                , calc(s1, s2, n-1, m-1) + (s1.charAt(n-1) == s2.charAt(m-1) ? 0 : 1)
        );
    }

    public static int calc(String s1, String s2) {
        return calc(s1, s2, s1.length(), s2.length());
    }
}
