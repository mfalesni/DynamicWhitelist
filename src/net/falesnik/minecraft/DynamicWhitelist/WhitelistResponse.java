package net.falesnik.minecraft.DynamicWhitelist;

public class WhitelistResponse {
	private String message;
	private Boolean allowed;
	
	public WhitelistResponse(Boolean allowed, String message) {
		this.message = message;
		this.allowed = allowed;
	}
	
	public Boolean canGoIn()
	{
		return allowed;
	}
	
	public String getMessage()
	{
		return this.message;
	}

}
