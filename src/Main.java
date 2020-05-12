import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Methods of encrypting and decrypting text.");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = input.nextLine();

        // Part 1
        System.out.println(normalizeText(text));

        // Part 2
        System.out.print("Enter shift value by: ");
        int shiftValue = input.nextInt();
        System.out.println(caesarify(text,shiftValue));

        // Part 3
        System.out.print("Enter size to group codes: ");
        int groupSize = input.nextInt();
        String codeGroupedString = groupify(text, groupSize);

        // Part 4
        System.out.println("Encrypting message.....");
        String cypherText = encryptString(text, shiftValue,groupSize);
        System.out.println(cypherText);

        // Part 5
        ungroupify(codeGroupedString);
        System.out.println("Decrypting message.....");
        String plainText = decryptString(cypherText,shiftValue);
        System.out.println(plainText);
    }

    public static String normalizeText(String text) {
        String norm = "";

        norm = text.replaceAll("[\\s.,:;'!?()\"@#$%^&*-+={}]", "");
        norm = norm.toUpperCase();
        return norm;
    }
    public static String caesarify(String t, int key) {
        String text = shiftAlphabet(key);
        text = t.replace(t, text);
        return text;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        if (result.length() > 26) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public static String groupify(String breakUpLetters, int lettersPerGroup) {
        String message = "";
        int count = 0;
        for (int i = 0; i < breakUpLetters.length(); i++) {
            if (i % lettersPerGroup == 0 && i != 0) {
                message = message + " ";
            }
            message = message + breakUpLetters.charAt(i);
        }

        for (int i = message.lastIndexOf(" ") + 1; i < message.length(); i++) {
            ++count;
        }

        for (int i = lettersPerGroup - count; i >0; i--) {
            message += "x";
        }
        return message;
    }

    public static String encryptString(String message, int key, int num) {
        String text = "";
        text = normalizeText(message);
        text = caesarify(message, key);
        text = groupify(message, num);

        return text;
    }

    public static String ungroupify(String groupedString) {
        String message = groupedString.replaceAll("[\\sx]", "");
        return message;
    }

    public static String decryptString(String message, int key) {
        return ungroupify(message);
    }
}
