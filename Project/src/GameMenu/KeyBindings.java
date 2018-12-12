package GameMenu;


import java.awt.*;


public class KeyBindings extends BasicOptionLayout {
    public KeyBindings()
    {
        super();
        lText.setFont(new Font("SansSerif",Font.PLAIN,20));
        lText.setText("<html>Key bindings: <br />" +
                "Fire: <b>X or Space</b><br />"+
                "Description: <b>D</b><br />"+
                "Turn Off Sound: <b>M</b><br />"+
                "</html>");

    }
}
