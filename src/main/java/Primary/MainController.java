package Primary;

import Things.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController extends JPanel implements ActionListener {
    private int NUMBERBALL = 1000;
    private Camera camera;

    private Ball[] ball;
    private JFrame frame;

    public MainController() {

        camera = new Camera(0,0);
        this.setFocusable(true);
        addKeyListener(camera);

        ball = new Ball[NUMBERBALL];

        for (int i=0;i<NUMBERBALL;i++){
            double x = Math.random()*(EnvironmentConfig.WIDTH-100);
            double y = Math.random()*(EnvironmentConfig.HEIGHT-100);
            ball[i] = new Ball((float)x,(float)y,camera);
        }
        Timer timer = new Timer(40,this);
        timer.start();

        frame = new JFrame("T.A.N.K");

        frame.setSize(EnvironmentConfig.WIDTH,EnvironmentConfig.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(this);

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball b : ball){
            b.draw(g);
        }
    }

    public void update(){
        for (Ball b : ball){
            b.update();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
}
