import javax.swing.*;

public class Capstone extends JFrame{
    private JPanel mainPanel;
    private JTextField TESTTextField;
    private JButton button1;
    private JButton button2;

    public Capstone(){
        JFrame frame = new JFrame("Group 8 Capstone");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
