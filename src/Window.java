import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Window extends JPanel implements ActionListener{
    private final int SIZE = 320;
    private final int CRAB_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image crab;
    private Image cake;
    private int cakeX;
    private int cakeY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int crabs;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;


    public Window(){
        setBackground(Color.white);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame(){
        crabs = 2;
        for (int i = 0; i < crabs; i++) {
            x[i] = 48 - i * CRAB_SIZE;
            y[i] = 48;
        }
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }

    public void createApple(){
        cakeX = new Random().nextInt(20) * CRAB_SIZE;
        cakeY = new Random().nextInt(20) * CRAB_SIZE;
    }

    public void loadImages(){
        ImageIcon iia = new ImageIcon("cake.png");
        cake = iia.getImage();
        ImageIcon iid = new ImageIcon("crab.png");
        crab = iid.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(cake, cakeX, cakeY,this);
            for (int i = 0; i < crabs; i++) {
                g.drawImage(crab, x[i], y[i] ,this);
            }
        }
        else{
            String str = "YOU LOSER";
            g.setColor(Color.PINK);
            g.drawString(str,125,SIZE/2);
        }
    }

    public void move(){
        for (int i = crabs; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if(up){
            y[0] -= CRAB_SIZE;
        }
        if(down){
            y[0] += CRAB_SIZE;
        }
        if(left){
            x[0] -= CRAB_SIZE;
        }
        if(right){
            x[0] += CRAB_SIZE;
        }
    }

    public void checkApple(){
        if(x[0] == cakeX && y[0] == cakeY){
            crabs++;
            createApple();
        }
    }

    public void checkCollisions(){
        for (int i = crabs; i > 0 ; i--) {
            if(i > 4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        if(x[0] > SIZE){
            inGame = false;
        }
        if(x[0] < 0){
            inGame = false;
        }
        if(y[0] > SIZE){
            inGame = false;
        }
        if(y[0] < 0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }

            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
        }
    }


}
