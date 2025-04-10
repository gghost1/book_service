package ru.book_service.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@RequiredArgsConstructor
public class Pbkdf2PasswordEncoder implements PasswordEncoder {

    private final int iterations;
    private final int keyLength;
    private final int saltLength;
    private final String algorithm;

    @Override
    public String encode(CharSequence password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLength];
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(
                String.valueOf(password).toCharArray(),
                salt,
                iterations,
                keyLength
        );
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    @Override
    public boolean matches(CharSequence password, String storedHash) {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] originalHash = Base64.getDecoder().decode(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(
                String.valueOf(password).toCharArray(),
                salt,
                iterations,
                originalHash.length * 8
        );

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            byte[] testHash = factory.generateSecret(spec).getEncoded();

            return java.security.MessageDigest.isEqual(testHash, originalHash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify password", e);
        }
    }

}
