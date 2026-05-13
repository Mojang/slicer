// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.mojang.slicer.library;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.mojang.slicer.library.OutputFile.REMOVED_MARKER;

public record TransformedBox(
        Box box,
        List<UnaryOperator<BufferedImage>> transformers,
        int offsetX,
        int offsetY
) {
    public void process(final BufferedImage inputImage, final BufferedImage outputImage, final Graphics leftover) {
        final int width = inputImage.getWidth();
        final int height = inputImage.getHeight();

        final int x = box.scaleX(width);
        final int y = box.scaleY(height);
        final int w = box.scaleW(width);
        final int h = box.scaleH(height);

        BufferedImage subImage = inputImage.getSubimage(x, y, w, h);
        for (final UnaryOperator<BufferedImage> op : transformers) {
            subImage = op.apply(subImage);
        }

        leftover.setColor(REMOVED_MARKER);
        leftover.fillRect(x, y, w, h);

        final Graphics2D graphics = outputImage.createGraphics();
        final int offsetXScaled = offsetX * inputImage.getWidth() / box.totalW();
        final int offsetYScaled = offsetY * inputImage.getHeight() / box.totalH();
        graphics.drawImage(subImage, offsetXScaled, offsetYScaled, null);
        graphics.dispose();
    }

    public static BufferedImage mirrorVertically(final BufferedImage image) {
        final BufferedImage mirroredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = mirroredImage.createGraphics();
        graphics.drawImage(image, 0, image.getHeight(), image.getWidth(), -image.getHeight(), null);
        graphics.dispose();
        return mirroredImage;
    }

    public static BufferedImage mirrorHorizontally(final BufferedImage image) {
        final BufferedImage mirroredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = mirroredImage.createGraphics();
        graphics.drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
        graphics.dispose();
        return mirroredImage;
    }

    public static BufferedImage rotate90(final BufferedImage image) {
        final BufferedImage rotatedImage = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = rotatedImage.createGraphics();
        final AffineTransform transform = new AffineTransform();
        transform.quadrantRotate(1, 0, 0);
        transform.translate(0, -image.getHeight());
        graphics.setTransform(transform);
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return rotatedImage;
    }

    public static BufferedImage rotate180(final BufferedImage image) {
        final BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = rotatedImage.createGraphics();
        final AffineTransform transform = new AffineTransform();
        transform.quadrantRotate(2, image.getWidth() / 2.0, image.getHeight() / 2.0);
        graphics.setTransform(transform);
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return rotatedImage;
    }

    public static BufferedImage rotate270(final BufferedImage image) {
        final BufferedImage rotatedImage = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = rotatedImage.createGraphics();
        final AffineTransform transform = new AffineTransform();
        transform.quadrantRotate(3, 0, 0);
        transform.translate(-image.getWidth(), 0);
        graphics.setTransform(transform);
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return rotatedImage;
    }
}
