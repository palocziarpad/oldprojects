package com.snakegame.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakegame.PictureFiles;

/**
 * Representation of a Snake Game Theme
 *
 */
public class SnakeTheme {

    private Logger logger = LoggerFactory.getLogger(SnakeTheme.class);

    private Image[] BIARRAY;
    private static String designPrefix;
    static {
	designPrefix = getStdDesign();
    }

    /**
     * Constructor.
     */
    public SnakeTheme() {
	designPrefix = getStdDesign();
	BIARRAY = new BufferedImage[21];

	resetTheme();
    }

    /***
     * Get selected theme
     * 
     * @return
     */
    public static String getSelectedTheme() {
	return designPrefix;
    }

    /**
     * Set the theme
     * 
     * @param designPrefix
     */
    public void setTheme(String designPrefix) {
	SnakeTheme.designPrefix = designPrefix;
	resetTheme();
    }

    /**
     * Reset the theme.
     */
    public void resetTheme() {
	int i = 0;
	for (PictureFiles bHolder : PictureFiles.values()) {
	    try {
		BIARRAY[i] = ImageIO
			.read(getClass().getResourceAsStream(SnakeTheme.getSelectedTheme() + bHolder.getValue()));
	    } catch (IOException e) {
		logger.error(e.getMessage(), e);
	    }
	    i++;
	    if (i == BIARRAY.length) {
		break;
	    }
	}
    }

    public static String getStdDesign() {
	return "/resources/mira/";
    }

    /**
     * Get the images
     * 
     * @return
     */
    public Image[] getBIARRAY() {
	return BIARRAY;
    }

    /**
     * Get the images
     * 
     * @return
     */
    public Image getImage(PictureFiles picturefile) {
	int number = picturefile.ordinal();
	if (number > BIARRAY.length - 1 || number < 0) {
	    logger.error("Error, the requested image does not have correct number! " + number + " "
		    + picturefile.getValue());
	    number = 0;
	}
	return BIARRAY[picturefile.ordinal()];
    }

}
