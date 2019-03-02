
import java.awt.Color;
import javax.swing.JFrame;


public class snack {
    public static void main(String [] args){
        JFrame crn = new JFrame();
        Gameplay gameplay = new Gameplay();
   
        crn.setBounds(10,10,905,700);
        crn.setBackground(Color.DARK_GRAY);
        crn.setResizable(false);
        crn.setVisible(true);
        crn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crn.add(gameplay);
         for(int i = 0;i < 850/25;i++){
            System.out.print(25 + ( 25 * i)+",");
        }
    }
    
    
}
