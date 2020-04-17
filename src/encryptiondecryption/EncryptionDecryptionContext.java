package encryptiondecryption;


public class EncryptionDecryptionContext {

    private EncryptionDecryption algorithmType;

    public void setAlgorithm(EncryptionDecryption algorithmType) {
        this.algorithmType = algorithmType;
    }

    public void initiateAlgorithm(String data, int key, boolean isData, String outputFile) {
        this.algorithmType.beginProcess(data, key, isData, outputFile);
    }
}
