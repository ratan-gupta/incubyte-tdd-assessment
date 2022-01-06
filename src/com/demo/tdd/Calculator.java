package com.demo.tdd;

public class Calculator {
    public int add(String numbers) {
        int sum = 0;
        if(numbers.length() == 0) return 0;
        if(numbers.length() == 1) return numbers.charAt(0) - '0';

        String[] nums = numbers.split(",");

        for(String str : nums) {
            sum += Integer.valueOf(str);
        }

        return sum;
    }
}
