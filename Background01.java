import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background01 extends JPanel {
    JavaSwing javaSwing = new JavaSwing();

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawSky(g2);
        drawhill(g2);
    }

    public void drawSky(Graphics2D g2) {
        g2.setBackground(new Color(0x6da6d9));
        g2.clearRect(0, 0, 600, 600);
    }

    public void drawhill(Graphics2D g2){
        g2.setColor(new Color(0x708e46));
        javaSwing.bezierFillDown(g2, 0, 0, 174, -13, 250, 95, 370, 170, 2, 300);
        
        g2.setColor(new Color(0xa1b95f));
        javaSwing.bezierFillDown(g2, 0, 200, 270, 147, 529, 157, 600, 183, 2, 600);

    }

    public void drawCloud(Graphics2D g2){

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
