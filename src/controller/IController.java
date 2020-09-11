package controller;

import java.awt.image.BufferedImage;

/**
 * The controller interface for the turtle program. The functions here have been
 * designed to give control to the controller, and the primary operation for the
 * controller to function (process a turtle command).
 */

public interface IController {
  /**
   * Process a given string command and return status or error as a string.
   * 
   * @param command the command given, including any parameters (e.g. "move 3")
   * @return status or error message
   */
  String processCommand(String command);

  /**
   * Get the original image.
   * 
   * @return the original image
   */
  public BufferedImage getImg();

  /**
   * Get the operated image.
   * 
   * @return the operated images
   */
  public BufferedImage getRet();

}
