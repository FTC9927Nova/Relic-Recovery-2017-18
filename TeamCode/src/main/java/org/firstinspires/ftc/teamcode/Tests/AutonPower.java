package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/9/17.
 */

@Autonomous(name = "pwr test")
public class AutonPower extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,this,gyro);
        if(opModeIsActive())
        {
            robot.driveTrain.setRightPower(1);
            robot.driveTrain.setLeftPower(1);
        }
    }
}
