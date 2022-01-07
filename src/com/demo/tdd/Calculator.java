package com.demo.tdd;

public class Calculator {
    public long add(String numbers) {
        long sum = 0;
        if(numbers.length() == 0) return 0;
        if(numbers.length() == 1) return numbers.charAt(0) - '0';

        String[] nums = helper(numbers);
        validateNums(nums);

        for(String str : nums) {
            sum += Long.parseLong(str);
        }

        return sum;
    }

    private void validateNums(String[] nums) {
        String errorMessageSuffix = "Negatives not allowed: ";
        String errorMessagePrefix = "";
        String errorMessage = "";

        for(String s : nums) {
            if(Long.parseLong(s) < 0) errorMessagePrefix += s + ", ";
        }

        if(errorMessagePrefix.length() > 0) {
            errorMessagePrefix = errorMessagePrefix.
                    substring(0, errorMessagePrefix.length() - 2);
            errorMessage = errorMessageSuffix + errorMessagePrefix;
        }

        if(errorMessage.length() > 0) {
            throw new RuntimeException(errorMessage);
        }
    }

    private String[] helper(String numbers) {
        // default delimiter
        char delimiter = ',';

        if(numbers.contains("//")) {
            delimiter = numbers.charAt(numbers.lastIndexOf('/') + 1);
            // removing "//[delimiter]\n" from the string
            numbers = numbers.replaceAll("\\//.*?\\\n", "");
        }

        String[] nums = numbers.replaceAll("\\n", String.valueOf(delimiter))
                .split("\\" + delimiter);

        return nums;
    }
}
