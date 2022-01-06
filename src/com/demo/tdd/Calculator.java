package com.demo.tdd;

public class Calculator {
    public long add(String numbers) {
        long sum = 0;
        if(numbers.length() == 0) return 0;
        if(numbers.length() == 1) return numbers.charAt(0) - '0';

        String[] nums = helper(numbers);

        for(String str : nums) {
            sum += Long.parseLong(str);
        }

        return sum;
    }

    private String[] helper(String numbers) {
        // default delimiter
        char delimiter = ',';

        if(numbers.contains("//")) {
            delimiter = numbers.charAt(numbers.lastIndexOf('/') + 1);
            // removing "//[delimiter]\n" from the string
            numbers = numbers.replaceAll("\\//.*?\\\n", "");
        }

        String[] nums = numbers.replaceAll("\\n", Character.toString(delimiter))
                .split(Character.toString(delimiter));

        return nums;
    }
}
