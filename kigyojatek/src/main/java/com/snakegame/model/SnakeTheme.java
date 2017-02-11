package com.snakegame.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.snakegame.PictureFiles;

/**
 * Representation of a Snake Game Theme
 *
 */
public class SnakeTheme {
    public Image[] BIARRAY;
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
                e.printStackTrace();
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

}
