package images;

import java.util.stream.IntStream;

/**
 * An subclass of ImageUtilities class that can generate new images, including
 * horizontal and vertical rainbows, checkboard and flags.
 *
 */
public class ImageGenerating extends ImageModel implements ImageGenerator {

  /**
   * Generate the integer array representing the rainbow image.
   * 
   * @param height       the height of the rainbow image
   * @param width        the width of the rainbow image
   * @param isHorizontal if the rainbow stripe is horizontal
   * @return the 3D integer array representing the rainbow image
   */
  @Override
  public int[][][] rainbow(int height, int width, boolean isHorizontal) {
    return fillStripe(height, width, isHorizontal, new double[] { 1, 1, 1, 1, 1, 1, 1 },
        Color.rainbow);
  }

  /**
   * Generate the integer array representing the checker board image.
   * 
   * @param heightBlock the number of blocks in height
   * @param widthBlock  the number of blocks in width
   * @param blockSize   the size of each block
   * @return the 3D integer array representing the checker board image
   */
  @Override
  public int[][][] checkBoard(int heightBlock, int widthBlock, int blockSize) {

    int[][][] ret = new int[heightBlock * blockSize][widthBlock * blockSize][3];

    for (int i = 0; i < heightBlock; i ++ ) {
      for (int j = 0; j < widthBlock; j ++ ) {
        if ((i + j) % 2 == 0) {
          fillBlock(ret, i * blockSize, j * blockSize, blockSize, blockSize, Color.black);
        } else {
          fillBlock(ret, i * blockSize, j * blockSize, blockSize, blockSize, Color.white);
        }
      }
    }
    return ret;

  }

  /**
   * Generate the integer array representing the flag of France image.
   * 
   * @param height the height of the flag of France image
   * @param width  the width of the flag of France image
   * @return the 3D integer array representing the flag of France image
   */
  @Override
  public int[][][] flagFrance(int height, int width) {
    return fillStripe(height, width, false, new double[] { 1, 1, 1 }, Color.france);
  }

  /**
   * Generate the integer array representing the flag of Switzerland image.
   * 
   * @param width the width of the flag of Switzerland image
   * @return the 3D integer array representing the flag of Switzerland image
   */
  @Override
  public int[][][] flagSwiterland(int width) {
    return flag(width, width, new double[] { 6, 7, 6, 7, 6 }, new double[] { 6, 7, 6, 7, 6 },
        Color.switzerland);

  }

  /**
   * Generate the integer array representing the flag of Norway image.
   * 
   * @param height the height of the flag of Norway image
   * @param width  the width of the flag of Norway image
   * @return the 3D integer array representing the flag of Norway image
   */
  @Override
  public int[][][] flagNorway(int height, int width) {
    return flag(height, width, new double[] { 6, 1, 2, 1, 6 }, new double[] { 6, 1, 2, 1, 12 },
        Color.norway);

  }

  /**
   * Generate the integer array representing the flag of Greece image.
   * 
   * @param height the height of the flag of Greece image
   * @param width  the width of the flag of Greece image
   * @return the 3D integer array representing the flag of Greece image
   */
  @Override
  public int[][][] flagGreece(int height, int width) {
    return flag(height, width, new double[] { 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        new double[] { 4, 2, 4, 17 }, Color.greece);

  }

  /**
   * Generate the integer array representing the flag image.
   * 
   * @param height      the height of the flag image
   * @param width       the width of the flag image
   * @param proportionx the proportion integer array on height
   * @param proportiony the proportion integer array on width
   * @param colorSet    the colorset to be used on the flag
   * @return the 3D integer array representing the flag image
   */
  private int[][][] flag(int height, int width, double[] proportionx, double[] proportiony,
      int[][][] colorSet) {
    int[][][] ret = new int[height][width][3];
    int[] p_i = configureProportion(proportionx, height);
    int[] p_j = configureProportion(proportiony, width);

    int sum_util_now_x = 0;
    int sum_util_now_y = 0;

    for (int i = 0; i < p_i.length; i ++ ) {
      for (int j = 0; j < p_j.length; j ++ ) {
        fillBlock(ret, sum_util_now_x, sum_util_now_y, p_i[i], p_j[j], colorSet[i][j]);
        sum_util_now_y += p_j[j];
      }
      sum_util_now_x += p_i[i];
      sum_util_now_y = 0;
    }

    return ret;
  }

  /**
   * Fill the blocks of given integer array with the given color in RGB format at
   * specified position of the array.
   * 
   * @param img    the image file to be
   * @param startx the start position of row
   * @param starty the start position of column
   * @param height the height of the block to be filled
   * @param width  the width of the block to be filled
   * @param rgb    the color to be filled with
   */
  private void fillBlock(int[][][] img, int startx, int starty, int height, int width, int[] rgb) {
    for (int i = startx; i < height + startx; i ++ ) {
      for (int j = starty; j < width + starty; j ++ ) {
        for (int k = 0; k < 3; k ++ ) {
          img[i][j][k] = rgb[k];
        }
      }
    }
  }

  /**
   * Fill the stripes of a new integer array with given direction and given color
   * set in RGB format with the proportion.
   * 
   * @param height       the height of the generated image
   * @param width        the width of the generated image
   * @param isHorizontal if the stripe is horizontal
   * @param proportion   the proportion of the stripes in each direction, could be
   *                     doubles such as {0.1, 0.3}
   * @param colorSet     the color set that corresponding to each proportion.
   * @return the 3D integer array representing the filled stripe image
   */
  private int[][][] fillStripe(int height, int width, boolean isHorizontal, double[] proportion,
      int[][] colorSet) {
    int[][][] ret = new int[height][width][3];
    int toBeSplit = isHorizontal ? height : width;

    int sum_util_now = 0;

    int[] p = configureProportion(proportion, toBeSplit);

    for (int i = 0; i < proportion.length; i ++ ) {
      if (isHorizontal) {
        fillBlock(ret, sum_util_now, 0, p[i], width, colorSet[i]);
      } else {
        fillBlock(ret, 0, sum_util_now, height, p[i], colorSet[i]);
      }
      sum_util_now += p[i];
    }
    return ret;
  }

  /**
   * Configure the proportion of double into integer that can be directly used on
   * the image. It will average out the extra pixels based on the proportion. For
   * example, proportion of {1, 1, 1}, and total of 32, will be configured to {11,
   * 11, 10}.
   * 
   * @param proportion the raw proportion of the image
   * @param total      the total pixels on the side
   * @return the configured proportion array corresponding to the total pixels
   *         that can be used on image filling
   */
  private int[] configureProportion(double[] proportion, int total) {
    int length = proportion.length;
    int[] ret = new int[length];
    Integer[] rest = new Integer[length];

    double sum = 0;
    for (double p : proportion) {
      sum += p;
    }

    for (int i = 0; i < length; i ++ ) {
      ret[i] = (int) (proportion[i] / sum * total);
      rest[i] = (int) (total * proportion[i] % sum);
    }

    int sum_r = 0;
    for (double p : ret) {
      sum_r += p;
    }

    int[] sorted = IntStream.range(0, rest.length).boxed()
        .sorted((i, j) -> rest[i].compareTo(rest[j])).mapToInt(ele -> ele).toArray();

    int rest_extra = (total - sum_r) % length;

    if (rest_extra != 0) {
      for (int i = 0; i < rest_extra; i ++ ) {
        ret[sorted[i]] += 1;
      }
    }

    return ret;

  }
}
