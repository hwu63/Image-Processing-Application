package images;

/**
 * An subclass of ImageUtilities class that can manipulate on image including
 * blurring, sharpening, and color transformation.
 *
 */
public class ImageOperating extends ImageModel implements ImageOperator {

  /**
   * Constructor of ImageOperating class with the clamping values.
   * 
   * @param min the minimum of the clamping value
   * @param max the maximum of the clamping value
   */
  public ImageOperating(int min, int max) {
    super(min, max);
  }

  /**
   * Constructor of ImageOperating class with the default clamping values of 0 and
   * 255.
   */
  public ImageOperating() {
    this(0, 255);
  }

  /**
   * Blurring the image represented by a 3D integer array with rows = height,
   * columns = width and depth = 3.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the blurred image file
   */
  @Override
  public int[][][] blur(int[][][] img) {
    double[][] blurMatrix = new double[][] { { 1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0 },
        { 1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0 }, { 1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0 } };
    return filtering(img, blurMatrix);
  }

  /**
   * Sharpening the image represented by a 3D integer array with rows = height,
   * columns = width and depth = 3, by accentuates edges (the boundaries between
   * regions of high contrast).
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the sharpened image file
   */
  @Override
  public int[][][] sharpen(int[][][] img) {
    double[][] sharpenMatrix = new double[][] { { - 0.125, - 0.125, - 0.125, - 0.125, - 0.125 },
        { - 0.125, 0.25, 0.25, 0.25, - 0.125 }, { - 0.125, 0.25, 1, 0.25, - 0.125 },
        { - 0.125, 0.25, 0.25, 0.25, - 0.125 }, { - 0.125, - 0.125, - 0.125, - 0.125, - 0.125 } };
    return filtering(img, sharpenMatrix);
  }

  /**
   * Convert a color image into a grey scale image. A grey scale image is composed
   * only of shades of grey (if the red, green and blue values are the same, it is
   * a shade of grey).
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the grey scale image file
   */
  @Override
  public int[][][] greyscale(int[][][] img) {
    double[][] greyscaleMatrix = new double[][] { { 0.2126, 0.7152, 0.0722 },
        { 0.2126, 0.7152, 0.0722 }, { 0.2126, 0.7152, 0.0722 } };
    return transformation(img, greyscaleMatrix);
  }

  /**
   * Photographs taken in the 19th and early 20th century had a characteristic
   * reddish brown tone. This is referred to as a Sepia tone.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the Sepia tone image file
   */
  @Override
  public int[][][] sepia(int[][][] img) {
    double[][] sepiaMatrix = new double[][] { { 0.393, 0.769, 0.189 }, { 0.349, 0.686, 0.168 },
        { 0.272, 0.534, 0.131 } };
    return transformation(img, sepiaMatrix);
  }

  /**
   * This operation of breaking down an image that has many colors into an image
   * that is made of dots from just a few colors is known as dithering.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the dithering image file
   */
  @Override
  public int[][][] dither(int[][][] img) {
    int[][][] ret = greyscale(img);
    int height = ret.length;
    int width = ret[0].length;

    int[][][] result = new int[height][width][3];
    for (int k = 0; k < 3; k ++ ) {
      for (int i = 0; i < height; i ++ ) {
        for (int j = 0; j < width; j ++ ) {

          int old_color = ret[i][j][k];
          int new_color = old_color < 128 ? 0 : 255;
          double error = old_color - new_color;

          result[i][j][k] = new_color;

          if (j + 1 < width) {
            ret[i][j + 1][k] += (int) Math.round(7.0 / 16.0 * error);
          }
          if (i + 1 < height && j - 1 >= 0) {
            ret[i + 1][j - 1][k] += (int) Math.round(3.0 / 16.0 * error);
          }
          if (i + 1 < height) {
            ret[i + 1][j][k] += (int) Math.round(5.0 / 16.0 * error);
          }
          if (i + 1 < height && j + 1 < width) {
            ret[i + 1][j + 1][k] += (int) Math.round(error / 16.0);
          }
        }
      }
    }

    return result;

  }

