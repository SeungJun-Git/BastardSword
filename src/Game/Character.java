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

    private CharacterDetailSetting cds;
    private MainFrame frame;

    public Character(MainFrame frame, String selected) {
        this.frame = frame;

        if(selected=="Warrior")
            warrior();
        else if(selected=="Mage")
            mage();
        else if(selected=="Assassin")
            assassin();
    }
    @Override
    public void warrior() {
        cds = new CharacterDetailSetting(frame, "Warrior",CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }

    @Override
    public void mage() {
        cds = new CharacterDetailSetting(frame, "Mage",CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }

    @Override
    public void assassin() {
        cds = new CharacterDetailSetting(frame, "Assassin",CHARACTER_WIDTH, CHARACTER_HEIGHT);
    }
}
