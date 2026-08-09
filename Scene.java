import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scene extends JPanel implements Runnable {

    Background02 background = new Background02();
    Girl girl = new Girl();
    CatSpirit cat = new CatSpirit();
    JavaSwing js = new JavaSwing();

    double time = 0;
    Thread animationThread;

    private BufferedImage bgImage;

    private BufferedImage girlSideImage;
    private BufferedImage girlBackImage;

    private BufferedImage catImage1;
    private BufferedImage catImage2;

    private static final double GIRL_SWITCH_SEC = 2.45; 
    private static final double TAIL_FLICK_SEC = 0.35; 
    private static final double BALL_CYCLE_SEC = TAIL_FLICK_SEC;

    public Scene() {
        setPreferredSize(new Dimension(600, 600));

        buildBackground();
        buildGirlFrames();
        buildCatFrames();

        animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    public void run() {
        while (true) {
            time += 0.033;
            repaint();

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buildBackground() {
        bgImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bgImage.createGraphics();
        background.drawWall(g2);
        background.drawFloor(g2);
        background.drawCarpet(g2);
        background.drawWindow(g2);
        background.drawPictureFrame(g2);
    }

    private void buildGirlFrames() {
        girlSideImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        girl.drawGirlSide(girlSideImage);

        girlBackImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        girl.drawGirlBack(girlBackImage);
    }

    private void buildCatFrames(){
        catImage1 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCat1(catImage1);

        catImage2 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCat2(catImage2);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bgImage, 0, 0, this);
        drawGirl(g2);
        drawCat(g2);
        drawBall(g2);
    }

    private void drawGirl(Graphics2D g2) {
        int cycle = (int) (time / GIRL_SWITCH_SEC) % 2;
        BufferedImage frame = (cycle == 0) ? girlSideImage : girlBackImage;
        g2.drawImage(frame, 0, 0, this);
    }

    private void drawCat(Graphics2D g2) {
        int cycle = (int)(time / TAIL_FLICK_SEC) % 2;
        BufferedImage frame = (cycle == 0) ? catImage1 : catImage2;
        g2.drawImage(frame, 0, 0, this);
    }

    private void drawBall(Graphics2D g2) {
        double ballTime = (int)(time / BALL_CYCLE_SEC) % 2;

        double y;
        if (ballTime < 0.5) {
            y = 520 - 6 * (ballTime / 0.5); // ขึ้น
        } else {
            y = 520 + 6* ((ballTime - 0.5) / 0.5); // ลง
        }

        g2.setColor(new Color(0xd8b3f2));
        js.midpointEllipseFill(g2, 265, (int)y, 15, 15);
    }

    public static void main(String[] args) {
        Scene scene = new Scene();
        JFrame f = new JFrame("My Memory Animation");
        f.add(scene);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}