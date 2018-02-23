package org.firstinspires.ftc.teamcode.Autons;

import android.util.Log;

import com.qualcomm.hardware.lynx.LynxVoltageSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;
import org.firstinspires.ftc.teamcode.Util.*;

/**
 * Created by Sumanth Kondapalli on 11/16/2017.
 */
@Autonomous(name = "RedGlyphy")
public class RedGlyphy extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    VisionUtil vision = new VisionUtil(this);
    MultiThreadBar4Lift threadBar4Lift = new MultiThreadBar4Lift();
    RelicRecoveryVuMark reading;

    double firstAnlge;



    @Override
    public void runOpMode() throws InterruptedException {



        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        reading = vision.readGraph(hardwareMap);

        double heading;

        int dist = 0;



        waitForStart();
        if (opModeIsActive()){
            firstAnlge = gyro.getYaw();
            threadBar4Lift.run();
            robot.jewelArm.armDown();



        sleep(1000);

            if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){
                telemetry.addData(String.valueOf(robot.jewelArm.getColor()),"00");
                telemetry.update();

                robot.driveTrain.setMoveDist(4);
                dist-=4;

            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "RED"){

                telemetry.addData(String.valueOf(robot.jewelArm.getColor()),"00");
                telemetry.update();

                robot.driveTrain.setMoveDist(-4);
                dist+=4;

            }
//
            robot.jewelArm.armMid();
            robot.jewelArm.arm2Mid();


            robot.driveTrain.setMoveDist(8 + dist);

            RelicRecoveryVuMark reading = vision.readGraph2(hardwareMap);


            robot.driveTrain.setMoveDist(18);

            telemetry.addData("vumark 1", reading);
            telemetry.update();


            switch (reading){
                case RIGHT:{
                    robot.driveTrain.setMoveDist(1.7);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);
                    robot.driveTrain.rotateDeg(88);
//                    robot.bar4.setMoveAngle(145);

                    placeBlock();
                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(11);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.driveTrain.rotateDeg(89);

//                    robot.bar4.setMoveAngle(145);
                    placeBlock();

                    break;
                }
                case LEFT:{


                    robot.driveTrain.setMoveDist(19);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.driveTrain.rotateDeg(89);
//                    robot.bar4.setMoveAngle(145);
                    placeBlock();

                    break;
                }
                default:{
                    robot.driveTrain.rotateDeg(90);
                    placeBlock();


                    break;

                }
            }


            heading = gyro.getYaw();
            telemetry.addData("heading",heading);
            Log.i("heading1",String.valueOf(heading));
            telemetry.update();

            robot.driveTrain.rotateDeg(180);

            robot.wheels.setRightWheels(0);
            robot.wheels.setLeftWheelPwr(0);

            timer.startTime();
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            while(robot.bumper.isPressed() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){

                if (timer.milliseconds() <= 1000) {

                    robot.wheels.setLeftWheelPwr(0);
                    robot.wheels.intakeRight();

                }
                else{

                    robot.wheels.intakeRight();
                    robot.wheels.setLeftWheelPwr(-0.4);
                }
                robot.driveTrain.setLeftPower(0.2);
                robot.driveTrain.setRightPower(0.2);



            }

            Log.i("AngleAfterBlock: ",String.valueOf(gyro.getYaw()));

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;

            robot.driveTrain.setMoveDist(-10.5);
            heading -= gyro.getYaw();
            telemetry.addData("yaw",gyro.getYaw());
            Log.i("getYaw",String.valueOf(gyro.getYaw()));
            telemetry.addData("heading",heading);
            Log.i("heading2",String.valueOf(heading));

            telemetry.update();
            robot.driveTrain.rotateDeg(-heading);

            Log.i("angleAfterTurn",String.valueOf(gyro.getYaw()));

            robot.driveTrain.setMoveDistEnc((leftTarget- (10 * constant.getTICKS_PER_INCH())));




            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            robot.bar4.setMoveAngle(167);



            placeBlock();
            robot.bar4.setPower(0);
            robot.driveTrain.setMoveDist(-10);
            robot.driveTrain.rotateDeg(heading);
            robot.driveTrain.setMoveDist(-20);
            robot.driveTrain.setMoveDist(10);

        }

    }

    public void placeBlock(){
        robot.driveTrain.setMoveDist(-2);
        robot.wheels.setRightWheels(1);
        robot.wheels.setLeftWheelPwr(1);
        sleep(700);
        robot.wheels.stopLeft();
        robot.wheels.stopRight();
        robot.driveTrain.setMoveDist(-8);

    }

}
