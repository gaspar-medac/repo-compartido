package controllers;

import ui.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JPanel OptionPanel;
    private JLabel TitleLabel;
    private JButton verMEDACStarsButton;
    private JButton jugarButton;
    private JButton chatButton;
    private JButton salirButton;
    private JButton logoutButton;

    public MainScreen() {
        add(OptionPanel);
        setTitle("MEDAC Stars - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        ImageIcon logo = new ImageIcon("./src/assets/images/logo.png");
        setIconImage(logo.getImage());

        Font montserratBold = FontBuilder.getFont("Montserrat-Bold.ttf");
        TitleLabel.setFont(montserratBold.deriveFont(14f));

        Font montserratSemiBold = FontBuilder.getFont("Montserrat-SemiBold.ttf");
        verMEDACStarsButton.setFont(montserratSemiBold.deriveFont(12f));
        jugarButton.setFont(montserratSemiBold.deriveFont(12f));
        chatButton.setFont(montserratSemiBold.deriveFont(12f));
        salirButton.setFont(montserratSemiBold.deriveFont(12f));
        logoutButton.setFont(montserratSemiBold.deriveFont(12f));
    }


    public static void main(String[] args) {
        new MainScreen();
    }

    private void createUIComponents() {
        OptionPanel = new JPanel();
        OptionPanel = new ImagePanel("background.jpg");
    }
}