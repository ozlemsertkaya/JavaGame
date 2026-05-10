import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
         

            static final int SCREEN_WIDTH=600;
            static final int SCREEN_HEIGHT=600;
            static final int UNIT_SIZE=25;
            char direction='R';
            Timer timer;
            boolean running = false;
            int applesEaten=0;

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
            this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
            this.setBackground(Color.black);
            this.setFocusable(true);
            this.requestFocusInWindow();
            this.addKeyListener(new MyKeyAdapter());
            startGame();
                
             }

              public void newApple(){
                Random random = new Random();
                appleX= random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
                appleY= random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
            }

             public void startGame(){
                newApple();
                 for(int i=0; i<bodyParts;i++){
                    x[i]=100-(i*UNIT_SIZE);
                    y[i]=100;
                 }
                   running=true;
                   timer=new Timer(150,this);
                   timer.start();
             }

            @Override
             public void paintComponent(Graphics g){
                super.paintComponent(g);
                draw(g);
             }
             public void checkApple(){
                if((x[0]==appleX) && (y[0]==appleY)){
                    bodyParts++;
                    applesEaten++;
                    newApple();
                }
             }
             public void checkCollisions(){
                //Kendi kendine çarpma
                for(int i=bodyParts;i>0;i--){
                    if((x[0]==x[i]) && (y[0]==y[i])){
                        timer.stop();
                    }
                }
                //Duvara çarpma
                if(x[0]<0){
                    running=false;
                }
                if(x[0]>=SCREEN_WIDTH){
                    running=false;
                }
                if(y[0]<0){
                    running=false;
                }
                if(y[0]>=SCREEN_HEIGHT){
                    running=false;
                }

                if(!running){
                    timer.stop();
                }
             }
             public void gameOver(Graphics g){
                g.setColor(Color.black);
                g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
              
                //Game Over yazısı
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free",Font.BOLD,75));
                FontMetrics metrics1 = getFontMetrics(g.getFont());
                g.drawString("Game Over",(SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2,SCREEN_WIDTH/2);
             }


             public void draw(Graphics g){
                //Elmanın rengini boyutunu ve konumunu belirledik
                if(running){
                    g.setColor(new Color(40,40,40));
                for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                    g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
                }
            
                 g.setColor(Color.red);
                 g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

                 for(int i=0;i<bodyParts;i++){
                   if(i==0){
                    g.setColor(new Color(0,150,0)); //Yılanın rengini belirledik ve baş kısmını ayırdık
                    g.fillRoundRect(x[i]+2, y[i]+2, UNIT_SIZE-4, UNIT_SIZE-4, 10, 10);
                    g.setColor(Color.white);
                    g.fillOval(x[i]+6, y[i]+6, 4, 4);
                    g.fillOval(x[i]+15, y[i]+6, 4, 4);
                   }else{
                    g.setColor(new Color(45,180,0));
                    g.fillRoundRect(x[i]+3, y[i]+3, UNIT_SIZE-6, UNIT_SIZE-6, 8, 8);
                   }
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free", Font.BOLD,40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
                 }
                 g.setColor(new Color(0,0,0,150));
                 g.fillRect(10,10,100,40);
                 g.setColor(Color.white);
                 g.drawRect(10,10,100,40);
                 g.setFont(new Font("Ink Free", Font.BOLD,20));
                 g.drawString("Score: "+applesEaten,20,37);



                }else{
                    gameOver(g);
                }

             }
             

@Override
public void actionPerformed(ActionEvent e) {
    if(running){
        move();
        checkApple();
        checkCollisions();
       
        
}
   repaint(); 

}


public class MyKeyAdapter extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(direction!='R'){
                    direction='L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(direction!='L'){
                    direction='R';
                }
                break;
            case KeyEvent.VK_UP:
                if(direction!='D'){
                    direction='U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if(direction!='U'){
                    direction='D';
                }
                break;
        }
    }
}
}