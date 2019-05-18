package io.github.mlutze.kickthebukkit.persistence;

import java.util.Map;

public interface Serializer<T> {
  Map<String, Object> serialize(T object);

  T deserialize(Map<String, Object> serialization);
}
