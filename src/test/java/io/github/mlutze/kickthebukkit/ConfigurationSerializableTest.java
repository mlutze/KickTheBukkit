package io.github.mlutze.kickthebukkit;

import com.google.common.reflect.ClassPath;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.junit.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;


public class ConfigurationSerializableTest {
    private static final String PACKAGE_NAME = "io.github.mlutze.kickthebukkit"; // TODO get programatically

    @Test
    public void allConfigurationSerializablesShouldHaveSerializableAs() throws IOException {
        List<Class> localClasses = loadLocalClasses();
        for (Class clazz : localClasses) {
            if (Arrays.asList(clazz.getInterfaces()).contains(ConfigurationSerializable.class)) {
                assertNotNull(clazz.getAnnotation(SerializableAs.class));
            }
        }
    }

    @Test
    public void allConfigurationSerializablesShouldHaveStaticDeserialize() throws NoSuchMethodException, IOException {
        List<Class> localClasses = loadLocalClasses();
        for (Class clazz : localClasses) {
            if (Arrays.asList(clazz.getInterfaces()).contains(ConfigurationSerializable.class)) {
                int modifiers = clazz.getMethod("deserialize", Map.class).getModifiers();
                assertTrue(Modifier.isStatic(modifiers));
                assertTrue(Modifier.isPublic(modifiers));
            }
        }
    }

    private List<Class> loadLocalClasses() throws IOException {
        ClassPath classPath = ClassPath.from(getClass().getClassLoader());
        return classPath.getAllClasses().stream()
                .filter(i -> i.getPackageName().startsWith(PACKAGE_NAME))
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toList());
    }
}
