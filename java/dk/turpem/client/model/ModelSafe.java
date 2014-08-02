package dk.turpem.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSafe extends ModelBase
{
    /** The chest lid in the chest's model. */
    public ModelRenderer chestDoor = (new ModelRenderer(this, 42, 0)).setTextureSize(64, 32);
    /** The model of the bottom of the chest. */
    public ModelRenderer chestBelow;

    public ModelSafe()
    {
        this.chestDoor.addBox(0.0F, -4.0F, -1.0F, 8, 8, 1, 0.0F);
        this.chestDoor.rotationPointX = 4.0F;
        this.chestDoor.rotationPointY = 13.0F;
        this.chestDoor.rotationPointZ = 1.0F;
        this.chestBelow = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
        this.chestBelow.addBox(0.0F, 0.0F, 0.0F, 14, 14, 14, 0.0F);
        this.chestBelow.rotationPointX = 1.0F;
        this.chestBelow.rotationPointY = 6.0F;
        this.chestBelow.rotationPointZ = 1.0F;
    }

    /**
     * This method renders out all parts of the chest model.
     */
    public void renderAll()
    {
        this.chestDoor.render(0.0625F);
        this.chestBelow.render(0.0625F);
    }
}