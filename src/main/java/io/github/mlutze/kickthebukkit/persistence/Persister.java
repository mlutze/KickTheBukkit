package io.github.mlutze.kickthebukkit.persistence;

import java.io.File;

public interface Persister<T> {
  void saveTo(T object, File file);

  T loadFrom(File file);
}
