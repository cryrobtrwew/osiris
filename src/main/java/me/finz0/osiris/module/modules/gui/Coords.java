package me.finz0.osiris.module.modules.gui;

import de.Hero.settings.Setting;
import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.module.Module;

import java.util.ArrayList;

public class Coords extends Module {
    public Coords() {
        super("Coordinates", Category.GUI);
        setDrawn(false);
    }

    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting rainbow;
    public Setting customFont;
    public Setting decimal;

    public void setup(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("0");
        modes.add("0.0");
        modes.add("0.00");
        modes.add("0.0#");
        red = new Setting("CoordsRed", this, 255, 0, 255, true);
        green = new Setting("CoordsGreen", this, 255, 0, 255, true);
        blue = new Setting("CoordsBlue", this, 255, 0, 255, true);
        OsirisMod.getInstance().settingsManager.rSetting(red);
        OsirisMod.getInstance().settingsManager.rSetting(green);
        OsirisMod.getInstance().settingsManager.rSetting(blue);
        OsirisMod.getInstance().settingsManager.rSetting(rainbow = new Setting("coordsRainbow", this, false));
        OsirisMod.getInstance().settingsManager.rSetting(customFont = new Setting("coordsCFont", this, false));
        OsirisMod.getInstance().settingsManager.rSetting(decimal = new Setting("coordsDecimalFormat", this, "0", modes));
    }

    public void onEnable(){
        disable();
    }
}
