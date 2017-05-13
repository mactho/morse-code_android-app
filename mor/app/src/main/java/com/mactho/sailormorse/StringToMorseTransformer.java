package com.mactho.sailormorse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by thomas on 13/05/17.
 */
public class StringToMorseTransformer {

    private HashMap<Character, String> characterCodes;

    public StringToMorseTransformer() {
        characterCodes = new HashMap<>();

        // Add the alphabet to the map//
        characterCodes.put('a', "01");
        characterCodes.put('b', "1000");
        characterCodes.put('c', "1010");
        characterCodes.put('d', "100");
        characterCodes.put('e', "0");
        characterCodes.put('f', "0010");
        characterCodes.put('g', "110");
        characterCodes.put('h', "0000");
        characterCodes.put('i', "00");
        characterCodes.put('j', "0111");
        characterCodes.put('k', "101");
        characterCodes.put('l', "0100");
        characterCodes.put('m', "11");
        characterCodes.put('n', "10");
        characterCodes.put('o', "111");
        characterCodes.put('p', "0110");
        characterCodes.put('q', "1101");
        characterCodes.put('r', "010");
        characterCodes.put('s', "000");
        characterCodes.put('t', "1");
        characterCodes.put('u', "001");
        characterCodes.put('v', "0001");
        characterCodes.put('w', "011");
        characterCodes.put('x', "1001");
        characterCodes.put('y', "1011");
        characterCodes.put('z', "1100");

        // Add the numbers to the map
        characterCodes.put('1', "01111");
        characterCodes.put('2', "00111");
        characterCodes.put('3', "00011");
        characterCodes.put('4', "00001");
        characterCodes.put('5', "00000");
        characterCodes.put('6', "10000");
        characterCodes.put('7', "11000");
        characterCodes.put('8', "11100");
        characterCodes.put('9', "11110");
        characterCodes.put('0', "11111");

        // Add space character
        //characterCodes.put(' ', "_");
    }

    private String convertChar(Character c) {
        if (this.characterCodes.containsKey(Character.toLowerCase(c))) {
            return this.characterCodes.get(Character.toLowerCase(c));
        } else {
            return "";
        }
    }

    private ArrayList<String> breakApartString(String s) {
        StringTokenizer tokenizer = new StringTokenizer(s);
        ArrayList<String> output = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            output.add(tokenizer.nextToken());
        }
        return output;
    }

    public ArrayList<String> transform(String s) {
        StringBuilder stringBuilder;
        ArrayList<String> words = breakApartString(s);
        ArrayList<String> morseCodes = new ArrayList<>();
        for (String word : words) {
            stringBuilder = new StringBuilder();
            for (int counter = 0; counter < word.length(); counter++) {
                stringBuilder.append(convertChar(word.charAt(counter)));
                if (counter < word.length() - 1) {
                    stringBuilder.append("-");
                }
            }
            morseCodes.add(stringBuilder.toString());
            System.out.println(word);
        }
        return morseCodes;
    }
}
