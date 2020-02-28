package me.finz0.osiris.module.modules.gui;

import de.Hero.settings.Setting;
import me.finz0.osiris.module.Module;

public class ArmorHUD extends Module {
    public ArmorHUD() {
        super("ArmorHUD", Category.GUI);
        rSetting(vertical = new Setting("ahVertical", this, false));
        rSetting(reverse = new Setting("ahReverse", this, false));
    }

    public Setting vertical;
    public Setting reverse;

    public void onEnable(){
        disable();
    }
}
