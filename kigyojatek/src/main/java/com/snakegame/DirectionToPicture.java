package com.snakegame;

/**
 * Converts a direction to a PictureFiles enum
 *
 */
public class DirectionToPicture {
    /***
     * Get the picture from direction.
     * 
     * @param direction
     * @return picture based on direction
     */
    public static PictureFiles getPictureFromBodyDirection(Direction direction) {
        switch (direction) {
        case UP:
        case DOWN:
            return PictureFiles.BODY_VERTICAL;
        case RIGHT:
        case LEFT:
            return PictureFiles.BODY_HORIZONTAL;
        }
        return null;
    }

    /**
     * Get the correct picture from the tail direction.
     * 
     * @param tail
     * @return picture based on tail direction
     */
    public static PictureFiles getPictureFromTailDirection(Direction direction) {
        switch (direction) {
        case UP:
            return PictureFiles.TAILUP;
        case RIGHT:
            return PictureFiles.TAILRIGHT;
        case DOWN:
            return PictureFiles.TAILDOWN;
        case LEFT:
            return PictureFiles.TAILLEFT;
        }
        return null;
    }

    /**
     * Get the correct picture from the head direction.
     * 
     * @param head
     * @return picture based on head direction
     */
    public static PictureFiles getPictureFromHeadDirection(Direction direction) {
        switch (direction) {
        case UP:
            return PictureFiles.HEADUP;
        case RIGHT:
            return PictureFiles.HEADRIGHT;
        case DOWN:
            return PictureFiles.HEADDOWN;
        case LEFT:
            return PictureFiles.HEADLEFT;
        }
        return null;
    }

}
