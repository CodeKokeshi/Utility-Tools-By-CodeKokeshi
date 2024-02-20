package utility.tools.bycodekokeshi;

public class _06_03_EncryptionService {
    // This is a simple encryption method where we shift the characters by 3. For example, "abc" becomes "def".
    private static final int SHIFT_KEY = 3;

    // This is the encryption process.
    public static String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            encrypted.append((char) (c + SHIFT_KEY));
        }
        return encrypted.toString();
    }

    // This is the decryption process.
    public static String decrypt(String input) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            decrypted.append((char) (c - SHIFT_KEY));
        }
        return decrypted.toString();
    }
}