package images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Image utility class that has methods to read an image from file and write to
 * a file.
 */
public abstract class ImageModel {
  protected int min;
  protected int max;

  /**
   * Constructor of ImageUtilites class with the clamping values.
   * 
   * @param min the minimum of the clamping value
   * @param max the maximum of the clamping value
   */
  public ImageModel(int min, int max) {
    this.min = min;
    this.max = max;
  }

  /**
   * Constructor of ImageUtilites class with the default clamping values with 0
   * and 255.
   * 
   */
  public ImageModel() {
    this(0, 255);
  }

  /**
   * Convert the image file with the file name to the 3D integer array with rows =
   * height, columns = width and depth = 3.
   * 
   * @param filename the file name of the image file to be converted to integer
   *                 array
   * @return the integer array representing this image file
   * @throws IOException throw exception when there is input output error
   */
  public static int[][][] readImage(String filename) throws IOException {
    BufferedImage input;

    input = ImageIO.read(new FileInputStream(filename));

    int[][][] result = new int[input.getHeight()][input.getWidth()][3];

    for (int i = 0; i < input.getHeight(); i ++ ) {
      for (int j = 0; j < input.getWidth(); j ++ ) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        result[i][j][0] = c.getRed();
        result[i][j][1] = c.getGreen();
        result[i][j][2] = c.getBlue();
      }
    }
    return result;
  }

  /**
   * Get the width of the image file.
   * 
   * @param filename the file name of the image file
   * @return the width of the image file
   * @throws IOException throw exception when there is input output error
   */
  private int getWidth(String filename) throws IOException {
    BufferedImage input;

    input = ImageIO.read(new FileInputStream(filename));

    return input.getWidth();
  }

  /**
   * Get the height of the image file.
   * 
   * @param filename the file name of the image file
   * @return the height of the image file
   * @throws IOException throw exception when there is input output error
   */
  private int getHeight(String filename) throws IOException {
    BufferedImage input;

    input = ImageIO.read(new FileInputStream(filename));

    return input.getHeight();
  }

  /**
   * Write the 3D Image array with rows = height, columns = width and depth = 3 to
   * the file.
   * 
   * @param rgb      the image array
   * @param width    the width of the file
   * @param height   the height of the file
   * @param filename the name of the file to be written to
   * @throws IOException throw exception when there is input output error
   */
  public static void writeImage(int[][][] rgb, int width, int height, String filename)
      throws IOException {

    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];

        // color is stored in 1 integer, with the 4 bytes storing ARGB in that
        // order. Each of r,g,b are stored in 8 bits (hence between 0 and 255).
        // So we put them all in one integer by using bit-shifting << as below
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    String extension = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(output, extension, new FileOutputStream(filename));
  }

  /**
   * Transfer image array to BufferedImage.
   * 
   * @param rgb the image array to be trnasfered
   * @return BufferedImage
   */
  public static BufferedImage toBufferedImage(int[][][] rgb) {
    if (rgb == null) {
      return null;
    }
    int height = rgb.length;
    int width = rgb[0].length;
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];

        // color is stored in 1 integer, with the 4 bytes storing ARGB in that
        // order. Each of r,g,b are stored in 8 bits (hence between 0 and 255).
        // So we put them all in one integer by using bit-shifting << as below
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    return output;
  }

  /**
   * We must "clamp" these values to avoid overflow and underflow while saving,
   * and to display such images properly. Clamping requires two values: the
   * permissible minimum and maximum. Then each value in an image that is lesser
   * than the minimum is assigned to the minimum, and each value greater than the
   * maximum is assigned to the maximum.
   * 
   * @param x the value to be clamped
   * @return the valid clamped value
   */
  protected int clamp(int x) {
    return Math.max(min, Math.min(max, x));
  }

}
