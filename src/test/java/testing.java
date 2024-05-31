package com.yourorganization.maven_sample;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.utils.CodeGenerationUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogicPositivizerTest {

    @Test
    public void testLogicPositivizer() {
        // Path to the input file
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(LogicPositivizer.class).resolve("src/test/resources"));

        // Parse the test file
        CompilationUnit cu = sourceRoot.parse("", "TestInput.java");

        // Apply the LogicPositivizer transformation
        cu.accept(new LogicPositivizer(), null);

        // Expected output after transformation
        String expected = "class Test {\n" +
                "    void testMethod() {\n" +
                "        if (a == b) {\n" +
                "            System.out.println(\"a equals b\");\n" +
                "        } else {\n" +
                "            System.out.println(\"a does not equal b\");\n" +
                "        }\n" +
                "    }\n" +
                "}";

        // Check if the transformed output matches the expected output
        assertTrue(cu.toString().contains(expected));
    }
}
