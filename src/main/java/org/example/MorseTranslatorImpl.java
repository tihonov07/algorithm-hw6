package org.example;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class MorseTranslatorImpl implements MorseTranslator {

    private Node root = new Node();
    private final Map<Character, String> morseMap = new HashMap<>();

    public MorseTranslatorImpl() {
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
        morseMap.put(' ', "/");
        morseMap.forEach((key, val) -> insert(val, key));
    }

    private void insert(String morseCode, char letter) {
        Node current = root;
        for (char c : morseCode.toCharArray()) {
            current.children.putIfAbsent(c, new Node());
            current = current.children.get(c);
        }
        current.letter = letter;
    }

    @Override
    public String decode(String morseCode) {
        var current = root;
        StringBuilder sb = new StringBuilder();
        for (char c : morseCode.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
                continue;
            }
            sb.append(current.letter);
            current = root;
        }
        return sb.toString();
    }

    @Override
    public String encode(String source) {
        StringBuilder sb = new StringBuilder();
        for (char c : source.toUpperCase().toCharArray()) {
            if (morseMap.containsKey(c)) {
                sb.append(morseMap.get(c)).append(" ");
            } else {
                throw new UnsupportedOperationException("Symbol not supported: " + c);
            }
        }
        return sb.toString();
    }

    @NoArgsConstructor
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        char letter;

    }
}
