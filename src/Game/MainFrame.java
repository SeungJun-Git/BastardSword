package Game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;
    private final int MOVEMENT = 0;
    private final int ACTION = 1;
    private JLabel background;
    private Character character;
    public MainFrame() {
        initSettings();
        initBackground();
        initListener();

        character = new Character(this, "Warrior");

        background.add(character.getCds());

        setVisible(true);
    }

    public void initSettings() {
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initBackground() {
        ImageControl control = new ImageControl(1,WINDOW_WIDTH,WINDOW_HEIGHT);
        background = new JLabel();
        background.setIcon(control.getIcon()[0]);
        background.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        add(background);
    }

    public void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(character.getCds().getStatus()[MOVEMENT]!=CharacterStat.MOVE_LEFT) {
                            character.getCds().getStatus()[MOVEMENT]=CharacterStat.MOVE_LEFT;
                            character.getCds().moveLeft();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(character.getCds().getStatus()[MOVEMENT]!=CharacterStat.MOVE_RIGHT) {
                            character.getCds().getStatus()[MOVEMENT]=CharacterStat.MOVE_RIGHT;
                            character.getCds().moveRight();
                        }
                        break;
                    case KeyEvent.VK_Z:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().getStatus()[ACTION]=CharacterStat.ATTACK;
                            character.getCds().attack1();
                        }
                        break;
                    case KeyEvent.VK_X:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().getStatus()[ACTION]=CharacterStat.ATTACK;
                            character.getCds().attack2();
                        }
                        break;
                    case KeyEvent.VK_C:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().getStatus()[ACTION]=CharacterStat.ATTACK;
                            character.getCds().attack3();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.UP&&character.getCds().getStatus()[ACTION]!=CharacterStat.DOWN) {
                            character.getCds().getStatus()[ACTION]=CharacterStat.UP;
                            character.getCds().jumpCharacter();
                        }
                        break;
                    case KeyEvent.VK_CONTROL:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.ROLL) {
                            character.getCds().getStatus()[ACTION]=CharacterStat.ROLL;
                            character.getCds().down();
                        }
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        character.getCds().getStatus()[MOVEMENT]=CharacterStat.STAY;
                        character.getCds().stayMotion();
                        break;
                }
            }

        });
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
