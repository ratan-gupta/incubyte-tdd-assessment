package com.demo.tdd;

public class Calculator {
    public long add(String numbers) {
        long sum = 0;
        if(numbers.length() == 0) return 0;
        if(numbers.length() == 1) return numbers.charAt(0) - '0';

        String[] nums = numbers.replaceAll("\\n",",")
                .split(",");

        for(String str : nums) {
            sum += Long.parseLong(str);
        }

        return sum;
    }
}
