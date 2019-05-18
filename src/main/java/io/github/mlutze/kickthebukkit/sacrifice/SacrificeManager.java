package io.github.mlutze.kickthebukkit.sacrifice;

import java.util.List;

public interface SacrificeManager {
  void addSacrifice(Sacrifice sacrifice);

  List<Sacrifice> getSacrifices();
}
