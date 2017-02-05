
/* 
 * Filename: QuickButton.java 
 * Written By: Sunit Katkar 
 * E-Mail:sunitkatkar@hotmail.com 
 * Home-Page : www.vidyut.com/sunit 
 * Java Page : www.vidyut.com/sunit/JavaPage.html 
 ****************************************************************** 
 * Description: 
 * Are you bored waiting for an image to download in a applet ? 
 * Do you site behind a firewall? Does a ImageButton, which you see 
 * everywhere, take long to load ? Then here is a simple NON-IMAGE 
 * yet somewhat attractive and configurable button which loads 
 * quickly. 
 * **************************************************************** 
 * Copyright (c) 1997 Sunit Katkar All Rights Reserved. 
 * 
 * Permission to use, copy, modify, and distribute this software 
 * for NON-COMMERCIAL or COMMERCIAL purposes and without fee 
 * is hereby granted. 
 * 
 * Whew ! That was too much legalese..even to have copied and pasted 
 * from some other place... PLEASE DO NOT BLAME ME in any way 
 * if your system crashes because of this code, or if anything else 
 * bad happens. In short "DO WHAT YOU WANT BUT DONT BLAME ME !" 
 *****************************************************************/

 import java.awt.*;
import java.applet.*;
public class QuickButton extends Canvas
{
        public  String label,font;
        public  Font fnt;
        public  int fontsize ;
        public  Color c;
        public  Dimension QuickButtonsize;

       /**
        * Two booleans to test if the mouse is down and to see
        * if the enablebutton()/disablebutton() method was
        * called by the parent applet
        **/

        //Is the mouse down? default -> false
        private boolean is_the_mouse_down = false;
        //true => enable the button
        private boolean you_want_to_disableMe = false;

       /**
        * Default constructor
       **/
        public QuickButton()
        {
                //Nothing spectacular happening :)
                super();
        }
       /**
        * public QuickButton(String label, String font, int fontsize, Color c)  
        * QuickButton Constructor  takes
        * - String label - text which is the button's label
        * - String font  -  Font to construct the label
        * - int fontsize - Size of font
        * - Color c      - Color to use as foreground of label
        **/
        public QuickButton(String label, String font, int fontsize, Color c)
        {
                this.label = label;
                this.font = font;
                this.fontsize = fontsize;
                this.c = c;
                //We use a fixed Font style, try changing this to suit your
                //needs. Maybe include this style parameter in the constructor
                fnt = new Font(font,Font.BOLD,fontsize);
        }

     /**
      * Returns the preferred size of this component. This method has to be
      * over-ridden, as it is called during addNotify() to establish the initial
      * size of the Canvas, and the default size is (0, 0).
     **/
    public Dimension preferredSize()
    {
      //must over-ride this, or there is NO canvas to draw!
          return QuickButtonsize;
    }
     /**
      * Returns the minimum size of this component. This method has to be
      * over-ridden, as it is called by many layout managers. Connected AWT
      * components call the peer for this information.
     **/
    public Dimension minimumSize()
    {
      //must over-ride this, or the layout manager may not function as desired
        return QuickButtonsize;
    }
     /**
      * Creates the peer.  This peer allows you to change the
      * user interface of the canvas without changing its functionality.
     **/
    public void addNotify()
    {
        super.addNotify();
       //Button size is dependent (by our choice) on the fontmetrics of the label and its font.
        QuickButtonsize = measureMe();
    }

     /**
      * Calculates the dimensions that are required by the particular label
      * using the particular font. Then calculates the button size
      * accordingly.
     **/
     protected Dimension measureMe()
     {
        FontMetrics fmt = this.getFontMetrics(fnt);
                if (fmt == null)
                {
                        return new Dimension(0,0);
                }
                else
                {
                  //added pixels for visual adjustment
                  int width = fmt.stringWidth(label)+15;
                  int actualheight = fmt.getHeight()+ fmt.getAscent() +5;
                  int height = actualheight;
                   height = Math.max(actualheight,width/4);
                  return new Dimension(width,height) ;
                }
     }
     /**
      * Disables Quickbutton.
     **/
     protected boolean disablebutton()
     {
         //Call the disable() method of super class java.awt.Component from which 
         //the Canvas is derived and our QuickButton is derived from Canvas
         super.disable();
         //set the boolean to true indicating that parent asked that the button be disabled.
         you_want_to_disableMe = true;
         //Call repaint to refresh button display
         repaint();
         //return true as this action has been performed
         return true;
     }
     /**
      * Enables Quickbutton.
     **/
    protected boolean enablebutton()
    {
         //Call the disable() method of super class java.awt.Component from which 
         //the Canvas is derived and our QuickButton is derived from Canvas
         super.enable();
         //set boolean to false that you set to true in disablebutton()
         you_want_to_disableMe = false;
         //Call repaint to refresh button display
         repaint();
         //return true as this action has been performed
         return true;
    }

