import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Background02 extends JPanel {
    JavaSwing javaSwing = new JavaSwing();

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawWall(g2);
        drawFloor(g2);
        drawCarpet(g2);
        drawWindow(g2);
        drawStar(g2);
        drawPictureFrame(g2);
    }

    public void drawWall(Graphics2D g2) {
        g2.setColor(new Color(0xeeb2b2));
        javaSwing.plot(g2, 0, 0, 600, 600);

        g2.setColor(new Color(0x695322));
        javaSwing.bresenhamLine(g2, 0, 345, 600, 345, 10, 10);

        g2.setColor(new Color(0x614200));
        javaSwing.bresenhamLine(g2, 0, 332, 600, 332, 13, 13);

        g2.setColor(new Color(0xb87d00));
        javaSwing.bresenhamLine(g2, 0, 328, 600, 328, 4, 4);

        // Wall shadow
        g2.setColor(new Color(0xd7a1a1));
        // javaSwing.bezierCurve(g2, -45, 166, 423, -18, 600, 0, 640, 139, 5, 5);
        javaSwing.bezierFillUp(g2, -45, 166, 423, -18, 600, 0, 640, 139, 5, 0);

    }

    public void drawFloor(Graphics2D g2) {
        g2.setColor(new Color(0xf7e1b4));
        javaSwing.bresenhamLine(g2, 0, 350, 600, 350, 1, 250);

        // floor grid
        // horizontal
        g2.setColor(new Color(0x695322));
        javaSwing.bresenhamLine(g2, 0, 555, 600, 555, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 505, 600, 505, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 455, 600, 455, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 410, 600, 410, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 375, 600, 375, 1, 1);

        // vertical
        javaSwing.bresenhamLine(g2, 300, 350, 300, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 220, 350, 200, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 380, 350, 400, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 140, 350, 100, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 460, 350, 500, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 60, 350, 0, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 540, 350, 600, 600, 2, 1);

    }

    public void drawCarpet(Graphics2D g2) {
        g2.setColor(new Color(0xd6845f));
        javaSwing.midpointEllipseFill(g2, 300, 470, 250, 60);

        g2.setColor(new Color(0xee9b78));
        javaSwing.midpointEllipseFill(g2, 300, 465, 250, 60); // จุดที่ ผญ นั่ง

        g2.setColor(new Color(0xffd9c1));
        javaSwing.midpointEllipseFill(g2, 300, 465, 206, 45);

    }

    public void drawWindow(Graphics2D g2) {
        int left = 420, right = 540; // ขอบซ้าย-ขวาของกระจก
        int bodyTop = 100; // ขอบบน
        int bodyBottom = 300; // ขอบล่าง
        int topArch = 20; // โค้ง
        int thick = 10; // ความหนากรอบไม้

        // หน้าต่าง
        g2.setColor(new Color(0x7a4a1e));
        javaSwing.bezierFillDown(g2, left - thick, bodyTop, left - thick, topArch, right + thick, topArch,
                right + thick, bodyTop, 1, bodyTop);
        javaSwing.bresenhamLine(g2, left - thick, bodyTop, right + thick, bodyTop, 1, bodyBottom - bodyTop);

        // วิวกลางคืน
        g2.setColor(new Color(0x0a1a4d));
        javaSwing.bezierFillDown(g2, left, bodyTop, left, topArch + thick, right, topArch + thick, right, bodyTop, 1,
                bodyTop);
        javaSwing.bresenhamLine(g2, left, bodyTop - thick, right, bodyTop - thick, 1, bodyBottom - bodyTop);

        // เส้นหน้าต่าง
        g2.setColor(new Color(0x7a4a1e));
        javaSwing.bresenhamLine(g2, left, bodyTop, right, bodyTop, 10, 10);
        javaSwing.bresenhamLine(g2, left, bodyTop + 65, right, bodyTop + 65, 10, 10);
        javaSwing.bresenhamLine(g2, left, bodyTop + 130, right, bodyTop + 130, 10, 10);

        // ดวงจันทร์
        g2.setColor(new Color(0xd9d97a));
        javaSwing.midpointEllipseFill(g2, right - 30, bodyTop + 40, 20, 20);

    }

    public void drawStar(Graphics2D g2) {
        int[][] starPos = { { 441, 87 }, { 479, 67 }, { 459, 89 }, { 520, 70 }, { 463, 154 },
                { 480, 154 }, { 440, 140 }, { 453, 191 }, { 491, 199 }, { 440, 220 },
                { 527, 213 }, { 469, 256 }, { 498, 271 }, { 530, 258 }, { 446, 274 } };

        g2.setColor(new Color(0xffffff));
        for (int[] pos : starPos) {
            int x = pos[0];
            int y = pos[1];
            javaSwing.midpointEllipseFill(g2, x, y, 3, 3);
        }

    }

    public void drawStar(Graphics2D g2, double time) {
        int[][] starPos = { { 441, 87 }, { 479, 67 }, { 459, 89 }, { 520, 70 }, { 463, 154 },
                { 480, 154 }, { 440, 140 }, { 453, 191 }, { 491, 199 }, { 440, 220 },
                { 527, 213 }, { 469, 256 }, { 498, 271 }, { 530, 258 }, { 446, 274 } };

        for (int i = 0; i < starPos.length; i++) {
            int x = starPos[i][0];
            int y = starPos[i][1];

            double brightness = (Math.sin(time * 3 + i * 1.7) + 1) / 2; // 0.0 - 1.0
            int alpha = (int) (100 + brightness * 155);
            int r = (brightness > 0.7) ? 3 : 2; // ดาวที่สว่างมากขยับรัศมีนิดหน่อย

            g2.setColor(new Color(255, 255, 220, alpha));
            javaSwing.midpointEllipseFill(g2, x, y, r, r);
        }
    }

    // ใส่รูปแมว
    public void drawPictureFrame(Graphics2D g2) {
        g2.setColor(new Color(0x8ca36e));
        g2.fillRect(55, 100, 120, 140);
        g2.setColor(new Color(0x79653a));
        g2.fillRect(205, 45, 110, 110); 
        g2.setColor(new Color(0xab8fbe));
        g2.fillRect(205, 165, 110, 110);

        g2.setColor(Color.WHITE);
        g2.fillRect(60, 105, 110, 130);
        g2.fillRect(210, 50, 100, 100);
        g2.fillRect(210, 170, 100, 100);
        
        // รูปแมว
        // drawPhotoInFrame(g2, "CatPic01.png", 217, 54, 96, 122);
        
        drawPhotoInFrame(g2, "catPic01.png", 219, 59, 82, 82);
        drawPhotoInFrame(g2, "catPic03.png", 219, 179, 82, 82);
        drawPhotoInFrame(g2, "catPic04.png", 70, 115, 90, 110);

    }

    public void drawPhotoInFrame(Graphics2D g2, String imagePath,
            int frameX, int frameY, int frameW, int frameH) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
            // BufferedImage img = ImageIO.read(new File(imagePath));

            // plot ทีละ pixel
            for (int y = 0; y < frameH; y++) {
                for (int x = 0; x < frameW; x++) {
                    int rgb = img.getRGB(x, y);
                    g2.setColor(new Color(rgb));
                    javaSwing.plot(g2, frameX + x, frameY + y, 1, 1);
                }
            }

        } catch (IOException e) {
            System.err.println("Can't load picture: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Background02 m = new Background02();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Background");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
