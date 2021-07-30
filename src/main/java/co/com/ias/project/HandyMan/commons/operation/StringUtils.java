package co.com.ias.project.HandyMan.commons.operation;

public class StringUtils {
    public static boolean nonBlank(String value) {
        String trimmed = value.trim();
        return trimmed.length() > 0;
    }
}
