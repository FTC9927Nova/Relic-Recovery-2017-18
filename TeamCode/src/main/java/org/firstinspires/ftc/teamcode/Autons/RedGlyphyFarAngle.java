package org.firstinspires.ftc.teamcode.Autons;


import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;
//class ThreadLift145 extends Thread implements Runnable{
//    Robot robot;
//
//    public ThreadLift145(Robot objectRobot){
//        objectRobot = robot;
//    }
//    private volatile boolean flag = false;
//    @Override
//    public void run() {
//        while (!flag){
//            robot.bar4.setMoveAngle(145);
//        }
//        super.run();
//    }
//    public void stopRunning()
//    {
//        flag = true;
//    }
//}
/**
 * Created by therat0981 on 12/14/17.
 */

@Autonomous(name = "RedGlyphyFarAngle")
public class RedGlyphyFarAngle extends LinearOpMode
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

        double dist = 17.5;
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
            robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw());


            switch (reading){
                case RIGHT:{
                    turn13();
                    robot.bar4.setMoveAngle(145);
                    placeBlock();


                    robot.driveTrain.setMoveDist(-5);
                    robot.bar4.setMoveAngle(167);
                    turn13();
                    robot.bar4.setMoveAngle(167);
                    correctAtLateral();
                    break;
                }
                case CENTER:{

                    turn28();
                    robot.bar4.setMoveAngle(145);
                    placeBlock();

                    robot.driveTrain.setMoveDist(-5);
                    robot.bar4.setMoveAngle(167);
                    turn28();
                    robot.bar4.setMoveAngle(167);
                    correctAtLateral();
                    break;
                }
                case LEFT:{
                    turn45();
                    robot.bar4.setMoveAngle(145);
                    robot.driveTrain.setMoveDist(3.5);
                    placeBlock();

                    robot.driveTrain.setMoveDist(-5);
                    robot.bar4.setMoveAngle(167);
                    robot.driveTrain.singleSideRotateDegCorrect(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw()+46.5, -0.2);
                    robot.bar4.setMoveAngle(167);
                    correctAtLateral();
                    break;
                }
                default:{

                    break;

                }
            }
            robot.driveTrain.setMoveDist(3);
            robot.driveTrain.singleSideRotateDegCorrect(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw()+90+44,0.8);
//            robot.driveTrain.rotateDeg(-64.5);
            timer.startTime();
            while(robot.bar4.isLowerHit()){
                robot.bar4.setPower(-1);
            }
            robot.bar4.setPower(0);
            robot.driveTrain.setMoveDist(18);
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            double i = 1;
            int c = 0;
            while(!robot.range.isGlyph() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 25) && gyro.getPitch() < 10){
                c = (int) (i);
                if (c % 2 == 0) {
                    robot.wheels.intakeLeft();
                    robot.wheels.setRightWheels(0);
                } else {
                    robot.wheels.intakeRight();
                    robot.wheels.setLeftWheelPwr(0);
                }
                i += 0.7;
                Log.i("dist", robot.range.getDist() + "");
                robot.driveTrain.setLeftPower(0.5);
                robot.driveTrain.setRightPower(0.5);
            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);
            sleep(200);

            int leftTarget = (robot.driveTrain.getLeftCurrentPosition() - startLeftEnc)/constant.getTICKS_PER_INCH();
            robot.driveTrain.setMoveDist(-leftTarget-11.5);
            //TODO: CHANGE FOR REEAL AUTO
            robot.driveTrain.rotateDeg(-gyro.getYaw()-1);
//            robot.driveTrain.setMoveDist(-1);
            robot.bar4.setMoveAngle(167);
//            robot.driveTrain.setMoveDist(1.5);
            placeBlock();

            robot.driveTrain.setMoveDist(-3);


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
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()+90);

        } else if (gyro.getYaw() > -90){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.RIGHT_SIDE,gyro.getYaw()+90);

        }
    }

    public void turn13(){
        if (gyro.getYaw() > -13){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.RIGHT_SIDE,gyro.getYaw()+13);

        } else if (gyro.getYaw() < -13){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()+13);

        }
    }

    public void turn28(){
        if (gyro.getYaw() > -28){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.RIGHT_SIDE,gyro.getYaw()+28);

        } else if (gyro.getYaw() < -28){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()+28);

        }
    }

    public void turn45(){
        if (gyro.getYaw() > -48.5){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.RIGHT_SIDE,gyro.getYaw()+48.5);

        } else if (gyro.getYaw() < -48.5){
            robot.driveTrain.singleSideTurnFar(DriveTrain.Side.LEFT_SIDE,gyro.getYaw()+48.5);

        }
    }


}