package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/9/17.
 */

@TeleOp(name = "gyro lark tester ")
@Disabled
public class GyroTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,this, gyro);

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addData("Yaw",gyro.getYaw());
            telemetry.addData("Pitch", gyro.getPitch());
            telemetry.addData("roll", gyro.getRoll());
            telemetry.update();
        }

    }
}
