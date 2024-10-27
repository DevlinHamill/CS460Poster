package com.example.poster;

/**
 * CS 460 Poster
 * @author Devlin Hamill
 */

/**
 * contains all poster data as an object
 */
public class Poster {

    /**
     * holds the name of the movie
     */
    String name;
    /**
     * holds the author info
     */
    String createdBy;
    /**
     * holds the description of the movie
     */
    String story;
    /**
     * holds the image resource as a integer
     */
    int image;
    /**
     * A boolean varaiable that will be updated later if the user has selected the current poster
     */
    Boolean isSelected = false;
    /**
     * contains the 5 star rating of the movie as a float.
     */
    float rating;

}
