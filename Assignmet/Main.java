import java.awt.CardLayout;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Scene1 scene1;
    private Scene2 scene2;

    private int currentScene = 1; // 1 = Scene1, 2 = Scene2
    private boolean isRunning = true;

    public Main() {
        frame = new JFrame("My Memory Animation");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        scene1 = new Scene1();
        scene2 = new Scene2();

        mainPanel.add(scene1, "Scene1");
        mainPanel.add(scene2, "Scene2");

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        playMusic("sound.wav");

        new Thread(this).start();
    }

    @Override
    public void run() {
        double lastTime = System.currentTimeMillis();

        while (isRunning) {
            double currentTime = System.currentTimeMillis();
            double elapsedTime = (currentTime - lastTime) / 1000.0;
            lastTime = currentTime;

            if (currentScene == 1) {
                scene1.update(elapsedTime);
                scene1.repaint();


                if (scene1.isFinished()) {
                    currentScene = 2;
                    cardLayout.show(mainPanel, "Scene2");
                }
            } else if (currentScene == 2) {
                scene2.update(elapsedTime);
                scene2.repaint();
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void playMusic(String musicFileName) {
        try {
            java.net.URL musicUrl = getClass().getResource("/" + musicFileName);
            
            if (musicUrl != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicUrl);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            System.exit(0); 
                        }
                    }
                });
                
                clip.start();
            } else {
                System.out.println("Can't find audio file: " + musicFileName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}