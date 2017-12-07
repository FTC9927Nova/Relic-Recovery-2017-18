package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by Ethan Pereira on 11/16/2017.
 */
@Autonomous(name = "RedGlyphy")
public class RedGlyphy extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);



        waitForStart();
        if (opModeIsActive()){

            robot.driveTrain.setMoveDist(17);

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
            while(!robot.bumper.isPressed() && opModeIsActive()){

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

            robot.bar4.setPower(.625);
            sleep(750);
            robot.bar4.setPower(0);

            robot.driveTrain.setMoveDistEnc(leftTarget);
            placeBlock();
            robot.bar4.setPower(.625);
            sleep(500);
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
