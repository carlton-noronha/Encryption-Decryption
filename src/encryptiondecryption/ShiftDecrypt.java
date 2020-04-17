package encryptiondecryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class ShiftDecrypt implements EncryptionDecryption {
    @Override
    public void beginProcess(String cipherText, int key, boolean isData, String outputFile) {

        StringBuilder plainText = new StringBuilder();

        HashMap<Character, Character> smallLetters = new HashMap<>();
        HashMap<Character, Character> capitalLetters = new HashMap<>();

        for (char letter = 'a'; letter <= 'z'; ++letter) {
            if (letter > (char) ('a' + key - 1))
                smallLetters.put(letter, (char) (letter - key));
            else {
                smallLetters.put(letter, (char) ('z' - (key - (letter - 'a' + 1))));
            }
        }

        for (char letter = 'A'; letter <= 'Z'; ++letter) {
            if (letter > (char) ('A' + key - 1))
                capitalLetters.put(letter, (char) (letter - key));
            else {
                capitalLetters.put(letter, (char) ('Z' - (key - (letter - 'A' + 1))));
            }
        }

        if (isData)
            for (char letter : cipherText.toCharArray()) {
                if (smallLetters.containsKey(letter))
                    plainText.append(smallLetters.get(letter).toString());
                else if (capitalLetters.containsKey(letter))
                    plainText.append(capitalLetters.get(letter).toString());
                else
                    plainText.append(letter);
            }
        else {

            final File cipherTextFile = new File("./" + cipherText);

            try (final Scanner scanner = new Scanner(cipherTextFile)) {
                while (scanner.hasNext()) {
                    String currentLine = scanner.nextLine();
                    for (char letter : currentLine.toCharArray()) {
                        if (smallLetters.containsKey(letter))
                            plainText.append(smallLetters.get(letter).toString());
                        else if (capitalLetters.containsKey(letter))
                            plainText.append(capitalLetters.get(letter).toString());
                        else
                            plainText.append(letter);
                    }
                }
            } catch (FileNotFoundException FNF) {
                System.out.println("Error");
            }
        }

        if (outputFile.equals("")) {
            System.out.println(plainText);
        } else {
            final File file = new File("./" + outputFile);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.println(plainText);
            } catch (IOException IOE) {
                System.out.println("Error");
            }
        }

    }
}
