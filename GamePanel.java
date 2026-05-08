import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

            static final int SCREEN_WIDTH=600;
            static final int SCRENN_HEIGHT=600;
            static final int UNIT_SIZE=25;
            char direction='R';
            Timer timer;
            

            int[] x= new int[600];
            int[] y= new int[600];
            int bodyParts=3;
            int appleX;
            int appleY;

            public void move(){
                for(int i=bodyParts;i>0;i--){
                    x[i]=x[i-1];
                    y[i]=y[i-1];
                }

                switch(direction){
                    case 'U':
                    y[0]=y[0]-UNIT_SIZE;
                    break;
                    case 'D':
                    y[0]=y[0]+UNIT_SIZE;
                    break;
                    case 'L':
                    x[0]=x[0]-UNIT_SIZE;
                    break;
                    case 'R':
                    x[0]=x[0]+UNIT_SIZE;
                    break;
            }
        }
           
       public GamePanel(){
            this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCRENN_HEIGHT));
            this.setBackground(Color.black);
            this.setFocusable(true);
            startGame();
                
             }

              public void newApple(){
                Random random = new Random();
                appleX= random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
                appleY= random.nextInt(SCRENN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
            }

             public void startGame(){
                   timer=new Timer(75,this);
                   timer.start();
             }

            @Override
             public void paintComponent(Graphics g){
                super.paintComponent(g);
                draw(g);
             }

             public void draw(Graphics g){
                //Elmanın rengini boyutunu ve konumunu belirledik
                 g.setColor(Color.red);
                 g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

                 for(int i=0;i<bodyParts;i++){
                   if(i==0){
                    g.setColor(Color.green); //Yılanın rengini belirledik ve baş kısmını ayırdık
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                   }else{
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                   }
                 }

                 


             }
             





@Override
public void actionPerformed(ActionEvent e) {
    move();
    repaint(); 
}

}