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
    private long lastBasket;

    private boolean isGameActive;


    public KoriKarhuFrame() {
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1280,1024);

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
        scorePanel.add(new JLabel(getScaledImageIcon("bb_white.png",200,200)));
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,300));
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(Box.createHorizontalStrut(70));
        scorePanel.add(scoreLabel);
        scorePanel.setBackground(Color.decode("#b94100"));
        mainPane.add(scorePanel,"pos 0px 450px 50% 80%");

        JPanel timePanel = new JPanel(new MigLayout("al center"));
        timePanel.add(new JLabel(getScaledImageIcon("time_white.png",174,200)));
        timeLabel = new JLabel("60");
        timeLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,300));
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
        infoPanel.setBackground(Color.WHITE);
        cont.add(infoPanel,BorderLayout.SOUTH);
        this.setVisible(true);

        new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        }).start();
    }

    private ImageIcon getScaledImageIcon(String name, int w, int h) {
        ImageIcon imageIcon = new ImageIcon(name); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }

    private ImageIcon getTitleImage() {
        ImageIcon imageIcon = new ImageIcon("kktitle.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(1200, 400,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }

    public void reset() {
        infoLabel.setVisible(true);
        time = 60;
        score = 0;
        isGameActive = false;
        infoText1="UUSI PELI -> PAINA START!";
        infoText2="AIKA LOPPUI!";
        lastBasket =0;
        refresh();
    }

    public void startGame() {
        reset();
        infoText1="";
        infoText2="";
        startCount();
        isGameActive = true;
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
        infoText1="UUSI PELI -> PAINA START!";
        infoText2="AIKA LOPPUI!";
        isGameActive = false;
        timer.stop();
    }

    public void startPressed() {
        if (!isGameActive) startGame();
    }

    public void basketSignal() {

        if (!isGameActive) return;

        // Not possible to score more often than every 2 secs
        System.out.println(System.currentTimeMillis() - lastBasket);
        if (System.currentTimeMillis() - lastBasket > 2000) {
            score ++;
            lastBasket=System.currentTimeMillis();
        }
    }

    public void refresh() {
        scoreLabel.setText(score+"");
        timeLabel.setText(time+"");
        if (((int)(System.currentTimeMillis()/1000)) % 2 == 0)
            infoLabel.setText(infoText1);
        else
            infoLabel.setText(infoText2);
    }
}
