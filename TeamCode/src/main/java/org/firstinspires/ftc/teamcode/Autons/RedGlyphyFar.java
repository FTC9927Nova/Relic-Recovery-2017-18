package org.firstinspires.ftc.teamcode.Autons;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by therat0981 on 12/14/17.
 */
@Disabled
@Autonomous(name = "RedGlyphyFar")
public class RedGlyphyFar extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();

    boolean getPictograph = true;
    VisionUtil vision = new VisionUtil(this);

    RelicRecoveryVuMark reading;
    VisionUtil visionUtil = new VisionUtil(this);

    double firstAnlge;


    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        reading = vision.readGraph(hardwareMap);

        double heading;

        int dist = 13;
        reading = vision.readGraph2(hardwareMap);
        telemetry.addData("vision", reading);
        telemetry.update();
        waitForStart();
        if (opModeIsActive()){
            firstAnlge = gyro.getYaw();
            robot.jewelArm.armDown();

            if (visionUtil.isDetected()){
                reading = vision.readGraph2(hardwareMap);
            }

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


            robot.driveTrain.setMoveDist(dist);

            telemetry.addData("vumark 1", reading);
            telemetry.update();

            robot.driveTrain.rotateDeg(-90);
            correctAtLateral();


            switch (reading){
                case RIGHT:{
                    robot.driveTrain.setMoveDist(8);
                    correctAtLateral();
                    robot.driveTrain.rotateDeg(90);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);
                    robot.driveTrain.setMoveDist(5);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);
                    robot.bar4.setMoveAngle(145);
                    placeBlock();
                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(13);
                    correctAtLateral();
                    robot.driveTrain.rotateDeg(90);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);
                    robot.driveTrain.setMoveDist(5.5);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.bar4.setMoveAngle(145);
                    placeBlock();
                    break;
                }
                case LEFT:{
                    robot.driveTrain.setMoveDist(25);
                    correctAtLateral();

                    robot.driveTrain.rotateDeg(90);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.driveTrain.setMoveDist(5.5);
                    robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()-360);

                    robot.bar4.setMoveAngle(145);
                    placeBlock();
                    break;
                }
                default:{


                    robot.driveTrain.rotateDeg(90);


                    placeBlock();

                    break;

                }
            }
//
//            robot.driveTrain.rotateDeg(-200);
//            timer.startTime();
//            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
//            while(robot.bumper.isPressed() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){
//
//                if (timer.milliseconds() <= 3000) {
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
//            robot.driveTrain.setLeftPower(0);
//            robot.driveTrain.setRightPower(0);
//            robot.wheels.setLeftWheelPwr(0);
//            robot.wheels.setRightWheels(0);
//
//            sleep(1000);
//
//            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;
//
//            robot.driveTrain.rotateDeg(-170);
//
//            robot.driveTrain.setLeftPower(0);
//            robot.driveTrain.setRightPower(0);
//            robot.wheels.setLeftWheelPwr(0);
//            robot.wheels.setRightWheels(0);
//
//            robot.bar4.setPower(1);
//            sleep(1200);
//            robot.bar4.setPower(0);
//
//            robot.driveTrain.setMoveDistEnc(leftTarget- (5 * constant.getTICKS_PER_INCH()));
//            placeBlock();
//            robot.bar4.setPower(0);
//            robot.driveTrain.setMoveDist(-10);
//            robot.driveTrain.rotateDeg(180);
//            robot.driveTrain.setMoveDist(-35);
//            robot.driveTrain.setMoveDist(10);

        }
    }

    public void placeBlock(){

        sleep(200);
        while(robot.range.getDist()<10 && opModeIsActive()) {
            robot.wheels.setRightWheels(1);
            robot.wheels.setLeftWheelPwr(1);
        }

        robot.wheels.setRightWheels(1);
        robot.wheels.setLeftWheelPwr(1);
        sleep(500);

        robot.wheels.stopLeft();
        robot.wheels.stopRight();
    }

    public void correctAtLateral(){
        if (gyro.getYaw() < -90){
            robot.driveTrain.singleSideRotateDegCorrect(DriveTrain.Side.RIGHT_SIDE,gyro.getYaw()+90, -0.2);

        } else if (gyro.getYaw() > -90){
            robot.driveTrain.singleSideRotateDegCorrect(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()+90, -0.2);

        }
    }
}
