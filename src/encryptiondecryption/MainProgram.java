package encryptiondecryption;

public class MainProgram {

    public static void main(String[] args) {

        String mode = "enc";
        int key = 0;
        String data = "";
        String in = "";
        String out = "";
        String alg = "shift";


        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }

        EncryptionDecryptionContext encryptionDecryptionContext = new EncryptionDecryptionContext();
        boolean isData = (!data.equals("")) || in.equals("");

        switch (mode) {
            case "enc":
                if (alg.equals("unicode")) {
                    encryptionDecryptionContext.setAlgorithm(EncryptionDecryptionFactory.algorithmSelector("UE"));
                    if (isData) {
                        encryptionDecryptionContext.initiateAlgorithm(data, key, isData, out);
                    } else {
                        encryptionDecryptionContext.initiateAlgorithm(in, key, isData, out);
                    }
                } else {
                    encryptionDecryptionContext.setAlgorithm(EncryptionDecryptionFactory.algorithmSelector("SE"));
                    if (isData) {
                        encryptionDecryptionContext.initiateAlgorithm(data, key, isData, out);
                    } else {
                        encryptionDecryptionContext.initiateAlgorithm(in, key, isData, out);
                    }
                }

                break;

            case "dec":

                if (alg.equals("unicode")) {
                    encryptionDecryptionContext.setAlgorithm(EncryptionDecryptionFactory.algorithmSelector("UD"));
                    if (isData) {
                        encryptionDecryptionContext.initiateAlgorithm(data, key, isData, out);
                    } else {
                        encryptionDecryptionContext.initiateAlgorithm(in, key, isData, out);
                    }
                } else {
                    encryptionDecryptionContext.setAlgorithm(EncryptionDecryptionFactory.algorithmSelector("SD"));
                    if (isData) {
                        encryptionDecryptionContext.initiateAlgorithm(data, key, isData, out);
                    } else {
                        encryptionDecryptionContext.initiateAlgorithm(in, key, isData, out);
                    }
                }
                break;
        }

    }
}
