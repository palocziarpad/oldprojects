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
    BODYHOR("testfugg.png"), //
    BODYVER("testvizsint.png"), //
    FOOD("kaja.png"), //
    GAMEBG("gamebg.png"), //
    TABLE("table.png"), //
    SCORE("highscore.png"), //
    SLANTRD("ferde1.png"), // rightdown
    SLANTLD("ferde2.png"), // leftdown
    SLANTLU("ferde3.png"), // leftup
    SLANTRU("ferde4.png"), // rightup
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
