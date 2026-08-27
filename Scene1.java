import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scene1 extends JPanel  {

    Background01 background = new Background01();
    Cat1 cat = new Cat1();
    JavaSwing js = new JavaSwing();

    double time = 0;
    // Thread animationThread;

    private BufferedImage skyImage = null;
    private BufferedImage cloudImage = null;
    private BufferedImage foregroundImage = null;
    private BufferedImage tapeBaseImage = null;
    private BufferedImage tapeTopImage = null;
    private BufferedImage catFrame1 = null;
    private BufferedImage catFrame2 = null;

    private static final double TAIL_FLICK_SEC = 0.35;
    private static final double CLOUD_SPEED = 10.0;
    private static final double FLASH_START_SEC = 4;

    public Scene1() {
        setPreferredSize(new Dimension(600, 600));

        buildBackground();
        buildCatFrames();
    }

    // @Override
    // public void run() {
    //     while (true) {
    //         time += 0.033;
    //         repaint();

    //         try {
    //             Thread.sleep(33);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // @Override
    // public void run() {
    //     double lastTime = System.currentTimeMillis();
    //     double currentTime, elapsedTime;

    //     while (true) {
    //         currentTime = System.currentTimeMillis();
    //         elapsedTime = currentTime - lastTime;
    //         lastTime = currentTime;
    //         time += elapsedTime / 1000.0;

    //         if (time >= 10.0) {
    //             time = 10.0;
    //             repaint();
    //             break;
    //         }

    //         repaint();

    //         try {
    //             Thread.sleep(16);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    public void update(double deltaTime) {
        time += deltaTime;
        if (time > 5) {
            time = 5;
        }
    }

    public boolean isFinished() {
        return time >= 5;
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
        // สร้างภาพฐานเทป (เลเยอร์ล่างสุด)
        tapeBaseImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawTapeBase(tapeBaseImage);
        
        // สร้างภาพฝาเทป (เลเยอร์บนสุด)
        tapeTopImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        background.drawTapeTop(tapeTopImage);
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
        catFrame1 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame1(catFrame1);

        catFrame2 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame2(catFrame2);
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
        drawCat(g2);
        drawFlashEffect(g2);

    }

    private void drawCloud(Graphics2D g2) {
        int cloudWidth = 700;
        int move = (int) ((time * CLOUD_SPEED * 5) % cloudWidth);

        g2.drawImage(cloudImage, -move, 0, this); //ก้อนแรก
        g2.drawImage(cloudImage, cloudWidth - move, 0, this); //ก้อน 2 

    }

    private void drawCat(Graphics2D g2) {
        int cycle = (int) (time / TAIL_FLICK_SEC) % 2;
        if (cycle == 0) {
            g2.drawImage(catFrame1, 0, 0, this);
        } else {
            g2.drawImage(catFrame2, 0, 0, this);
        }
    }

    private void drawFlashEffect(Graphics2D g2) {
        if (time >= FLASH_START_SEC) {
            // คำนวณความสว่างสีขาว (0.0 ถึง 1.0)
            float alpha = (float) ((time - FLASH_START_SEC) / (10.0 - FLASH_START_SEC));
            alpha = Math.max(0.0f, Math.min(1.0f, alpha)); // จำกัดค่าให้อยู่ช่วง 0.0 - 1.0

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());
            
            // คืนค่า Composite เป็นปกติ
            g2.setComposite(AlphaComposite.SrcOver);
        }
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