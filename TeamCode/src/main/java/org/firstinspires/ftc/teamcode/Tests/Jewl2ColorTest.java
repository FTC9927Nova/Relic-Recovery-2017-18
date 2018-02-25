package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Ethan Pereira on 2/24/2018.
 */
@Autonomous(name = "Jewl2 ColorTest")
public class Jewl2ColorTest  extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot();
        Gyro gyro = new Gyro();
        robot.init(hardwareMap, this, gyro);
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Pos", String.valueOf(robot.jewelArm.getPos2()));
            telemetry.update();

            //            robot.jewelArm.arm2Down();
//            sleep(1000);
//
//            if(String.valueOf(robot.jewelArm.getColor2()) == "BLUE"){
//                telemetry.addData(String.valueOf(robot.jewelArm.getColor2()),"00");
//                telemetry.update();
//
//                robot.driveTrain.setMoveDist(4);
//
//            }
//
//
//            else if(String.valueOf(robot.jewelArm.getColor2()) == "RED"){
//
//                telemetry.addData(String.valueOf(robot.jewelArm.getColor2()),"00");
//                telemetry.update();
//
//                robot.driveTrain.setMoveDist(-4);
//
//
//            }
//            robot.jewelArm.arm2Mid();
        }
    }
}
