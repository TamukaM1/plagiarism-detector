package com.manjemu.plagiarisimdetector.service;

import java.util.HashSet;
import java.util.Set;

public class SimilarityCalculator {
    public static double calculateJaccardSimilarity(Set<String> shingles1, Set<String> shingles2) {
        Set<String> intersection = new HashSet<>(shingles1);
        intersection.retainAll(shingles2);

        Set<String> union = new HashSet<>(shingles1);
        union.addAll(shingles2);

        return (double) intersection.size() / union.size();
    }
}