      /**
       * Paints the QuickButton
      **/
      public void paint(Graphics g)
      {
            //Check conditions and make method calls accordingly
            if(is_the_mouse_down || you_want_to_disableMe)
            {
                //if mouse is down or parent asked to
                //disable this button  draw a down state/disabled button
                paintDepressedButton(g);
            }
            else
            {
                //draw a normal button
                paintNormalButton(g);
            }
        }
       /**
       * Paints the Normal/UP State QuickButton
       **/ 
        protected void paintNormalButton(Graphics g)
        {
          //set the font
          g.setFont(fnt);
          //Get the required width and height of the button
          int w = measureMe().width;
          int h = measureMe().height;
          //Again calculate the font height & width
          int fonttotalheight = g.getFontMetrics().getHeight()
                                + g.getFontMetrics().getLeading();
          int fonttotalwidth = g.getFontMetrics().stringWidth(label);
          int drawfonty = (h-((h - fonttotalheight)/2)-4);
          int drawfontx = ((w - fonttotalwidth)/2)+2;
          //These set the button background color - try changing these
          g.setColor(Color.gray.brighter());
          g.fill3DRect(0,0,w-1,h-1,true);
          g.setColor(Color.gray.brighter());
          g.setColor(Color.white);
          g.drawString(label,drawfontx+1,drawfonty+1);
          g.setColor(c);
          g.drawString(label,drawfontx,drawfonty);
        }
       /**
       * Paints the Disabled/DOWN State QuickButton
       * Here we are basically changing the button and label background to 
       * a darker shade and shifting the label by 3 pixels 
       **/
        protected void paintDepressedButton(Graphics g)
        {
          g.setFont(fnt);

          int w = measureMe().width;
          int h = measureMe().height;
          int fonttotalheight = g.getFontMetrics().getHeight()
                                + g.getFontMetrics().getLeading();
          int fonttotalwidth = g.getFontMetrics().stringWidth(label);
          int drawfonty = (h-((h - fonttotalheight)/2)-4);
          int drawfontx = ((w - fonttotalwidth)/2)+2;
            
          g.setColor(Color.gray);
          g.fill3DRect(0,0,w-1,h-1,true);
          g.setColor(Color.black);
          g.drawString(label,drawfontx+3,drawfonty+3);
          g.setColor(Color.lightGray);
          g.drawString(label,drawfontx+2,drawfonty+2);
        }

        /**
         * Mouse handling routines
        **/
        public boolean mouseDown (Event evt, int x, int y)
        {
            is_the_mouse_down = true;
            repaint();
            //handle the event here itself so return true
            return true;
        }

        public boolean mouseUp (Event evt, int x, int y)
        {
                //Check whether a mouse down occured on the button
                //and if it did we ned to 'post' this event
                if(is_the_mouse_down)
                {
                    buttonwasClicked();
                    is_the_mouse_down = false;
                    //handle the event here itself
                    repaint();
                }
            return true ;
        }

        public boolean mouseDrag (Event evt, int x, int y)
        {
            return true ;
        }
        public boolean mouseEnter(Event evt, int x, int y)
        {
            return true;
        }

        public boolean mouseExit(Event evt, int x, int y)
        {
            return true;
        }
        /**
         * Action handling routines
        **/
        public boolean action(Event e, Object o)
        {
           //Let parent applet handle this, as parent may want 
           //to enable/disable certain instances of this 
           //button depending on certain conditions. 
           //Hence return false
           return false;

        }
        /**
         * If mouse is down on a button and a mouse up follows
         * It means that user wants to 'click' our button.
         * So we need to 'post' a event to the parent
        **/
        public void buttonwasClicked()
        {
            Event theEvent = new Event(this, Event.ACTION_EVENT, label);
            postEvent(theEvent);
        }

 } //All ends :) 