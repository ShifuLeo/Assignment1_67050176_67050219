import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cat1 extends JPanel {
    JavaSwing javaSwing = new JavaSwing();

    private BufferedImage cachedCat = null;
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (cachedCat == null) {
            cachedCat = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
            drawBaseCat(cachedCat, true);
        }
        g2.drawImage(cachedCat, 0, 0, this);
    }

    public void drawBaseCat(BufferedImage img, boolean isBlink) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);

        // L, R leg 
        javaSwing.bezierCurve(g2, 128, 393, 180, 420, 128, 357, 172, 350, 2, 2);
        javaSwing.bezierCurve(g2, 310, 404, 320, 415, 330, 428, 292, 458, 2, 2);

        // L, R body
        javaSwing.bezierCurve(g2, 243, 286, 233, 297, 183, 318, 172, 350, 2, 2);
        javaSwing.bezierCurve(g2, 310, 404, 331, 352, 326, 360, 320, 326, 2, 2);
        
        // L, R ear
        javaSwing.bezierCurve(g2, 284, 243, 283, 247, 268, 211, 272, 248, 2, 2);
        javaSwing.bezierCurve(g2, 323, 270, 332, 233, 323, 235, 315, 251, 2, 2);

        // head
        javaSwing.bezierCurve(g2, 243, 286, 263, 266, 246, 269, 272, 248, 2, 2);
        javaSwing.bezierCurve(g2, 284, 243, 283, 247, 292, 232, 315, 251, 2, 2);
        javaSwing.bezierCurve(g2, 323, 270, 327, 288, 311, 297, 320, 326, 2, 2);

        // R, L foot
        javaSwing.bezierCurve(g2, 289, 476, 298, 496, 330, 480, 292, 458, 2, 2);
        javaSwing.bezierCurve(g2, 289, 476, 240, 465, 267, 454, 258, 449, 2, 2);

        javaSwing.bezierCurve(g2, 139, 414, 180, 444, 178, 388, 202, 427, 2, 2);
        javaSwing.bezierCurve(g2, 139, 414, 137, 406, 110, 410, 120, 393, 2, 2);
        javaSwing.bezierCurve(g2, 139, 414, 109, 404, 118, 389, 128, 393, 2, 2);

        // butt
        javaSwing.bezierCurve(g2, 218, 436, 224, 444, 255, 438, 258, 449, 2, 2);

        // L, R hand
        g2.setColor(Color.WHITE);
        javaSwing.bezierCurve(g2, 240, 326, 237, 345, 237, 340, 229, 355, 2, 2);
        javaSwing.bezierCurve(g2, 238, 362, 226, 380, 219, 365, 229, 355, 2, 2);
        javaSwing.bezierCurve(g2, 238, 362, 254, 348, 259, 333, 267, 315, 2, 2);

        javaSwing.bezierCurve(g2, 291, 326, 283, 356, 282, 344, 265, 372, 2, 2);
        javaSwing.bezierCurve(g2, 298, 351, 277, 387, 254, 391, 265, 372, 2, 2);
        
        if (isBlink) {
            // หลับตา
            g2.setColor(Color.BLACK);
            javaSwing.midpointEllipseFill(g2, 280, 265, 8, 8);
            javaSwing.midpointEllipseFill(g2, 305, 270, 8, 8);
        } else {
            // ลืมตา
            g2.setColor(Color.BLACK);
            javaSwing.midpointCircle(g2, 280, 265, 8, 2, 2);
            g2.setColor(Color.WHITE);
            javaSwing.midpointCircle(g2, 280, 265, 5, 3, 3);
            g2.setColor(Color.BLACK);
            javaSwing.midpointCircle(g2, 280, 265, 4, 2, 2);
            javaSwing.floodFill(img, 281, 266, new Color(0,0,0,0), Color.BLACK);

            javaSwing.midpointCircle(g2, 305, 270, 8, 2, 2);
            g2.setColor(Color.WHITE);
            javaSwing.midpointCircle(g2, 305, 270, 5, 3, 3);
            g2.setColor(Color.BLACK);
            javaSwing.midpointCircle(g2, 305, 270, 4, 2, 2);
            javaSwing.floodFill(img, 306, 271, new Color(0,0,0,0), Color.BLACK);
        }
    }

    public void drawCatFrame1(BufferedImage img, boolean isBlink) {
        drawBaseCat(img, isBlink);
        drawCatTail0(img);
        javaSwing.floodFill(img, 280, 400, new Color(0,0,0,0), Color.BLACK);
    }

    public void drawCatFrame2(BufferedImage img, boolean isBlink) {
        drawBaseCat(img, isBlink);
        drawCatTail1(img);
        javaSwing.floodFill(img, 280, 400, new Color(0,0,0,0), Color.BLACK);
    }

    public void drawCatTail0(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);
        //cat tail
        javaSwing.bezierCurve(g2, 218, 436, 168, 494, 292, 464, 257, 547, 2, 2);
        javaSwing.bezierCurve(g2, 225, 500, 243, 514, 226, 556, 257, 547, 2, 2);
        javaSwing.bezierCurve(g2, 225, 500, 180, 477, 174, 465, 202, 427, 2, 2);
    }

    public void drawCatTail1(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);
        //cat tail
        javaSwing.bezierCurve(g2, 218, 436, 148, 450, 214, 498, 170, 535, 2, 2);
        javaSwing.bezierCurve(g2, 150, 518, 106, 552, 165, 548, 170, 535, 2, 2);
        javaSwing.bezierCurve(g2, 150, 518, 200, 520, 129, 439, 202, 427, 2, 2);
    }

    public static void main(String[] args) {
        Cat1 m = new Cat1();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Cat1");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}