  /**
   * Convert a color image into a grey scale normalized Edge detection image. Edge
   * detection produces a grayscale image where edges (areas of high contrast) are
   * highlighted.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the Edge detection image file
   */
  @Override
  public int[][][] edgeDetection(int[][][] img) {
    double[][] kx = new double[][] { { 1, 0, - 1 }, { 2, 0, - 2 }, { 1, 0, - 1 } };
    double[][] ky = new double[][] { { 1, 2, 1 }, { 0, 0, 0 }, { - 1, - 2, - 1 } };
    int[][][] gx = filtering(img, kx);
    int[][][] gy = filtering(img, ky);

    int max = 0;
    int min = Integer.MAX_VALUE;
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];
    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        for (int k = 0; k < 3; k ++ ) {
          ret[i][j][k] = (int) (Math.sqrt(gx[i][j][k] * gx[i][j][k] + gy[i][j][k] * gy[i][j][k]));
          if (ret[i][j][k] > max) {
            max = ret[i][j][k];
          }
          if (ret[i][j][k] < min) {
            min = ret[i][j][k];
          }
        }
      }
    }
    return greyscale(normalize(ret, max, min));
  }

  /**
   * Image values may not be in the range (0, 255), normalizing means to re-scale
   * color values.
   * 
   * @param img    the integer array representing the image file
   * @param maxImg the maximum value in the original image array
   * @param minImg the minimum value in the original image array
   * @return the normalized image array
   */
  @Override
  public int[][][] normalize(int[][][] img, int maxImg, int minImg) {
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];
    double temp = 255.0 / (maxImg - minImg);

    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        for (int k = 0; k < 3; k ++ ) {
          ret[i][j][k] = (int) ((img[i][j][k] - minImg) * temp);

        }
      }
    }

    return ret;
  }

  /**
   * Applying the color transformation matrix to the 3D integer array representing
   * the image with rows = height, columns = width and depth = 3.
   * 
   * @param img    the 3D integer array representing the image with rows = height,
   *               columns = width and depth = 3.
   * @param matrix the 3*3 matrix to be applied on the image
   * @return the applied integer array representing the image
   */
  @Override
  public int[][][] transformation(int[][][] img, double[][] matrix) {
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];
    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        ret[i][j][0] = clamp((int) (matrix[0][0] * img[i][j][0] + matrix[0][1] * img[i][j][1]
            + matrix[0][2] * img[i][j][2]));
        ret[i][j][1] = clamp((int) (matrix[1][0] * img[i][j][0] + matrix[1][1] * img[i][j][1]
            + matrix[1][2] * img[i][j][2]));
        ret[i][j][2] = clamp((int) (matrix[2][0] * img[i][j][0] + matrix[2][1] * img[i][j][1]
            + matrix[2][2] * img[i][j][2]));
      }
    }
    return ret;
  }

  @Override
  public int[][][] filtering(int[][][] img, double[][] matrix) {
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];

    int m_h = matrix.length;
    int m_w = matrix[0].length;
    int mh_half = m_h / 2;
    int mw_half = m_w / 2;

    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        for (int k = 0; k < 3; k ++ ) {
          if (i >= mh_half && i < height - mh_half && j >= mw_half && j < width - mw_half) {
            for (int mi = 0; mi < m_h; mi ++ ) {
              for (int mj = 0; mj < m_w; mj ++ ) {
                ret[i][j][k] += (int) (matrix[mi][mj] * img[i - mh_half + mi][j - mw_half + mj][k]);
              }
            }
          } else {
            ret[i][j][k] = img[i][j][k];
          }
          ret[i][j][k] = clamp(ret[i][j][k]);
        }
      }
    }
    return ret;
  }

  @Override
  public int[][][] mosaic(int[][][] img, int seeds) {
    int[] seedx = new int[seeds];
    int[] seedy = new int[seeds];
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];
    for (int i = 0; i < seeds; i ++ ) {
      seedx[i] = (int) (Math.random() * height);
      seedy[i] = (int) (Math.random() * width);
    }

    double max = Double.MAX_VALUE;
    double min = max;
    int[][] clusterAssigned = new int[height][width];
    double distance = 0.0;
    int[][] cluster = new int[seeds][4];
    int temp = 0;

    // assign cluster
    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        min = max;

        for (int s = 0; s < seeds; s ++ ) {
          int x = seedx[s];
          int y = seedy[s];
          distance = (int) (Math.sqrt((i - x) * (i - x) + (j - y) * (j - y)));
          if (distance < min) {
            min = distance;
            clusterAssigned[i][j] = s;
            temp = s;
          }
        }
        for (int k = 0; k < 3; k ++ ) {
          cluster[temp][k] += img[i][j][k];
        }
        cluster[temp][3] ++ ;
      }
    }

    // calculate average color value and assign to pixel
    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        for (int k = 0; k < 3; k ++ ) {
          int[] c = cluster[clusterAssigned[i][j]];
          ret[i][j][k] = c[k] / c[3];
        }
      }
    }

    return ret;
  }

  @Override
  public int[][][] grayscaleContrastEnhancement(int[][][] img) {
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];

    int[] histogram = new int[256];
    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        histogram[img[i][j][0]] += 1;
      }
    }

    int[] cdf = new int[256];
    cdf[0] = histogram[0];
    int min = 0;
    if (cdf[0] == 0) {
      for (int i = 1; i < 256; i ++ ) {
        if (cdf[i] != 0) {
          min = cdf[i];
          break;
        }
      }
    }
    for (int i = 1; i < 256; i ++ ) {
      cdf[i] = cdf[i - 1] + histogram[i];
    }
    int[] idealized = new int[256];
    double temp = 255.0 / (height * width);
    for (int i = 0; i < 256; i ++ ) {
      idealized[i] = (int) Math.round((cdf[i] - min) * temp);
    }

    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        int color = idealized[img[i][j][0]];
        for (int k = 0; k < 3; k ++ ) {
          ret[i][j][k] = color;
        }
      }
    }
    return ret;
  }

  @Override
  public int[][][] redEye(int[][][] img, int startHeight, int startWidth, int endHeight,
      int endWidth) {
    int height = img.length;
    int width = img[0].length;
    int[][][] ret = new int[height][width][3];

    for (int i = 0; i < height; i ++ ) {
      for (int j = 0; j < width; j ++ ) {
        System.arraycopy(img[i][j], 0, ret[i][j], 0, 3);
      }
    }

    for (int i = startHeight; i < endHeight; i ++ ) {
      for (int j = startWidth; j < endWidth; j ++ ) {
        int bg = (int) ((img[i][j][1] + img[i][j][2]) * 0.8);
        if (img[i][j][0] > bg) {
          ret[i][j][0] = bg / 2;
        }
      }
    }
    return ret;
  }

}
