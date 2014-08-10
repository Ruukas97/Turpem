package dk.turpem.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSafe extends ModelBase
{
    /** The door in the safe's model. */
    public ModelRenderer safeDoor = (new ModelRenderer(this, 42, 0)).setTextureSize(64, 32);
    /** The model of the base of the chest. */
    public ModelRenderer safeBase;

    public ModelSafe()
    {
        this.safeDoor.addBox(0.0F, -4.0F, -1.0F, 8, 8, 1, 0.0F);
        this.safeDoor.rotationPointX = 4.0F;
        this.safeDoor.rotationPointY = 13.0F;
        this.safeDoor.rotationPointZ = 1.0F;
        this.safeBase = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
        this.safeBase.addBox(0.0F, 0.0F, 0.0F, 14, 14, 14, 0.0F);
        this.safeBase.rotationPointX = 1.0F;
        this.safeBase.rotationPointY = 6.0F;
        this.safeBase.rotationPointZ = 1.0F;
    }

    /**
     * This method renders out all parts of the chest model.
     */
    public void renderAll()
    {
        this.safeDoor.render(0.0625F);
        this.safeBase.render(0.0625F);
    }
}