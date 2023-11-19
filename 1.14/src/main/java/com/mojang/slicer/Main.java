// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.function.UnaryOperator;

@ParametersAreNonnullByDefault
public class Main {
    private static Box b256(final int x, final int y, final int w, final int h) {
        return new Box(x, y, w, h, 256, 256);
    }

    private static Box b128(final int x, final int y, final int w, final int h) {
        return new Box(x, y, w, h, 128, 128);
    }

    private static final UnaryOperator<BufferedImage> SQUARE = img -> {
        final int width = img.getWidth();
        final int height = img.getHeight();
        final int dim = Math.max(width, height);

        final int dx = (dim - width) / 2;
        final int dy = (dim - height) / 2;

        final BufferedImage newImg = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
        newImg.getGraphics().drawImage(img, dx, dy, width, height, null);
        return newImg;
    };

    private static OutputFile gridSprite(final String path, final int x, final int y, final int w, final int h, final int xOff, final int yOff, final int xScale, final int yScale) {
        return new OutputFile(path, b256(xScale * x + xOff, yScale * y + yOff, w * xScale, h * yScale));
    }

    private static OutputFile painting(final String path, final int x, final int y, final int w, final int h) {
        return gridSprite("assets/minecraft/textures/painting/" + path + ".png", x, y, w, h, 0, 0, 16, 16);
    }

    private static OutputFile effect(final String path, final int x, final int y) {
        return gridSprite("assets/minecraft/textures/mob_effect/" + path + ".png", x, y, 1, 1, 0, 198, 18, 18);
    }

    private static OutputFile particle(final String path, final int x, final int y, final int w, final int h) {
        return gridSprite("assets/minecraft/textures/particle/" + path + ".png", x, y, w, h, 0, 0, 8, 8);
    }

    private static OutputFile explosion(final String path, final int x, final int y) {
        return new OutputFile("assets/minecraft/textures/particle/" + path + ".png", b128(32 * x, 32 * y, 32, 32));
    }

    private static OutputFile particle(final String path, final int x, final int y) {
        return particle(path, x, y, 1, 1);
    }

    private static OutputFile particle(final String path, final int x, final int y, final int xOff, final int yOff, final int w, final int h) {
        return gridSprite("assets/minecraft/textures/particle/" + path + ".png", x, y, w, h, xOff, yOff, 8, 8);
    }

    private static OutputFile sweep(final int i, final int x, final int y) {
        return new OutputFile("assets/minecraft/textures/particle/sweep_" + i + ".png", new Box(32 * x, 16 * y, 32, 16, 128, 32)).apply(SQUARE);
    }

    private static InputFile input(final String path, final OutputFile... outputs) {
        return new InputFile(path).outputs(outputs);
    }

