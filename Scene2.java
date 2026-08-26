import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scene2 extends JPanel {

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
    private static final double BALL_START_ROLL_SEC = 4.0;
    private static final double BALL_ROLL_SPEED = 180.0;
    private static final double CAT_FADE_DURATION = 2.0;
    private static final double WHITE_HOLD_SEC = 1;
    private static final double FADE_FINISH_SEC = 2;

    public Scene2() {
        setPreferredSize(new Dimension(600, 600));

        buildBackground();
        buildGirlFrames();
        buildCatFrames();

        // animationThread = new Thread(this);
        // animationThread.start();
    }

    // @Override
    // public void run() {
    // double lastTime = System.currentTimeMillis();
    // double currentTime, elapsedTime;

    // while (true) {
    // currentTime = System.currentTimeMillis();
    // elapsedTime = currentTime - lastTime;
    // lastTime = currentTime;

    // time += elapsedTime / 1000.0;

    // if (time >= 10.0) {
    // time = 10.0;
    // repaint();
    // break;
    // }

    // repaint();

    // try {
    // Thread.sleep(16);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    public void update(double deltaTime) {
        time += deltaTime;
        if (time >= 10.0) {
            time = 10.0;
        }
    }

    public boolean isFinished() {
        return time >= 10.0;
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

    private void buildCatFrames() {
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
        drawStarImage(g2);
        drawGirl(g2);
        drawCat(g2);
        drawBall(g2);
        drawFadeInEffect(g2);
    }

    private void drawStarImage(Graphics2D g2) {
        background.drawStar(g2, time);
    }

    private void drawGirl(Graphics2D g2) {
        int cycle = (int) (time / GIRL_SWITCH_SEC) % 2;
        BufferedImage frame = (cycle == 0) ? girlSideImage : girlBackImage;
        g2.drawImage(frame, 0, 0, this);
    }

    private void drawCat(Graphics2D g2) {
        if (time < BALL_START_ROLL_SEC) {

            int cycle = (int) (time / TAIL_FLICK_SEC) % 2;
            BufferedImage frame = (cycle == 0) ? catImage1 : catImage2;

            g2.drawImage(frame, 0, 0, this);
            return;
        }

        double rollTime = time - BALL_START_ROLL_SEC;
        double ballExitTime = (630 - 265) / BALL_ROLL_SPEED;
        double fadeTime = rollTime - ballExitTime;
        float alpha = (float) (1.0 - fadeTime / CAT_FADE_DURATION);

        alpha = Math.max(0.0f, Math.min(1.0f, alpha));

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.drawImage(catImage1, 0, 0, this);
        g2.setComposite(AlphaComposite.SrcOver);

    }

    private void drawBall(Graphics2D g2) {
        if (time < BALL_START_ROLL_SEC) {

            double ballTime = (int) (time / BALL_CYCLE_SEC) % 2;

            double y;

            if (ballTime < 0.5) {
                y = 520 - 6 * (ballTime / 0.5);
            } else {
                y = 520 + 6 * ((ballTime - 0.5) / 0.5);
            }

            g2.setColor(new Color(0xd8b3f2));
            js.midpointEllipseFill(g2, 265, (int) y, 15, 15);

        } else {

            double rollTime = time - BALL_START_ROLL_SEC;
            double x = 265 + BALL_ROLL_SPEED * rollTime;
            if (x < 630) {
                g2.setColor(new Color(0xd8b3f2));
                js.midpointEllipseFill(g2, (int) x, 520, 15, 15);
            }
        }
    }

    private void drawFadeInEffect(Graphics2D g2) {
        if (time < FADE_FINISH_SEC) {
        float alpha = 1.0f;

        if (time >= WHITE_HOLD_SEC) {
            alpha = (float) (1.0 - ((time - WHITE_HOLD_SEC) / (FADE_FINISH_SEC - WHITE_HOLD_SEC)));
        }

        alpha = Math.max(0.0f, Math.min(1.0f, alpha));

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        // คืนค่า Composite ปกติ
        g2.setComposite(AlphaComposite.SrcOver);
    }
    }

    public static void main(String[] args) {
        Scene2 scene = new Scene2();
        JFrame f = new JFrame("My Memory Animation");
        f.add(scene);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}