package me.finz0.osiris.module.modules.gui;

import de.Hero.settings.Setting;
import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.module.Module;

import java.awt.*;

public class Fps extends Module {
    public Fps() {
        super("FPS", Category.GUI);
        setDrawn(false);
    }
    public Setting rainbow;
    public Setting customFont;
    public Setting color;

    public void setup(){
        OsirisMod.getInstance().settingsManager.rSetting(rainbow = new Setting("fpsRainbow", this, false));
        OsirisMod.getInstance().settingsManager.rSetting(customFont = new Setting("fpsCFont", this, false));
        rSetting(color = new Setting("fpsColor", this, Color.WHITE));
    }

    public void onEnable(){
        disable();
    }
}
