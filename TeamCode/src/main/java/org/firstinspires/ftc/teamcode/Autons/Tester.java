package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Autonomous(name = "autonGlyphTest")
public class Tester extends LinearOpMode {

    Robot robot = new Robot();

    Gyro gyro = new Gyro();


    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();

        robot.jewelArm.armMid();

        if (opModeIsActive()) {

            while(!robot.bumper.isPressed() && opModeIsActive()){

                robot.driveTrain.setLeftPower(1);
                robot.driveTrain.setRightPower(1);
                robot.wheels.intakeRight();
                robot.wheels.intakeLeft();

            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);

        }
    }

}
