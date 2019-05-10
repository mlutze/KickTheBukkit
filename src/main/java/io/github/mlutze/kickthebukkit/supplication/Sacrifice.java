package io.github.mlutze.kickthebukkit.supplication;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@SerializableAs("Sacrifice")
public class Sacrifice implements ConfigurationSerializable {
    private final List<ItemStack> items;

    @SuppressWarnings("unchecked")
    public static Sacrifice deserialize(Map<String, Object> serialization) {
        List<Map<String, Object>> itemStrings = (List<Map<String, Object>>) serialization.get("items");
        List<ItemStack> items = itemStrings.stream().map(ItemStack::deserialize).collect(Collectors.toList());
        return new Sacrifice(items);
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>builder()
                .put("items", items.stream().map(ItemStack::serialize).collect(Collectors.toList()))
                .build();
    }
}
