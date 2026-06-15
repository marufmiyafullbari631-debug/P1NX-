import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.time.Instant;

/**
 * API Key Generator - Generates secure API keys for authentication
 */
public class ApiKeyGenerator {
    
    private static final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 32;
    
    /**
     * Generate a random API key
     * @return A secure random API key
     */
    public static String generateApiKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < KEY_LENGTH; i++) {
            key.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return key.toString();
    }
    
    /**
     * Generate API key with prefix
     * @param prefix The prefix for the API key (e.g., "sk_", "pk_")
     * @return API key with prefix
     */
    public static String generateApiKeyWithPrefix(String prefix) {
        return prefix + generateApiKey();
    }
    
    /**
     * Generate a Base64 encoded API key
     * @return Base64 encoded API key
     */
    public static String generateBase64ApiKey() {
        byte[] randomBytes = new byte[24];
        random.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
    
    /**
     * Generate API key with timestamp
     * @return API key with timestamp information
     */
    public static String generateApiKeyWithTimestamp() {
        long timestamp = Instant.now().toEpochMilli();
        String key = generateApiKey();
        return key + "_" + Long.toHexString(timestamp);
    }
    
    /**
     * Generate a UUID-based API key
     * @return UUID-based API key
     */
    public static String generateUuidApiKey() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Generate a secure random token
     * @param length The desired length of the token
     * @return Random token of specified length
     */
    public static String generateSecureToken(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < length; i++) {
            token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return token.toString();
    }
    
    /**
     * Validate API key format
     * @param apiKey The API key to validate
     * @return true if valid, false otherwise
     */
    public static boolean validateApiKey(String apiKey) {
        return apiKey != null && apiKey.length() >= 20 && apiKey.matches("[a-zA-Z0-9_-]+");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== API Key Generator ===\n");
        
        System.out.println("1. Basic API Key:");
        System.out.println("   " + generateApiKey());
        
        System.out.println("\n2. API Key with Prefix (sk_):");
        System.out.println("   " + generateApiKeyWithPrefix("sk_"));
        
        System.out.println("\n3. API Key with Prefix (pk_):");
        System.out.println("   " + generateApiKeyWithPrefix("pk_"));
        
        System.out.println("\n4. Base64 Encoded API Key:");
        System.out.println("   " + generateBase64ApiKey());
        
        System.out.println("\n5. API Key with Timestamp:");
        System.out.println("   " + generateApiKeyWithTimestamp());
        
        System.out.println("\n6. UUID-based API Key:");
        System.out.println("   " + generateUuidApiKey());
        
        System.out.println("\n7. Secure Token (50 characters):");
        System.out.println("   " + generateSecureToken(50));
        
        System.out.println("\n8. Key Validation:");
        String testKey = generateApiKey();
        System.out.println("   Key: " + testKey);
        System.out.println("   Valid: " + validateApiKey(testKey));
    }
}
