package org.firstinspires.ftc.teamcode.Autons.Actions;


/**
 * Created by therat0981 on 11/2/17.
 */

public class Wait implements Action
{
    private double waitTime;
    private double StartTime;

    public Wait(double time)
    {
        waitTime = time;
    }
    @Override
    public void start()
    {
        StartTime = System.nanoTime();
    }

    @Override
    public void update()
    {

    }

    @Override
    public boolean isFinished() {
        return ((System.nanoTime()- StartTime)>waitTime);
    }

    @Override
    public void stop() {

    }
}
