package edu.mines.littletheatre.minecraftleanbot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LeanBotPlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(new LeanListener(this), this);
  }
}
