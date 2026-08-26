import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scene1 extends JPanel implements Runnable {

    Background01 background = new Background01();
    // Girl girl = new Girl();
    Cat1 cat = new Cat1();
    JavaSwing js = new JavaSwing();

    double time = 0;
    Thread animationThread;

    private BufferedImage bgImage = null;

    private BufferedImage catFrame1 = null; 
    private BufferedImage catFrame2 = null;
    
    private static final double TAIL_FLICK_SEC = 0.35;

    public Scene1() {
        setPreferredSize(new Dimension(600, 600));

        buildBackground();
        // buildGirlFrames();
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
        background.drawSky(g2);
        background.drawHill(g2);
        // background.drawCarpet(g2);
        // background.drawWindow(g2);
        // background.drawPictureFrame(g2);
    }

    // private void buildGirlFrames() {
    //     girlSideImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    //     girl.drawGirlSide(girlSideImage);

    //     girlBackImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    //     girl.drawGirlBack(girlBackImage);
    // }

    private void buildCatFrames(){
        catFrame1 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame1(catFrame1); 

        catFrame2 = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        cat.drawCatFrame2(catFrame2);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // 1. วาดพื้นหลัง
        g2.drawImage(bgImage, 0, 0, this);
        
        // 2. คำนวณจังหวะสลับเฟรม
        int cycle = (int)(time / 0.35) % 2; 
        
        // 3. เลือกว่าจะวาดรูปแมวเฟรมไหนลงจอ
        if (cycle == 0) {
            g2.drawImage(catFrame1, 0, 0, this);
        } else {
            g2.drawImage(catFrame2, 0, 0, this);
        }
    }

    // private void drawStarImage(Graphics2D g2){
    //     background.drawStar(g2, time);
    // }

    // private void drawGirl(Graphics2D g2) {
    //     int cycle = (int) (time / GIRL_SWITCH_SEC) % 2;
    //     BufferedImage frame = (cycle == 0) ? girlSideImage : girlBackImage;
    //     g2.drawImage(frame, 0, 0, this);
    // }

    // private void drawCat(Graphics2D g2) {
    //     // วาดภาพ cached ของแมวที่ตำแหน่ง 0, 0
    //     g2.drawImage(catImage, 0, 0, this); 
    // }

    // private void drawCat(Graphics2D g2) {
    //     int cycle = (int)(time / TAIL_FLICK_SEC) % 2;
    //     BufferedImage frame = (cycle == 0) ? catImage : catImage2;
    //     g2.drawImage(frame, 0, 0, this);
    // }

    // private void drawBall(Graphics2D g2) {
    //     double ballTime = (int)(time / BALL_CYCLE_SEC) % 2;

    //     double y;
    //     if (ballTime < 0.5) {
    //         y = 520 - 6 * (ballTime / 0.5); // ขึ้น
    //     } else {
    //         y = 520 + 6* ((ballTime - 0.5) / 0.5); // ลง
    //     }

    //     g2.setColor(new Color(0xd8b3f2));
    //     js.midpointEllipseFill(g2, 265, (int)y, 15, 15);
    // }

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