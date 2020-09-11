package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

import images.ImageGenerating;
import images.ImageModel;
import images.ImageOperating;
import images.ImageOperator;

/**
 * A MVC Controller.
 *
 */
public class MVCController implements IController {
  static ImageModel u;
  static ImageOperator f = new ImageOperating();
  static ImageGenerating g = new ImageGenerating();
  private int[][][] org = null;
  private int[][][] img = null;
  private int[][][] ret = null;

  @Override
  public BufferedImage getImg() {
    return u.toBufferedImage(org);
  }

  @Override
  public BufferedImage getRet() {
    return u.toBufferedImage(ret);
  }

  @Override
  public String processCommand(String command) {
    Scanner sc = new Scanner(command);
    String[] commands = command.split(" ");
    switch ( commands[0] ) {
      case "load" :
        if (commands.length == 2) {
          try {
            org = u.readImage(commands[1]);
            img = org;
            ret = org;
          } catch (IOException e) {
            return "File not found.";
          }
        } else {
          return "File not found.";
        }
        break;

      case "dither" :
        if (img != null && commands.length == 1) {
          ret = f.dither(img);
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "edgeDetection" :
        if (img != null && commands.length == 1) {
          ret = f.edgeDetection(img);
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "mosaic" :
        if (img != null && commands.length == 2) {
          ret = f.mosaic(img, Integer.parseInt(commands[1]));
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "grayscaleContrastEnhancement" :
        if (img != null && commands.length == 1) {
          ret = f.grayscaleContrastEnhancement(img);
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "redEye" :
        if (img != null && commands.length == 5) {
          ret = f.redEye(img, Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
              Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "greyscale" :
        if (img != null && commands.length == 1) {
          ret = f.greyscale(img);
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "sepia" :
        if (img != null && commands.length == 1) {
          ret = f.sepia(img);
          img = ret;
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "blur" :
        if (img != null) {
          if (commands.length == 1) {
            ret = f.blur(img);
            img = ret;
          } else if (commands.length == 2) {
            try {
              ret = img;
              int times = Integer.parseInt(commands[1]);
              for (int i : IntStream.range(0, times).toArray()) {
                ret = f.blur(ret);
              }
              img = ret;
            } catch (NumberFormatException e) {
              return "Incorrect command syntax";
            }
          }
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "sharpen" :
        if (img != null) {
          if (commands.length == 1) {
            ret = f.sharpen(img);
            img = ret;
          } else if (commands.length == 2) {
            try {
              ret = img;
              int times = Integer.parseInt(commands[1]);
              for (int i : IntStream.range(0, times).toArray()) {
                ret = f.sharpen(ret);
              }
              img = ret;
            } catch (NumberFormatException e) {
              return "Incorrect command syntax";
            }
          }
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "checkBoard" :
        if (commands.length == 4) {
          try {
            ret = g.checkBoard(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
                Integer.parseInt(commands[3]));
            org = null;
            img = null;
          } catch (NumberFormatException e) {
            return "Incorrect command syntax";
          }
        } else {
          return "Incorrect command syntax or You have not load an image.";
        }
        break;

      case "rainbow" :
        if (commands.length == 4) {
          try {
            ret = g.rainbow(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
                Boolean.parseBoolean(commands[3]));
            org = null;
            img = null;
          } catch (NumberFormatException e) {
            return "Incorrect command syntax";
          }
        } else {
          return "Incorrect command syntax";
        }
        break;

      case "flagFrance" :
        if (commands.length == 3) {
          try {
            ret = g.flagFrance(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
            org = null;
            img = null;
          } catch (NumberFormatException e) {
            return "Incorrect command syntax";
          }
        } else {
          return "Incorrect command syntax";
        }
        break;

      case "flagSwiterland" :
        if (commands.length == 2) {
          try {
            ret = g.flagSwiterland(Integer.parseInt(commands[1]));
            org = null;
            img = null;
          } catch (NumberFormatException e) {
            return "Incorrect command syntax";
          }
        } else {
          return "Incorrect command syntax";
        }
        break;

      case "flagNorway" :
        if (commands.length == 3) {
          try {
            ret = g.flagNorway(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
            org = null;
            img = null;
          } catch (NumberFormatException e) {
            return "Incorrect command syntax";
          }
        } else {
          return "Incorrect command syntax";
        }
        break;

      case "flagGreece" :
        if (commands.length == 3) {
          try {
            ret = g.flagGreece(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
            org = null;
            img = null;
          } catch (NumberFormatException e) {
            return "Incorrect command syntax";
          }
        } else {
          return "Incorrect command syntax";
        }
        break;

      case "save" :
        if (commands.length == 2) {
          try {
            u.writeImage(ret, ret[0].length, ret.length, commands[1]);
          } catch (IOException e) {
            return "No image loaded or File can't written.";
          }
        } else {
          return "Incorrect command syntax";
        }
        break;

      case "clear" :
        img = org;
        ret = org;
        break;

      case "" :
        break;

      default :
        return "Command not recognized.";

    }
    return "Successfully executed: " + command;
  }
}