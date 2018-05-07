package com.oybek.ekbts.algorithms;

public class Levenshtein {
    private static final int MAX_STRING_LENGTH = 100;

    private static int[][] memo = new int[MAX_STRING_LENGTH][MAX_STRING_LENGTH];

    public static int calc(String s1, String s2) {
        for (int i = 0; i <= s1.length(); ++i)
            memo[i][0] = i;

        for (int i = 0; i <= s2.length(); ++i)
            memo[0][i] = i;

        for (int i = 1; i <= s1.length(); ++i)
            for (int j = 1; j <= s2.length(); ++j)
                memo[i][j] = min(
                    memo[i][j-1]+1,
                    memo[i-1][j]+1,
                    memo[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1)
                );

        return memo[s1.length()][s2.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
