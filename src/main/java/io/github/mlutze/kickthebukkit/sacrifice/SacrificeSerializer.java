package io.github.mlutze.kickthebukkit.sacrifice;

import io.github.mlutze.kickthebukkit.persistence.Serializer;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SacrificeSerializer implements Serializer<Sacrifice> {
  private final Serializer<ItemStack> itemStackSerializer;

  @Override
  public Map<String, Object> serialize(Sacrifice object) {
    return ImmutableMap.<String, Object>builder()
        .put("reason", object.getReason())
        .put("items",
            object.getItems().stream().map(ItemStack::serialize).collect(Collectors.toList()))
        .build();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Sacrifice deserialize(Map<String, Object> serialization) {
    String reason = (String) serialization.get("reason");
    List<Map<String, Object>> itemSerializations =
        (List<Map<String, Object>>) serialization.get("items");
    List<ItemStack> items =
        itemSerializations.stream().map(itemStackSerializer::deserialize)
            .collect(Collectors.toList());
    return new Sacrifice(reason, items);
  }

  // TODO javadoc everywhere
}
