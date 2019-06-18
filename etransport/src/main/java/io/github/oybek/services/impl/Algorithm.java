package io.github.oybek.services.impl;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class Algorithm {

    private final int MAX_STRING_LENGTH = 100;
    private final int[][] dist = new int[MAX_STRING_LENGTH][MAX_STRING_LENGTH];

    private int calc(String s1, String s2) {
        for (int i = 0; i <= s1.length(); ++i) {
            dist[i][0] = i;
        }

        for (int i = 0; i <= s2.length(); ++i) {
            dist[0][i] = i;
        }

        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                dist[i][j] = min(
                        dist[i][j - 1] + 1,
                        dist[i - 1][j] + 1,
                        dist[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1)
                );
            }
        }

        return dist[s1.length()][s2.length()];
    }

    int calcDist(List<String> s1s, String s2) {
        final String s2prepared = prepare(s2);
        return s1s
                .stream()
                .map(s1 -> calc(prepare(s1), s2prepared))
                .min(Integer::compare)
                .orElse(100);
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    // Prepares string before Levenshtein calculations
    private String prepare(String s) {
        return s
                .trim()
                .toLowerCase()
                .replaceAll("пл\\.", "")
                .replaceAll("ст\\.", "")
                .replaceAll("г\\.", "")
                .replaceAll("гост\\.", "")
                .replaceAll("пер\\.", "")
                .replaceAll("м\\.", "")
                .replaceAll("трц", "")
                .replaceAll("площадь", "")
                .replaceAll("остановка", "")
                .replaceAll("\\(.*\\)", "")
                .replaceAll( "[^0-9а-я]", "" );
    }
}
