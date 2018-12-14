/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: Shape.java
 * Source of Help: tutor, website
 * This program find the location
 */

import java.awt.*;
import objectdraw.*;

/**
 * This class create shape hierarchy
 */

public abstract class Shape{
    private String name;
   
     /**
      * Method name is Shape
      * purpose: 
      * @param no parameter
      * @return void
      */

    public Shape(){
        this.setName("Shape");
    }

     /**
      * name is Shape
      * purpose: 
      * @param name is String
      * @return void
      */

    public Shape(String name){
        this.setName(name);
    }


     /**
      * Method name is getName
      * purpose: 
      * @param no parameter
      * @return void
      */
    public String getName(){
        return name;
    }

    /**
      * Method name is setName
      * purpose: 
      * @param name is string
      * @return void
      */
    private void setName(String name){
        this.name = name;
    }




    /* Copy this as is in your Shape.java */
    @Override
    public boolean equals (Object o){
        String s = "\n**************************\n"
            +"This should never print. If it does print, then/n"
            +"you did not overrde equals() properlu in class"
            + this.getClass().getName() + "\n"
            + "*********************************\n";

        System.out.println(s);
        return this == o;
    }

     /**
      * Method name is move
      * purpose: 
      * @param xDelta and yDelta is int
      * @return void
      */

    public abstract void move(int xDelta, int yDelta);


    /**
      * Method name is draw
      * purpose: 
      * @param canvas is DrawingCanvas, c is Color, fill boolean
      * @return void
      */
    public abstract void draw(DrawingCanvas canvas, Color c, boolean fill);


}
