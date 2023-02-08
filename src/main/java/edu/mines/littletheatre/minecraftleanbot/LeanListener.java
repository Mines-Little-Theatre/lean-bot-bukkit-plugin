package edu.mines.littletheatre.minecraftleanbot;

import java.util.Locale;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class LeanListener implements Listener {
  private static final String MESSAGE_PREFIX =
      "[" + ChatColor.LIGHT_PURPLE + "Lean Bot" + ChatColor.RESET + "] " +
      ChatColor.BOLD + "I LOVE" + ChatColor.RESET + " ";
  private static final Pattern LEAN_REGEXP =
      Pattern.compile("(?:^|[^a-z])([a-z]*lean[a-z]*)(?:[^a-z]|$)");

  private final Plugin plugin;

  public LeanListener(Plugin plugin) { this.plugin = plugin; }

  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
    var matcher = LEAN_REGEXP.matcher(e.getMessage().toLowerCase(Locale.ROOT));
    if (matcher.find()) {
      var leanWord = matcher.group(1);
      var leanWordFormatted = leanWord.replaceAll(
          "lean", ChatColor.BOLD + "LEAN" + ChatColor.RESET);
      var leanWordCleaned = leanWordFormatted.replaceAll(
          "" + ChatColor.BOLD + ChatColor.RESET, "");
      Bukkit.getScheduler().scheduleSyncDelayedTask(
          plugin,
          () -> Bukkit.broadcastMessage(MESSAGE_PREFIX + leanWordCleaned));
    }
  }
}