    private static final List<InputFile> INPUTS = List.of(
        input("assets/minecraft/textures/painting/paintings_kristoffer_zetterstrand.png",
            painting("back", 15, 0, 1, 1),

            painting("kebab", 0, 0, 1, 1),
            painting("aztec", 1, 0, 1, 1),
            painting("alban", 2, 0, 1, 1),
            painting("aztec2", 3, 0, 1, 1),
            painting("bomb", 4, 0, 1, 1),
            painting("plant", 5, 0, 1, 1),
            painting("wasteland", 6, 0, 1, 1),
            painting("pool", 0, 2, 2, 1),
            painting("courbet", 2, 2, 2, 1),
            painting("sea", 4, 2, 2, 1),
            painting("sunset", 6, 2, 2, 1),
            painting("creebet", 8, 2, 2, 1),
            painting("wanderer", 0, 4, 1, 2),
            painting("graham", 1, 4, 1, 2),
            painting("match", 0, 8, 2, 2),
            painting("bust", 2, 8, 2, 2),
            painting("stage", 4, 8, 2, 2),
            painting("void", 6, 8, 2, 2),
            painting("skull_and_roses", 8, 8, 2, 2),
            painting("wither", 10, 8, 2, 2),
            painting("fighters", 0, 6, 4, 2),
            painting("pointer", 0, 12, 4, 4),
            painting("pigscene", 4, 12, 4, 4),
            painting("burning_skull", 8, 12, 4, 4),
            painting("skeleton", 12, 4, 4, 3),
            painting("donkey_kong", 12, 7, 4, 3)
        ),
        input("assets/minecraft/textures/gui/container/inventory.png",
            effect("speed", 0, 0),
            effect("slowness", 1, 0),
            effect("haste", 2, 0),
            effect("mining_fatigue", 3, 0),
            effect("strength", 4, 0),
            effect("jump_boost", 2, 1),
            effect("nausea", 3, 1),
            effect("regeneration", 7, 0),
            effect("resistance", 6, 1),
            effect("fire_resistance", 7, 1),
            effect("water_breathing", 0, 2),
            effect("invisibility", 0, 1),
            effect("blindness", 5, 1),
            effect("night_vision", 4, 1),
            effect("hunger", 1, 1),
            effect("weakness", 5, 0),
            effect("poison", 6, 0),
            effect("wither", 1, 2),
            effect("health_boost", 7, 2),
            effect("absorption", 2, 2),
            effect("glowing", 4, 2),
            effect("levitation", 3, 2),
            effect("luck", 5, 2),
            effect("unluck", 6, 2),
            effect("slow_falling", 8, 0),
            effect("conduit_power", 9, 0),
            effect("dolphins_grace", 10, 0)
        ),
        input("assets/minecraft/textures/particle/particles.png",
            particle("generic_0", 0, 0),
            particle("generic_1", 1, 0),
            particle("generic_2", 2, 0),
            particle("generic_3", 3, 0),
            particle("generic_4", 4, 0),
            particle("generic_5", 5, 0),
            particle("generic_6", 6, 0),
            particle("generic_7", 7, 0),

            particle("splash_0", 3, 1),
            particle("splash_1", 4, 1),
            particle("splash_2", 5, 1),
            particle("splash_3", 6, 1),

            particle("sga_a", 1, 14),
            particle("sga_b", 2, 14),
            particle("sga_c", 3, 14),
            particle("sga_d", 4, 14),
            particle("sga_e", 5, 14),
            particle("sga_f", 6, 14),
            particle("sga_g", 7, 14),
            particle("sga_h", 8, 14),
            particle("sga_i", 9, 14),
            particle("sga_j", 10, 14),
            particle("sga_k", 11, 14),
            particle("sga_l", 12, 14),
            particle("sga_m", 13, 14),
            particle("sga_n", 14, 14),
            particle("sga_o", 15, 14),
            particle("sga_p", 0, 15),
            particle("sga_q", 1, 15),
            particle("sga_r", 2, 15),
            particle("sga_s", 3, 15),
            particle("sga_t", 4, 15),
            particle("sga_u", 5, 15),
            particle("sga_v", 6, 15),
            particle("sga_w", 7, 15),
            particle("sga_x", 8, 15),
            particle("sga_y", 9, 15),
            particle("sga_z", 10, 15),

            particle("effect_0", 0, 8),
            particle("effect_1", 1, 8),
            particle("effect_2", 2, 8),
            particle("effect_3", 3, 8),
            particle("effect_4", 4, 8),
            particle("effect_5", 5, 8),
            particle("effect_6", 6, 8),
            particle("effect_7", 7, 8),

            particle("glitter_0", 0, 11),
            particle("glitter_1", 1, 11),
            particle("glitter_2", 2, 11),
            particle("glitter_3", 3, 11),
            particle("glitter_4", 4, 11),
            particle("glitter_5", 5, 11),
            particle("glitter_6", 6, 11),
            particle("glitter_7", 7, 11),

            particle("spark_0", 0, 10),
            particle("spark_1", 1, 10),
            particle("spark_2", 2, 10),
            particle("spark_3", 3, 10),
            particle("spark_4", 4, 10),
            particle("spark_5", 5, 10),
            particle("spark_6", 6, 10),
            particle("spark_7", 7, 10),

            particle("spell_0", 0, 9),
            particle("spell_1", 1, 9),
            particle("spell_2", 2, 9),
            particle("spell_3", 3, 9),
            particle("spell_4", 4, 9),
            particle("spell_5", 5, 9),
            particle("spell_6", 6, 9),
            particle("spell_7", 7, 9),

            particle("bubble_pop_0", 0 * 2, 16, 0, 3, 2, 2),
            particle("bubble_pop_1", 1 * 2, 16, 0, 3, 2, 2),
            particle("bubble_pop_2", 2 * 2, 16, 0, 3, 2, 2),
            particle("bubble_pop_3", 3 * 2, 16, 0, 3, 2, 2),
            particle("bubble_pop_4", 4 * 2, 16, 0, 3, 2, 2),

            particle("flash", 4, 2, 4, 4),
            particle("nautilus", 0, 13),
            particle("note", 0, 4),
            particle("angry", 1, 5),
            particle("bubble", 0, 2),
            particle("damage", 3, 4),
            particle("flame", 0, 3),
            particle("lava", 1, 3),
            particle("heart", 0, 5),
            particle("glint", 2, 5),
            particle("enchanted_hit", 2, 4),
            particle("critical_hit", 1, 4),
            particle("drip_hang", 0, 7),
            particle("drip_fall", 1, 7),
            particle("drip_land", 2, 7),

            new OutputFile("assets/minecraft/textures/entity/fishing_hook.png", b256(8 * 1, 8 * 2, 8, 8))
        ),
        input("assets/minecraft/textures/entity/explosion.png",
            explosion("explosion_0", 0, 0),
            explosion("explosion_1", 1, 0),
            explosion("explosion_2", 2, 0),
            explosion("explosion_3", 3, 0),

            explosion("explosion_4", 0, 1),
            explosion("explosion_5", 1, 1),
            explosion("explosion_6", 2, 1),
            explosion("explosion_7", 3, 1),

            explosion("explosion_8", 0, 2),
            explosion("explosion_9", 1, 2),
            explosion("explosion_10", 2, 2),
            explosion("explosion_11", 3, 2),

            explosion("explosion_12", 0, 3),
            explosion("explosion_13", 1, 3),
            explosion("explosion_14", 2, 3),
            explosion("explosion_15", 3, 3)
        ),
        input("assets/minecraft/textures/entity/sweep.png",
            sweep(0, 0, 0),
            sweep(1, 1, 0),
            sweep(2, 2, 0),
            sweep(3, 3, 0),
            sweep(4, 0, 1),
            sweep(5, 1, 1),
            sweep(6, 2, 1),
            sweep(7, 3, 1)
        )
    );

    public static void main(final String[] argv) throws IOException {
        Console console = System.console();
        if (console != null) {
            Slicer.parse(argv).process(INPUTS);
        } else {
            new AppGui("1.14", INPUTS);
        }
    }
}
