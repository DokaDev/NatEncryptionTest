package Test2;

import java.security.*;
import java.util.Base64;

public class EncryptionEngine {
    // 비밀번호를 해싱하는 메소드
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((salt + password).getBytes());
        byte[] hashedPassword = md.digest();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // 솔트를 생성하는 메소드
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // 비밀번호 검증
    public static boolean verifyPassword(String enteredPassword, String storedHash, String storedSalt) throws NoSuchAlgorithmException {
        String computedHash = hashPassword(enteredPassword, storedSalt);
        return computedHash.equals(storedHash);
    }
}
