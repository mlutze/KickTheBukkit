package io.github.mlutze.kickthebukkit.persistence;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@AllArgsConstructor
public class InnateSerializer<T extends ConfigurationSerializable> implements Serializer<T> {
  private final Class<T> clazz;

  @Override
  public Map<String, Object> serialize(T object) {
    return object.serialize();
  }

  @Override
  @SuppressWarnings("unchecked")
  public T deserialize(Map<String, Object> serialization) {
    try {
      Method deserializeMethod = clazz.getDeclaredMethod("deserialize", Map.class);
      Object object = deserializeMethod.invoke(null, serialization);
      return (T) object;
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException("ConfigurationSerializable " + clazz.getName()
          + " does not correctly implement static deserialize(Map<String, Object>", e);
    }
  }

}
