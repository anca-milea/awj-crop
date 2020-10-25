package packageTranslate;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.io.File;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class DisplayImage extends JFrame {

    JFrame parent;
    QuickDrawPanel quickDrawPanel;

    public DisplayImage(File file) {
        super("View Pictures");
        setSize(650, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        parent = this;
        quickDrawPanel = new QuickDrawPanel();

        Container c = getContentPane();
        loadImage(file);

        JPanel north = new JPanel();

        north.setBackground(Color.GRAY);
        north.setForeground(Color.BLUE);
        c.add(north, "First");
        c.add(new JScrollPane(quickDrawPanel), "Center");
    }

    private void loadImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("read error: " + e.getMessage());
        }
        quickDrawPanel.setImage(image);
    }

    public static void main(String args[]) {
        System.setProperty("swing.defaultlaf",
                "javax.swing.plaf.metal.MetalLookAndFeel");

    }
}

class QuickDrawPanel extends JPanel {

    BufferedImage image;
    Dimension size = new Dimension();

    public QuickDrawPanel() {
    }

    public QuickDrawPanel(BufferedImage image) {
        this.image = image;
        setComponentSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(BufferedImage bi) {
        image = bi;
        setComponentSize();
        repaint();
    }

    private void setComponentSize() {
        if (image != null) {
            size.width = image.getWidth();
            size.height = image.getHeight();
            revalidate();  // signal parent/scrollpane
        }
    }
}
