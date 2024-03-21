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
        )
    );

    public static void main(final String[] argv) throws IOException {
        Slicer.parse(argv).process(INPUTS);
    }
}
