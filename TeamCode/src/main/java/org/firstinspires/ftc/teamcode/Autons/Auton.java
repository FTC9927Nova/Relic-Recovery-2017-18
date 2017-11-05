package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autons.Actions.Action;

/**
 * Created by therat0981 on 11/5/17.
 */

public class Auton extends LinearOpMode
{
    ActionTest actionTest = new ActionTest(this);
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if(opModeIsActive())
        {
            try {
                actionTest.routine();
            } catch (UnexpectedStop x)
            {

            }

        }
    }
}
