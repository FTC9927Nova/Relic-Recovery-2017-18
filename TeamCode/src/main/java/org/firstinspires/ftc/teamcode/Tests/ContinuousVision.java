package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

import static java.lang.Thread.sleep;

/**
 * Created by Ethan Pereira on 11/30/2017.
 */
@Autonomous(name = "cVision")
public class ContinuousVision extends LinearOpMode {

    CheckVision checkVision = new CheckVision();

    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        checkVision.setHardwareMap(hardwareMap);

        waitForStart();
        if (opModeIsActive()){
        checkVision.startThread();

        }


//
//
//            switch (reading){
//                case RIGHT:{
//                    robot.driveTrain.setMoveDist(-66-25);
//                    robot.driveTrain.rotateDeg(87.5);
//
//                    placeBlock();
//                    break;
//                }
//                case CENTER:{
//
//                    robot.driveTrain.setMoveDist(-66-10);
//                    robot.driveTrain.rotateDeg(87.5);
//
//                    placeBlock();
//                    break;
//                }
//                case LEFT:{
//                    robot.driveTrain.setMoveDist(-64);
//                    robot.driveTrain.rotateDeg(87.5);
//
//                    placeBlock();
//                    break;
//                }
//                default:{
//
//                    robot.driveTrain.rotateDeg(87.5);
//
//
//                    placeBlock();
//                    break;
//
//                }
//            }

//
//            robot.driveTrain.rotateDeg(175);
//            timer.startTime();
//            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
//            while(!robot.bumper.isPressed() && opModeIsActive()){
//
//                if (timer.milliseconds() <= 2000) {
//
//                    robot.wheels.intakeRight();
//                    robot.wheels.intakeLeft();
//
//                }
//                else{
//
//                    robot.wheels.intakeLeft();
//                    robot.wheels.setRightWheels(0);
//
//                }
//                robot.driveTrain.setLeftPower(1);
//                robot.driveTrain.setRightPower(1);
//
//
//
//            }
//
//            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;
//
//            robot.driveTrain.setLeftPower(0);
//            robot.driveTrain.setRightPower(0);
//            robot.wheels.setLeftWheelPwr(0);
//            robot.wheels.setRightWheels(0);
//
//            robot.driveTrain.rotateDeg(-175);
//
//            robot.bar4.setPower(.25);
//            sleep(500);
//
//            robot.driveTrain.setMoveDistEnc(leftTarget);
//            placeBlock();
//
//        }
//
//    }
//
//    public void placeBlock(){
//
//        robot.driveTrain.setMoveDist(3);
//        robot.wheels.outtakeLeft();
//        robot.wheels.outtakeRight();
//        sleep(500);
//        robot.wheels.stopLeft();
//        robot.wheels.stopRight();
//        robot.driveTrain.setMoveDist(-3);
    }
}



