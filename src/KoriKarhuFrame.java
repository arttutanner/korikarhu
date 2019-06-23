import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

public class KoriKarhuFrame extends JFrame {

    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel infoLabel;
    private int score;
    private int time;
    private String infoText1;
    private String infoText2;
    private Timer timer;

    public KoriKarhuFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setBackground(Color.WHITE);
        Container cont = this.getContentPane();
        cont.setBackground(Color.WHITE);
        cont.setLayout(new BorderLayout());
        JPanel mainPane = new JPanel();
        mainPane.setBackground(Color.WHITE);
        mainPane.setLayout(new MigLayout("fillx"));
        mainPane.add(Box.createVerticalStrut(20),"wrap");
        mainPane.add(new JLabel(getTitleImage()),"alignx center");

        JPanel scorePanel = new JPanel(new MigLayout("al center"));
        scorePanel.add(new JLabel(new ImageIcon("bb_white.png")));
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,400));
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(Box.createHorizontalStrut(70));
        scorePanel.add(scoreLabel);
        scorePanel.setBackground(Color.decode("#b94100"));
        mainPane.add(scorePanel,"pos 0px 450px 50% 80%");

        JPanel timePanel = new JPanel(new MigLayout("al center"));
        timePanel.add(new JLabel(new ImageIcon("time_white.png")));
        timeLabel = new JLabel("60");
        timeLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,400));
        timeLabel.setForeground(Color.WHITE);
        timePanel.add(Box.createHorizontalStrut(70));
        timePanel.add(timeLabel);
        timePanel.setBackground(Color.decode("#002cb9"));
        mainPane.add(timePanel,"pos 50% 450px 100% 80%");



        cont.add(mainPane,BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new MigLayout("fillx"));
        infoLabel = new JLabel("UUSI PELI -> PAINA START!");
        infoLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD+Font.ITALIC,80));
        infoLabel.setForeground(Color.BLACK);
        infoPanel.add(infoLabel,"alignx center wrap");
        cont.add(infoPanel,BorderLayout.SOUTH);

        this.setVisible(true);
    }


    private ImageIcon getTitleImage() {
        ImageIcon imageIcon = new ImageIcon("kktitle.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(1200, 400,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }

    public void reset() {
        infoLabel.setVisible(false);
        time = 60;
        score = 0;
        startCount();
    }

    public void startCount() {
        if (timer==null) timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerTick();
            }
        });
        timer.start();
    }

    private void timerTick() {
        time --;
        refresh();
        if (time == 0) {
            endGame();
        }
    }

    public void endGame() {

    }

    public void refresh() {
        scoreLabel.setText(score+"");
        timeLabel.setText(time+"");
    }
}
