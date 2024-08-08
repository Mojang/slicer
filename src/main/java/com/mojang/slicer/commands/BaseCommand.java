package com.mojang.slicer.commands;

import me.satanicantichrist.Command;
import me.satanicantichrist.EasyCli;
import me.satanicantichrist.Flag;

public class BaseCommand extends Command {
    private final String VERSION;

    public BaseCommand(String versionData) {
        super("Slicer", "Slicer is tool made to update your resourcepacks to newer minecraft Version.");
        this.VERSION = versionData;
        addFlag(new Flag(false, "version", 'v', "Shows version of Slicer."));
    }

    @Override
    public void onRun() {
        if (getFlag("version").isInArgv()) {
            System.out.println("Version: " + this.VERSION);
            return;
        }
        if (getFlag("help").isInArgv()) {
            System.out.println(EasyCli.getMainHelp());
        }

    }
}
