package GameMenu;

import java.awt.*;

public class Credits extends BasicOptionLayout {
    public Credits()
    {
        super();
        lText.setFont(new Font("SansSerif",Font.PLAIN,20));
        lText.setText("<html>Game created by: Michał Ziółek<br /><br />" +
                "You can read more about the authors of main textures used in this game, by seeing img and sound folder.<br />"+
                "There are also links to the websites, from which you can get these textures and sounds."+
                "</html>");
    }
}
