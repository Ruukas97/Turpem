package dk.turpem.chat;

import net.minecraft.util.EnumChatFormatting;
import dk.turpem.util.GrammarHelper;

public enum EnumChat {
	ASK(25, EnumChatFormatting.GOLD, false),
	QUIETLYSAY(10, EnumChatFormatting.GRAY, "quietly says", "qsay", false),
	QUIETLYASK(10, EnumChatFormatting.GRAY, "quietly asks", "qask", true),
	SCREAM(80, EnumChatFormatting.DARK_RED, false),
	SHOUT(50, EnumChatFormatting.RED, false), 
	TALK(25, EnumChatFormatting.YELLOW, "says", "say", false),
	WHISPER(3, EnumChatFormatting.DARK_GRAY, false);
	
	@Override public String toString() {
		return GrammarHelper.capitalize(super.toString().toLowerCase(), false);
	}
	
	private int range;
	private EnumChatFormatting color;
	private String saying;
	private String command;
	private char ending;
	private boolean forceEnding;
	 
	private EnumChat(int range, EnumChatFormatting color, boolean forceEnding) {
		this.range = range;
		this.color = color;
		this.saying = toString().toLowerCase() + "s";
		this.command = super.toString().toLowerCase();
		if(getCommand().contains("ask"))
			this.ending = '?';
		else if(getCommand().equals("scream"))
			this.ending = '!';
		else this.ending = 'n';
		this.forceEnding = forceEnding;
		}
	
	private EnumChat(int range, EnumChatFormatting color, String saying, String command, boolean forceEnding) {
		this.range = range;
		this.color = color;
		this.saying = saying;
		this.command = command;
		if(getCommand().contains("ask"))
			this.ending = '?';
		else if(getCommand().equals("scream"))
			this.ending = '!';
		else this.ending = 'n';
		this.forceEnding = forceEnding;
	}
	
	public static EnumChat fromString(String text){
		if (text != null) {
			for (EnumChat enumchat : EnumChat.values()){
				System.out.println(enumchat.getCommand() + " " + enumchat.getCommand().length());
				System.out.println(text + " " + text.length());
				if (text.equalsIgnoreCase(enumchat.command)){
					return enumchat;
				}
			}
		}
		return null;
	}
	 
	public int getRange(){
		return range;
	}
	
	public EnumChatFormatting getColor(){
		return color;
	}
	
	public String getSaying(){
		return saying;
	}
	
	public String getCommand(){
		return command;
	}
	
	public char ending(){
		return ending;
	}
	
	public boolean forceEnding(){
		return forceEnding;
	}

	public boolean shouldCAPS() {
		return this.equals(SCREAM);
	}
}
