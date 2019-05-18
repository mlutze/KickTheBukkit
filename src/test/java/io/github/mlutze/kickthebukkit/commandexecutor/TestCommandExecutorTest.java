package io.github.mlutze.kickthebukkit.commandexecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestCommandExecutorTest {

  private TestCommandExecutor sut;

  @Before
  public void setUp() {
    sut = new TestCommandExecutor();
  }

  @Test
  public void shouldSendMessageToPlayer() {
    CommandSender commandSender = Mockito.mock(CommandSender.class);
    Command command = Mockito.mock(Command.class);

    Mockito.doNothing().when(commandSender).sendMessage("Test successful.");
    sut.onCommand(commandSender, command, "label", new String[] {});

    Mockito.verify(commandSender).sendMessage("Test successful.");
  }

}