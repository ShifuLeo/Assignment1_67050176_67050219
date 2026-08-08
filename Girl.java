import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Girl extends JPanel {
    JavaSwing javaSwing = new JavaSwing();
    int w2 = 2, h2 = 2; // เส้นบาง
    int w3 = 3, h3 = 3; // เส้นหนา
    Color transparent = new Color(0, 0, 0, 0);
    Color drawColor = new Color(0x161f30);
    Color hairColor = new Color(0x25344e);
    Color faceColor = new Color(0xffd9c1);
    Color pantColor = new Color(0x0e1523);
    Color hoodieColor = new Color(0x40536f);


    private BufferedImage cachedGirl = null;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (cachedGirl == null) {
            cachedGirl = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
            drawGirlSide(cachedGirl);
            // drawGirlBack(cachedGirl);
        }
        g2.drawImage(cachedGirl, 0, 0, this);
    }

    public void drawGirlSide(BufferedImage img) {
        Graphics2D g2 = img.createGraphics();

        //hand
        g2.setColor(faceColor);
        g2.fillOval(282, 394, 19, 9);
        
        // Hair
        g2.setColor(drawColor);
        javaSwing.bezierCurve(g2, 288, 264, 233, 235, 350, 130, 391, 256, w2, h2);
        javaSwing.bezierCurve(g2, 288, 264, 327, 263, 336, 210, 348, 274, w2, h2);
        // Face
        javaSwing.bezierCurve(g2, 288, 264, 312, 266, 292, 297, 335, 283, w2, h2);
        // hair
        javaSwing.bezierCurve(g2, 310, 285, 322, 310, 318, 304, 320, 325, w2, h2);

        // Hoodie
        javaSwing.bezierCurve(g2, 320, 320, 329, 362, 305, 397, 293, 402, w3, h3);
        javaSwing.bezierCurve(g2, 320, 320, 357, 227, 395, 340, 416, 304, w2, h2);
        javaSwing.bezierCurve(g2, 320, 320, 325, 250, 466, 217, 427, 309, w3, h3);
        javaSwing.bezierCurve(g2, 440, 460, 470, 443, 447, 376, 427, 309, w3, h3);
        javaSwing.bezierCurve(g2, 440, 460, 348, 477, 379, 434, 294, 404, w3, h3);
        javaSwing.bezierCurve(g2, 296, 464, 212, 434, 220, 380, 294, 404, w3, h3);
        javaSwing.bezierCurve(g2, 296, 464, 207, 434, 270, 508, 331, 491, w3, h3);
        javaSwing.bezierCurve(g2, 432, 464, 434, 494, 402, 493, 331, 491, w3, h3);
        javaSwing.bezierCurve(g2, 370, 326, 368, 359, 365, 383, 341, 412, w3, h3);
        javaSwing.bezierCurve(g2, 304, 393, 307, 395, 290, 390, 280, 400, w2, h2);

        // eye
        javaSwing.bezierCurve(g2, 314, 262, 305, 261, 304, 265, 305, 270, w2, h2);
        g2.fillOval(307, 263, 7, 7);

        // cheek
        g2.setColor(new Color(0xffb6c1));
        g2.fillOval(312, 272, 10, 6);

        // tear
        g2.setColor(new Color(0xadd8e6));
        g2.fillOval(309, 268, 6, 3);

        //fill face
        javaSwing.floodFill(img, 325, 280, transparent, faceColor);

        //fill hair
        javaSwing.floodFill(img, 350, 205, transparent, hairColor);
        javaSwing.floodFill(img, 325, 295, transparent, hairColor);

        //fill hoodie
        javaSwing.floodFill(img, 335, 375, transparent, hoodieColor);

        //fill pant
        javaSwing.floodFill(img, 335, 450, transparent, pantColor);

    }

    public void drawGirlBack(BufferedImage img) {

        Graphics2D g2 = img.createGraphics();

        //hand
        g2.setColor(faceColor);
        g2.fillOval(282, 394, 19, 9);

        g2.setColor(drawColor);
        javaSwing.bezierCurve(g2, 311, 280, 289, 278, 291, 253, 290, 238, w2, h2);
        javaSwing.bezierCurve(g2, 326, 301, 295, 294, 330, 252, 290, 237, w2, h2);
        // hair
        javaSwing.bezierCurve(g2, 313, 199, 280, 200, 280, 215, 290, 237, w2, h2);
        javaSwing.bezierCurve(g2, 313, 199, 355, 187, 364, 202, 386, 256, w2, h2);

        javaSwing.bezierCurve(g2, 320, 320, 329, 362, 305, 397, 293, 402, w3, h3);
        javaSwing.bezierCurve(g2, 320, 320, 357, 227, 395, 340, 416, 304, w2, h2);
        javaSwing.bezierCurve(g2, 320, 320, 325, 250, 466, 217, 427, 309, w3, h3);
        javaSwing.bezierCurve(g2, 440, 460, 470, 443, 447, 376, 427, 309, w3, h3);
        javaSwing.bezierCurve(g2, 440, 460, 348, 477, 379, 434, 294, 404, w3, h3);
        javaSwing.bezierCurve(g2, 296, 464, 212, 434, 220, 380, 294, 404, w3, h3);
        javaSwing.bezierCurve(g2, 296, 464, 207, 434, 270, 508, 331, 491, w3, h3);
        javaSwing.bezierCurve(g2, 432, 464, 434, 494, 402, 493, 331, 491, w3, h3);
        javaSwing.bezierCurve(g2, 370, 326, 368, 359, 365, 383, 341, 412, w3, h3);
        javaSwing.bezierCurve(g2, 304, 393, 307, 395, 290, 390, 280, 400, w2, h2);

        //fill face
        javaSwing.floodFill(img, 300, 258, transparent, faceColor);

        //fill hair
        javaSwing.floodFill(img, 350, 205, transparent, hairColor);
        javaSwing.floodFill(img, 325, 295, transparent, hairColor);

        //fill hoodie
        javaSwing.floodFill(img, 335, 375, transparent, hoodieColor);

        //fill pant
        javaSwing.floodFill(img, 335, 450, transparent, pantColor);

    }

    // public static void main(String[] args) {
    //     Girl m = new Girl();
    //     JFrame f = new JFrame();
    //     f.add(m);
    //     f.setTitle("Background");
    //     f.setSize(600, 600);
    //     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     f.setVisible(true);
    // }

}
