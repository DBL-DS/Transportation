package main;

import ui.MainForm;

/**
 * Created by Hugh on 2015/2/25 0025.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        MainFrame mainFrame = new MainFrame();
        mainFrame.showFrame();
    }
}
