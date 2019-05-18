package io.github.mlutze.kickthebukkit.sacrifice;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultSacrificeManager implements SacrificeManager {

  private final List<Sacrifice> sacrifices;

  @Override
  public void addSacrifice(Sacrifice sacrifice) {
    sacrifices.add(sacrifice);
  }

  @Override
  public List<Sacrifice> getSacrifices() {
    return sacrifices;
  }
}
