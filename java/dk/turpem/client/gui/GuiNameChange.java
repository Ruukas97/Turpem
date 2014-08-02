package dk.turpem.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import dk.turpem.Turpem;
import dk.turpem.packet.RequestNameChange;

public class GuiNameChange extends GuiScreen {
    private static final Logger logger = LogManager.getLogger();
    private GuiTextField nameField;
    private GuiTextField oldNameField;
    /** "Done" button for the GUI. */
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
	private EntityPlayer player;
	
	public GuiNameChange(EntityPlayer player) {
		super();
		this.player = player;
	}
	
	@Override
    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.nameField.updateCursorCounter();
    }

    /**
     * Draws the screen and all the components in it.
     */
	@Override     
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Set the display name for your character!", new Object[0]), this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("Name", new Object[0]), this.width / 2 - 150, 37, 10526880);
        this.nameField.drawTextBox();
        byte b0 = 75;
        byte b1 = 0;
        FontRenderer fontrenderer = this.fontRendererObj;
        String s = I18n.format("2 - 16 Characters", new Object[0]);
        int i1 = this.width / 2 - 150;
        int l = b1 + 1;
        this.drawString(fontrenderer, s, i1, b0 + b1 * this.fontRendererObj.FONT_HEIGHT, 10526880);
        this.drawString(this.fontRendererObj, I18n.format("Can only contain letters A-Z", new Object[0]), this.width / 2 - 150, b0 + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);
        this.drawString(this.fontRendererObj, I18n.format("Can not contain any spaces", new Object[0]), this.width / 2 - 150, b0 + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);

        if (this.oldNameField.getText().length() > 0)
        {
            int k = b0 + l * this.fontRendererObj.FONT_HEIGHT + 20;
            this.drawString(this.fontRendererObj, I18n.format("Previous Name", new Object[0]), this.width / 2 - 150, k, 10526880);
            this.oldNameField.drawTextBox();
        }

        super.drawScreen(par1, par2, par3);
    }
	
	@Override
	public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done", new Object[0])));
        this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        this.nameField = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 50, 300, 20);
        this.nameField.setMaxStringLength(16);
        this.nameField.setFocused(true);
        this.nameField.setText(player.getDisplayName());
        this.nameField.setCanLoseFocus(false);
        this.oldNameField = new GuiTextField(this.fontRendererObj, this.width / 2 - 150, 135, 300, 20);
        this.oldNameField.setMaxStringLength(32767);
        this.oldNameField.setEnabled(false);
        this.oldNameField.setText("");

        if (player.getCommandSenderName() != player.getDisplayName())
        {
            this.oldNameField.setText(player.getDisplayName());
        }

        this.doneBtn.enabled = this.nameField.getText().trim().length() > 1 && this.nameField.getText() != this.oldNameField.getText();
	}
	
	@Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
        	//Cancel Button
            if (button.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            //Done Button
            else if (button.id == 0)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
                player.getEntityData().setString("DisplayName", nameField.getText());
                player.refreshDisplayName();
            	Turpem.snw.sendToServer(new RequestNameChange(nameField.getText()));
            }
        }
    }
	
	@Override
	public boolean doesGuiPauseGame() {
        return true;
    }
	
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        this.nameField.textboxKeyTyped(par1, par2);
        this.oldNameField.textboxKeyTyped(par1, par2);
        this.doneBtn.enabled = this.nameField.getText().trim().length() > 1;

        if (par2 != 28 && par2 != 156)
        {                                                     
            if (par2 == 1)                                    
            {                                                 
                this.actionPerformed(this.cancelBtn);
            }                                                 
        }                                                     
        else                                                  
        {
            this.actionPerformed(this.doneBtn);
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.nameField.mouseClicked(par1, par2, par3);
    }
}
