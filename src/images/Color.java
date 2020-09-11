package images;

/**
 * The color class that contains color of RGB format in integer arrays.
 */
public class Color {

  static int[] red = new int[] { 201, 1, 50 };
  static int[] orange = new int[] { 255, 60, 38 };
  static int[] yellow = new int[] { 242, 206, 0 };
  static int[] green = new int[] { 105, 217, 89 };
  static int[] darkGreen = new int[] { 5, 167, 130 };
  static int[] blue = new int[] { 0, 113, 183 };
  static int[] purple = new int[] { 69, 61, 180 };
  static int[] black = new int[] { 0, 0, 0 };
  static int[] white = new int[] { 255, 255, 255 };
  static int[] france_blue = new int[] { 0, 85, 164 };
  static int[] france_red = new int[] { 239, 65, 53 };
  static int[][] rainbow = new int[][] { red, orange, yellow, green, darkGreen, blue, purple };
  static int[][] france = new int[][] { france_blue, white, france_red };
  static int[][][] switzerland = new int[][][] { { red, red, red, red, red },
      { red, red, white, red, red }, { red, white, white, white, red },
      { red, red, white, red, red }, { red, red, red, red, red } };
  static int[][][] norway = new int[][][] { { red, white, blue, white, red },
      { white, white, blue, white, white }, { blue, blue, blue, blue, blue },
      { white, white, blue, white, white }, { red, white, blue, white, red } };
  static int[][][] greece = new int[][][] { { france_blue, white, france_blue, france_blue },
      { france_blue, white, france_blue, white }, { white, white, white, france_blue },
      { france_blue, white, france_blue, white }, { france_blue, white, france_blue, france_blue },
      { white, white, white, white }, { france_blue, france_blue, france_blue, france_blue },
      { white, white, white, white }, { france_blue, france_blue, france_blue, france_blue } };

  /**
   * Get red color.
   * 
   * @return red color of RGB format in integer array
   */
  public static int[] red() {
    return Color.red;
  }

  /**
   * Get orange color.
   * 
   * @return orange color of RGB format in integer array
   */
  public static int[] orange() {
    return Color.orange;
  }

  /**
   * Get yellow color.
   * 
   * @return yellow color of RGB format in integer array
   */
  public static int[] yellow() {
    return Color.yellow;
  }

  /**
   * Get green color.
   * 
   * @return green color of RGB format in integer array
   */
  public static int[] green() {
    return Color.green;
  }

  /**
   * Get dark green color.
   * 
   * @return dark green color of RGB format in integer array
   */
  public static int[] darkGreen() {
    return Color.darkGreen;
  }

  /**
   * Get blue color.
   * 
   * @return blue color of RGB format in integer array
   */
  public static int[] blue() {
    return Color.blue;
  }

  /**
   * Get purple color.
   * 
   * @return purple color of RGB format in integer array
   */
  public static int[] purple() {
    return Color.purple;
  }

  /**
   * Get black color.
   * 
   * @return black color of RGB format in integer array
   */
  public static int[] black() {
    return Color.black;
  }

  /**
   * Get white color.
   * 
   * @return white color of RGB format in integer array
   */
  public static int[] white() {
    return Color.white;
  }

  /**
   * Get rainbow color set.
   * 
   * @return rainbow color set with color in order
   */
  public static int[][] rainbow() {
    return new int[][] { red, orange, yellow, green, darkGreen, blue, purple };
  }

  /**
   * Get the color set of the flag of France.
   * 
   * @return color set of the flag of France with color in order
   */
  public static int[][] france() {
    return new int[][] { france_blue, white, france_red };
  }

  /**
   * Get the color set of the flag of Switzerland.
   * 
   * @return color set of the flag of Switzerland with color in order
   */
  public static int[][][] switzerland() {
    return new int[][][] { { red, red, red, red, red }, { red, red, white, red, red },
        { red, white, white, white, red }, { red, red, white, red, red },
        { red, red, red, red, red } };
  }

  /**
   * Get the color set of the flag of Norway.
   * 
   * @return color set of the flag of Norway with color in order
   */
  public static int[][][] norway() {
    return new int[][][] { { red, white, blue, white, red }, { white, white, blue, white, white },
        { blue, blue, blue, blue, blue }, { white, white, blue, white, white },
        { red, white, blue, white, red } };
  }

  /**
   * Get the color set of the flag of Greece.
   * 
   * @return color set of the flag of Greece with color in order
   */
  public static int[][][] greece() {
    return new int[][][] { { france_blue, white, france_blue, france_blue },
        { france_blue, white, france_blue, white }, { white, white, white, france_blue },
        { france_blue, white, france_blue, white },
        { france_blue, white, france_blue, france_blue }, { white, white, white, white },
        { france_blue, france_blue, france_blue, france_blue }, { white, white, white, white },
        { france_blue, france_blue, france_blue, france_blue } };
  }
}
