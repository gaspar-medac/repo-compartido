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
    private boolean[] keyStates = new boolean[256];
    private List<JLabel> collisionLabels = new ArrayList<>();
    private List<JLabel> invisibleLabels = new ArrayList<>();

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
        if (keyStates[KeyEvent.VK_UP] || keyStates[KeyEvent.VK_DOWN] ||
                keyStates[KeyEvent.VK_LEFT] || keyStates[KeyEvent.VK_RIGHT]) {

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
        keyStates[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyStates[e.getKeyCode()] = false;
    }

    public static void main(String[] args) {
        new GameplayScreen();
    }

    private void createUIComponents() {
        // GamePanel initialization with an image background
        GamePanel = new JPanel();
        GamePanel = new ImagePanel("gameplayArena.jpg");
    }
}
