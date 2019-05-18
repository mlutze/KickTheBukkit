package io.github.mlutze.kickthebukkit.sacrifice;

import java.util.List;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
public class Sacrifice {
  private final String reason;
  private final List<ItemStack> items;
}
