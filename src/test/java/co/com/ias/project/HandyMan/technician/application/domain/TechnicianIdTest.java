package co.com.ias.project.HandyMan.technician.application.domain;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class TechnicianIdTest {

    @TestFactory //test para validar valores validos para la clase de technicianId
    List<DynamicTest> validValuesTest() {
        List<DynamicTest> tests = new ArrayList<>();
        List<String> validValues = Arrays.asList(
                "232323",
                "000458"
        );
        for (String value : validValues) {
            String testName = "Valid values for technician id";
            Executable assertions = () -> assertDoesNotThrow(() -> TechnicianId.of(value));
            DynamicTest dynamicTest = dynamicTest(testName, assertions);
            tests.add(dynamicTest);
        }

        return tests;
    }


    @TestFactory //test para validar valores invalidos para la clase de technicianId
    Stream<DynamicTest> invalidValuesTest() {

        return Stream.of(null, "", " ")
                .map(invalidValue -> {
                    String testName = "Value " + invalidValue + " should be invalid";
                    return dynamicTest(testName, () -> assertThrows(RuntimeException.class,
                            () -> TechnicianId.of(invalidValue)
                    ));
                });
    }


}