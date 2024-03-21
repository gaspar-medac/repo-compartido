package controllers;

import java.awt.*;

public class FontBuilder {
    private static final String FONT_PATH = "./src/assets/fonts/";

    public static Font getFont(String name) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(FONT_PATH + name)).deriveFont(12f);
            ge.registerFont(font);
            return font;
        } catch (FontFormatException | java.io.IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.BOLD, 12);
        }
    }
}
