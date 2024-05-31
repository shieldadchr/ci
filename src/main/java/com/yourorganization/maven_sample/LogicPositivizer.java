package com.yourorganization.maven_sample;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.utils.CodeGenerationUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicPositivizerTest {

    @Test
    public void testLogicPositivizerNoChange() {
        // Path to the input file
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(LogicPositivizer.class).resolve("src/test/resources"));

        // Parse the test file
        CompilationUnit cu = sourceRoot.parse("", "TestNoChange.java");

        // Capture original content for comparison
        String originalContent = cu.toString();

        // Apply the LogicPositivizer transformation
        cu.accept(new LogicPositivizer(), null);

        // Capture transformed content
        String transformedContent = cu.toString();

        // Check if the original content matches the transformed content
        assertEquals(originalContent, transformedContent, "The content should not have changed");
    }
}
