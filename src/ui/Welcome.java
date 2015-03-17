package ui;

import javax.swing.*;

/**
 * Created by Hugh on 2015/2/22 0022.
 */
public class Welcome {
    private JPanel welcomePanel;

    public JPanel getWelcomePanel() {
        return welcomePanel;
    }

    public void setBackground(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabel label = new JLabel(imageIcon);
        welcomePanel.add(label);
    }
}
