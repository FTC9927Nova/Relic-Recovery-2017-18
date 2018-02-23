package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Bumper;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.Potentiometer;

/**
 * Created by Sumanth on 11/3/17.
 */


@TeleOp(name = "Potentiometer Test")
public class PotTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();



    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(this.hardwareMap);
        robot.init(hardwareMap,this,gyro);
        waitForStart();
        if (opModeIsActive())
        {
          robot.bar4.setMoveAngle(185);

        }
    }
}
