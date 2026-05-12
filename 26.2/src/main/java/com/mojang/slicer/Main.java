// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer;

import com.mojang.slicer.library.Box;
import com.mojang.slicer.library.InputFile;
import com.mojang.slicer.library.MultipartOutputFile;
import com.mojang.slicer.library.OutputFile;
import com.mojang.slicer.library.SimpleOutputFile;
import com.mojang.slicer.library.Slicer;
import com.mojang.slicer.library.TransformedBox;

import java.io.IOException;
import java.util.List;

public class Main {
    private static InputFile input(final String path, final OutputFile... outputs) {
        return new InputFile(path).outputs(outputs);
    }

    private static final List<InputFile> INPUTS = List.of(
            makeStaticBedPartProcessor(),
            makeBedProcessor("black"),
            makeBedProcessor("blue"),
            makeBedProcessor("brown"),
            makeBedProcessor("cyan"),
            makeBedProcessor("gray"),
            makeBedProcessor("green"),
            makeBedProcessor("light_blue"),
            makeBedProcessor("light_gray"),
            makeBedProcessor("lime"),
            makeBedProcessor("magenta"),
            makeBedProcessor("orange"),
            makeBedProcessor("pink"),
            makeBedProcessor("purple"),
            makeBedProcessor("red"),
            makeBedProcessor("white"),
            makeBedProcessor("yellow"),
            makeStandingSignProcessor("acacia"),
            makeStandingSignProcessor("birch"),
            makeStandingSignProcessor("crimson"),
            makeStandingSignProcessor("mangrove"),
            makeStandingSignProcessor("pale_oak"),
            makeStandingSignProcessor("warped"),
            makeStandingSignProcessor("bamboo"),
            makeStandingSignProcessor("cherry"),
            makeStandingSignProcessor("dark_oak"),
            makeStandingSignProcessor("jungle"),
            makeStandingSignProcessor("oak"),
            makeStandingSignProcessor("spruce"),
            makeHangingSignProcessor("acacia"),
            makeHangingSignProcessor("birch"),
            makeHangingSignProcessor("crimson"),
            makeHangingSignProcessor("mangrove"),
            makeHangingSignProcessor("pale_oak"),
            makeHangingSignProcessor("warped"),
            makeHangingSignProcessor("bamboo"),
            makeHangingSignProcessor("cherry"),
            makeHangingSignProcessor("dark_oak"),
            makeHangingSignProcessor("jungle"),
            makeHangingSignProcessor("oak"),
            makeHangingSignProcessor("spruce")
    );


    private static InputFile makeStaticBedPartProcessor() {
        return input("minecraft/textures/entity/bed/black.png",
                new SimpleOutputFile("minecraft/textures/block/bed_down.png", new Box(28, 6, 16, 16, 64, 64)).apply(TransformedBox::rotate180),
                new MultipartOutputFile("minecraft/textures/block/bed_head_north.png", 16, 16,
                        new TransformedBox(
                                new Box(6, 0, 16, 6, 64, 64),
                                List.of(TransformedBox::rotate180),
                                0,
                                7
                        ),
                        new TransformedBox(
                                new Box(53, 21, 3, 3, 64, 64),
                                List.of(),
                                0,
                                13
                        ),
                        new TransformedBox(
                                new Box(56, 21, 3, 3, 64, 64),
                                List.of(),
                                3,
                                13
                        ),
                        new TransformedBox(
                                new Box(59, 9, 3, 3, 64, 64),
                                List.of(),
                                10,
                                13
                        ),
                        new TransformedBox(
                                new Box(50, 9, 3, 3, 64, 64),
                                List.of(),
                                13,
                                13
                        )
                )
        );
    }

