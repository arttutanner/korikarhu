import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KoriKarhuMain {



    public static void main(String[] args) {
        KoriKarhuFrame kkf = new KoriKarhuFrame();
        kkf.reset();
        kkf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if (e.getKeyChar()=='s') kkf.startGame();
                if (e.getKeyChar()=='b') kkf.basketSignal();
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
    }

}
