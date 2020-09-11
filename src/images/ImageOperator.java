package images;

/**
 * An interface for ImageOperator class, which performs operations upon an
 * provided image.
 *
 */
public interface ImageOperator {

  /**
   * Blurring the image represented by a 3D integer array with rows = height,
   * columns = width and depth = 3.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the blurred image file
   */
  int[][][] blur(int[][][] img);

  /**
   * Sharpening the image represented by a 3D integer array with rows = height,
   * columns = width and depth = 3, by accentuates edges (the boundaries between
   * regions of high contrast).
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the sharpened image file
   */
  int[][][] sharpen(int[][][] img);

  /**
   * Convert a color image into a grey scale image. A grey scale image is composed
   * only of shades of grey (if the red, green and blue values are the same, it is
   * a shade of grey).
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the grey scale image file
   */
  int[][][] greyscale(int[][][] img);

  /**
   * Photographs taken in the 19th and early 20th century had a characteristic
   * reddish brown tone. This is referred to as a Sepia tone.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the Sepia tone image file
   */
  int[][][] sepia(int[][][] img);

  /**
   * This operation of breaking down an image that has many colors into an image
   * that is made of dots from just a few colors is known as dithering.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the dithering image file
   */
  int[][][] dither(int[][][] img);

  /**
   * Convert a color image into a grey scale normalized Edge detection image. Edge
   * detection produces a grayscale image where edges (areas of high contrast) are
   * highlighted.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the Edge detection image file
   */
  int[][][] edgeDetection(int[][][] img);

  /**
   * Image values may not be in the range (0, 255), normalizing means to re-scale
   * color values.
   * 
   * @param img    the integer array representing the image file
   * @param maxImg the maximum value in the original image array
   * @param minImg the minimum value in the original image array
   * @return the normalized image array
   */
  int[][][] normalize(int[][][] img, int maxImg, int minImg);

  /**
   * Applying the color transformation matrix to the 3D integer array representing
   * the image with rows = height, columns = width and depth = 3.
   * 
   * @param img    the 3D integer array representing the image with rows = height,
   *               columns = width and depth = 3.
   * @param matrix the 3*3 matrix to be applied on the image
   * @return the applied integer array representing the image
   */
  int[][][] transformation(int[][][] img, double[][] matrix);

  /**
   * Applying the filtering matrix to the 3D integer array representing the image
   * with rows = height, columns = width and depth = 3.
   * 
   * @param img    the 3D integer array representing the image with rows = height,
   *               columns = width and depth = 3.
   * @param matrix the matrix to be applied on the image
   * @return the applied integer array representing the image
   */
  int[][][] filtering(int[][][] img, double[][] matrix);

  /**
   * Applying the mosaic matrix to the 3D integer array representing the image
   * with rows = height, columns = width and depth = 3.
   * 
   * @param img   the 3D integer array representing the image with rows = height,
   *              columns = width and depth = 3.
   * @param seeds the number of seeds for this mosaic image
   * @return the applied integer array representing the image
   */
  int[][][] mosaic(int[][][] img, int seeds);

  /**
   * This operation uses image intensity to increase the contrast of an image.
   * 
   * @param img the integer array representing the image file
   * @return the integer array representing the enhanced contrast image file
   */
  int[][][] grayscaleContrastEnhancement(int[][][] img);

  /**
   * Red-eye removal is accomplished by identifying the area of an image that
   * looks like a red-eye and averaging out the redness from the area.
   * 
   * @param img    the integer array representing the image file
   * @param startX the start x position of applying red eye on this image
   * @param startY the start y position of applying red eye on this image
   * @param endX   the end x position of applying red eye on this image
   * @param endY   the end y position of applying red eye on this image
   * @return the integer array representing the red-eye removed image file
   */

  int[][][] redEye(int[][][] img, int startX, int startY, int endX, int endY);

}