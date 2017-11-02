package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Autonomous(name = "Blue Jewl Park Auto ")
public class BLUEJewlParkAuton extends LinearOpMode{

    Robot robot = new Robot();

    Gyro gyro = new Gyro();

    int dist = -20;
    RobotConstants constants = new RobotConstants();



    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();



        if(opModeIsActive()){
            robot.claw.close();
            robot.driveTrain.setMoveDist(-2);

            robot.jewelArm.armDown();

            sleep(1000);

//

            if(String.valueOf(robot.jewelArm.getColor()) == "RED"){

                robot.driveTrain.setMoveDist(4);
                dist-=2;

            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){

                robot.driveTrain.setMoveDist(-4);

                dist+=4;

            }

            robot.jewelArm.armMid();
            sleep(1000);
            robot.driveTrain.setMoveDist(-dist);
            while(Math.abs(gyro.getYaw() + 37.5) > constants.getTurnTolerance() && opModeIsActive()){

                robot.driveTrain.setLeftPower(0.1);
                robot.driveTrain.setRightPower(-0.1);

            }
            robot.driveTrain.setMoveDist(25);
            sleep(1000);
            robot.claw.open();
            sleep(1000);
            robot.elevator.moveLevel(1);
            sleep(1000);
            robot.driveTrain.setMoveDist(-2);

        }


    }


}