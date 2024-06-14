package com.czy.bms;

public class warningTest {
    public static void main(String[] args) {
        String input = "{\"Mx\":11.0,\"Mi\":9.6,\"Ix\":12.0,\"Ii\":11.7}";
        float Mx = parseValue(input, "Mx");
        float Mi = parseValue(input, "Mi");
        float Ix = parseValue(input, "Ix");
        float Ii = parseValue(input, "Ii");

        System.out.println("Mx: " + Mx);
        System.out.println("Mi: " + Mi);
        System.out.println("Ix: " + Ix);
        System.out.println("Ii: " + Ii);
    }

    public static float parseValue(String input, String variable) {
        int startIndex = input.indexOf(variable) + variable.length() + 2;
        int endIndex = input.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = input.indexOf("}", startIndex);
        }
        String valueString = input.substring(startIndex, endIndex);
        return Float.parseFloat(valueString);
    }
}
