package africa.semicolon.unicoin.utils;

import java.util.Random;

public class RandomStringGenerator {

    public static StringBuilder randomStringGenerator(int length){
        String values = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(values.charAt(random.nextInt(values.length())));
        }
        return stringBuilder;
    }
}
