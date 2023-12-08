import javax.swing.*;

public class BattleSequence {
    private JTextArea textArea1;
    private JPanel battleSeqPanel;
    private final JFrame frame;

    public BattleSequence(){
        frame =new JFrame("Battle Sequence");
        frame.setContentPane(this.battleSeqPanel);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setSize(500,250);
        frame.setLocation(750,250);
        frame.setVisible(true);
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }
    public void removeFrame(){
        frame.dispose();
    }
}
