package org.firstinspires.ftc.teamcode.Autons;

import android.accounts.AuthenticatorException;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autons.Actions.Action;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by therat0981 on 11/2/17.
 *
 *
 *
 * Inspired by FRC Team 254 Cheesy Poofs
 */

public abstract class AutoModeBase
{

    public abstract void routine() throws UnexpectedStop;
    private boolean running;
    private LinearOpMode linearOpMode;

    public AutoModeBase(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
    }

    public void run()
    {
        running = true;
        try
        {
            routine();
        } catch (UnexpectedStop e)
        {
            return;
        }
        stop();
    }

    private void stop()
    {

    }

    public boolean isRunning()
    {
        return running;
    }

    public boolean isRunningWithException() throws UnexpectedStop
    {
        if(!isRunning())
            throw new UnexpectedStop();
        return isRunning();
    }

    public void runAction (Action action) throws UnexpectedStop
    {
        action.start();
        isRunningWithException();
        while(linearOpMode.opModeIsActive()
                && isRunningWithException()
                && !action.isFinished())
        {
            action.update();
            try
            {
                Thread.sleep(20);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        action.stop();
    }





}
