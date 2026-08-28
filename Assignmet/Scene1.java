import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scene1 extends JPanel {

    Background01 background = new Background01();
    Cat1 cat = new Cat1();
    JavaSwing js = new JavaSwing();

    double time = 0;

    private BufferedImage skyImage = null;
    private BufferedImage cloudImage = null;
    private BufferedImage foregroundImage = null;
    private BufferedImage tapeBaseImage = null;
    private BufferedImage tapeTopImage = null;
    private BufferedImage catFrame1Open = null;
    private BufferedImage catFrame1Blink = null;
    private BufferedImage catFrame2Open = null;
    private BufferedImage catFrame2Blink = null;
    private BufferedImage note1Image = null;
    private BufferedImage note2Image = null;

    private static final double CLOUD_SPEED = 10.0;
    private static final double SCENE_DURATION_SEC = 5.0;
    private static final double BLINK_CYCLE_SEC = 1.5;
    private static final double BLINK_DURATION_SEC = 0.2;
    private static final double TAIL_FLICK_SEC = 0.9;
    private static final double FLASH_START_SEC = 4;

    public Scene1() {
        setPreferredSize(new Dimension(600, 600));

        buildBackground();
        buildCatFrames();
    }

    public void update(double deltaTime) {
        time += deltaTime;
        if (time > SCENE_DURATION_SEC) {
            time = SCENE_DURATION_SEC;
        }
    }

    public boolean isFinished() {
        return time >= SCENE_DURATION_SEC;
    }

    private void buildBackground() {
        // ท้องฟ้า
        skyImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawSky(skyImage);

        // เมฆ
        cloudImage = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawCloud(cloudImage);

        // เขา && ดอกไม้
        foregroundImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawhill(foregroundImage);
        background.drawFlowers(foregroundImage);

        // tape
        // ฐานเทป
        tapeBaseImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawTapeBase(tapeBaseImage);

        // ฝาเทป
        tapeTopImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawTapeTop(tapeTopImage);

        note1Image = createNoteSprite(1);
        note2Image = createNoteSprite(2);
    }

    private void drawSpinningTape(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        double angle = time * 0.5;
        AffineTransform oldTransform = g2.getTransform();

        // วงซ้าย
        g2.rotate(angle, 429, 377);
        js.bresenhamLine(g2, 429, 377, 429, 355, 3, 3);
        g2.setTransform(oldTransform);

        // วงขวา
        g2.rotate(angle, 472, 368);
        js.bresenhamLine(g2, 472, 368, 472, 388, 3, 3);
        g2.setTransform(oldTransform);
    }

    private void buildCatFrames() {
        catFrame1Open = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame1(catFrame1Open, false);
        
        catFrame1Blink = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame1(catFrame1Blink, true);

        catFrame2Open = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame2(catFrame2Open, false);
        
        catFrame2Blink = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame2(catFrame2Blink, true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(skyImage, 0, 0, this);
        drawCloud(g2);
        g2.drawImage(foregroundImage, 0, 0, this);
        g2.drawImage(tapeBaseImage, 0, 0, this);
        drawSpinningTape(g2);
        g2.drawImage(tapeTopImage, 0, 0, this);
        
        drawNotes(g2);
        drawCat(g2);
        
        drawFlashEffect(g2);
    }

    private void drawCloud(Graphics2D g2) {
        int cloudWidth = 700;
        int move = (int) ((time * CLOUD_SPEED * 5) % cloudWidth);

        g2.drawImage(cloudImage, -move, 0, this); // ก้อนแรก
        g2.drawImage(cloudImage, cloudWidth - move, 0, this); // ก้อน 2

    }

    private void drawCat(Graphics2D g2) {
        int tailCycle = (int) (time / TAIL_FLICK_SEC) % 2;
        boolean isBlink = (time % BLINK_CYCLE_SEC) < BLINK_DURATION_SEC;
        BufferedImage frame;
        
        if (tailCycle == 0) {
            frame = isBlink ? catFrame1Blink : catFrame1Open;
        } else {
            frame = isBlink ? catFrame2Blink : catFrame2Open;
        }
        
        g2.drawImage(frame, 0, 0, this);
    }

    private void drawFlashEffect(Graphics2D g2) {
        if (time >= FLASH_START_SEC) {
            float alpha = (float) ((time - FLASH_START_SEC) / (SCENE_DURATION_SEC - FLASH_START_SEC));
            alpha = Math.max(0.0f, Math.min(1.0f, alpha)); // จำกัดค่าให้อยู่ช่วง 0.0 - 1.0

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());

            // คืนค่า Composite เป็นปกติ
            g2.setComposite(AlphaComposite.SrcOver);
        }
    }

    private BufferedImage createNoteSprite(int which) {
        BufferedImage img = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(0xa1b95f));
        g2.fillRect(0, 0, 600, 600);
        g2.dispose();

        if (which == 1)
            background.drawNote01(img);
        else
            background.drawNote02(img);

        int sentinelRGB = new Color(0xa1b95f).getRGB();
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == sentinelRGB) {
                    img.setRGB(x, y, 0x00000000);
                }
            }
        }
        return img;
    }

    private void drawNotes(Graphics2D g2) {
        double dy1 = Math.sin(time * 2.0) * 6;
        double dx1 = Math.cos(time * 1.3) * 3;
        double dy2 = Math.sin(time * 2.0 + Math.PI / 2) * 6;
        double dx2 = Math.cos(time * 1.3 + Math.PI / 2) * 3;

        g2.drawImage(note1Image, (int) Math.round(dx1), (int) Math.round(dy1), this);
        g2.drawImage(note2Image, (int) Math.round(dx2), (int) Math.round(dy2), this);
    }

    public static void main(String[] args) {
        Scene1 scene = new Scene1();
        JFrame f = new JFrame("My Memory Animation");
        f.add(scene);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}