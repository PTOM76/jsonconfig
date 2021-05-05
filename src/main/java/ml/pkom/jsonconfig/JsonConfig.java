package ml.pkom.jsonconfig;

import org.bukkit.plugin.java.JavaPlugin;

public class JsonConfig extends JavaPlugin
{

    public final String MOD_ID = "jsonconfig";
    public final String MOD_NAME = "JsonConfig";
    public final String MOD_VER = "1.0.0";
    public final String MOD_AUT = "Pitan";

    @Override
    public void onEnable()
    {
        getLogger().info(MOD_NAME + " is enabled!");        
    }

    @Override
    public void onDisable()
    {
        getLogger().info(MOD_NAME + " is disabled!");
    }
}