import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameWindow extends JFrame {
    private final Random rand = new Random();
    private int presentDoor, lives;
    GameWindow() {
        this.setTitle("Treasure Hunt");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        cnt = this.getContentPane();
        cnt.setLayout(null);
        cnt.setBackground(new Color(255, 255, 255));
    }
    class ActionListenerManager implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == door[0]) {
                vOrL(0);
            } else if (e.getSource() == door[1]) {
                vOrL(1);
            } else if (e.getSource() == door[2]) {
                vOrL(2);
            } else if (e.getSource() == startAgain) {
                label.setText("Please choose a door!");
                lives = 3;
                liveLevel.setText("Lives left: " + lives);
                door[0].setEnabled(true);
                door[1].setEnabled(true);
                door[2].setEnabled(true);
                door[0].setBackground(new Color(3, 78, 116));
                door[1].setBackground(new Color(3, 78, 116));
                door[2].setBackground(new Color(3, 78, 116));
                label.setForeground(new Color(0, 151, 91));
                cnt.setBackground(new Color(255, 255, 255));
                doorPanel.setBackground(new Color(255, 255, 255));
            } else if (e.getSource() == exitProgram) {
                System.exit(0);
            }
        }
        private void vOrL(int x) {
            if (presentDoor == x) {
                label.setText("You are victorious!");
                door[0].setEnabled(false);
                door[1].setEnabled(false);
                door[2].setEnabled(false);
                label.setForeground(new Color(255, 255, 255));
                cnt.setBackground(new Color(0, 255, 111));
                doorPanel.setBackground(new Color(0, 255, 111));
            } else {
                lives--;
                liveLevel.setText("Lives left: " + lives);
                if (lives == 2) label.setText("Oops! Wrong guess...");
                else if (lives == 1) label.setText("Wrong guess again...");
                label.setForeground(new Color(250, 71, 77));
                if (lives == 0) {
                    label.setText("You Lost!");
                    door[0].setEnabled(false);
                    door[1].setEnabled(false);
                    door[2].setEnabled(false);
                    label.setForeground(new Color(255, 255, 255));
                    cnt.setBackground(new Color(255, 132, 134));
                    doorPanel.setBackground(new Color(255, 132, 134));
                }
            }
            presentDoor = rand.nextInt(0, 3);
        }
    }
    public void launch() {
        presentDoor = rand.nextInt(0, 3);
        lives = 3;

        label = new JLabel();
        label.setText("Please choose a door!");
        label.setBounds(20, 0, 380, 60);
        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        label.setForeground(new Color(0, 151, 91));
        cnt.add(label);

        liveLevel = new JLabel();
        liveLevel.setText("Lives left: " + lives);
        liveLevel.setBounds(380, 0, 186, 60);
        liveLevel.setHorizontalAlignment(SwingConstants.RIGHT);
        liveLevel.setVerticalAlignment(SwingConstants.TOP);
        liveLevel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
        liveLevel.setForeground(new Color(199, 0, 24));
        cnt.add(liveLevel);

        doorPanel = new JPanel();
        doorPanel.setBounds(20, 60, 545, 230);
        doorPanel.setLayout(new GridLayout(1, 3, 20, 0));
        doorPanel.setBackground(new Color(255, 255, 255));
        cnt.add(doorPanel);

        door[0] = new JButton();
        door[0].setText("Door #1");
        door[0].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        door[0].setForeground(new Color(255, 255, 255));
        door[0].setBackground(new Color(3, 78, 116));
        door[0].setBorderPainted(false);
        doorPanel.add(door[0]);

        door[1] = new JButton();
        door[1].setText("Door #2");
        door[1].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        door[1].setForeground(new Color(255, 255, 255));
        door[1].setBackground(new Color(3, 78, 116));
        door[1].setBorderPainted(false);
        doorPanel.add(door[1]);

        door[2] = new JButton();
        door[2].setText("Door #3");
        door[2].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        door[2].setForeground(new Color(255, 255, 255));
        door[2].setBackground(new Color(3, 78, 116));
        door[2].setBorderPainted(false);
        doorPanel.add(door[2]);

        startAgain = new JButton();
        startAgain.setText("Start Again");
        startAgain.setBounds(20, 305, 265, 40);
        startAgain.setForeground(new Color(255, 255, 255));
        startAgain.setBackground(new Color(0, 181, 220));
        startAgain.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
        cnt.add(startAgain);

        exitProgram = new JButton();
        exitProgram.setText("Exit Program");
        exitProgram.setBounds(300, 305, 265, 40);
        exitProgram.setForeground(new Color(255, 255, 255));
        exitProgram.setBackground(new Color(250, 71, 77));
        exitProgram.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
        cnt.add(exitProgram);

        door[0].addActionListener(new ActionListenerManager());
        door[1].addActionListener(new ActionListenerManager());
        door[2].addActionListener(new ActionListenerManager());
        startAgain.addActionListener(new ActionListenerManager());
        exitProgram.addActionListener(new ActionListenerManager());
    }
    private final Container cnt;
    private JLabel label, liveLevel;
    private JPanel doorPanel;
    private final JButton[] door = new JButton[3];
    private JButton startAgain, exitProgram;
}
