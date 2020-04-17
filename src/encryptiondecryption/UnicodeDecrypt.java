package encryptiondecryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UnicodeDecrypt implements EncryptionDecryption {
    @Override
    public void beginProcess(String cipherText, int key, boolean isData, String outputFile) {

        StringBuilder plainText = new StringBuilder();

        if (isData)
            for (int i = 0; i < cipherText.length(); ++i) {
                plainText.append(Character.toString(cipherText.charAt(i) - key));
            }
        else {

            final File cipherTextFile = new File("./" + cipherText);

            try (final Scanner scanner = new Scanner(cipherTextFile)) {

                while (scanner.hasNext()) {
                    String currentLine = scanner.nextLine();
                    for (int i = 0; i < currentLine.length(); ++i) {
                        plainText.append(Character.toString(currentLine.charAt(i) - key));
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
                printWriter.println(plainText.toString());
            } catch (IOException IOE) {
                System.out.println("Error");
            }
        }
    }

}
