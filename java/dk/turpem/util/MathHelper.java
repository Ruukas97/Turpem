package dk.turpem.util;

import net.minecraft.entity.Entity;

public class MathHelper {
	public static double calculateDistanceEntities3D(final Entity entity1, final Entity entity2){
		return getDistanceXYZ(entity1.posX, entity1.posY, entity1.posZ, entity2.posX, entity2.posY, entity2.posZ);
	}
	
	public static double calculateDistanceEntities2D(final Entity entity1, final Entity entity2){
		return getDistanceXY(entity1.posX, entity1.posY, entity2.posX, entity2.posY);
	}
	
	public static double getDistanceXYZ(double x1, double y1, double z1, double x2, double y2, double z2){
		final double x = x1 - x2;
		final double y = y1 - y2;
		final double z = z1 - z2;
		return Math.sqrt(Math.sqrt(x*x + y*y + z*z));
	}
	
	public static double getDistanceXY(double x1, double y1, double x2, double y2){
		final double x = x1 - x2;
		final double y = y1 - y2;
		return Math.sqrt(Math.sqrt(x*x + y*y));
	}
}