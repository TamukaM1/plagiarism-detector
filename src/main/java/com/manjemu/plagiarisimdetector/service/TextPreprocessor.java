package com.manjemu.plagiarisimdetector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TextPreprocessor {
    public static List<String> preprocess(String text) {
        // Remove all non-alphanumeric characters except whitespace
        text = text.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

        // Tokenize the cleaned text
        StringTokenizer tokenizer = new StringTokenizer(text);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
}
