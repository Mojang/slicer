// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@ParametersAreNonnullByDefault
public class OutputFile {
    private static final Color REMOVED_MARKER = new Color(128, 0, 0, 128);

    private final String path;
    private final Box box;
    private final List<UnaryOperator<BufferedImage>> transformers = new ArrayList<>();

    public OutputFile(final String path, final Box box) {
        this.path = path;
        this.box = box;
    }

    public void process(final Path root, final BufferedImage image, final Graphics leftover) throws IOException {
        final int width = image.getWidth();
        final int height = image.getHeight();

        final Path outputPath = root.resolve(path);
        final int x = box.scaleX(width);
        final int y = box.scaleY(height);
        final int w = box.scaleW(width);
        final int h = box.scaleH(height);
        BufferedImage subImage = image.getSubimage(x, y, w, h);

        for (final UnaryOperator<BufferedImage> op : transformers) {
            subImage = op.apply(subImage);
        }

        Slicer.writeImage(outputPath, subImage);

        leftover.setColor(REMOVED_MARKER);
        leftover.fillRect(x, y, w, h);
    }

    public OutputFile apply(final UnaryOperator<BufferedImage> transform) {
        transformers.add(transform);
        return this;
    }
}
