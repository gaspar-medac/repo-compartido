package controllers;

import ui.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends JFrame implements KeyListener {
    private JPanel GamePanel;
    private JLabel movableBox;
    private final boolean[] keyStates = new boolean[256];
    private final List<JLabel> collisionLabels = new ArrayList<>();
    private final List<JLabel> invisibleLabels = new ArrayList<>();

    public GameplayScreen() {
        add(GamePanel);
        setTitle("MEDAC Stars - Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setFocusable(true);
        addKeyListener(this);
        setVisible(true);
        setResizable(false);

        ImageIcon logo = new ImageIcon("./src/assets/images/logo.png");
        setIconImage(logo.getImage());

        addMovableBox();
        addCollisionLabels();
        addInvisibleLabels();
        GamePanel.repaint();
        new Timer(20, e -> update()).start();
    }

    public static void main(String[] args) {
        new GameplayScreen();
    }

    private void addMovableBox() {
        movableBox = new JLabel();
        movableBox.setOpaque(true);
        movableBox.setBackground(Color.BLUE);
        movableBox.setBounds(100, 100, 10, 10);
        GamePanel.setLayout(null);
        GamePanel.add(movableBox);
    }

    private void addCollisionLabels() {
        addCollisionLabel(90, 525, 25, 50);
        addCollisionLabel(115, 550, 25, 25);

        // Third big row (Center)
        addCollisionLabel(35, 315, 10, 30);

        addCollisionLabel(92, 295, 20, 70);
        addCollisionLabel(115, 300, 20, 65);

        addCollisionLabel(160, 315, 10, 30);

        addCollisionLabel(195, 300, 20, 65);
        addCollisionLabel(218, 295, 10, 70);

        addCollisionLabel(275, 315, 10, 30);
        addCollisionLabel(400, 315, 10, 30);

        addCollisionLabel(458, 295, 10, 70);
        addCollisionLabel(470, 300, 20, 65);

        addCollisionLabel(515, 315, 10, 30);

        addCollisionLabel(550, 300, 20, 65);
        addCollisionLabel(573, 295, 20, 70);

        addCollisionLabel(640, 315, 10, 30);
    }

    private void addCollisionLabel(int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
        label.setBounds(x, y, width, height);
        GamePanel.add(label);
        collisionLabels.add(label);
    }

    private void addInvisibleLabels() {
        // Edges
        addInvisibleLabel(0, 0, 700, 20);
        addInvisibleLabel(0, 0, 20, 700);
        addInvisibleLabel(0, 640, 700, 20);
        addInvisibleLabel(665, 0, 20, 700);

        addInvisibleLabel(115, 530, 10, 10);
        addInvisibleLabel(115, 540, 10, 10);
        addInvisibleLabel(125, 540, 10, 10);

        addInvisibleLabel(262, 484, 35, 35);
        addInvisibleLabel(388, 484, 35, 35);

        // Third big row (Center)
        addInvisibleLabel(235, 305, 15, 50);
        addInvisibleLabel(228, 295, 10, 70);

        addInvisibleLabel(320, 295, 45, 70);
        addInvisibleLabel(310, 305, 65, 50);

        addInvisibleLabel(435, 305, 15, 50);
        addInvisibleLabel(447, 295, 10, 70);
    }

    private void addInvisibleLabel(int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.RED);
        label.setBounds(x, y, width, height);
        GamePanel.add(label);
        invisibleLabels.add(label);
    }

    private void update() {
        if (keyStates[KeyEvent.VK_UP] || keyStates[KeyEvent.VK_DOWN] || keyStates[KeyEvent.VK_LEFT] || keyStates[KeyEvent.VK_RIGHT]) {

            Point position = movableBox.getLocation();
            int MOVE_INCREMENT = 2;
            int newMoveX = position.x;
            int newMoveY = position.y;

            if (keyStates[KeyEvent.VK_UP]) {
                newMoveY = Math.max(0, newMoveY - MOVE_INCREMENT);
            }
            if (keyStates[KeyEvent.VK_DOWN]) {
                newMoveY = Math.min(GamePanel.getHeight() - movableBox.getHeight(), newMoveY + MOVE_INCREMENT);
            }
            if (keyStates[KeyEvent.VK_LEFT]) {
                newMoveX = Math.max(0, newMoveX - MOVE_INCREMENT);
            }
            if (keyStates[KeyEvent.VK_RIGHT]) {
                newMoveX = Math.min(GamePanel.getWidth() - movableBox.getWidth(), newMoveX + MOVE_INCREMENT);
            }

            Rectangle boxBounds = new Rectangle(newMoveX, newMoveY, movableBox.getWidth(), movableBox.getHeight());

            boolean intersectsInvisibleLabel = false;
            for (JLabel label : invisibleLabels) {
                if (boxBounds.intersects(label.getBounds())) {
                    intersectsInvisibleLabel = true;
                    break;
                }
            }

            movableBox.setVisible(!intersectsInvisibleLabel);

            for (JLabel label : collisionLabels) {
                if (boxBounds.intersects(label.getBounds())) {
                    return;
                }
            }
            movableBox.setLocation(newMoveX, newMoveY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keyStates.length) keyStates[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keyStates.length) keyStates[e.getKeyCode()] = false;
    }

    private void createUIComponents() {
        // GamePanel initialization with an image background
        GamePanel = new JPanel();
        GamePanel = new ImagePanel("gameplayArena.jpg");
    }
}
