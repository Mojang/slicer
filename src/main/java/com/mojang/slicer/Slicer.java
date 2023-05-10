// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;

@ParametersAreNonnullByDefault
public class Slicer {
    private final Path inputPath;
    private final Path outputPath;
    @Nullable
    private final Path leftoverPath;

    public Slicer(final Path inputPath, final Path outputPath, @Nullable final Path leftoverPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.leftoverPath = leftoverPath;
    }

    public static Slicer parse(final String[] argv) throws IOException {
        final int argc = argv.length;
        if (argc != 2 && argc != 3) {
            throw new IllegalArgumentException("Usage: <input dir or zip> <output dir> [<leftover dir>]");
        }

        final Path inputPath = Paths.get(argv[0]);

        final Path outputPath = Paths.get(argv[1]);
        Files.createDirectories(outputPath.getParent());

        final Path leftoverPath;
        if (argc == 3) {
            leftoverPath = Paths.get(argv[2]);
            Files.createDirectories(leftoverPath.getParent());
        } else {
            leftoverPath = null;
        }

        return new Slicer(inputPath, outputPath, leftoverPath);
    }

    public static void writeImage(final Path path, final BufferedImage image) throws IOException {
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        try (final OutputStream os = Files.newOutputStream(path)) {
            ImageIO.write(image, "png", os);
            System.out.println(path.toAbsolutePath());
        }
    }

    public void process(final Collection<InputFile> inputs) throws IOException {
        if (Files.isDirectory(inputPath)) {
            process(inputs, inputPath, outputPath, leftoverPath);
        } else if (inputPath.getFileName().toString().endsWith(".zip")) {
            final URI fsUri = URI.create("jar:" + inputPath.toUri());
            try (final FileSystem fs = FileSystems.newFileSystem(fsUri, Collections.emptyMap())) {
                process(inputs, fs.getPath("/"), outputPath, leftoverPath);
            }
        } else {
            throw new IllegalStateException("Expected either directory or zip file");
        }
    }

    private static void process(final Collection<InputFile> inputs, final Path inputPath, final Path outputPath, @Nullable final Path leftoverPath) throws IOException {
        for (final InputFile input : inputs) {
            input.process(inputPath, outputPath, leftoverPath);
        }
    }
}
