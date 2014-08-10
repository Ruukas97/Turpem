package dk.turpem.client.gui;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
 
public class TestGui extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;
    private EntityPlayer player;
 
    public TestGui(EntityPlayer player) { 
    	this.player = player;
    	
        b1 = new JButton("Disable middle button");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("disable");
 
        b2 = new JButton("Fly");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_F);
        b2.setActionCommand("fly");
 
        b3 = new JButton("Enable middle button");
        //Use the default text position of CENTER, TRAILING (RIGHT).
        b3.setMnemonic(KeyEvent.VK_E);
        b3.setActionCommand("enable");
        b3.setEnabled(false);
        
        
 
        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
 
        b1.setToolTipText("Click this button to disable the middle button.");
        b2.setToolTipText("This middle button does nothing when you click it.");
        b3.setToolTipText("Click this button to enable the middle button.");
 
        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b2);
        add(b3);
    }
 
    public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
        	player.canBreatheUnderwater();
            b2.setEnabled(false);
            b1.setEnabled(false);
            b3.setEnabled(true);
        }
        else if ("fly".equals(e.getActionCommand())) {
        	player.motionY++;
        	player.motionX+=10;
        	b2.setEnabled(true);
            b1.setEnabled(true);
            b3.setEnabled(true);
            player.dropItem(Items.diamond, 1);
        }
        else {
            b2.setEnabled(true);
            b1.setEnabled(true);
            b3.setEnabled(false);
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    public static void createAndShowGUI(EntityPlayer player) {
        //Create and set up the window.
        JFrame frame = new JFrame("TestGui");
 
        //Create and set up the content pane.
        TestGui newContentPane = new TestGui(player);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}