package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Disabled
@Autonomous
public class BLUEJewlAuton extends LinearOpMode{

    Robot robot = new Robot();

    Gyro gyro = new Gyro();



    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();

        robot.jewelArm.armDown();

        if (opModeIsActive()) {
            sleep(2000);

            if (String.valueOf(robot.jewelArm.getColor()).equals("BLUE")) {

                sleep(2000);
                robot.driveTrain.setLeftPower(1);
                robot.driveTrain.setRightPower(1);
                sleep(250);
                robot.driveTrain.setRightPower(0);
                robot.driveTrain.setLeftPower(0);

            } else if (String.valueOf(robot.jewelArm.getColor()).equals("RED")) {
                sleep(2000);

                robot.driveTrain.setLeftPower(-1);
                robot.driveTrain.setRightPower(-1);
                sleep(250);
                robot.driveTrain.setLeftPower(0);
                robot.driveTrain.setRightPower(0);

            }

            sleep(1000);
            robot.jewelArm.armMid();
            sleep(1000);
            robot.driveTrain.setLeftPower(1);
            robot.driveTrain.setRightPower(1);
            sleep(3000);
            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);

        }
    }
}


