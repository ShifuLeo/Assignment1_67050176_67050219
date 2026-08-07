import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Background02 extends JPanel {
    JavaSwing javaSwing = new JavaSwing();

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawWall(g2);
        drawFloor(g2);
        drawCarpet(g2);
        drawWindow(g2);
        drawPictureFrame(g2);
    }

    public void drawWall(Graphics2D g2) {
        g2.setColor(new Color(0xeeb2b2));
        javaSwing.plot(g2, 0, 0, 600, 600);

        g2.setColor(new Color(0x695322));
        javaSwing.bresenhamLine(g2, 0, 345, 600, 345, 10, 10);

        g2.setColor(new Color(0x614200));
        javaSwing.bresenhamLine(g2, 0, 332, 600, 332, 13, 13);

        g2.setColor(new Color(0xb87d00));
        javaSwing.bresenhamLine(g2, 0, 328, 600, 328, 4, 4);

        // Wall shadow
        g2.setColor(new Color(0xd7a1a1));
        // javaSwing.bezierCurve(g2, -45, 166, 423, -18, 600, 0, 640, 139, 5, 5);
        javaSwing.bezierFillUp(g2, -45, 166, 423, -18, 600, 0, 640, 139, 5, 0);

    }

    public void drawFloor(Graphics2D g2) {
        g2.setColor(new Color(0xf7e1b4));
        javaSwing.bresenhamLine(g2, 0, 350, 600, 350, 1, 250);

        // floor grid
        // horizontal
        g2.setColor(new Color(0x695322));
        javaSwing.bresenhamLine(g2, 0, 505, 600, 505, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 455, 600, 455, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 410, 600, 410, 1, 1);
        javaSwing.bresenhamLine(g2, 0, 375, 600, 375, 1, 1);

        // vertical
        javaSwing.bresenhamLine(g2, 300, 350, 300, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 220, 350, 200, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 380, 350, 400, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 140, 350, 100, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 460, 350, 500, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 60, 350, 0, 600, 2, 1);
        javaSwing.bresenhamLine(g2, 540, 350, 600, 600, 2, 1);

    }

    public void drawCarpet(Graphics2D g2) {
        g2.setColor(new Color(0xd6845f));
        javaSwing.midpointEllipseFill(g2, 300, 460, 225, 55);

        g2.setColor(new Color(0xee9b78));
        javaSwing.midpointEllipseFill(g2, 300, 455, 225, 55); //จุดที่ ผญ นั่ง

        g2.setColor(new Color(0xffd9c1));
        javaSwing.midpointEllipseFill(g2, 300, 455, 180, 40);

    }

    public void drawWindow(Graphics2D g2) {
        int left = 420, right = 540; // ขอบซ้าย-ขวาของกระจก
        int bodyTop = 100; // ขอบบน
        int bodyBottom = 300; // ขอบล่าง
        int topArch = 20; // โค้ง
        int thick = 10; // ความหนากรอบไม้

        //หน้าต่าง
        g2.setColor(new Color(0x7a4a1e));
        javaSwing.bezierFillDown(g2, left - thick, bodyTop, left - thick, topArch, right + thick, topArch, right + thick, bodyTop, 1, bodyTop);
        javaSwing.bresenhamLine(g2, left - thick, bodyTop, right + thick, bodyTop, 1, bodyBottom - bodyTop);

        //วิวกลางคืน
        g2.setColor(new Color(0x0a1a4d));
        javaSwing.bezierFillDown(g2, left, bodyTop , left , topArch + thick , right, topArch + thick, right, bodyTop , 1, bodyTop);
        javaSwing.bresenhamLine(g2, left, bodyTop - thick, right , bodyTop - thick, 1, bodyBottom - bodyTop);

        //เส้นหน้าต่าง
        g2.setColor(new Color(0x7a4a1e));
        javaSwing.bresenhamLine(g2, left, bodyTop, right, bodyTop, 10, 10);
        javaSwing.bresenhamLine(g2, left, bodyTop + 65, right, bodyTop + 65, 10, 10);
        javaSwing.bresenhamLine(g2, left, bodyTop + 130, right, bodyTop + 130, 10, 10);


        // 5) ดวงจันทร์ && ดาว
        g2.setColor(new Color(0xd9d97a));
        javaSwing.midpointEllipseFill(g2, right - 30, bodyTop + 40, 20, 20);

    }
    
    //ใส่รูปแมว
    public void drawPictureFrame(Graphics2D g2){
        g2.fillRect(55, 100, 120, 140);
        g2.fillRect(210, 50, 100, 100);
        g2.fillRect(210, 170, 100, 100);

    }

    public static void main(String[] args) {
        Background02 m = new Background02();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Background");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
