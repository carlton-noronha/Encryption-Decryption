package encryptiondecryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class ShiftEncrypt implements EncryptionDecryption {
    @Override
    public void beginProcess(String plainText, int key, boolean isData, String outputFile) {

        StringBuilder cipherText = new StringBuilder();

        HashMap<Character, Character> smallLetters = new HashMap<>();
        HashMap<Character, Character> capitalLetters = new HashMap<>();

        for (char letter = 'a'; letter <= 'z'; ++letter) {
            if (('z' - letter) >= key) {
                smallLetters.put(letter, (char) (letter + key));
            } else {
                smallLetters.put(letter, (char) (key - 1 - ('z' - letter) + 'a'));
            }
        }

        for (char letter = 'A'; letter <= 'Z'; ++letter) {
            if (('Z' - letter) >= key)
                capitalLetters.put(letter, (char) (letter + key));
            else {
                smallLetters.put(letter, (char) (key - 1 - ('Z' - letter) + 'A'));
            }
        }

        if (isData)
            for (char letter : plainText.toCharArray()) {
                if (smallLetters.containsKey(letter))
                    cipherText.append(smallLetters.get(letter).toString());
                else if (capitalLetters.containsKey(letter)) {
                    cipherText.append(capitalLetters.get(letter).toString());
                } else
                    cipherText.append(letter);
            }
        else {

            final File plainTextFile = new File("./" + plainText);

            try (final Scanner scanner = new Scanner(plainTextFile)) {
                while (scanner.hasNext()) {
                    String currentLine = scanner.nextLine();
                    for (char letter : currentLine.toCharArray()) {
                        if (smallLetters.containsKey(letter))
                            cipherText.append(smallLetters.get(letter).toString());
                        else if (capitalLetters.containsKey(letter))
                            cipherText.append(capitalLetters.get(letter).toString());
                        else
                            cipherText.append(letter);
                    }
                }
            } catch (FileNotFoundException FNF) {
                System.out.println("Error");
            }
        }

        if (outputFile.equals("")) {
            System.out.println(cipherText);
        } else {
            final File file = new File("./" + outputFile);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.println(cipherText.toString());
            } catch (IOException IOE) {
                System.out.println("Error");
            }
        }

    }
}
