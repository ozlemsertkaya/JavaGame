import javax.swing.JFrame;

public class Game extends JFrame {


 public Game(){
    this.add(new GamePanel());
    this.setTitle("Snake Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
 }

    public static void main(String[] args) {
        new Game();
    }
}