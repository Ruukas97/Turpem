package dk.turpem.potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;

public class TurpemPotion extends Potion{
    public TurpemPotion(int id, boolean isBadEffect, int color) {
    	super(id, isBadEffect, color);
    }
	
    /**
     * Sets the index for the icon displayed in the player's inventory when the status is active.
     */
	@Override
	public Potion setIconIndex(int x, int y)
    {
        return super.setIconIndex(x, y);
    }
}
