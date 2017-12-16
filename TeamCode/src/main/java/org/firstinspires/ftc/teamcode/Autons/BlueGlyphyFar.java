package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by therat0981 on 12/14/17.
 */

@Autonomous(name = "BlueGlyphyFar")
public class BlueGlyphyFar extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();

    boolean getPictograph = true;
    VisionUtil vision = new VisionUtil(this);

    RelicRecoveryVuMark reading;


    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        reading = vision.readGraph(hardwareMap);
        int dist = 0;



        waitForStart();
        if (opModeIsActive()){


            robot.driveTrain.rotateDeg(15);
            if (!reading.equals(RelicRecoveryVuMark.UNKNOWN)){
                getPictograph = false;
            }
            robot.jewelArm.arm2Down();

            sleep(500);

//

            if(String.valueOf(robot.jewelArm.getColor2()) == "RED"){

                robot.driveTrain.setMoveDist(6);
                dist+=6;

            }


            else if(String.valueOf(robot.jewelArm.getColor2()) == "BLUE"){

                robot.driveTrain.setMoveDist(-6);

                dist-=4;

            }

            robot.jewelArm.arm2Mid();

            if (getPictograph) {
                if(reading.equals(RelicRecoveryVuMark.UNKNOWN)) {
                    robot.driveTrain.setMoveDist(-9 - dist);

                    reading = vision.readGraph2(hardwareMap);

                    sleep(1000);
                }

            }

            robot.driveTrain.setMoveDist(54-dist);

            telemetry.addData("vumark 1", reading);
            telemetry.update();

            robot.driveTrain.rotateDeg(90);



            switch (reading){
                case RIGHT:{
                    robot.driveTrain.setMoveDist(30);

                    robot.driveTrain.rotateDeg(-90);

                    placeBlock();
                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(20);
                    robot.driveTrain.rotateDeg(-90);

                    placeBlock();
                    break;
                }
                case LEFT:{
                    robot.driveTrain.setMoveDist(7);
                    robot.driveTrain.rotateDeg(-90);

                    placeBlock();
                    break;
                }
                default:{


                    robot.driveTrain.rotateDeg(-90);


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

        robot.driveTrain.setMoveDist(4);
        robot.wheels.setRightWheels(-1);
        robot.wheels.setLeftWheelPwr(-1);
        sleep(500);
        robot.wheels.stopLeft();
        robot.wheels.stopRight();
        robot.driveTrain.setMoveDist(-8);
    }
}
