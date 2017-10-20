package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by therat0981 on 10/19/17.
 */

@TeleOp(name = "Rotate Deg Test")
public class RotateDegTest extends LinearOpMode
{
    Robot robot = new Robot();
//      Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException
    {
        robot.init(hardwareMap);
        waitForStart();
        if(opModeIsActive())
        {
            robot.driveTrain.rotateDeg(90);
        }
    }
}
