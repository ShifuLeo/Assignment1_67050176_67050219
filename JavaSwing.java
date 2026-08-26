import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

public class JavaSwing extends JPanel {

    public void plot(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    public void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2, int width, int height) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        boolean isSwap = false;

        if (dy > dx) {
            int temp = dx;
            dx = dy;
            dy = temp;
            isSwap = true;
        }

        int D = 2 * dy - dx;
        int x = x1;
        int y = y1;

        for (int i = 1; i <= dx; i++) {
            plot(g, x, y, width, height);

            if (D >= 0) {
                if (isSwap)
                    x += sx;
                else
                    y += sy;

                D -= 2 * dx;
            }

            if (isSwap)
                y += sy;
            else
                x += sx;

            D += 2 * dy;
        }

    }

    public void bezierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3,
                                    int x4, int y4, int width, int height) {
        int numPoint = 1000;
        double step = 1.0 / numPoint;

        for (double t = 0; t <= 1; t += step) {
            double x = Math.pow(1 - t, 3) * x1
                    + 3 * t * Math.pow(1 - t, 2) * x2
                    + 3 * Math.pow(t, 2) * (1 - t) * x3
                    + Math.pow(t, 3) * x4;

            double y = Math.pow(1 - t, 3) * y1
                    + 3 * t * Math.pow(1 - t, 2) * y2
                    + 3 * Math.pow(t, 2) * (1 - t) * y3
                    + Math.pow(t, 3) * y4;

            plot(g, (int) x, (int) y, width, height);
        }
    }

    public void bezierFillDown(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3,
                                            int x4, int y4, int width, int baselineY) {
        int numPoint = 1000;
        double step = 1.0 / numPoint;

        for (double t = 0; t <= 1; t += step) {
            double x = Math.pow(1 - t, 3) * x1
                    + 3 * t * Math.pow(1 - t, 2) * x2
                    + 3 * Math.pow(t, 2) * (1 - t) * x3
                    + Math.pow(t, 3) * x4;

            double y = Math.pow(1 - t, 3) * y1
                    + 3 * t * Math.pow(1 - t, 2) * y2
                    + 3 * Math.pow(t, 2) * (1 - t) * y3
                    + Math.pow(t, 3) * y4;

            int height = baselineY - (int) y;
            if (height > 0) {
                plot(g, (int) x, (int) y, width, height);
            }
        }
    }

    public void bezierFillUp(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3,
                                        int x4, int y4, int width, int topY) {
        int numPoint = 1000;
        double step = 1.0 / numPoint;

        for (double t = 0; t <= 1; t += step) {
            double x = Math.pow(1 - t, 3) * x1
                    + 3 * t * Math.pow(1 - t, 2) * x2
                    + 3 * Math.pow(t, 2) * (1 - t) * x3
                    + Math.pow(t, 3) * x4;

            double y = Math.pow(1 - t, 3) * y1
                    + 3 * t * Math.pow(1 - t, 2) * y2
                    + 3 * Math.pow(t, 2) * (1 - t) * y3
                    + Math.pow(t, 3) * y4;

            int height = (int) y - topY;
            if (height > 0) {
                plot(g, (int) x, topY, width, height);
            }
        }
    }

    public void midpointCircle(Graphics g, int xc, int yc, int r, int width, int height) {
        int x = 0;
        int y = r;

        int d = 1 - r;
        int dx = 2 * x;
        int dy = 2 * y;

        while (x <= y) {
            plot(g, x + xc, y + yc, width, height);
            plot(g, -x + xc, y + yc, width, height);
            plot(g, x + xc, -y + yc, width, height);
            plot(g, -x + xc, -y + yc, width, height);
            plot(g, y + xc, x + yc, width, height);
            plot(g, -y + xc, x + yc, width, height);
            plot(g, y + xc, -x + yc, width, height);
            plot(g, -y + xc, -x + yc, width, height);

            x++;
            dx += 2;
            d = d + dx + 1;

            if (d >= 0) {
                y--;
                dy -= 2;
                d = d - dy;
            }
        }
    }

    public void midpointEllipse(Graphics g, int xc, int yc, int a, int b, int width, int height) {
        int a2 = a * a;
        int b2 = b * b;
        int twoA2 = 2 * a2;
        int twoB2 = 2 * b2;

        int x = 0;
        int y = b;
        int d = (int) Math.round(b2 - a2 * b + a2 / 4);
        int dx = 0;
        int dy = twoA2 * y;

        while (dx <= dy) {

            plot(g, x + xc, y + yc, width, height);
            plot(g, -x + xc, y + yc, width, height);
            plot(g, x + xc, -y + yc, width, height);
            plot(g, -x + xc, -y + yc, width, height);

            x++;
            dx = dx + twoB2;
            d = d + dx + b2;

            if (d >= 0) {
                y--;
                dy = dy - twoA2;
                d = d - dy;
            }
        }

        x = a;
        y = 0;
        d = (int) Math.round(a2 - b2 * a + b2 / 4);
        dx = twoB2 * x;
        dy = 0;

        while (dx >= dy) {

            plot(g, x + xc, y + yc, width, height);
            plot(g, -x + xc, y + yc, width, height);
            plot(g, x + xc, -y + yc, width, height);
            plot(g, -x + xc, -y + yc, width, height);

            y++;
            dy = dy + twoA2;
            d = d + dy + a2;

            if (d >= 0) {
                x--;
                dx = dx - twoB2;
                d = d - dx;
            }
        }
    }

    public void midpointEllipseFill(Graphics g, int xc, int yc, int a, int b) {
        int a2 = a * a;
        int b2 = b * b;
        int twoA2 = 2 * a2;
        int twoB2 = 2 * b2;

        // Region 1: fill vertical strips (top-bottom) for each x
        int x = 0;
        int y = b;
        int d = (int) Math.round(b2 - a2 * b + a2 / 4);
        int dx = 0;
        int dy = twoA2 * y;

        while (dx <= dy) {
            plot(g, x + xc, -y + yc, 1, 2 * y); // ขวา: บนสุดถึงล่างสุด
            plot(g, -x + xc, -y + yc, 1, 2 * y); // ซ้าย: บนสุดถึงล่างสุด

            x++;
            dx = dx + twoB2;
            d = d + dx + b2;

            if (d >= 0) {
                y--;
                dy = dy - twoA2;
                d = d - dy;
            }
        }

        // Region 2: fill horizontal strips (left-right) for each y
        x = a;
        y = 0;
        d = (int) Math.round(a2 - b2 * a + b2 / 4);
        dx = twoB2 * x;
        dy = 0;

        while (dx >= dy) {
            plot(g, -x + xc, y + yc, 2 * x, 1); // ล่าง: ซ้ายสุดถึงขวาสุด
            plot(g, -x + xc, -y + yc, 2 * x, 1); // บน: ซ้ายสุดถึงขวาสุด

            y++;
            dy = dy + twoA2;
            d = d + dy + a2;

            if (d >= 0) {
                x--;
                dx = dx - twoB2;
                d = d - dx;
            }
        }
    }

    public BufferedImage floodFill(BufferedImage m, int x, int y, Color target_color, Color replacement_color) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        int width = m.getWidth();
        int height = m.getHeight(); 

        if (m.getRGB(x, y) == target_color.getRGB()) {
            g2.setColor(replacement_color);
            plot(g2, x, y, 1, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {

            Point p = q.poll();

            // south
            if (p.y < height - 1 && m.getRGB(p.x, p.y + 1) == target_color.getRGB()) {
                g2.setColor(replacement_color);
                plot(g2, p.x, p.y + 1, 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }

            // north
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) == target_color.getRGB()) {
                g2.setColor(replacement_color);
                plot(g2, p.x, p.y - 1, 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }

            // east
            if (p.x < width - 1 && m.getRGB(p.x + 1, p.y) == target_color.getRGB()) {
                g2.setColor(replacement_color);
                plot(g2, p.x + 1, p.y, 1, 1);
                q.add(new Point(p.x + 1, p.y));
            }

            // west
            if (p.x > 0 && m.getRGB(p.x - 1, p.y) == target_color.getRGB()) {
                g2.setColor(replacement_color);
                plot(g2, p.x - 1, p.y, 1, 1);
                q.add(new Point(p.x - 1, p.y));
            }

        }

        return m;
    }

}