package com.snakegame;

/***
 * Picture filename class.
 *
 */
public enum PictureFiles {
    EMPTY("ures.png"), //
    TAILLEFT("farokbal.png"), //
    TAILUP("farokfel.png"), //
    TAILRIGHT("farokjobb.png"), //
    TAILDOWN("farokle.png"), //
    HEADUP("fejfel.png"), //
    HEADRIGHT("fejjobb.png"), //
    HEADDOWN("fejle.png"), //
    HEADLEFT("fejbal.png"), //
    BODY_VERTICAL("testfugg.png"), //
    BODY_HORIZONTAL("testvizsint.png"), //
    FOOD("kaja.png"), //
    GAMEBG("gamebg.png"), // 12
    TABLE("table.png"), //
    SCORE("highscore.png"), //
    SLANT_RIGHT_DOWNER("ferde1.png"), // rightdown
    SLANT_LEFT_DOWNER("ferde2.png"), // leftdown
    SLANT_LEFT_UPPER("ferde3.png"), // leftup
    SLANT_RIGHT_UPPER("ferde4.png"), // rightup
    BONUSFOOD("kaja2.png"), //
    NEWPART("zoldkor.png"), //
    OPENBG("openbg.png"), //
    GAMEOVERSTUN("gameover_stunned.png"), //
    GAMEOVERBITE("gameover_dead.png"); //

    private String fileName;

    /**
     * Constructor.
     * 
     * @param fileNAme
     */
    PictureFiles(String fileNAme) {
        this.fileName = fileNAme;
    }

    /**
     * Get the value
     * 
     * @return the value
     */
    public String getValue() {
        return fileName;
    }

}
