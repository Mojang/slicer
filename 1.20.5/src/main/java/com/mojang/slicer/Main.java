// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer;

import com.mojang.slicer.Box;
import com.mojang.slicer.InputFile;
import com.mojang.slicer.OutputFile;
import com.mojang.slicer.Slicer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.List;

@ParametersAreNonnullByDefault
public class Main {
    private static OutputFile mapDecoration(final String path, final int index) {
        final int x = index % 16;
        final int y = index / 16;
        return new OutputFile(
            "assets/minecraft/textures/map/decorations/" + path + ".png",
            new Box(x * 8, y * 8, 8, 8, 128, 128)
        );
    }

    private static OutputFile experienceOrb(final String path, final int index) {
        final int x = index % 4;
        final int y = index / 4;
        return new OutputFile(
            "assets/minecraft/textures/experience_orb/" + path + ".png",
            new Box(x * 16, y * 16, 16, 16, 64, 64)
        );
    }

    private static OutputFile moonPhase(final String path, final int index) {
        final int x = index % 2;
        final int y = index / 4;
        return new OutputFile(
            "assets/minecraft/textures/celestial_body/" + path + ".png",
            new Box(x * 32, y * 32, 32, 32, 128, 64)
        );
    }

    private static InputFile input(final String path, final OutputFile... outputs) {
        return new InputFile(path).outputs(outputs);
    }

    private static final List<InputFile> INPUTS = List.of(
        input("assets/minecraft/textures/map/map_icons.png",
            mapDecoration("player", 0),
            mapDecoration("frame", 1),
            mapDecoration("red_marker", 2),
            mapDecoration("blue_marker", 3),
            mapDecoration("target_x", 4),
            mapDecoration("target_point", 5),
            mapDecoration("player_off_map", 6),
            mapDecoration("player_off_limits", 7),
            mapDecoration("woodland_mansion", 8),
            mapDecoration("ocean_monument", 9),
            mapDecoration("white_banner", 10),
            mapDecoration("orange_banner", 11),
            mapDecoration("magenta_banner", 12),
            mapDecoration("light_blue_banner", 13),
            mapDecoration("yellow_banner", 14),
            mapDecoration("lime_banner", 15),
            mapDecoration("pink_banner", 16),
            mapDecoration("gray_banner", 17),
            mapDecoration("light_gray_banner", 18),
            mapDecoration("cyan_banner", 19),
            mapDecoration("purple_banner", 20),
            mapDecoration("blue_banner", 21),
            mapDecoration("brown_banner", 22),
            mapDecoration("green_banner", 23),
            mapDecoration("red_banner", 24),
            mapDecoration("black_banner", 25),
            mapDecoration("red_x", 26),
            mapDecoration("desert_village", 27),
            mapDecoration("plains_village", 28),
            mapDecoration("savanna_village", 29),
            mapDecoration("snowy_village", 30),
            mapDecoration("taiga_village", 31),
            mapDecoration("jungle_temple", 32),
            mapDecoration("swamp_hut", 33)
        ),
        input("assets/minecraft/textures/entity/experience_orb.png",
            experienceOrb("smallest", 0),
            experienceOrb("value_3", 1),
            experienceOrb("value_7", 2),
            experienceOrb("value_17", 3),
            experienceOrb("value_37", 4),
            experienceOrb("value_73", 5),
            experienceOrb("value_149", 6),
            experienceOrb("value_307", 7),
            experienceOrb("value_617", 8),
            experienceOrb("value_1237", 9),
            experienceOrb("value_2477", 10)
        ),
        input("assets/minecraft/textures/environment/moon_phases.png",
            moonPhase("moon_full", 0),
            moonPhase("moon_waning_gibbous", 1),
            moonPhase("moon_last_quarter", 2),
            moonPhase("moon_waning_crescent", 3),
            moonPhase("moon_new", 4),
            moonPhase("moon_waxing_crescent", 5),
            moonPhase("moon_first_quarter", 6),
            moonPhase("moon_waxing_gibbous", 7)
        )
    );

    public static void main(final String[] argv) throws IOException {
        Slicer.parse(argv).process(INPUTS);
    }
}
