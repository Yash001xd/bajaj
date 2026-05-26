package com.bajaj.api.service.impl;

import com.bajaj.api.dto.BfhlRequest;
import com.bajaj.api.dto.BfhlResponse;
import com.bajaj.api.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String FULL_NAME = "yash_upadhyay";
    private static final String DATE_OF_BIRTH = "21092005";
    private static final String EMAIL = "yashupadhyay230834@acropolis.in";
    private static final String ROLL_NUMBER = "0827CI231152";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        BfhlResponse response = new BfhlResponse();
        
        // Generate user_id format: full_name_ddmmyyyy (lowercase, spaces replaced with underscores)
        String userId = (FULL_NAME.toLowerCase() + "_" + DATE_OF_BIRTH).replace(" ", "_");
        response.setUserId(userId);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);

        if (request == null || request.getData() == null) {
            response.setIsSuccess(false);
            response.setEvenNumbers(new ArrayList<>());
            response.setOddNumbers(new ArrayList<>());
            response.setAlphabets(new ArrayList<>());
            response.setSpecialCharacters(new ArrayList<>());
            response.setSum("0");
            response.setConcatString("");
            return response;
        }

        List<String> inputData = request.getData();
        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;
        StringBuilder alphaChars = new StringBuilder();

        for (String element : inputData) {
            if (element == null || element.isEmpty()) {
                continue;
            }

            // Check if element is a valid integer (including negative integers)
            if (isNumeric(element)) {
                long num = Long.parseLong(element);
                sum += num;
                // Numbers must be returned as strings (original string)
                if (num % 2 == 0) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }
            } 
            // Check if element is purely alphabetic (words or characters)
            else if (isAlphabetic(element)) {
                alphabets.add(element.toUpperCase());
                // Extract alphabetic characters from alphabetic elements
                for (char c : element.toCharArray()) {
                    if (Character.isLetter(c)) {
                        alphaChars.append(c);
                    }
                }
            } 
            // Otherwise, it is classified as a special character
            else {
                specialCharacters.add(element);
                // Also extract any embedded alphabetical characters if they exist
                for (char c : element.toCharArray()) {
                    if (Character.isLetter(c)) {
                        alphaChars.append(c);
                    }
                }
            }
        }

        // Concatenate all alphabetical characters in reverse order
        String reversedAlpha = alphaChars.reverse().toString();
        StringBuilder alternatingCaps = new StringBuilder();
        for (int i = 0; i < reversedAlpha.length(); i++) {
            char c = reversedAlpha.charAt(i);
            if (i % 2 == 0) {
                alternatingCaps.append(Character.toUpperCase(c));
            } else {
                alternatingCaps.append(Character.toLowerCase(c));
            }
        }

        response.setIsSuccess(true);
        response.setEvenNumbers(evenNumbers);
        response.setOddNumbers(oddNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcatString(alternatingCaps.toString());

        return response;
    }

    private boolean isNumeric(String str) {
        // Matches integers (positive, negative, zero)
        return str.matches("-?\\d+");
    }

    private boolean isAlphabetic(String str) {
        // Matches strings containing only letters (a-z, A-Z)
        return str.matches("[a-zA-Z]+");
    }
}
