import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Background01 extends JPanel {
    JavaSwing javaSwing = new JavaSwing();
    private BufferedImage cachedBg = null;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (cachedBg == null) {
            cachedBg = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
            drawSky(cachedBg);
            drawCloud(cachedBg);
            drawhill(cachedBg);
            drawFlowers(cachedBg); 
            drawTapeBase(cachedBg);
            drawTapeTop(cachedBg);
        }
        g2.drawImage(cachedBg, 0, 0, this);
    }

    public void drawSky(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setBackground(new Color(0x89b0df));
        g2.clearRect(0, 0, 600, 600);
    }

    public void drawhill(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(0x708e46));
        javaSwing.bezierFillDown(g2, 0, 0, 174, -13, 250, 95, 370, 170, 2, 300);

        g2.setColor(new Color(0xa1b95f));
        javaSwing.bezierFillDown(g2, 0, 200, 270, 147, 529, 157, 600, 183, 2, 600);

    }

// 413, 215
// 501, 200
// 492, 142
// 402, 159
// y + 200

    public void drawTapeBase(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();

        g2.setColor(Color.WHITE);
        javaSwing.fillQuad(g2, 409, 364, 487, 349, 492, 385, 418, 400, 2, 2);

        g2.setColor(Color.BLACK);
        javaSwing.midpointCircle(g2, 429, 377, 22, 2, 2);
        Color target = new Color(img.getRGB(429, 377), true);
        javaSwing.floodFill(img, 429, 377, target, new Color(0xC2DDF7));
        
        javaSwing.midpointCircle(g2, 472, 368, 22, 2, 2);
        javaSwing.floodFill(img, 472, 368, target, new Color(0xC2DDF7));
    }

    public void drawTapeTop(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        
        g2.setColor(Color.PINK);
        javaSwing.fillQuad(g2, 402, 359, 492, 342, 487, 349, 409, 364, 2, 2);
        javaSwing.fillQuad(g2, 418, 400, 492, 385, 501, 400, 413, 415, 2, 2);
        javaSwing.fillQuad(g2, 402, 359, 409, 364, 418, 400, 413, 415, 2, 2);
        javaSwing.fillQuad(g2, 487, 349, 492, 342, 501, 400, 492, 385, 2, 2);

        g2.setColor(Color.BLACK);
        javaSwing.bresenhamLine(g2, 402, 359, 492, 342, 2, 2);
        javaSwing.bresenhamLine(g2, 492, 342, 501, 400, 2, 2);
        javaSwing.bresenhamLine(g2, 501, 400, 413, 415, 2, 2);
        javaSwing.bresenhamLine(g2, 413, 415, 402, 359, 2, 2);
        
        javaSwing.bresenhamLine(g2, 409, 364, 487, 349, 2, 2);
        javaSwing.bresenhamLine(g2, 487, 349, 492, 385, 2, 2);
        javaSwing.bresenhamLine(g2, 492, 385, 418, 400, 2, 2);
        javaSwing.bresenhamLine(g2, 418, 400, 409, 364, 2, 2);
    }

    public void drawCloud(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();

        g2.setColor(Color.WHITE);
        javaSwing.bezierCurve(g2, 580, 47, 618, 68, 568, 83, 600, 98, 1, 1);
        javaSwing.bezierCurve(g2, 580, 47, 546, -30, 490, 24, 465, 54, 1, 1);
        javaSwing.bezierCurve(g2, 380, 108, 414, 54, 420, 74, 465, 54, 1, 1);
        javaSwing.bezierCurve(g2, 380, 108, 385, 107, 326, 102, 298, 138, 1, 1);
        javaSwing.bezierCurve(g2, 623, 223, 611, 194, 721, 121, 600, 98, 1, 1);

        javaSwing.bezierCurve(g2, 380, 108, 416, 54, 350, 50, 348, 37, 1, 1);
        javaSwing.bezierCurve(g2, 259, -24, 284, -11, 330, -8, 348, 37, 1, 1);
        javaSwing.bezierCurve(g2, 259, -24, 240, -49, 150, 0, 142, 26, 1, 1);
        javaSwing.bezierCurve(g2, 50, 100, 98, 17, 85, 86, 142, 26, 1, 1);
        javaSwing.bezierCurve(g2, 50, 100, 9, 125, 24, 214, 120, 205, 1, 1);
        javaSwing.bezierCurve(g2, 623, 223, 611, 271, 286, 216, 120, 205, 1, 1);

        Color target = new Color(img.getRGB(570, 50), true);
        javaSwing.floodFill(img, 570, 50, target, Color.WHITE);

    }

    public void drawFlowers(BufferedImage img) {
        flower(img, 0, 0); // 368,300
        flower(img, -260, -60);
        flower(img, -320, 30);
        flower(img, -250, 225);
        flower(img, 80, 150);
        flower(img, 135, -60);
        flower(img, -170, 90);

        //บนเขาด้านบน
        flower(img, -315, -165);
        flower(img, -350, -250);
        flower(img, -230, -155);
        flower(img, -180, -220);
        flower(img, -270, -265);

    }

    public void flower(BufferedImage img, int x, int y) { // เพิ่ม x , y เพื่อปรับตำแหน่งดอกไม้
        Graphics2D g2 = img.createGraphics();
        g2.translate(x, y);

        g2.setColor(new Color(0xeff2dd));
        javaSwing.bezierCurve(g2, 366, 295, 372, 283, 386, 285, 370, 298, 2, 2);
        javaSwing.bezierCurve(g2, 367, 294, 372, 283, 349, 276, 364, 297, 2, 2);
        javaSwing.bezierCurve(g2, 364, 301, 355, 317, 345, 301, 364, 297, 2, 2);
        javaSwing.bezierCurve(g2, 364, 301, 359, 316, 378, 319, 370, 301, 2, 2);
        javaSwing.bezierCurve(g2, 370, 296, 392, 293, 386, 311, 370, 301, 2, 2);
        
        int bgX = 363 + x;
        int bgY = 293 + y;
        Color bgColorAtSeed = new Color(img.getRGB(bgX, bgY), true);

        javaSwing.floodFill(img, bgX, bgY, bgColorAtSeed, new Color(0xeff2dd));

        // ตรงกลางของดอกไม้
        g2.setColor(new Color(0xe1d08a));
        javaSwing.midpointEllipseFill(g2, 368, 300, 6, 6);

    }

    public static void main(String[] args) {
        Background01 m = new Background01();
        m.setPreferredSize(new Dimension(600, 600));

        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Background");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}
