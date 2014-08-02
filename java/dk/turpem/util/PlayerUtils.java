package dk.turpem.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerUtils {
    /**
     * Sets the width and height of the entity. Args: width, height
     */
    public static void setSize(EntityPlayer player, float par1, float par2)
    {
        float f2;

        if (par1 != player.width || par2 != player.height)
        {
            f2 = player.width;
            player.width = par1;
            player.height = par2;
            player.boundingBox.maxX = player.boundingBox.minX + (double)player.width;
            player.boundingBox.maxZ = player.boundingBox.minZ + (double)player.width;
            player.boundingBox.maxY = player.boundingBox.minY + (double)player.height;
        }

        f2 = par1 % 2.0F;

        if ((double)f2 < 0.375D)
        {
            player.myEntitySize = Entity.EnumEntitySize.SIZE_1;
        }
        else if ((double)f2 < 0.75D)
        {
            player.myEntitySize = Entity.EnumEntitySize.SIZE_2;
        }
        else if ((double)f2 < 1.0D)
        {
            player.myEntitySize = Entity.EnumEntitySize.SIZE_3;
        }
        else if ((double)f2 < 1.375D)
        {
            player.myEntitySize = Entity.EnumEntitySize.SIZE_4;
        }
        else if ((double)f2 < 1.75D)
        {
            player.myEntitySize = Entity.EnumEntitySize.SIZE_5;
        }
        else
        {
            player.myEntitySize = Entity.EnumEntitySize.SIZE_6;
        }
    }
}
