package Test2;

import Test1.EncryptionEngine;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Application {
    private List<Account> userDatabase = new ArrayList<>();

    public void registerAccount(String userName, String password) {
        try {
            // 1. 솔트 생성
            String salt = EncryptionEngine.generateSalt();

            // 2. 해싱된 비밀번호 생성
            String hashedPassword = EncryptionEngine.hashPassword(password, salt);

            // 3. 계정 생성
            Account account = new Account(userName, hashedPassword, salt);
            userDatabase.add(account);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String userName, String password) {
        try {
            Account account = findAccountByUserName(userName);
            if(account == null) {
                System.out.println("No such user");
                return false;
            }

            boolean b = EncryptionEngine.verifyPassword(password, account.getPasswordHash(), account.getSalt());
            return b;
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Account findAccountByUserName(String userName) {
        for (Account account : userDatabase) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Application app = new Application();

        // 1. 계정 생성
        app.registerAccount("user123", "mySecretPassword");

        // 2. 로그인 시도
        boolean login = app.login("user123", "mySecretPassword");
        System.out.println("Login success: " + login);
    }
}
