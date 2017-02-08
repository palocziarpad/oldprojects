package com.snakegame.model;

import java.io.File;

public class DirListing {
    private static void dirlist(String fname) {
        File dir = new File(fname);
        String[] chld = dir.list();
        File[] files = dir.listFiles();
        if (chld == null) {
            System.out.println("Specified directory does not exist or is not a directory.");
            System.exit(0);
        } else {
            for (int i = 0; i < chld.length; i++) {
                String fileName = chld[i];
                if (files[i].isDirectory())
                    System.out.println(files[i].getName());
            }
        }
    }

    /*
     * public static void main(String[] args) { dirlist("resources"); /*switch
     * (args.length) { case 0:
     * System.out.println("Directory has not mentioned."); System.exit(0); case
     * 1: dirlist(args[0]); System.exit(0); default:
     * System.out.println("Multiple files are not allow."); System.exit(0); }
     */
    // }*/
}