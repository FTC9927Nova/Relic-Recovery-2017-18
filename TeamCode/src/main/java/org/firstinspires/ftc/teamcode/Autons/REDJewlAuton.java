package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Autonomous
public class REDJewlAuton extends LinearOpMode{

    Robot robot = new Robot();

    Gyro gyro = new Gyro();



    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();
        if(opModeIsActive()){
            robot.driveTrain.setMoveDist(-3);
            robot.jewelArm.armDown();
            if(String.valueOf(robot.jewelArm.getColor()) == "RED"){
//JewelArm.ColorDetected.RED
                sleep(1000);

                robot.driveTrain.setMoveDist(-4);

            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){
                sleep(1000);
               robot.driveTrain.setMoveDist(2);

            }

        }


    }


}
