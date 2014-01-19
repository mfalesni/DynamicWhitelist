package net.falesnik.minecraft.DynamicWhitelist;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicWhitelist extends JavaPlugin
{	
	private Boolean configLoaded = false;
	static final Logger log = Logger.getLogger("Minecraft");
	private final PlayerListener playerListener = new PlayerListener(this);
	static final String encoding = "UTF-8";
	
	public void logInfo(String message)
	{
		getLogger().info(message);
    }
	
	@Override
	public void onEnable()
	{
		logInfo("Started");
		if (!this.configLoaded) {
	        getConfig().options().copyDefaults(true);
	        saveConfig();
	        logInfo("Configuration loaded.");
	    } else {
	        reloadConfig();
	        getConfig().options().copyDefaults(false);
	        logInfo("Configuration reloaded.");
	    }
	    configLoaded = true;
	    getServer().getPluginManager().registerEvents(playerListener, this);
	}
	
	@Override
	public void onDisable()
	{
		logInfo("Stopped");
	}
	
	private String getURL()
	{
		return getConfig().getString("url", "http://localhost:8080/check_whitelist");
	}
	
	private String getCannotConnectString()
	{
		return getConfig().getString("cannot_connect", "Cannot connect to validation server!");
	}
	
	public WhitelistResponse checkWhitelisted(String player)
	{
		String query;
		String result;
		try {
			query = String.format("player=%s", URLEncoder.encode(player, encoding));
			URLConnection connection = new URL(getURL() + "?" + query).openConnection();
			InputStream response = connection.getInputStream();
			//connection.setRequestProperty("Accept-Charset", encoding);
			result = Utils.readString(response, 64, encoding);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(result);
			return new WhitelistResponse((Boolean) obj.get("whitelisted"), (String) obj.get("message"));
		} catch (IOException | ParseException e) {
			return new WhitelistResponse(Boolean.FALSE, getCannotConnectString());
		}
		
	}
	
	
}
