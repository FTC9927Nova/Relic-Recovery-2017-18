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
@Autonomous(name = "2ndGlyphyTest")
@Disabled
public class SecondGlyphy extends LinearOpMode
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
            if(robot.wheels.secondGlyphIntake()) {
                if(startTime==0)
                    startTime = timer.milliseconds();
                else {
                    if (((timer.milliseconds() - startTime) > 500)) {
                        if((timer.milliseconds()-startTime)<1500)
                        {
                            robot.wheels.intakeRight();
                            robot.wheels.intakeLeft();
                        }
                        else
                        {
                            robot.wheels.stop();
                        }
                    } else {
                        robot.wheels.setLeftWheelPwr(-1);
                        robot.wheels.setRightWheels(0.2);
                    }
                }
             //   robot.wheels.stop();
            }
            else
            {
                robot.wheels.intakeLeft();
                robot.wheels.intakeRight();
            }
            telemetry.addData("",robot.wheels.getWheelVelocity());
            telemetry.update();
        }
    }
}