    private static InputFile makeBedProcessor(final String color) {
        return input("minecraft/textures/entity/bed/" + color + ".png",
                new SimpleOutputFile("minecraft/textures/block/" + color + "_bed_head_up.png", new Box(6, 6, 16, 16, 64, 64)),
                new SimpleOutputFile("minecraft/textures/block/" + color + "_bed_foot_up.png", new Box(6, 28, 16, 16, 64, 64)),
                new MultipartOutputFile("minecraft/textures/block/" + color + "_bed_foot_south.png", 16, 16,
                        new TransformedBox(
                                new Box(22, 22, 16, 6, 64, 64),
                                List.of(TransformedBox::mirrorVertically),
                                0,
                                7
                        ),
                        new TransformedBox(
                                new Box(53, 3, 3, 3, 64, 64),
                                List.of(),
                                0,
                                13
                        ),
                        new TransformedBox(
                                new Box(56, 3, 3, 3, 64, 64),
                                List.of(),
                                3,
                                13
                        ),
                        new TransformedBox(
                                new Box(59, 15, 3, 3, 64, 64),
                                List.of(),
                                10,
                                13
                        ),
                        new TransformedBox(
                                new Box(50, 15, 3, 3, 64, 64),
                                List.of(),
                                13,
                                13
                        )
                ),
                new MultipartOutputFile("minecraft/textures/block/" + color + "_bed_foot_west.png", 16, 16,
                        new TransformedBox(
                                new Box(0, 28, 6, 16, 64, 64),
                                List.of(TransformedBox::rotate270),
                                0,
                                7
                        ),
                        new TransformedBox(
                                new Box(56, 0, 3, 3, 64, 64),
                                List.of(TransformedBox::mirrorVertically),
                                7,
                                13
                        ),
                        new TransformedBox(
                                new Box(59, 3, 3, 3, 64, 64),
                                List.of(),
                                10,
                                13
                        ),
                        new TransformedBox(
                                new Box(50, 3, 3, 3, 64, 64),
                                List.of(),
                                13,
                                13
                        )
                ),
                new MultipartOutputFile("minecraft/textures/block/" + color + "_bed_foot_east.png", 16, 16,
                        new TransformedBox(
                                new Box(22, 28, 6, 16, 64, 64),
                                List.of(TransformedBox::rotate90),
                                0,
                                7
                        ),
                        new TransformedBox(
                                new Box(53, 15, 3, 3, 64, 64),
                                List.of(),
                                0,
                                13
                        ),
                        new TransformedBox(
                                new Box(56, 15, 3, 3, 64, 64),
                                List.of(),
                                3,
                                13
                        ),
                        new TransformedBox(
                                new Box(56, 12, 3, 3, 64, 64),
                                List.of(TransformedBox::rotate270, TransformedBox::mirrorVertically),
                                6,
                                13
                        )
                ),
                new MultipartOutputFile("minecraft/textures/block/" + color + "_bed_head_east.png", 16, 16,
                        new TransformedBox(
                                new Box(22, 6, 6, 16, 64, 64),
                                List.of(TransformedBox::rotate90),
                                0,
                                7
                        ),
                        new TransformedBox(
                                new Box(56, 18, 3, 3, 64, 64),
                                List.of(TransformedBox::rotate180, TransformedBox::mirrorVertically),
                                7,
                                13
                        ),
                        new TransformedBox(
                                new Box(59, 21, 3, 3, 64, 64),
                                List.of(),
                                10,
                                13
                        ),
                        new TransformedBox(
                                new Box(50, 21, 3, 3, 64, 64),
                                List.of(),
                                13,
                                13
                        )
                ),
                new MultipartOutputFile("minecraft/textures/block/" + color + "_bed_head_west.png", 16, 16,
                        new TransformedBox(
                                new Box(0, 6, 6, 16, 64, 64),
                                List.of(TransformedBox::rotate270),
                                0,
                                7
                        ),
                        new TransformedBox(
                                new Box(53, 9, 3, 3, 64, 64),
                                List.of(),
                                0,
                                13
                        ),
                        new TransformedBox(
                                new Box(56, 9, 3, 3, 64, 64),
                                List.of(),
                                3,
                                13
                        ),
                        new TransformedBox(
                                new Box(56, 6, 3, 3, 64, 64),
                                List.of(TransformedBox::rotate90, TransformedBox::mirrorVertically),
                                6,
                                13
                        )
                )
        );
    }

