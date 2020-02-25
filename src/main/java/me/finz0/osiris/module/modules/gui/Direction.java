package me.finz0.osiris.module.modules.gui;

import de.Hero.settings.Setting;
import me.finz0.osiris.module.Module;

import java.awt.Color;
import java.util.ArrayList;

public class Direction extends Module {
    public Direction() {
        super("Direction", Category.GUI);
        ArrayList<String> modes = new ArrayList<>();
        modes.add("XZ");
        modes.add("NSWE");
        rSetting(mode = new Setting("dirMode", this, "XZ", modes));
        rSetting(color = new Setting("dirColor", this, Color.WHITE));
        rSetting(rainbow = new Setting("dirRainbow", this, false));
        rSetting(customFont = new Setting("dirCFont", this, false));
    }

    public Setting color;
    public Setting mode;
    public Setting rainbow;
    public Setting customFont;
}
