package com.snakegame.model;

/***
 * Stores the different theme options
 * 
 */
public class ThemeOptions {

    private String[] themes;

    /***
     * Constructor.
     */
    public ThemeOptions() {
        super();
        this.themes = new String[] { "/resources/mira/", "/resources/basic/", "/resources/mira2/",
                "/resources/tentacle/" };
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