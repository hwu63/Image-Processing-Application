package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * A pop up for red eye removal.
 *
 */
public class Popup extends Component implements MouseListener, MouseMotionListener {

  /**
   * Get the selected.
   * 
   * @return the return index selected
   */
  public String getRet() {
    return ret;
  }

  private int sX = - 1;
  private int sY = - 1;
  private int curX = - 1;
  private int curY = - 1;
  private Image bImage;
  private boolean dragging = false;
  private String ret;

  /**
   * Constructor.
   * 
   * @param i image
   */
  public Popup(BufferedImage i) {
    super();
    bImage = i;
    setPreferredSize(new Dimension(i.getWidth(), i.getHeight()));
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  @Override
  public void mousePressed(MouseEvent event) {

    Point point = event.getPoint();
    System.out.println("mousePressed at " + point);
    sX = point.x;
    sY = point.y;
    dragging = true;
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    dragging = false;
    ret = sY + " " + sX + " " + curY + " " + curX;
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    Point p = event.getPoint();
    curX = p.x;
    curY = p.y;
    if (dragging) {
      repaint();
    }

  }

  @Override
  public void paint(Graphics graphic) {
    int w = curX - sX;
    int h = curY - sY;
    Dimension dims = getSize();
    graphic.drawImage(bImage, 0, 0, dims.width, dims.height, this);
    if (sX < 0 || sY < 0) {
      return;
    }
    graphic.setColor(Color.red);
    graphic.drawRect(sX, sY, w, h);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }
}
