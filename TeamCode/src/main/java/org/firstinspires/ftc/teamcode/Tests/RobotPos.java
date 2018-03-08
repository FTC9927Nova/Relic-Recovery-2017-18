package org.firstinspires.ftc.teamcode.Tests;

import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;

/**
 * Created by Ethan Pereira on 3/7/2018.
 */

public class RobotPos
{
    //X Y Thetea



    private double x;
    private double y;
    private double phi;

    public RobotPos(double x, double y, double phi)
    {
        this.x = x;
        this.y = y;
        this.phi = phi;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getPhi()
    {
        return phi;
    }

    public GeneralMatrixF getMat()
    {
        float[] data =
                {
                        (float)x,(float)y,(float)phi
                };

        return new GeneralMatrixF(1,3, data);
    }

    public void update(double dl, double dr)
    {
        double Dc = (dl+dr)/2;
        x+= Dc*Math.cos(phi);
        y+=Dc*Math.sin(phi);
        phi += (dr-dl)/2;
    }


}
