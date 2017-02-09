package com.snakegame.model;

/***
 * Stores the different theme options
 * 
 *
 */
public class ThemeOptions {
    private String[] themes;

    /***
     * Constructor.
     */
    public ThemeOptions() {
        super();
        this.themes = new String[4];
        themes[0] = "/resources/mira/";
        themes[1] = "/resources/basic/";
        themes[2] = "/resources/mira2/";
        themes[3] = "/resources/tentacle/";
    }

    /**
     * Get all the available themes
     * 
     * @return the themes
     */
    public String[] getThemes() {
        return themes;
    }

}