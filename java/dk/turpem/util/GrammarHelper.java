package dk.turpem.util;

import org.apache.commons.lang3.text.WordUtils;

import net.minecraft.util.EnumChatFormatting;

public class GrammarHelper {
	public static String punctuate(String str){
		if(str.endsWith("."))
			return str;
		return str + ".";
	}
	
	//TODO Fix to work with #EnumChat
	public static String punctuateChat(String str, EnumChatFormatting format, char ending, boolean forceEnding){
		if(ending != 'n' && forceEnding){
			if(str.endsWith(".") || str.endsWith("?") || str.endsWith("!"))
				return str.substring(0, str.length()-1) + format + ending;
			else return str + format + ending;
		}
		if(str.endsWith(".") || str.endsWith("?") || str.endsWith("!")){
			String endingStr = str.substring(str.length()-1);
			return (str.substring(0, str.length()-1) + format + endingStr).toUpperCase();	
		}
		return (str + format + ".").toUpperCase();
	}
	
	public static String capitalize(String text, boolean caps) {
		if(caps)
			return text.toUpperCase();
		text = text.toLowerCase();
		int pos = 0;
		boolean capitalize = true;
		StringBuilder sb = new StringBuilder(text);
		while (pos < sb.length()) {
		    if (sb.charAt(pos) == '.' || sb.charAt(pos) == '?'  || sb.charAt(pos) == '!') {
		        capitalize = true;
		    } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
		        sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
		        capitalize = false;
		    }
		    pos++;
		}
		return sb.toString();
	}	
}
