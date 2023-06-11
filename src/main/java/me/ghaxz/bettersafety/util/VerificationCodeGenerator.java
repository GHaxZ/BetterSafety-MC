package me.ghaxz.bettersafety.util;

// Used for generating verification codes
public class VerificationCodeGenerator {
    private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateCode(int length) {
        String code = "";

        for(int x = 0; x < length; x++) {
            code += characters.charAt(randInt(0, characters.length()) - 1);
        }

        return code;
    }

    private static int randInt(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1) + min);
    }
}
