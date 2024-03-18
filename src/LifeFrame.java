import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LifeFrame extends JFrame implements ActionListener{
    JPanel panel = new LifePanel();
    public LifeFrame(){
        add(panel);
        setSize(1306, 1329);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer(100000 ,this);
        timer.setRepeats(true);
        timer.start();

    }

    public static void main(String[] args) {
        new LifeFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        remove(panel);
        panel = new LifePanel();
        add(panel);
    }
}
