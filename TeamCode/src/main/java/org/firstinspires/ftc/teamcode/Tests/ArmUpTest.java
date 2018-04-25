package org.firstinspires.ftc.teamcode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 2/24/2018.
 */
@Autonomous(name = "Arm Up Test ")
@Disabled
public class ArmUpTest extends LinearOpMode
{
    Robot robot = new Robot();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    double startTime = 0;
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,this,gyro);
        waitForStart();
        timer.startTime();
        while(opModeIsActive())
        {
           if(robot.bar4.setMoveHeight(10))
               telemetry.addData("yay","neing");
           else
               telemetry.addData("asf","fasdfa");
           telemetry.update();
        }
    }
}
