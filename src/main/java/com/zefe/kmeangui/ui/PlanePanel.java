package com.zefe.kmeangui.ui;

import com.zefe.kmeangui.infrastructure.DataStore;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class PlanePanel extends JPanel {

    private static final int CENTROID_RADIUS = 6;
    private static final int POINT_BG = 0xFF101010;
    private static final int GRID_COLOR = 0xFF1E1E1E;
    private static final int AXIS_COLOR = 0xFF505050;
    private static final int POINT_NO_ASSIGN = 0xFFE0E0E0;

    private final DataStore dataStore;
    private int[] palette = new int[0];
    private int paletteSize = -1;

    private BufferedImage baseImage;
    private BufferedImage mainImage;
    private int cachedWidth = -1;
    private int cachedHeight = -1;

    private boolean dirty = true;

    public PlanePanel(DataStore dataStore) {
        this.dataStore = dataStore;
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void markDirty() {
        this.dirty = true;
    }

    public void rebuildPalette(int k) {
        if (k <= 0) {
            this.palette = new int[0];
            this.paletteSize = -1;
            return;
        }
        if (k == this.paletteSize) {
            return;
        }
        int[] p = new int[k];
        for (int i = 0; i < k; i++) {
            float h = (float) i / (float) k;
            float s = 0.85f;
            float v = 0.95f;
            p[i] = hsvToRgb(h, s, v);
        }
        this.palette = p;
        this.paletteSize = k;
    }

    private static int hsvToRgb(float h, float s, float v) {
        float c = v * s;
        float x = c * (1 - Math.abs(((h * 6) % 2) - 1));
        float m = v - c;
        float r, g, b;
        int sector = (int) (h * 6) % 6;
        switch (sector) {
            case 0: r = c; g = x; b = 0; break;
            case 1: r = x; g = c; b = 0; break;
            case 2: r = 0; g = c; b = x; break;
            case 3: r = 0; g = x; b = c; break;
            case 4: r = x; g = 0; b = c; break;
            default: r = c; g = 0; b = x; break;
        }
        int ri = Math.round((r + m) * 255);
        int gi = Math.round((g + m) * 255);
        int bi = Math.round((b + m) * 255);
        return 0xFF000000 | (ri << 16) | (gi << 8) | bi;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = this.getWidth();
        int h = this.getHeight();
        if (w <= 0 || h <= 0) {
            return;
        }

        if (this.mainImage == null || w != this.cachedWidth || h != this.cachedHeight) {
            this.baseImage = this.buildBaseImage(w, h);
            this.mainImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            this.cachedWidth = w;
            this.cachedHeight = h;
            this.dirty = true;
        }

        if (this.dirty) {
            this.renderIntoMain(w, h);
            this.dirty = false;
        }

        g.drawImage(this.mainImage, 0, 0, null);
    }

    private void renderIntoMain(int w, int h) {
        int[] raster = ((DataBufferInt) this.mainImage.getRaster().getDataBuffer()).getData();
        int[] baseRaster = ((DataBufferInt) this.baseImage.getRaster().getDataBuffer()).getData();
        System.arraycopy(baseRaster, 0, raster, 0, w * h);

        int[] px = this.dataStore.getPointsX();
        int[] py = this.dataStore.getPointsY();
        int pointCount = this.dataStore.getPointCount();

        int[] assignment = this.dataStore.getAssignment();

        if (px != null && pointCount > 0) {
            if (assignment != null && this.palette.length > 0) {
                for (int i = 0; i < pointCount; i++) {
                    int x = px[i];
                    int y = py[i];
                    if (x < 0 || x >= w || y < 0 || y >= h) {
                        continue;
                    }
                    raster[y * w + x] = this.palette[assignment[i] % this.palette.length];
                }
            } else {
                for (int i = 0; i < pointCount; i++) {
                    int x = px[i];
                    int y = py[i];
                    if (x < 0 || x >= w || y < 0 || y >= h) {
                        continue;
                    }
                    raster[y * w + x] = POINT_NO_ASSIGN;
                }
            }
        }

        Graphics2D g2d = this.mainImage.createGraphics();
        int[] cxs = this.dataStore.getResultCentroidXs();
        int[] cys = this.dataStore.getResultCentroidYs();
        int cCount = this.dataStore.getResultCentroidCount();
        if (cxs == null || cCount == 0) {
            cxs = this.dataStore.getCentroidsX();
            cys = this.dataStore.getCentroidsY();
            cCount = this.dataStore.getCentroidCount();
        }
        if (cxs != null && cCount > 0) {
            for (int c = 0; c < cCount; c++) {
                int cx = cxs[c];
                int cy = cys[c];
                g2d.setColor(Color.WHITE);
                g2d.fillOval(cx - CENTROID_RADIUS - 1, cy - CENTROID_RADIUS - 1,
                        (CENTROID_RADIUS + 1) * 2, (CENTROID_RADIUS + 1) * 2);
                g2d.setColor(Color.BLACK);
                g2d.fillOval(cx - CENTROID_RADIUS, cy - CENTROID_RADIUS,
                        CENTROID_RADIUS * 2, CENTROID_RADIUS * 2);
            }
        }
        g2d.dispose();
    }

    private BufferedImage buildBaseImage(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int[] raster = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        Arrays.fill(raster, POINT_BG);
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(GRID_COLOR));
        for (int x = 0; x < w; x += 20) {
            g.drawLine(x, 0, x, h);
        }
        for (int y = 0; y < h; y += 20) {
            g.drawLine(0, y, w, y);
        }
        g.setColor(new Color(AXIS_COLOR));
        g.drawLine(0, h / 2, w, h / 2);
        g.drawLine(w / 2, 0, w / 2, h);
        g.dispose();
        return img;
    }
}
