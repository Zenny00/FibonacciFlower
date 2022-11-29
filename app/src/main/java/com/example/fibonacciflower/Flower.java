package com.example.fibonacciflower;

public class Flower {
    //Constants for math
    public final double GOLDEN_RATIO = 0.618033989;
    public final double GROW_WIDTH = 0.03 * GOLDEN_RATIO;
    public final double GROW_HEIGHT = 0.03 * GOLDEN_RATIO;

    //Attributes for each petal
    private double angle;
    private int rotate;
    private float scaleX;
    private float scaleY;
    private int xCenter;
    private int yCenter;
    private float degenerate;

    //Constructor
    public Flower()
    {
        rotate = 0;
        scaleX = (float) 0.3;
        scaleY = (float) 0.3;
        degenerate = (float) 1.001;
        angle = 360 * GOLDEN_RATIO;
    }

    //Setup first petal
    public void initialize()
    {
        rotate = 0;
        scaleX = (float) 0.3;
        scaleY = (float) 0.3;
        degenerate = (float) 1.001;
        angle = 360 * GOLDEN_RATIO;
    }

    //Getters
    public float getScaleX()
    {
        return scaleX;
    }

    public float getScaleY()
    {
        return scaleY;
    }

    public int getRotate()
    {
        return rotate;
    }

    public int get_xCenter()
    {
        return xCenter;
    }

    public int get_yCenter()
    {
        return yCenter;
    }

    //Setters
    public void setScaleX(float scale)
    {
        scaleX = scale;
    }

    public void setScaleY(float scale)
    {
        scaleY = scale;
    }

    public void setRotate(int rot)
    {
        rotate = rot;
    }

    public void set_xCenter(int x)
    {
        xCenter = x;
    }

    public void set_yCenter(int y)
    {
        yCenter = y;
    }

    public void setDegenerate(float deg)
    {
        degenerate = deg;
    }

    //Setup functions
    public void initializeAngle()
    {
        angle = 360 * GOLDEN_RATIO;
    }

    public void updatePetalValues()
    {
        rotate += angle;
        scaleX += scaleX * GROW_WIDTH;
        scaleY += scaleY * GROW_HEIGHT;
        angle *= degenerate;
    }
}
