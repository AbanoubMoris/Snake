import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener , ActionListener{
    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];
    
    private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,
        300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,
        725,750,775,800,825,850};
    private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,
        375,400,425,450,475,500,525,550,575,600,625};
    
    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    
            

    
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private int moves = 0;
    
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private int lengthofsnack = 3;
    
    private Timer timer;
    private int delay=100;
    private ImageIcon snackimage;

    private ImageIcon TitleImage;
    
    private int score =0;
    public Gameplay()
    {
        
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        if (moves == 0){
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;
            
            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
            
        }
        
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        //draw the title image
        TitleImage = new ImageIcon(getClass().getResource("src/snaketitle.jpg"));
        TitleImage.paintIcon(this, g, 25, 11);
        
        //draw the border for gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);
        
        //draw the BackHround for the gameplay
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        
        //draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length: "+lengthofsnack, 780, 50);
        
        rightmouth = new ImageIcon(getClass().getResource("src/rightmouth.png"));
        rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
        
        for (int i=0;i<lengthofsnack;i++){
            if(i==0 && right){
                rightmouth = new ImageIcon(getClass().getResource("src/rightmouth.png"));
                rightmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i==0 && left){
                leftmouth = new ImageIcon(getClass().getResource("src/leftmouth.png"));
                leftmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i==0 && down){
                downmouth = new ImageIcon(getClass().getResource("src/downmouth.png"));
                downmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if(i==0 && up){
                upmouth = new ImageIcon(getClass().getResource("src/upmouth.png"));
                upmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i!=0){
                snackimage = new ImageIcon(getClass().getResource("src/snakeimage.png"));
                snackimage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
        }
        enemyimage = new ImageIcon(getClass().getResource("src/enemy.png"));
        if (enemyxpos[xpos] == snakeXlength[0] && enemyypos[ypos] == snakeYlength[0]){
            lengthofsnack++;
            score++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        for (int b=1;b<lengthofsnack;b++)
        {
            if(snakeXlength[b]==snakeXlength[0] && snakeYlength[b]==snakeYlength[0])
            {
                right=left=up=down=false;
                g.setColor(Color.red);
                g.setFont(new Font("arial" ,Font.BOLD ,50));
                g.drawString("Game Over", 300, 300);
                g.setColor(Color.white);
                g.setFont(new Font("arial" ,Font.BOLD ,20));
                g.drawString("Space to RESTART..", 350, 340);
            }
        }
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_SPACE){
            moves=0;
            score=0;
            lengthofsnack=3;
            repaint();
        }

        if (e.getKeyCode()== KeyEvent.VK_RIGHT){
            moves++;
            right=true;
            if (!left) right=true;
            else {
                right = false;
                left = true;
            }
            left = up = down = false;
        }
        if (e.getKeyCode()== KeyEvent.VK_LEFT){
            moves++;
            left=true;
            if (!right) left=true;
            else {
                left = false;
                right = true;
            }
            up = down = false;
        }
        if (e.getKeyCode()== KeyEvent.VK_UP){
            moves++;
            up=true;
            if (!down) up=true;
            else {
                up = false;
                down = true;
            }
            right = left = false;
        }
        if (e.getKeyCode()== KeyEvent.VK_DOWN){
            moves++;
            down=true;
            if (!up) down=true;
            else {
                down = false;
                up = true;
            }
            right = left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right)
        {
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                snakeYlength[r+1] = snakeYlength[r];
            }
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                if (r==0){
                    snakeXlength[r] = snakeXlength[r] + 25;
                }
                else {
                    snakeXlength[r] = snakeXlength[r-1];
                }
                if (snakeXlength[r]>850){
                    snakeXlength[r] = 25;
                }
               
                
            }
             repaint();
        }
        if (left)
        {
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                snakeYlength[r+1] = snakeYlength[r];
            }
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                if (r==0){
                    snakeXlength[r] = snakeXlength[r] - 25;
                }
                else {
                    snakeXlength[r] = snakeXlength[r-1];
                }
                if (snakeXlength[r]<25)
                {
                    snakeXlength[r] = 850;
                }  
            }
            repaint();
            
        }
        if (up)
        {
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                snakeXlength[r+1] = snakeXlength[r];
            }
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                if (r==0){
                    snakeYlength[r] = snakeYlength[r] - 25;
                }
                else {
                    snakeYlength[r] = snakeYlength[r-1];
                }
                if (snakeYlength[r] < 75){
                    snakeYlength[r] = 625;
                }
                
                
            }
            repaint();
            
        }
        if (down)
        {
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                snakeXlength[r+1] = snakeXlength[r];
            }
            for (int r = lengthofsnack-1 ; r>=0 ; r--){
                if (r==0){
                    snakeYlength[r] = snakeYlength[r] + 25;
                }
                else {
                    snakeYlength[r] = snakeYlength[r-1];
                }
                if (snakeYlength[r]> 625){
                    snakeYlength[r] = 75;
                }

                
            }
            repaint();
        }
}
}
