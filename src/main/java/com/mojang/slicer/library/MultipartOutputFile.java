// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer.library;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.function.UnaryOperator;

public class MultipartOutputFile extends OutputFile {
    private final int width;
    private final int height;
    private final List<TransformedBox> boxes;

    public MultipartOutputFile(final String path, final int width, final int height, final TransformedBox... boxes) {
        super(path);
        this.width = width;
        this.height = height;
        this.boxes = List.of(boxes);
    }

    @Override
    public void process(final Path root, final Path imagePath, final BufferedImage inputImage, final Graphics leftover) throws IOException {
        final TransformedBox firstOutput = boxes.get(0);
        final int outputWidth = this.width * inputImage.getWidth() / firstOutput.box().totalW();
        final int outputHeight = this.height * inputImage.getHeight() / firstOutput.box().totalH();
        BufferedImage outputImage = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_ARGB);
        for (TransformedBox box : boxes) {
            box.process(inputImage, outputImage, leftover);
        }

        final Path outputPath = root.resolve(path);
        Files.createDirectories(outputPath.getParent());

        for (final UnaryOperator<BufferedImage> op : transformers) {
            outputImage = op.apply(outputImage);
        }
        Slicer.writeImage(outputPath, outputImage);

        final Path inputMetaPath = getMetaPath(imagePath);
        if (Files.exists(inputMetaPath)) {
            Files.copy(inputMetaPath, getMetaPath(outputPath), StandardCopyOption.REPLACE_EXISTING);
        } else if (metadata != null) {
            Files.writeString(getMetaPath(outputPath), metadata);
        }
    }

    private static Path getMetaPath(final Path path) {
        return path.resolveSibling(path.getFileName().toString() + ".mcmeta");
    }
}
