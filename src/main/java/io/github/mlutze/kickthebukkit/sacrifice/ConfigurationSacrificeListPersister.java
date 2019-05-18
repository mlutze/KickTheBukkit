package io.github.mlutze.kickthebukkit.sacrifice;

import io.github.mlutze.kickthebukkit.persistence.Persister;
import io.github.mlutze.kickthebukkit.persistence.Serializer;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ConfigurationSacrificeListPersister implements Persister<List<Sacrifice>> {
  private final Serializer<Sacrifice> sacrificeSerializer;

  @Override
  public void saveTo(List<Sacrifice> object, File file) {
    List<Map<String, Object>> serializedSacrifices =
        object.stream().map(sacrificeSerializer::serialize).collect(Collectors.toList());
    Configuration configuration = YamlConfiguration.loadConfiguration(file);
    configuration.set("sacrifices", serializedSacrifices);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Sacrifice> loadFrom(File file) {
    Configuration configuration = YamlConfiguration.loadConfiguration(file);
    List<Map<?, ?>> serializedSacrifices = configuration.getMapList("sacrifices");
    return serializedSacrifices.stream()
        .map(s -> sacrificeSerializer.deserialize((Map<String, Object>) s))
        .collect(Collectors.toList());
  }
}
