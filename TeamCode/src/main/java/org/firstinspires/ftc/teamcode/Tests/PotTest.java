package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/3/17.
 */


@TeleOp(name = "bar4 Test")
public class PotTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();



    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(this.hardwareMap);
        robot.init(hardwareMap,this,gyro);
        waitForStart();
        while (opModeIsActive())
        {
            robot.bar4.setMoveHeight(10);
        }
    }
}
