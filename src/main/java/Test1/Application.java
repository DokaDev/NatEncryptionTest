package Test1;

import java.security.NoSuchAlgorithmException;

public class Application {
    public static void main(String[] args) {
        try {
            String password = "mySecretPassword";
            String salt = EncryptionEngine.generateSalt();
            String hashedPassword = EncryptionEngine.hashPassword(password, salt);

            // 계정 생성
            Account account = new Account("user123", hashedPassword, salt);

            // 로그인 시도 시 비밀번호 검증
            boolean isMatch = EncryptionEngine.verifyPassword("mySecretPassword", account.getPasswordHash(), account.getSalt());
            System.out.println("Password match: " + isMatch);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
