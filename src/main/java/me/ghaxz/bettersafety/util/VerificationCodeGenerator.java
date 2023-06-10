package me.ghaxz.bettersafety.util;

public class VerificationCodeGenerator {
    private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateCode(int length) {
        String code = "";

        for(int x = 0; x < length; x++) {
            code += characters.charAt(randInt(0, characters.length()));
        }

        return code;
    }

    private static int randInt(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1) + min);
    }
}
