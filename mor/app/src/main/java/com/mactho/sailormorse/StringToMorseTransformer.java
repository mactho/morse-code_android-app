package com.mactho.sailormorse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by thomas on 13/05/17.
 */
public class StringToMorseTransformer{

    private HashMap<Character, String> characterCodes;

    public StringToMorseTransformer(){
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
        characterCodes.put(' ', "_");
    }

    private String convertChar( Character c ){
        if (this.characterCodes.containsKey(Character.toLowerCase(c))) {
            return this.characterCodes.get(Character.toLowerCase(c));
        } else {
            return "";
        }
    }

    public String transform( String s ){
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(s.length());

        for(int counter = 0 ; counter < s.length() ; counter++){
            String charCode = convertChar(s.charAt(counter));
            stringBuilder.append(charCode);
            if(counter < s.length() -1){
                if(s.charAt(counter+1) != (' ') && s.charAt(counter) != (' ')) {
                    stringBuilder.append('-');
                }
            }
        }
        return stringBuilder.toString();
    }
}
