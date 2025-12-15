package hk.edu.hkmu.jiang.javafinal.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Gson Utility Class for Map and JSON conversions
 * Provides static methods for serializing and deserializing between Map and JSON formats
 */
public class GsonUtil {

    // Thread-safe Gson instance with common configurations
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    // Private constructor to prevent instantiation
    private GsonUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Convert a Map to JSON string
     *
     * @param map The Map to convert (Map<String, Object> or any Map type)
     * @return JSON string representation of the Map
     */
    public static String mapToJson(Map<?, ?> map) {
        if (map == null) {
            return "null";
        }
        return GSON.toJson(map);
    }

    /**
     * Convert JSON string to Map<String, Object>
     *
     * @param json The JSON string to convert
     * @return Map containing the JSON data, or empty Map if JSON is invalid
     */
    public static Map<String, Object> mapFromJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new HashMap<>();
        }

        try {
            Type mapType = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> result = GSON.fromJson(json, mapType);
            return result != null ? result : new HashMap<>();
        } catch (Exception e) {
            System.err.println("Error parsing JSON to Map: " + e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Convert JSON string to strongly typed Map with specific key and value types
     *
     * @param json      The JSON string to convert
     * @param keyType   The class type for Map keys (usually String.class)
     * @param valueType The class type for Map values
     * @return Typed Map containing the JSON data
     */
    public static <K, V> Map<K, V> mapFromJson(String json, Class<K> keyType, Class<V> valueType) {
        if (json == null || json.trim().isEmpty()) {
            return new HashMap<>();
        }

        try {
            Type mapType = TypeToken.getParameterized(Map.class, keyType, valueType).getType();
            Map<K, V> result = GSON.fromJson(json, mapType);
            return result != null ? result : new HashMap<>();
        } catch (Exception e) {
            System.err.println("Error parsing JSON to typed Map: " + e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Convert Map to formatted JSON string with pretty printing
     *
     * @param map The Map to convert
     * @return Formatted JSON string
     */
    public static String mapToFormattedJson(Map<?, ?> map) {
        if (map == null) {
            return "null";
        }

        Gson prettyGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                .create();

        return prettyGson.toJson(map);
    }

    /**
     * Convert JSON string to Map while preserving numeric types (avokes double conversion)
     *
     * @param json The JSON string to convert
     * @return Map with proper numeric types
     */
    public static Map<String, Object> mapFromJsonWithNumberTypes(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new HashMap<>();
        }

        try {
            // Use custom type adapter to preserve number types
            Gson gsonWithNumberHandling = new GsonBuilder()
                    .registerTypeAdapter(Double.class, (com.google.gson.JsonDeserializer<Double>) (jsonElement, type, context) -> {
                        if (jsonElement.isJsonPrimitive()) {
                            return jsonElement.getAsDouble();
                        }
                        return 0.0;
                    })
                    .create();

            Type mapType = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> result = gsonWithNumberHandling.fromJson(json, mapType);
            return result != null ? result : new HashMap<>();
        } catch (Exception e) {
            System.err.println("Error parsing JSON with number types: " + e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Check if a string is valid JSON
     *
     * @param json The string to validate
     * @return true if valid JSON, false otherwise
     */
    public static boolean isValidJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return false;
        }

        try {
            GSON.fromJson(json, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the underlying Gson instance for advanced operations
     *
     * @return The configured Gson instance
     */
    public static Gson getGson() {
        return GSON;
    }
}