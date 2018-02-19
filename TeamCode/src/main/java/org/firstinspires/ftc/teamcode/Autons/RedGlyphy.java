package org.firstinspires.ftc.teamcode.Autons;

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


            robot.jewelArm.armDown();

            sleep(1000);

            while(!robot.bar4.isHit())
                robot.bar4.setPower(0.4);
            robot.bar4.setPower(0);


            if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){

                robot.driveTrain.setMoveDist(4);
                dist-=4;

            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "RED"){

                robot.driveTrain.setMoveDist(-4);

                dist+=4;

            }
//
            robot.jewelArm.armMid();



            robot.driveTrain.setMoveDist(8 + dist);

            RelicRecoveryVuMark reading = vision.readGraph2(hardwareMap);
            //robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

            robot.driveTrain.setMoveDist(18);

            telemetry.addData("vumark 1", reading);
            telemetry.update();


            switch (reading){
                case RIGHT:{
                    robot.driveTrain.setMoveDist(2.7);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);
                    robot.driveTrain.rotateDeg(85);

                    placeBlock();
                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(12.5);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.driveTrain.rotateDeg(85);

                    placeBlock();
                    break;
                }
                case LEFT:{


                    robot.driveTrain.setMoveDist(20);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.driveTrain.rotateDeg(85);

                    placeBlock();
                    break;
                }
                default:{
                    robot.driveTrain.rotateDeg(90);


                    placeBlock();

                    break;

                }
            }
            robot.bar4.setPower(0);

            heading = gyro.getYaw();
            robot.driveTrain.rotateDeg(180);
            robot.wheels.setRightWheels(0);
            robot.wheels.setLeftWheelPwr(0);



            //code to life arm then come down
            robot.bar4.setPower(0.5);
            sleep(200);
            robot.bar4.setPower(-0.1);
            sleep(100);
            //DOnee
            timer.startTime();
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            while(robot.bumper.isPressed() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){

                if (timer.milliseconds() <= 2000) {

                    robot.wheels.intakeLeft();
                    robot.wheels.setRightWheels(-0.5);


                }
                else{

                    robot.wheels.intakeRight();
                    robot.wheels.setLeftWheelPwr(-0.5);

                }
                robot.driveTrain.setLeftPower(0.2);
                robot.driveTrain.setRightPower(0.2);



            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);

            sleep(300);

            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;


            heading -= gyro.getYaw();
            robot.driveTrain.rotateDeg(-heading);







            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            robot.bar4.setPower(0.8);

            robot.driveTrain.setMoveDistEnc((leftTarget- (5 * constant.getTICKS_PER_INCH())));


            robot.driveTrain.setMoveDist(13);
            robot.bar4.setPower(0.04);



            placeBlock();
            robot.bar4.setPower(0);
            robot.driveTrain.setMoveDist(-10);
            robot.driveTrain.rotateDeg(180);
            robot.driveTrain.setMoveDist(-20);
            robot.driveTrain.setMoveDist(10);

        }

    }

    public void placeBlock(){
        robot.driveTrain.setMoveDist(2);
        robot.wheels.setRightWheels(1);
        robot.wheels.setLeftWheelPwr(1);
        sleep(500);
        robot.wheels.stopLeft();
        robot.wheels.stopRight();
        robot.driveTrain.setMoveDist(-8);

    }

}
