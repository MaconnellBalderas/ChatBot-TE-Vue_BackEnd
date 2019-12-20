package com.techelevator.authentication;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

    private static final int KEY_LENGTH = 128;
    private static final int WORK_FACTOR = 100000;

    private SecureRandom random;

    public PasswordHasher() {
        random = new SecureRandom();
    }

    /**
     * Given a clear text password and a salt, hash the password and return
     * the computed hash.
     *
     * @param clearTextPassword the password as given by the user
     * @param salt a salt to add to the password during hashing
     * @return the hashed password
     */
    public String computeHash(String clearTextPassword, byte[] salt) {
        Key key = createKey(clearTextPassword, salt);
        byte[] digest = key.getEncoded();
        return new String(Base64.encode(digest));
    }

    /**
     * Generate a new random salt.
     *
     * @return a new random array of bytes to be used as a salt
     */
    public byte[] generateRandomSalt() {
        return random.generateSeed(128);
    }

    /**
     * This is the function that actually generates the hash. You can see that
     * it uses a work factor to generate the hash. The work factor should be high
     * enough that it's painful for attackers, but not for users. A good half
     * second to one second delay should be plenty.
     *
     * @param password the password as given by the user
     * @param salt the random salt
     * @return the resulting hash as a Key object
     */
    private Key createKey(String password, byte[] salt) {
        SecretKeyFactory factory = getSecretKeyFactory();
        KeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, WORK_FACTOR, KEY_LENGTH);
        Key key;
        try {
            key = factory.generateSecret(keyspec);
        } catch (InvalidKeySpecException e) {
            // This should never happen
            throw new RuntimeException("Something weird happened...", e);
        }
        return key;
    }

    private SecretKeyFactory getSecretKeyFactory() {
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            // this should never happen
            throw new RuntimeException("Something weird happened...", e);
        }
        return factory;
    }
}
