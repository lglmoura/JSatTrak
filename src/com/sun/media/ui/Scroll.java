// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Scroll.java

package com.sun.media.ui;

import java.awt.*;
import java.awt.event.*;

// Referenced classes of package com.sun.media.ui:
//            BasicComp

public class Scroll extends Component
    implements MouseListener, MouseMotionListener
{

    public Scroll()
    {
        this(null, null);
    }

    public Scroll(float detents[])
    {
        this(detents, null);
    }

    public Scroll(float detents[], Color background)
    {
        paintG = null;
        leftBorder = 8;
        rightBorder = 8;
        lower = 0.0F;
        upper = 1.0F;
        range = 1.0F;
        value = 0.5F;
        dragging = false;
        grabberVisible = true;
        actionListener = null;
        imageGrabber = BasicComp.fetchImage("grabber.gif");
        imageGrabberDown = BasicComp.fetchImage("grabber-pressed.gif");
        imageGrabberX = BasicComp.fetchImage("grabber-disabled.gif");
        this.detents = detents;
        if(background != null)
            setBackground(background);
        width = 115;
        height = 18;
        displayPercent = 100;
        dimension = new Dimension(width, height);
        sliderWidth = width - leftBorder - rightBorder;
        setSize(width, height);
        setVisible(true);
        grabbed = false;
        entered = false;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setActionListener(ActionListener al)
    {
        actionListener = al;
    }

    public void setValue(float value)
    {
        lower = 0.0F;
        upper = 1.0F;
        range = upper - lower;
        setSliderPosition(value - lower, range);
        repaint();
    }

    public float getValue()
    {
        return value;
    }

    public void setEnabled(boolean state)
    {
        super.setEnabled(state);
        repaint();
    }

    public Point getPosition()
    {
        return new Point(grabberPosition + leftBorder, 10);
    }

    public void setDisplayPercent(int percent)
    {
        if(percent != displayPercent)
        {
            displayPercent = percent;
            if(displayPercent > 100)
                displayPercent = 100;
            else
            if(displayPercent < 0)
                displayPercent = 0;
            repaint();
        }
    }

    public void paint(Graphics g)
    {
        Dimension size = getSize();
        int y = size.height / 2 - 2;
        paintG = g;
        int grabberX = (grabberPosition + leftBorder) - 5;
        g.setColor(getBackground());
        y = getSize().height / 2 - 2;
        g.draw3DRect(2, y, size.width - 4, 3, false);
        if(displayPercent < 100)
        {
            g.setColor(Color.green);
            int x = (sliderWidth * displayPercent) / 100 + 3;
            y += 2;
            g.drawLine(x, y, size.width - 4, y);
        }
        if(detents != null && detents.length != 0)
        {
            paintG.setColor(Color.black);
            for(int i = 0; i < detents.length; i++)
            {
                int x = leftBorder + (int)((detents[i] * (float)sliderWidth) / range);
                paintG.drawLine(x, 12, x, 15);
            }

        }
        if(grabberVisible)
        {
            Image image;
            if(isEnabled())
            {
                if(grabbed || entered)
                    image = imageGrabberDown;
                else
                    image = imageGrabber;
            } else
            {
                image = imageGrabberX;
            }
            paintG.drawImage(image, grabberX, 4, this);
        }
    }

    private int limitGrabber(int mousex)
    {
        int x = mousex - leftBorder;
        if(x < 0)
            x = 0;
        else
        if(x > sliderWidth)
            x = sliderWidth;
        return x;
    }

    private void setSliderPosition(float value, float range)
    {
        grabberPosition = (int)((value / range) * (float)sliderWidth);
    }

    private void seek()
    {
        value = (float)grabberPosition / (float)sliderWidth;
        if(detents != null && detents.length > 0 && dragging)
        {
            float tolerance = 0.05F;
            for(int i = 0; i < detents.length; i++)
                if(Math.abs(detents[i] - value) <= tolerance)
                    value = detents[i];

        }
        repaint();
        if(actionListener != null)
            actionListener.actionPerformed(new ActionEvent(this, 1001, "scroll"));
    }

    public void mousePressed(MouseEvent me)
    {
        int modifier = me.getModifiers();
        if((modifier & 8) == 0 && (modifier & 4) == 0 && isEnabled())
        {
            dragging = false;
            grabbed = true;
            grabberPosition = limitGrabber(me.getX());
            seek();
        }
    }

    public void mouseReleased(MouseEvent me)
    {
        int modifier = me.getModifiers();
        if((modifier & 8) == 0 && (modifier & 4) == 0 && isEnabled())
        {
            dragging = false;
            grabbed = false;
            repaint();
        }
    }

    public void mouseDragged(MouseEvent me)
    {
        int modifier = me.getModifiers();
        if((modifier & 8) == 0 && (modifier & 4) == 0 && isEnabled())
        {
            dragging = true;
            grabberPosition = limitGrabber(me.getX());
            seek();
        }
    }

    public void mouseEntered(MouseEvent me)
    {
        entered = true;
        repaint();
    }

    public void mouseExited(MouseEvent me)
    {
        entered = false;
        repaint();
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
    }

    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        paintG = null;
        repaint();
    }

    public Dimension getPreferredSize()
    {
        return dimension;
    }

    Image imageGrabber;
    Image imageGrabberX;
    Image imageGrabberDown;
    Graphics paintG;
    boolean grabbed;
    boolean entered;
    int grabberPosition;
    int leftBorder;
    int rightBorder;
    int sliderWidth;
    int width;
    int height;
    int displayPercent;
    float detents[];
    Dimension dimension;
    float lower;
    float upper;
    float range;
    float value;
    boolean dragging;
    boolean grabberVisible;
    ActionListener actionListener;
}
