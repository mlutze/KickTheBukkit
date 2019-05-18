package io.github.mlutze.kickthebukkit.persistence;

import static org.junit.Assert.assertEquals;
import java.util.Collections;
import java.util.Map;
import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.junit.Before;
import org.junit.Test;

public class InnateSerializerTest {

  private InnateSerializer<MockInnatelySerializable> sut;

  @Before
  public void setUp() {
    sut = new InnateSerializer<>(MockInnatelySerializable.class);
  }

  @Test
  public void shouldDelegateSerializeToClass() {
    Map<String, Object> expected = Collections.singletonMap("key", "value");
    Map<String, Object> actual = sut.serialize(new MockInnatelySerializable("value"));
    assertEquals(expected, actual);
  }

  @Test
  public void shouldDelegateDeserializeToClass() {
    MockInnatelySerializable expected = new MockInnatelySerializable("value");
    MockInnatelySerializable actual = sut.deserialize(Collections.singletonMap("key", "value"));
    assertEquals(expected, actual);
  }


  @Data
  private static class MockInnatelySerializable implements ConfigurationSerializable {
    private final String key;

    public static MockInnatelySerializable deserialize(Map<String, Object> serialization) {
      return new MockInnatelySerializable(serialization.get("key").toString());
    }

    @Override
    public Map<String, Object> serialize() {
      return Collections.singletonMap("key", key);
    }
  }

}