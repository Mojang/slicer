// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer.library;
import org.jspecify.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InputFile {
    private final String path;
    private final List<OutputFile> outputs = new ArrayList<>();

    public InputFile(final String path) {
        this.path = path;
    }

    public InputFile outputs(final OutputFile... files) {
        Collections.addAll(outputs, files);
        return this;
    }

    public void process(final Path inputRoot, final Path outputRoot, final Map<String, BufferedImage> leftovers) throws IOException {
        final Path inputPath = inputRoot.resolve(this.path);
        if (Files.exists(inputPath)) {
            try (final InputStream is = Files.newInputStream(inputPath)) {
                final BufferedImage image = ImageIO.read(is);
                BufferedImage leftoverImage = leftovers.get(this.path);
                final Graphics2D leftoverGraphics;
                if (leftoverImage == null) {
                    leftoverImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    leftovers.put(this.path, leftoverImage);
                    leftoverGraphics = leftoverImage.createGraphics();
                    leftoverGraphics.drawImage(image, 0, 0, null);
                } else {
                    leftoverGraphics = leftoverImage.createGraphics();
                }

                for (final OutputFile outputFile : outputs) {
                    outputFile.process(outputRoot, inputPath, image, leftoverGraphics);
                }

                leftoverGraphics.dispose();
            }
        } else {
            System.err.println("Input file " + inputPath.toAbsolutePath() + " not found, skipping!");
        }
    }
}
