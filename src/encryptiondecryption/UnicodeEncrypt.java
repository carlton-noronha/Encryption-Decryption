package encryptiondecryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UnicodeEncrypt implements EncryptionDecryption {
    @Override
    public void beginProcess(String plainText, int key, boolean isData, String outputFile) {

        StringBuilder cipherText = new StringBuilder();

        if (isData)
            for (int i = 0; i < plainText.length(); ++i) {
                cipherText.append(Character.toString(plainText.charAt(i) + key));
            }
        else {
            final File plainTextFile = new File("./" + plainText);

            try (final Scanner scanner = new Scanner(plainTextFile)) {

                while (scanner.hasNext()) {
                    String currentLine = scanner.nextLine();
                    for (int i = 0; i < currentLine.length(); ++i) {
                        cipherText.append(Character.toString(currentLine.charAt(i) + key));
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
