package io.github.mlutze.kickthebukkit.supplication;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@SerializableAs("Supplication")
public class Supplication implements ConfigurationSerializable {
    private final List<Sacrifice> sacrifices;
    private final String request;

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>builder()
                .put("sacrifices", sacrifices.stream().map(Sacrifice::serialize).collect(Collectors.toList()))
                .put("request", request)
                .build();
    }

    @SuppressWarnings("unchecked")
    public static Supplication deserialize(Map<String, Object> serialization) {
        List<Map<String, Object>> serializedSacrifices = (List<Map<String, Object>>) serialization.get("sacrifices");
        List<Sacrifice> sacrifices =
                serializedSacrifices.stream().map(Sacrifice::deserialize).collect(Collectors.toList());
        String request = (String) serialization.get("request");
        return new Supplication(sacrifices, request);
    }
}
