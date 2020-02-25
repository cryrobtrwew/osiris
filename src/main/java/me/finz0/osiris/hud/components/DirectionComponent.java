package me.finz0.osiris.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.Hero.clickgui.ClickGUI;
import de.Hero.clickgui.Panel;
import de.Hero.clickgui.util.ColorUtil;
import de.Hero.clickgui.util.FontUtil;
import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.module.ModuleManager;
import me.finz0.osiris.module.modules.gui.Direction;
import me.finz0.osiris.util.Rainbow;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;

import java.awt.*;

public class DirectionComponent extends Panel {
    public DirectionComponent(double ix, double iy, ClickGUI parent) {
        super("Direction", ix, iy, 10, 10, false, parent);
        this.isHudComponent = true;

    }



    Direction mod = ((Direction) ModuleManager.getModuleByName("Direction"));

    Color c;
    boolean font;
    Color text;
    Color color;
    String direction;


    public void drawHud(){
        doStuff();
        if(font) OsirisMod.fontRenderer.drawStringWithShadow(direction, (float)x, (float)y, text.getRGB());
        else mc.fontRenderer.drawStringWithShadow(direction, (float)x, (float)y, text.getRGB());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        doStuff();
        double w = mc.fontRenderer.getStringWidth(direction) + 2;
        c = new Color(50, 50, 50, 100);
        if(isHudComponentPinned) c = new Color(ColorUtil.getClickGUIColor().darker().getRed(), ColorUtil.getClickGUIColor().darker().getGreen(), ColorUtil.getClickGUIColor().darker().getBlue(), 100);
        if (this.dragging) {
            x = x2 + mouseX;
            y = y2 + mouseY;
        }
        this.width = w;
        this.height = FontUtil.getFontHeight() + 2;
        Gui.drawRect((int)x, (int)y, (int)x + (int)width, (int)y + (int)height, c.getRGB());
        FontUtil.drawStringWithShadow(title, x, y + height / 2 - FontUtil.getFontHeight()/2f, 0xffffffff);

        if(extended) {
            double startY = y + height;
            Gui.drawRect((int) x, (int) startY, (int) x + (int) width, (int) startY + (int) height, c.getRGB());
            if (font) OsirisMod.fontRenderer.drawStringWithShadow(direction, (float) x, (float) startY, text.getRGB());
            else mc.fontRenderer.drawStringWithShadow(direction, (float) x, (float) startY, text.getRGB());
        }
    }

    private void doStuff() {
        color = mod.color.getValColor();
        text = mod.rainbow.getValBoolean() ? Rainbow.getColor() : color;
        font = mod.customFont.getValBoolean();
        Entity entity = mc.getRenderViewEntity();
        EnumFacing enumfacing = entity.getHorizontalFacing();
        String s = "Invalid";
        boolean xz = mod.mode.getValString().equalsIgnoreCase("XZ");
        switch (enumfacing) {
            case NORTH: s = xz ? "-Z" : "N";break;
            case SOUTH: s = xz ? "+Z" : "S";break;
            case WEST: s = xz ? "-X" : "W";break;
            case EAST: s = xz ? "+X" : "E";
        }
        direction = ChatFormatting.GRAY + "[" + ChatFormatting.RESET + s + ChatFormatting.GRAY + "]";
    }
}
