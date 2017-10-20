package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by therat0981 on 10/15/17.
 */


@TeleOp(name = "Gyro Test the third")
public class myGyroTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode()
    {
        waitForStart();
        while (opModeIsActive())
        {
            telemetry.addData("yaw", gyro.getYaw());
            telemetry.update();
        }
    }
}
