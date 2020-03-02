package me.finz0.osiris.module.modules.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.Hero.settings.Setting;
import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//TODO: AM/PM and 12 hour formats don't work
public class ChatTimeStamps extends Module {
    public ChatTimeStamps() {
        super("ChatTimeStamps", Category.CHAT);
        ArrayList<String> formats = new ArrayList<>();
        formats.add("HOUR24:MIN");
        formats.add("HOUR12:MIN");
        formats.add("HOUR12:MIN AM/PM");
        formats.add("HOUR24:MIN:SEC");
        formats.add("HOUR12:MIN:SEC");
        formats.add("HOUR12:MIN:SEC AM/PM");
        ArrayList<String> deco = new ArrayList<>(); deco.add("< >"); deco.add("[ ]"); deco.add("NONE");
        ArrayList<String> colors = new ArrayList<>();
        for(ChatFormatting cf : ChatFormatting.values()){
            colors.add(cf.getName());
        }

        rSetting(format = new Setting("ctsFormat", this, "HOUR24:MIN", formats));
        rSetting(color = new Setting("ctsColor", this, ChatFormatting.GRAY.getName(), colors));
        rSetting(decoration = new Setting("ctsDeco", this, "[ ]", deco));
        rSetting(space = new Setting("ctsSpace", this, false));
    }

    Setting format;
    Setting color;
    Setting decoration;
    Setting space;

    @EventHandler
    private Listener<ClientChatReceivedEvent> listener = new Listener<>(event -> {
        boolean beMe = decoration.getValString().equalsIgnoreCase("< >");
        boolean brackets = decoration.getValString().equalsIgnoreCase("[ ]");
        String decoLeft = beMe ? "<" : brackets ? "[" : "";
        String decoRight = beMe ? ">" : brackets ? "]" : "";
        if(space.getValBoolean()) decoRight += " ";
        String dateFormat = format.getValString()
                .replace("HOUR24", "k")
                .replace("HOUR12", "H")
                .replace("MIN", "mm")
                .replace("SEC", "ss")
                .replace("AP/PM", "a");
        String date = new SimpleDateFormat(dateFormat).format(new Date());
        TextComponentString newMsg = new TextComponentString(ChatFormatting.getByName(color.getValString()) + decoLeft + date + decoRight + ChatFormatting.RESET);
        event.setMessage(newMsg.appendSibling(event.getMessage()));
    });

    public void onEnable(){
        OsirisMod.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        OsirisMod.EVENT_BUS.unsubscribe(this);
    }

}
