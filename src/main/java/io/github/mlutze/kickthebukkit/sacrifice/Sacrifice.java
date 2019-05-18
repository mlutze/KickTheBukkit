package io.github.mlutze.kickthebukkit.sacrifice;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public class Sacrifice {
  private final String reason;
  private final List<ItemStack> items;
}
