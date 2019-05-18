package io.github.mlutze.kickthebukkit.sacrifice;

import lombok.AllArgsConstructor;

import java.util.List;

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
