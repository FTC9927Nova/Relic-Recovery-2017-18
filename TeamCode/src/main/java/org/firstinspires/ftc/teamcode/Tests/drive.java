package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/11/17.
 */

@Disabled
@TeleOp(name = "asdfadf")
public class drive extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData(robot.driveTrain.display(),"");
            telemetry.update();
        }
    }
}
