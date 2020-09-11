package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

import images.ImageGenerating;
import images.ImageModel;
import images.ImageOperating;
import images.ImageOperator;

/**
 * Controller through file input.
 *
 */
public class FileController {
  static ImageModel u;
  static ImageOperator f = new ImageOperating();
  static ImageGenerating g = new ImageGenerating();

  /**
   * The main method generating the image.
   * 
   * @param args the main method arguments
   * @throws IOException throws Exception when input or output is not correct
   */
  public static void main(String args[]) throws IOException {
    File file = new File(args[0]);
    int[][][] img = null;
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        img = process(img, sc.nextLine());
      }
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }

  }

  private static int[][][] process(int[][][] img, String command) throws IOException {
    String[] commands = command.split(" ");
    int[][][] ret = img;
    switch ( commands[0] ) {
      case "load" :
        if (commands.length == 2) {
          ret = u.readImage(commands[1]);
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct example: ");
          System.out.println("load [filename]");
        }
        break;

      case "dither" :
        if (img != null && commands.length == 1) {
          ret = f.dither(img);
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("dither");
        }
        break;

      case "edgeDetection" :
        if (img != null && commands.length == 1) {
          ret = f.edgeDetection(img);
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("edgeDetection");
        }
        break;

      case "mosaic" :
        if (img != null && commands.length == 2) {
          ret = f.mosaic(img, Integer.parseInt(commands[1]));
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("mosaic 1000");
        }
        break;

      case "grayscaleContrastEnhancement" :
        if (img != null && commands.length == 1) {
          ret = f.grayscaleContrastEnhancement(img);
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("grayscaleContrastEnhancement");
        }
        break;

      case "redEye" :
        if (img != null && commands.length == 5) {
          ret = f.redEye(img, 0, 0, img.length, img[0].length);
          // ret = f.redEye(img, Integer.parseInt(commands[1]),
          // Integer.parseInt(commands[2]),
          // Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));

        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("redEye 0 0 100 100");
        }
        break;

      case "greyscale" :
        if (img != null && commands.length == 1) {
          ret = f.greyscale(img);
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("greyscale");
        }
        break;

      case "sepia" :
        if (img != null && commands.length == 1) {
          ret = f.sepia(img);
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("sepia");
        }
        break;

      case "blur" :
        if (img != null) {
          if (commands.length == 1) {
            ret = f.blur(img);
          } else if (commands.length == 2) {
            ret = img;
            int times = Integer.parseInt(commands[1]);
            for (int i : IntStream.range(0, times).toArray()) {
              ret = f.blur(ret);
            }
          }
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("blur");
          System.out.println("blur 2");
        }
        break;

      case "sharpen" :
        if (img != null) {
          if (commands.length == 1) {
            ret = f.sharpen(img);
          } else if (commands.length == 2) {
            ret = img;
            int times = Integer.parseInt(commands[1]);
            for (int i : IntStream.range(0, times).toArray()) {
              ret = f.sharpen(ret);
            }
          }
        } else {
          System.out.println("Incorrect command syntax or You have not load an image.");
          System.out.println("Correct example: ");
          System.out.println("sharpen");
          System.out.println("sharpen 2");
        }
        break;

      case "checkBoard" :
        if (commands.length == 4) {
          ret = g.checkBoard(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
              Integer.parseInt(commands[3]));
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct syntax: ");
          System.out.println("checkBoard [heightBlocks] [widthBlocks] [blockSize]");
        }
        break;

      case "rainbow" :
        if (commands.length == 4) {
          ret = g.rainbow(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
              Boolean.parseBoolean(commands[3]));
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct syntax: ");
          System.out.println("rainbow [height] [width] [isHorizontal]");
        }
        break;

      case "flagFrance" :
        if (commands.length == 3) {
          ret = g.flagFrance(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct syntax: ");
          System.out.println("flagFrance [height] [width]");
        }
        break;

      case "flagSwiterland" :
        if (commands.length == 2) {
          ret = g.flagSwiterland(Integer.parseInt(commands[1]));
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct syntax: ");
          System.out.println("flagSwiterland [width]");
        }
        break;

      case "flagNorway" :
        if (commands.length == 3) {
          ret = g.flagNorway(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct syntax: ");
          System.out.println("flagNorway [height] [width]");
        }
        break;

      case "flagGreece" :
        if (commands.length == 3) {
          ret = g.flagGreece(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct syntax: ");
          System.out.println("flagGreece [height] [width]");
        }
        break;

      case "save" :
        if (commands.length == 2) {
          u.writeImage(img, img[0].length, img.length, commands[1]);
        } else {
          System.out.println("Incorrect command syntax.");
          System.out.println("Correct example: ");
          System.out.println("save [filename]");
        }
        break;

      case "" :
        break;

      default :
        System.out.println("Command not recognized.");

    }
    return ret;
  }
}
