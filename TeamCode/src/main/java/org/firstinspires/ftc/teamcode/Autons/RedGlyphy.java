package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;
import org.firstinspires.ftc.teamcode.Util.*;

/**
 * Created by Ethan Pereira on 11/16/2017.
 */
@Autonomous(name = "RedGlyphy")
public class RedGlyphy extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();

    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        int dist = 0;



        waitForStart();
        if (opModeIsActive()){

            robot.jewelArm.armDown();

            sleep(1000);

//

            if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){

                robot.driveTrain.setMoveDist(4);
                dist-=4;

            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "RED"){

                robot.driveTrain.setMoveDist(-4);

                dist+=4;

            }

            robot.driveTrain.setMoveDist(17 + dist);

            VisionUtil visionUtil = new VisionUtil();

            RelicRecoveryVuMark reading = visionUtil.readGraph(hardwareMap);

            sleep(2000);

            robot.driveTrain.setMoveDist(25);

            telemetry.addData("vumark 1", reading);
            telemetry.update();


            switch (reading){
                case RIGHT:{
                    placeBlock();
                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(10);
                    placeBlock();
                    break;
                }
                case LEFT:{
                    robot.driveTrain.setMoveDist(20);
                    placeBlock();
                    break;
                }
                default:{

                    placeBlock();
                    break;

                }
            }

            robot.driveTrain.rotateDeg(175);
            timer.startTime();
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            while(!robot.bumper.isPressed() && opModeIsActive() && (robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 90)){

                if (timer.milliseconds() <= 2000) {

                    robot.wheels.intakeRight();
                    robot.wheels.intakeLeft();

                }
                else{

                    robot.wheels.intakeLeft();
                    robot.wheels.setRightWheels(0);

                }
                robot.driveTrain.setLeftPower(1);
                robot.driveTrain.setRightPower(1);



            }

            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);

            robot.driveTrain.rotateDeg(-175);

            robot.bar4.setPower(1);
            sleep(1000);
            robot.bar4.setPower(0);

            robot.driveTrain.setMoveDistEnc(leftTarget);
            placeBlock();
            robot.bar4.setPower(0);
            robot.driveTrain.setMoveDist(-15);

        }

    }

    public void placeBlock(){

        robot.driveTrain.setMoveDist(4);
        robot.wheels.outtakeLeft();
        robot.wheels.outtakeRight();
        sleep(500);
        robot.wheels.stopLeft();
        robot.wheels.stopRight();
        robot.driveTrain.setMoveDist(-3);
    }
}