    private static InputFile makeStandingSignProcessor(final String woodType) {
        return input("minecraft/textures/entity/signs/" + woodType + ".png",
                new MultipartOutputFile("minecraft/textures/gui/signs/" + woodType + ".png", 24, 26,
                        new TransformedBox(
                                new Box(2, 2, 24, 12, 64, 32),
                                List.of(),
                                0,
                                0
                        ),
                        new TransformedBox(
                                new Box(2, 16, 2, 14, 64, 32),
                                List.of(),
                                11,
                                12
                        )
                ),
                new MultipartOutputFile("minecraft/textures/block/" + woodType + "_sign.png", 32, 32,
                        new TransformedBox(
                                new Box(2, 0, 24, 14, 64, 32),
                                List.of(),
                                0,
                                0
                        ),
                        new TransformedBox(
                                new Box(26, 2, 2, 12, 64, 32),
                                List.of(),
                                24,
                                2
                        ),
                        new TransformedBox(
                                new Box(28, 2, 24, 12, 64, 32),
                                List.of(),
                                0,
                                16
                        ),
                        new TransformedBox(
                                new Box(0, 2, 2, 12, 64, 32),
                                List.of(),
                                24,
                                16
                        ),
                        new TransformedBox(
                                new Box(26, 0, 24, 2, 64, 32),
                                List.of(TransformedBox::mirrorVertically),
                                0,
                                28
                        ),
                        new TransformedBox(
                                new Box(2, 16, 4, 14, 64, 32),
                                List.of(),
                                28,
                                0
                        ),
                        new TransformedBox(
                                new Box(6, 16, 2, 14, 64, 32),
                                List.of(),
                                28,
                                16
                        ),
                        new TransformedBox(
                                new Box(0, 16, 2, 14, 64, 32),
                                List.of(),
                                30,
                                16
                        ),
                        new TransformedBox(
                                new Box(4, 14, 2, 2, 64, 32),
                                List.of(TransformedBox::mirrorVertically),
                                28,
                                30
                        )
                )
        );
    }

    private static InputFile makeHangingSignProcessor(final String woodType) {
        return input("minecraft/textures/entity/signs/hanging/" + woodType + ".png",
                new MultipartOutputFile("minecraft/textures/block/" + woodType + "_hanging_sign.png", 32, 32,
                        new TransformedBox(
                                // west - front - east - back
                                new Box(0, 14, 32, 10, 64, 32),
                                List.of(),
                                0,
                                16
                        ),
                        new TransformedBox(
                                // up
                                new Box(2, 12, 14, 2, 64, 32),
                                List.of(),
                                2,
                                14
                        ),
                        new TransformedBox(
                                // down
                                new Box(16, 12, 14, 2, 64, 32),
                                List.of(TransformedBox::mirrorVertically),
                                2,
                                26
                        ),
                        new TransformedBox(
                                // bar: up - front
                                new Box(4, 0, 16, 6, 64, 32),
                                List.of(),
                                0,
                                0
                        ),
                        new TransformedBox(
                                // bar: down
                                new Box(20, 0, 16, 4, 64, 32),
                                List.of(TransformedBox::rotate180),
                                0,
                                9
                        ),
                        new TransformedBox(
                                // bar: east
                                new Box(20, 4, 4, 2, 64, 32),
                                List.of(),
                                16,
                                4
                        ),
                        new TransformedBox(
                                // bar: back
                                new Box(24, 4, 16, 2, 64, 32),
                                List.of(),
                                0,
                                7
                        ),
                        new TransformedBox(
                                // bar: west
                                new Box(0, 4, 4, 2, 64, 32),
                                List.of(),
                                16,
                                7
                        ),
                        new TransformedBox(
                                // chain angled
                                new Box(14, 6, 12, 6, 64, 32),
                                List.of(),
                                20,
                                0
                        ),
                        new TransformedBox(
                                // chain straight
                                new Box(0, 6, 9, 6, 64, 32),
                                List.of(),
                                22,
                                7
                        )
                )
        );
    }

    public static void main(final String[] argv) throws IOException {
        Slicer.parse(argv).process(INPUTS);
    }
}
