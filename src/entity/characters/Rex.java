package entity.characters;

import entity.race.compy;
import main.GamePanel;

public class Rex extends compy {

    public Rex(GamePanel gp) {
        super(gp);

        setName("Rex");

        exp = (58*2)*2;
        checkLevelUp();

    }


}
