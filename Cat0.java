import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cat0 extends JPanel{
    JavaSwing javaSwing = new JavaSwing();
    private BufferedImage cachedCat00 = null;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (cachedCat00 == null) {
            cachedCat00 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
            drawCat(cachedCat00);
            // drawCat00Back(cachedCat00);
        }
        g2.drawImage(cachedCat00, 0, 0, this);
    }

    public void drawCat(BufferedImage img){
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);

        //ear
        javaSwing.bezierCurve(g2, 136, 244, 131, 242, 160, 248, 168, 253, 2, 2);
        javaSwing.bezierCurve(g2, 136, 244, 146, 269, 149, 264, 150, 270, 2, 2);

        javaSwing.bezierCurve(g2, 264, 254, 252, 266, 255, 273, 243, 287, 2, 2);
        javaSwing.bezierCurve(g2, 264, 254, 254, 250, 264, 259, 232, 262, 2, 2);

        //head
        javaSwing.bezierCurve(g2, 147, 333, 132, 303, 143, 287, 150, 270, 2, 2);
        javaSwing.bezierCurve(g2, 168, 253, 212, 255, 182, 259, 232, 262, 2, 2);

        //body
        javaSwing.bezierCurve(g2, 147, 333, 149, 403, 119, 390, 171, 460, 2, 2);
        javaSwing.bezierCurve(g2, 293, 483, 300, 420, 235, 369, 243, 287, 2, 2);
        //leg
        javaSwing.bezierCurve(g2, 169, 499, 190, 493, 180, 480, 171, 460, 2, 2);
        javaSwing.bezierCurve(g2, 169, 499, 157, 514, 290, 507, 291, 497, 2, 2);

        //tail
        javaSwing.bezierCurve(g2, 333, 367, 294, 417, 346, 465, 291, 497, 2, 2);
        javaSwing.bezierCurve(g2, 333, 367, 328, 334, 300, 380, 300, 400, 2, 2);
        javaSwing.bezierCurve(g2, 293, 483, 314, 464, 299, 445, 300, 400, 2, 2);

        
        //hand line
        g2.setColor(Color.WHITE);
        javaSwing.bezierCurve(g2, 205, 391, 165, 316, 180, 313, 226, 377, 1, 1);
        //leg line
        javaSwing.bezierCurve(g2, 216, 495, 187, 441, 226, 415, 240, 424, 1, 1);
        javaSwing.bezierCurve(g2, 216, 495, 213, 496, 198, 493, 195, 505, 1, 1);

        //eye
        javaSwing.midpointEllipseFill(g2, 166, 295, 15, 15);
        javaSwing.midpointEllipseFill(g2, 215, 297, 15, 15);
        
        g2.setColor(Color.black);
        javaSwing.midpointEllipseFill(g2, 170, 295, 3, 6);
        javaSwing.midpointEllipseFill(g2, 207, 297, 3, 6);
        
        
        javaSwing.floodFill(img, 200, 500, new Color(0,0,0,0), Color.BLACK);
    }

    public static void main(String[] args) {
        Cat0 m = new Cat0();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Background");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
