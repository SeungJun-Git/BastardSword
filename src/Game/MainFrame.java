package Game;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;
    private final int MOVEMENT = 0;
    private final int ACTION = 1;
    private final int CHARACTERWAY=2;
    private JLabel background;
    private final Character character;
    private Character character2;
    //캐릭터 테두리 선
    private LineBorder b = new LineBorder(Color.red, 3,true);
    private JLabel testLabel, healthBar;
    public MainFrame() {
        initSettings();
        initBackground();
        initListener();
        initPlayer2();

        character = new Character(this, "Priestess");

        background.add(character.getCds());

        testSettings();
        setVisible(true);
    }

    public void testSettings() {
        character.getCds().setBorder(b);
        testLabel = new JLabel();
        testLabel.setLocation(character.getCds().getX()+350,character.getCds().getY()+260);
        testLabel.setSize(110,140);
        testLabel.setBorder(b);
        background.add(testLabel);

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

    public void initPlayer2() {
        character2 = new Character(this, "Assassin");
        character2.getCds().setX(880);
        character2.getCds().setY(320);

        background.add(character2.getCds());

        healthBar = new JLabel("Player2 health: "+character2.getHealth());
        healthBar.setBorder(b);
        healthBar.setFont(new Font("Serif", Font.BOLD, 30));
        healthBar.setForeground(Color.WHITE);
        healthBar.setSize(300,50);
        healthBar.setLocation(1600,0);

        background.add(healthBar);
    }

    public void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(character.getCds().getStatus()[MOVEMENT]!=CharacterStat.MOVE_LEFT && character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().setStatus(CHARACTERWAY, CharacterStat.WAY_LEFT);
                            character.getCds().moveLeft();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(character.getCds().getStatus()[MOVEMENT]!=CharacterStat.MOVE_RIGHT && character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().setStatus(CHARACTERWAY, CharacterStat.WAY_RIGHT);
                            character.getCds().moveRight();
                        }
                        break;
                    case KeyEvent.VK_Z:
                        if(character.getCds().getStatus()[ACTION]==CharacterStat.NONE) {
                            character.getCds().attack1();
                            isAttack(character,character2);
                        }
                        break;
                    case KeyEvent.VK_X:
                        if(character.getCds().getStatus()[ACTION]==CharacterStat.NONE) {
                            character.getCds().attack2();
                            isAttack(character,character2);
                        }
                        break;
                    case KeyEvent.VK_C:
                        if(character.getCds().getStatus()[ACTION]==CharacterStat.NONE) {
                            character.getCds().attack3();
                            isAttack(character,character2);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.UP && character.getCds().getStatus()[ACTION]!=CharacterStat.DOWN && character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().jumpCharacter();
                        }
                        break;
                    case KeyEvent.VK_CONTROL:
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.ROLL && character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK) {
                            character.getCds().roll();
                        }
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT :
                        if(character.getCds().getStatus()[ACTION]!=CharacterStat.ATTACK)
                            character.getCds().stayMotion();
                        break;
                }
            }

        });
    }

    public void isAttack(Character c1, Character c2) {
        if(Math.abs(c1.getCds().getX() - c2.getCds().getX())<250 && Math.abs(c1.getCds().getY() - c2.getCds().getY())<200) {
            c2.getCds().takeHit();
            c2.setHealth(c2.getHealth()-1);
            healthBar.setText("Player2 health: "+c2.getHealth());
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
