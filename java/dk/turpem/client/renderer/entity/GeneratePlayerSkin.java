package dk.turpem.client.renderer.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.player.EntityPlayer;
import scala.reflect.io.Path;

public class GeneratePlayerSkin {

	public static int generatePlayerSkin(EntityPlayer player){
		try{
			File path = new File(URI.create("file:///Users/Lucas/desktop/TurpemSkins/"));
			
			// create the new image, canvas size is the max. of both image sizes
			BufferedImage playerSkin = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
			
			Graphics g = playerSkin.getGraphics();
			
			BufferedImage skin = ImageIO.read(new File(path, "skin.png"));
			g.drawImage(skin, 0, 0, null);
			
			BufferedImage hair = ImageIO.read(new File(path, "hair.png"));
			g.drawImage(hair, 0, 0, null);

			ImageIO.write(playerSkin, "PNG", new File(path, player.getCommandSenderName() + ".png"));	
			TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), playerSkin, false, false);
		}
		catch (IOException e) {
			System.out.println("Failed to generate skin.");
			return -1; 
		}
		return TextureUtil.glGenTextures();
	}
	
	/*public static void addPlayerClothing(EntityPlayer player) {
		players.put(clothing.player, clothing);
		try {
			if(clothing.glTextureID == -1) {
				clothing.glTextureID = TextureUtil.glGenTextures();
			}
			clothing.createClothingFile(clothingDir, clothingFileDir);
			BufferedImage image = ImageIO.read(clothing.getClothingFile(clothingFileDir));
			TextureUtil.uploadTextureImageAllocate(clothing.glTextureID, image, false, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
