package com.mojang.slicer.commands;

import com.mojang.slicer.AppGui;
import com.mojang.slicer.InputFile;
import com.mojang.slicer.Slicer;
import me.satanicantichrist.Command;
import me.satanicantichrist.Flag;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Slice extends Command {
    private final String version;
    private final List<InputFile> inputs;

    public Slice(String version, List<InputFile> inputs) {
        super("slice", "Upgrades resourcepack textures, to new formats");
        this.version = version;
        this.inputs = inputs;
        addFlag(new Flag(true, "source", 's', "Source resourcepack to be updated. (Can be folder or a .zip file)"));
        addFlag(new Flag(true, "output", 'o', "Output folder where the updated resourcepack will be saved."));
        addFlag(new Flag(true, "leftover", 'l', "Leftover folder, where textures or parts of them, will be saved, to show what parts of them was left unused in the new version. (optional)"));
        addFlag(new Flag(false, "gui", 'g', "Runs slicer in GUI mode, good for easier navigation for folders."));
    }

    @Override
    public void onRun() {
        if (getFlag("gui").isInArgv()) {
            new AppGui(this.version, this.inputs, (String) getFlag("source").getValue(), (String) getFlag("output").getValue(), (String) getFlag("leftover").getValue());
            return;
        }
        if (getFlag("source").getValue() == null) {
            System.out.println("Missing --source flag value");
            return;
        }
        if (getFlag("output").getValue() == null) {
            System.out.println("Missing --output flag value");
            return;
        }
        Path source = Path.of((String) getFlag("source").getValue());
        Path output = Path.of((String) getFlag("output").getValue());
        Path leftover = null;

        if (getFlag("leftover").isInArgv()) {
            if (getFlag("leftover").getValue() == null) {
                System.out.println("Missing --leftover flag value");
                return;
            }
            leftover = Path.of((String) getFlag("leftover").getValue());
        }

        try {
            new Slicer(source, output, leftover).process(this.inputs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
