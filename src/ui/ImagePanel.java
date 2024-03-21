package ui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private static final String IMAGE_PATH = "./src/assets/images/";
    private Image backgroundImage;

    public ImagePanel(String fileName) {
        setOpaque(false);
        try {
            backgroundImage = new ImageIcon(IMAGE_PATH + fileName).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
