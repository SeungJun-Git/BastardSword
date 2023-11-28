package Game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Character implements CharacterClass{
    //캐릭터 사이즈 설정
    private static final int CHARACTER_WIDTH = 800;
    private static final int CHARACTER_HEIGHT = CHARACTER_WIDTH/2;

    private int health;

    private CharacterDetailSetting cds;
    private MainFrame frame;

    public Character(MainFrame frame, String selected) {
        this.frame = frame;
        health = 10;

        if(selected=="Warrior")
            warrior();
        else if(selected=="Priestess")
            priestess();
        else if(selected=="Assassin")
            assassin();
    }
    @Override
    public void warrior() {
        cds = new CharacterDetailSetting(frame, "Warrior",CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }

    @Override
    public void priestess() {
        cds = new CharacterDetailSetting(frame, "Priestess",CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }

    @Override
    public void assassin() {
        cds = new CharacterDetailSetting(frame, "Assassin",CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }
}
