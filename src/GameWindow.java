import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        setTitle("Crab Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new Window());
        setVisible(true);
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
    }
}
