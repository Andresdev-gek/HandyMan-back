package co.com.ias.project.HandyMan.commons.operation;

import static co.com.ias.project.HandyMan.commons.operation.StringUtils.nonBlank;

public class NonEmptyString {
    private final String value;

    public NonEmptyString(String value) {
        // validation logic
        Validate.notNull(value, "NonEmptyString can not be null");
        Validate.isTrue(nonBlank(value), "NonEmptyString can not be empty");
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NonEmptyString{" +
                "value='" + value + '\'' +
                '}';
    }
}
