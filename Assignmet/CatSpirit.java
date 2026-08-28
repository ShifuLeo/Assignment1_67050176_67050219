import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CatSpirit extends JPanel {

    JavaSwing javaSwing = new JavaSwing();
    private BufferedImage cachedCat = null;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (cachedCat == null) {
            cachedCat = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
            drawCat1(cachedCat);
            // drawCatBack(cachedCat);
        }
        g2.drawImage(cachedCat, 0, 0, this);
    }

    public void drawCat1(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        Color catSpiritColor = new Color(0x8b, 0xd2, 0xec, 150);
        g2.setColor(new Color(0x8bd2ec));
        //cat head
        javaSwing.bezierCurve(g2, 267, 449, 262, 438, 267, 425, 260, 420, 2, 2);
        javaSwing.bezierCurve(g2, 232, 458, 260, 460, 260, 460, 267, 449, 2, 2);
        //cat ear
        javaSwing.bezierCurve(g2, 245, 413, 256, 411, 269, 393, 260, 420, 2, 2);
        //cat back
        javaSwing.bezierCurve(g2, 245, 413, 170, 445, 184, 410, 120, 430, 2, 2);
        //cat tail
        drawCatTail1(img);

        //cat ass
        javaSwing.bezierCurve(g2, 100, 480, 104, 458, 95, 461, 101, 444, 2, 2);

        //right leg
        //back
        javaSwing.bezierCurve(g2, 100, 480, 79, 495, 80, 520, 88, 529, 2, 2);
        javaSwing.bezierCurve(g2, 177, 489, 67, 490, 109, 533, 88, 529, 2, 2);
        //front
        javaSwing.bezierCurve(g2, 177, 489, 180, 500, 165, 530, 194, 521, 2, 2);
        javaSwing.bezierCurve(g2, 232, 458, 161, 512, 203, 507, 194, 521, 2, 2);

        //left leg
        //front
        drawLeftLegFront1(img);
        //back
        javaSwing.bezierCurve(g2, 145, 490, 120, 520, 164, 511, 153, 526, 2, 2);
        javaSwing.bezierCurve(g2, 121, 495, 112, 517, 124, 526, 153, 526, 2, 2);

        javaSwing.floodFill(img, 145, 480, new Color(0, 0, 0, 0), catSpiritColor);
        javaSwing.floodFill(img, 125, 500, new Color(0, 0, 0, 0), catSpiritColor);
        javaSwing.floodFill(img, 210, 485, new Color(0, 0, 0, 0), catSpiritColor);
    }

    public void drawCat2(BufferedImage img){
        Graphics2D g2 = img.createGraphics();
        Color catSpiritColor = new Color(0x8b, 0xd2, 0xec, 150);

        g2.setColor(new Color(0x8bd2ec));

        //cat head
        javaSwing.bezierCurve(g2, 267, 449, 262, 438, 267, 425, 260, 420, 2, 2);
        javaSwing.bezierCurve(g2, 232, 458, 260, 460, 260, 460, 267, 449, 2, 2);
        //cat ear
        javaSwing.bezierCurve(g2, 245, 413, 256, 411, 269, 393, 260, 420, 2, 2);
        //cat back
        javaSwing.bezierCurve(g2, 245, 413, 170, 445, 184, 410, 120, 430, 2, 2);
        //cat tail
        drawCatTail2(img);

        //cat ass
        javaSwing.bezierCurve(g2, 100, 480, 104, 458, 95, 461, 101, 444, 2, 2);

        //right leg
        //back
        javaSwing.bezierCurve(g2, 100, 480, 79, 495, 80, 520, 88, 529, 2, 2);
        javaSwing.bezierCurve(g2, 177, 489, 67, 490, 109, 533, 88, 529, 2, 2);
        //front
        javaSwing.bezierCurve(g2, 177, 489, 180, 500, 165, 530, 194, 521, 2, 2);
        javaSwing.bezierCurve(g2, 232, 458, 161, 512, 203, 507, 194, 521, 2, 2);

        //left leg
        //front
        drawRightLegFront2(img);
        //back
        javaSwing.bezierCurve(g2, 145, 490, 120, 520, 164, 511, 153, 526, 2, 2);
        javaSwing.bezierCurve(g2, 121, 495, 112, 517, 124, 526, 153, 526, 2, 2);

        javaSwing.floodFill(img, 145, 480, new Color(0, 0, 0, 0), catSpiritColor);
        javaSwing.floodFill(img, 125, 500, new Color(0, 0, 0, 0), catSpiritColor);
        javaSwing.floodFill(img, 210, 485, new Color(0, 0, 0, 0), catSpiritColor);
    }

    public void drawCatTail1(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(0x8bd2ec));
        //cat tail
        javaSwing.bezierCurve(g2, 120, 380, 104, 396, 91, 425, 120, 430, 2, 2);
        javaSwing.bezierCurve(g2, 120, 380, 102, 356, 73, 423, 101, 444, 2, 2);
    }

    public void drawCatTail2(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(0x8bd2ec));
        //cat tail
        javaSwing.bezierCurve(g2, 91, 372, 110, 373, 91, 420, 120, 430, 2, 2);
        javaSwing.bezierCurve(g2, 91, 372, 85, 367, 86, 429, 101, 444, 2, 2);

    }

    public void drawLeftLegFront1(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(0x8bd2ec));
        //left leg front
        javaSwing.bezierCurve(g2, 201, 485, 216, 486, 250, 522, 267, 505, 2, 2);
        javaSwing.bezierCurve(g2, 232, 458, 220, 488, 256, 489, 267, 505, 2, 2);

    }
    public void drawRightLegFront2(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(0x8bd2ec));
        //right leg front
        javaSwing.bezierCurve(g2, 201, 485, 208, 487, 280, 512, 263, 486, 2, 2);
        javaSwing.bezierCurve(g2, 232, 458, 220, 480, 240, 486, 263, 486, 2, 2);

    }

    public static void main(String[] args) {
        CatSpirit m = new CatSpirit();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Background");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
