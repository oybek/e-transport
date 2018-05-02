package com.oybek.ekbts.algorithms;

import java.util.Arrays;

public class Levenshtein {
    private static final int MAX_STRING_LENGTH = 100;

    private static int[][] memo = new int[MAX_STRING_LENGTH][MAX_STRING_LENGTH];

    private static int calc(String s1, String s2, int n, int m) {
        if (memo[n][m] == -1) {
            memo[n][m] = min(
                    calc(s1, s2, n, m - 1) + 1
                    , calc(s1, s2, n - 1, m) + 1
                    , calc(s1, s2, n - 1, m - 1) + (s1.charAt(n - 1) == s2.charAt(m - 1) ? 0 : 1)
            );
        }
        return memo[n][m];
    }

    public static int calc(String s1, String s2) {
        for (int[] row : memo)
            Arrays.fill(row, -1);

        for (int i = 0; i <= s1.length(); ++i)
            memo[i][0] = i;

        for (int i = 0; i <= s2.length(); ++i)
            memo[0][i] = i;

        return calc(s1, s2, s1.length(), s2.length());
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
