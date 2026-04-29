// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer;

import org.jspecify.annotations.Nullable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public abstract class OutputFile {
    protected static final Color REMOVED_MARKER = new Color(128, 0, 0, 128);
    protected final String path;
    protected final List<UnaryOperator<BufferedImage>> transformers = new ArrayList<>();
    protected @Nullable String metadata;

    public OutputFile(final String path) {
        this.path = path;
    }

    public abstract void process(Path root, Path imagePath, BufferedImage image, Graphics leftover) throws IOException;

    public OutputFile apply(final UnaryOperator<BufferedImage> transform) {
        transformers.add(transform);
        return this;
    }

    public OutputFile metadata(final String metadata) {
        this.metadata = metadata;
        return this;
    }
}
