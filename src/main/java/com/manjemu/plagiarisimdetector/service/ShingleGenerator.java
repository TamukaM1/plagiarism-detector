package com.manjemu.plagiarisimdetector.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShingleGenerator {
    public static Set<String> generateShingles(List<String> tokens, int n) {
        Set<String> shingles = new HashSet<>();
        for (int i = 0; i <= tokens.size() - n; i++) {
            StringBuilder shingle = new StringBuilder();
            for (int j = 0; j < n; j++) {
                shingle.append(tokens.get(i + j)).append(" ");
            }
            shingles.add(shingle.toString().trim());
        }
        return shingles;
    }
}
