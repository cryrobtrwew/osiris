package me.finz0.osiris.command.commands;

import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.command.Command;

public class ClientnameCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{
                "name", "modname", "clientname", "suffix", "watermark"
        };
    }

    @Override
    public String getSyntax() {
        return "name <new name>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        OsirisMod.MODNAME = args[0];
    }
}
