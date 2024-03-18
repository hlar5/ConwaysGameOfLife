import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LifePanel extends JPanel implements ActionListener{
    int X_Panel = 1300;
    int Y_Panel = 1300;
    int size = 15;
    int width = X_Panel/size;
    int height = Y_Panel/size;
    int[][] life = new int[width][height];
    int[][] beforeLife = new int[width][height];
    boolean starts = true;

    public LifePanel(){
        setSize(X_Panel, Y_Panel);
        setLayout(null);
        setBackground(Color.WHITE);

        new Timer(80, this).start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        grid(g);
        spawn(g);
        display(g);
    }

    private void grid(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < life.length; i++){
            g.drawLine(0, i*size, X_Panel, i*size); //row
            g.drawLine(i*size, 0, i*size, Y_Panel); //column
        }
    }

    private void spawn(Graphics g){
        if(starts){
            for(int x = 0; x<life.length; x++){
                for (int y = 0; y<life[x].length; y++){
                    if((int)(Math.random() * 5) == 0){
                        beforeLife[x][y] = 1;
                    }
                }
            }
        }
        starts = false;
    }

    private void display(Graphics g){
        g.setColor(Color.RED);
        copyArray();
        for(int x = 0; x<life.length; x++){
            for (int y = 0; y<life[x].length; y++){
                if(life[x][y] == 1){
                    g.fillRect(x * size, y * size, size, size);}
            }
        }
    }

    private void copyArray(){
        for(int x = 0; x<life.length; x++){
            for (int y = 0; y<life[x].length; y++){
                life[x][y] = beforeLife[x][y];
            }
        }
    }

    private int check(int x, int y){
        int alive = 0;
        alive += life[(x+width-1)%width][(y+height-1)%height];
        alive += life[(x+width)%width][(y+height-1)%height];
        alive += life[(x+width+1)%width][(y+height-1)%height];
        alive += life[(x+width-1)%width][(y+height)%height];
        alive += life[(x+width+1)%width][(y+height)%height];
        alive += life[(x+width-1)%width][(y+height+1)%height];
        alive += life[(x+width)%width][(y+height+1)%height];
        alive += life[(x+width+1)%width][(y+height+1)%height];

        return alive;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int alive;
        for(int x = 0; x<life.length; x++){
            for (int y = 0; y<life[x].length; y++){
                alive = check(x, y);

                if (alive == 3){
                    beforeLife[x][y]=1;

                } else if (alive == 2 && life[x][y] == 1) {
                    beforeLife[x][y]=1;

                }
                else {
                    beforeLife[x][y]=0;
                }
            }
        }
        repaint();
    }
}
