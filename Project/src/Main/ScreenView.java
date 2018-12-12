package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class ScreenView extends JFrame {

    public static boolean listenersCreated;
    public ScreenView()
    {
        listenersCreated=false;

        JFrame Screen=Game.Screen;


        ListenForWindow lForWindow = new ListenForWindow();
        Screen.addWindowListener(lForWindow);


        Screen.setSize(1024, 768);
        Screen.setMinimumSize(new Dimension(1024, 768));

        Screen.setTitle("Bubble Invasion");
        Screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen.setLocationRelativeTo(null);
        Screen.setFocusable(true);
        Screen.pack();

    }

    private class ListenForWindow implements WindowListener
    {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            //setExtendedState(JFrame.MAXIMIZED_BOTH);

        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

    }

}
