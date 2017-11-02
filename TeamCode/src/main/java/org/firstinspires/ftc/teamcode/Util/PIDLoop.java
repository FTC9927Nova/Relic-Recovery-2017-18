package org.firstinspires.ftc.teamcode.Util;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by therat0981 on 9/16/17.
 */

public class PIDLoop
{

    private double kp = 0;
    private double ki = 0;
    private double kd =0;

    private double target;

    private double error;
    private double pastError;
    private double sumError;
    private double deltaError;
    private double timeC;

    public  PIDLoop()
    {}


    public PIDLoop(double kp, double ki, double kd)
    {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    public double getKp()
    {
        return this.kp;
    }

    public double getKd()
    {
        return this.kd;
    }

    public double getKi()
    {
        return this.ki;
    }


    public void setTarget(double target)
    {
        this.target = target;
    }

    public double getError()
    {
        return error;
    }


    public double pLoop(double input)
    {
        error = target - input;
        return (kp*error);
    }

    public double pidLoop(double input, double dt)
    {
        pastError = error;
        error = target - input;
        sumError += pastError;
        if((sumError)>1)
            sumError = 1 / sumError;
        return kp * error + (((error)-pastError)/dt)*kd + ki*sumError;
    }

    public String display()
    {
        return String.valueOf(error);
    }







}
