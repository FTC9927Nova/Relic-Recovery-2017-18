package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by therat0981 on 10/19/17.
 */

@TeleOp(name = "Rotate Deg JewlTest")
public class RotateDegTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();
        if(opModeIsActive())
        {
            robot.driveTrain.rotateDeg(90);
            robot.driveTrain.rotateDeg(-90);
        }
    }
}
