package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.MVCController;

class Gui {
  static MVCController controller = new MVCController();

  public static void main(String args[]) {

    // Creating the Frame
    JFrame frame = new JFrame("Image Utilities");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Creating the MenuBar and adding components
    JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("FILE");
    JMenu operator = new JMenu("Operator");
    JMenu generator = new JMenu("Generator");
    mb.add(m1);
    mb.add(operator);
    mb.add(generator);
    JMenuItem open = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save as");
    m1.add(open);
    m1.add(save);

    JLabel originalImage = new JLabel();
    JLabel modifiedImage = new JLabel();

    JScrollPane scroller1 = new JScrollPane(originalImage);
    scroller1.setPreferredSize(new Dimension(400, 400));
    scroller1.setAutoscrolls(true);

    JScrollPane scroller2 = new JScrollPane(modifiedImage);
    scroller2.setPreferredSize(new Dimension(400, 400));
    scroller2.setAutoscrolls(true);

    // Creating the panel at bottom and adding components

    JPanel panel = new JPanel();
    JMenuItem dither = new JMenuItem("Dither");
    JMenuItem edgeDetection = new JMenuItem("Edge Detection");
    JMenuItem mosaic = new JMenuItem("Mosaic");
    JMenuItem greyscale = new JMenuItem("Grayscale");
    JMenuItem grayscaleContrastEnhancement = new JMenuItem("Grayscale Contrast Enhancement");
    JMenuItem redEye = new JMenuItem("Red Eye Removel");
    JMenuItem sepia = new JMenuItem("Sepia Mode");
    JMenuItem blur = new JMenuItem("Blur");
    JMenuItem sharpen = new JMenuItem("Sharpen");

    JMenuItem checkBoard = new JMenuItem("Checker Board");
    JMenuItem rainbow = new JMenuItem("Rainbow");
    JMenuItem flagFrance = new JMenuItem("flag of France");
    JMenuItem flagSwiterland = new JMenuItem("flag of Switerland");
    JMenuItem flagNorway = new JMenuItem("flag of Norway");
    JMenuItem flagGreece = new JMenuItem("flag of Greece");
    JMenuItem clear = new JMenuItem("clear filter");

    operator.add(dither);
    operator.add(edgeDetection);
    operator.add(mosaic);
    operator.add(greyscale);
    operator.add(grayscaleContrastEnhancement);
    operator.add(redEye);
    operator.add(sepia);
    operator.add(blur);
    operator.add(sharpen);
    generator.add(checkBoard);
    generator.add(rainbow);
    generator.add(flagFrance);
    generator.add(flagSwiterland);
    generator.add(flagNorway);
    generator.add(flagGreece);
    panel.add(clear);

    Action actions = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
          String input_path = "load "
              + JOptionPane.showInputDialog(frame, "Input Image File Path:", null);
          String output = controller.processCommand(input_path);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == save) {
          String output_path = "save " + JOptionPane.showInputDialog(frame, "Save as:", null);
          String output = controller.processCommand(output_path);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == dither) {
          String output = controller.processCommand("dither");
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == edgeDetection) {
          String output = controller.processCommand("edgeDetection");
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == mosaic) {
          String mos = "mosaic " + JOptionPane.showInputDialog(frame, "How many seeds?", null);
          String output = controller.processCommand(mos);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == greyscale) {
          String output = controller.processCommand("greyscale");
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == grayscaleContrastEnhancement) {
          String output = controller.processCommand("grayscaleContrastEnhancement");
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == sepia) {
          String output = controller.processCommand("sepia");
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == blur) {
          String command = "blur "
              + JOptionPane.showInputDialog(frame, "How many time you want to repeat?", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == sharpen) {
          String command = "sharpen "
              + JOptionPane.showInputDialog(frame, "How many time you want to repeat?", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == checkBoard) {
          String command = "checkBoard "
              + JOptionPane.showInputDialog(frame, "Number of blocks in height:", null) + " "
              + JOptionPane.showInputDialog(frame, "Number of blocks in width:", null) + " "
              + JOptionPane.showInputDialog(frame, "Number of pixels in block:", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == rainbow) {
          String command = "rainbow " + JOptionPane.showInputDialog(frame, "Height in pixel:", null)
              + " " + JOptionPane.showInputDialog(frame, "Width in pixel:", null) + " "
              + (JOptionPane.showConfirmDialog(frame,
                  "Is it horizontal?") == JOptionPane.YES_OPTION);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == flagFrance) {
          String command = "flagFrance "
              + JOptionPane.showInputDialog(frame, "Height in pixel:", null) + " "
              + JOptionPane.showInputDialog(frame, "Width in pixel:", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == flagSwiterland) {
          String command = "flagSwiterland "
              + JOptionPane.showInputDialog(frame, "Width in pixel:", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == flagNorway) {
          String command = "flagNorway "
              + JOptionPane.showInputDialog(frame, "Height in pixel:", null) + " "
              + JOptionPane.showInputDialog(frame, "Width in pixel:", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == flagGreece) {
          String command = "flagGreece "
              + JOptionPane.showInputDialog(frame, "Height in pixel:", null) + " "
              + JOptionPane.showInputDialog(frame, "Width in pixel:", null);
          String output = controller.processCommand(command);
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == clear) {
          String output = controller.processCommand("clear");
          JOptionPane.showMessageDialog(frame, output);
        } else if (e.getSource() == redEye) {
          JFrame jFrame = new JFrame("Red Eye Removel");
          BufferedImage temp = controller.getImg();
          Popup p = new Popup(temp);
          Container panel = jFrame.getContentPane();

          JButton confirm = new JButton("Confirm");
          confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              String output = controller.processCommand("redEye " + p.getRet());
              JOptionPane.showMessageDialog(frame, output);
              modifiedImage.setIcon(new ImageIcon(controller.getRet()));
              frame.revalidate();
            }
          });
          panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
          panel.add(p);
          panel.add(confirm);
          jFrame.pack();
          jFrame.setVisible(true);
        }
        BufferedImage temp = controller.getImg();
        ImageIcon org = temp == null ? new ImageIcon() : new ImageIcon(temp);
        originalImage.setIcon(org);
        scroller1
            .setPreferredSize(new Dimension(originalImage.getWidth(), originalImage.getHeight()));

        temp = controller.getRet();
        ImageIcon mod = temp == null ? new ImageIcon() : new ImageIcon(temp);
        modifiedImage.setIcon(mod);
        scroller2
            .setPreferredSize(new Dimension(modifiedImage.getWidth(), modifiedImage.getHeight()));

        frame.revalidate();
      }
    };

    dither.addActionListener(actions);
    edgeDetection.addActionListener(actions);
    mosaic.addActionListener(actions);
    greyscale.addActionListener(actions);
    grayscaleContrastEnhancement.addActionListener(actions);
    redEye.addActionListener(actions);
    sepia.addActionListener(actions);
    blur.addActionListener(actions);
    sharpen.addActionListener(actions);
    checkBoard.addActionListener(actions);
    rainbow.addActionListener(actions);
    flagFrance.addActionListener(actions);
    flagSwiterland.addActionListener(actions);
    flagNorway.addActionListener(actions);
    flagGreece.addActionListener(actions);
    open.addActionListener(actions);
    save.addActionListener(actions);
    clear.addActionListener(actions);

    panel.add(scroller1);
    panel.add(scroller2);

    frame.getContentPane().add(BorderLayout.NORTH, mb);
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.pack();
    frame.setVisible(true);
  }

}