package com.snakegame;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.snakegame.model.SnakeTheme;

public enum BinaryPicHolder {
    EMPTY(PictureFiles.EMPTY.getValue()), //
    TAILLEFT(PictureFiles.TAILLEFT.getValue()), //
    TAILUP(PictureFiles.TAILUP.getValue()), //
    TAILRIGHT(PictureFiles.TAILRIGHT.getValue()), //
    TAILDOWN(PictureFiles.TAILDOWN.getValue()), //
    HEADUP(PictureFiles.HEADUP.getValue()), //
    HEADRIGHT(PictureFiles.HEADRIGHT.getValue()), //
    HEADDOWN(PictureFiles.HEADDOWN.getValue()), //
    HEADLEFT(PictureFiles.HEADLEFT.getValue()), //
    BODYHOR(PictureFiles.BODYHOR.getValue()), //
    BODYVER(PictureFiles.BODYVER.getValue()), //
    FOOD(PictureFiles.FOOD.getValue()), //
    GAMEBG(PictureFiles.GAMEBG.getValue()), //
    TABLE(PictureFiles.TABLE.getValue()), //
    SCORE(PictureFiles.SCORE.getValue()), //
    SLANTRD(PictureFiles.SLANTRD.getValue()), // rightdown
    SLANTLD(PictureFiles.SLANTLD.getValue()), // leftdown
    SLANTLU(PictureFiles.SLANTLU.getValue()), // leftup
    SLANTRU(PictureFiles.SLANTRU.getValue()), // rightup
    FOOD2(PictureFiles.FOOD2.getValue()), //
    NEWPART(PictureFiles.NEWPART.getValue()), //
    OPENBG(PictureFiles.OPENBG.getValue()), //
    GAMEOVERSTUN(PictureFiles.GAMEOVERSTUN.getValue()), //
    GAMEOVERBITE(PictureFiles.GAMEOVERBITE.getValue()); //
    private Image image;
    private String imageName;

    private BinaryPicHolder(String imageName) {
        if (image == null) {
            this.imageName = imageName;
            try {
                this.image = ImageIO.read(getClass().getResourceAsStream(SnakeTheme.getSelectedTheme() + imageName));
            } catch (IOException e) {
                e.printStackTrace();
                this.image = null;
            }
        }

    }

    /**
     * Get the value of the
     * 
     * @return
     */
    public Image getValue() {
        return image;
    }

    /**
     * Refresh the image to use the new path.
     */
    public void refresh() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(SnakeTheme.getSelectedTheme() + imageName));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

}
