package encryptiondecryption;

public class EncryptionDecryptionFactory {

    public static EncryptionDecryption algorithmSelector(String type) {

        switch (type) {
            case "UE":
                return new UnicodeEncrypt();
            case "UD":
                return new UnicodeDecrypt();
            case "SE":
                return new ShiftEncrypt();
            case "SD":
                return new ShiftDecrypt();
            default:
                return null;
        }

    }
}
