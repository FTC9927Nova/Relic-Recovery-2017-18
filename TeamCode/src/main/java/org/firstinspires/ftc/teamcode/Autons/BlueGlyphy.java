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
@Autonomous(name = "BlueGlyphy")
public class BlueGlyphy extends LinearOpMode {

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
            robot.jewelArm.arm2Down();


            sleep(600);

            if(String.valueOf(robot.jewelArm.getColor2()) == "RED"){
                robot.driveTrain.setMoveDist(4);
                dist-=4;

            }


            else if(String.valueOf(robot.jewelArm.getColor2()) == "BLUE"){
                robot.driveTrain.setMoveDist(-4);
                dist+=4;
            }
//
            robot.jewelArm.armMid();
            robot.jewelArm.arm2Up();


            robot.driveTrain.setMoveDist(-8 + dist);

            RelicRecoveryVuMark reading = vision.readGraph2(hardwareMap);

            robot.driveTrain.setMoveDist(32);

            while(opModeIsActive()&&robot.bar4.isHit())
                robot.bar4.setPower(1);
            robot.bar4.setPower(0);







            telemetry.addData("vumark 1", reading);
            telemetry.update();

            switch (reading){
                case RIGHT:{

                    robot.driveTrain.setMoveDist(23);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw());

                    robot.driveTrain.rotateDeg(-89);
                    robot.driveTrain.setMoveDist(6);
                    robot.bar4.setMoveAngle(175);
                    placeBlock();

                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(14);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw());

                    robot.driveTrain.rotateDeg(-89);
                    robot.driveTrain.setMoveDist(6);
                    robot.bar4.setMoveAngle(175);

                    placeBlock();

                    break;
                }
                case LEFT:{
                    robot.driveTrain.setMoveDist(4);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw());
                    robot.driveTrain.rotateDeg(-88);
                    robot.driveTrain.setMoveDist(6);

                    robot.bar4.setMoveAngle(175);
                    placeBlock();
                    break;
                }
                default:{
                    robot.driveTrain.rotateDeg(-90);
                    robot.driveTrain.setMoveDist(6);

                    robot.bar4.setMoveAngle(145);


                    placeBlock();


                    break;

                }
            }


            robot.driveTrain.setMoveDist(-6);


            heading = gyro.getYaw();

            telemetry.update();

            robot.driveTrain.rotateDeg(180);
            robot.bar4.setMoveAngle(126);

            robot.wheels.setRightWheels(0);
            robot.wheels.setLeftWheelPwr(0);


            robot.driveTrain.setMoveDist(-18);
            robot.driveTrain.setMoveDist(18);


            timer.startTime();
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            robot.driveTrain.setMoveDist(12);
            while(!robot.range.isGlyph() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){

                if (timer.milliseconds() <= 1000) {

                    robot.wheels.setLeftWheelPwr(-1);
                    robot.wheels.setRightWheels(-0.3);

                }
                else{

                    robot.wheels.intakeRight();
                    robot.wheels.setLeftWheelPwr(-0.4);
                }
                robot.driveTrain.setLeftPower(0.2);
                robot.driveTrain.setRightPower(0.2);
            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;

            robot.driveTrain.setMoveDist(-12.5);
            heading -= gyro.getYaw();

            robot.bar4.setMoveAngle(167);
            robot.driveTrain.setMoveDistEnc(-(leftTarget- (10 * constant.getTICKS_PER_INCH())));
            robot.driveTrain.setMoveDist(12);
            robot.driveTrain.rotateDeg(-heading);

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            robot.bar4.setMoveAngle(167);


            placeBlock();
//            while(robot.bar4.isLowerHit())
//                robot.bar4.setPower(-1);
//            //original was 7
//            robot.driveTrain.setMoveDist(9);
//            robot.driveTrain.setMoveDist(-3);
            robot.driveTrain.rotateDeg(180);
            robot.driveTrain.setMoveDist(-8);
            robot.driveTrain.setMoveDist(6);
            //original was -2


        }

    }

    public void placeBlock(){
        sleep(200);
        robot.wheels.setRightWheels(1);
        robot.wheels.setLeftWheelPwr(1);
        sleep(1200);
//        while(robot.range.getDist()<10 && opModeIsActive()) {
//
//        }
        robot.wheels.stopLeft();
        robot.wheels.stopRight();

    }


}
