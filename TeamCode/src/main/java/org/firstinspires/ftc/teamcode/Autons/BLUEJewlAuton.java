package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Autonomous(name = "Blue Jewl Auto ")
public class BLUEJewlAuton extends LinearOpMode{

    Robot robot = new Robot();


    Gyro gyro = new Gyro();

    int dist = -35;



    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();



        if(opModeIsActive()){

            robot.jewelArm.armDown();

            sleep(1000);

//

            if(String.valueOf(robot.jewelArm.getColor()) == "RED"){

                robot.driveTrain.setMoveDist(-4);
                telemetry.addData("color", "red");


            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){

                robot.driveTrain.setMoveDist(4);
                telemetry.addData("color", "blue");



            }
            telemetry.update();
            robot.jewelArm.armMid();
//            sleep(500);
//            robot.driveTrain.setMoveDist(dist);
//            robot.driveTrain.rotateDeg(-90);
//            robot.driveTrain.setMoveDist(9.5);
//            robot.claw.open();
//            robot.driveTrain.setMoveDist(-2);



        }


    }


}