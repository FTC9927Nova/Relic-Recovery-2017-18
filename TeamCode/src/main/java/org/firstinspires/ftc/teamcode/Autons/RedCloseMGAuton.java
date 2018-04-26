package org.firstinspires.ftc.teamcode.Autons;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.FourBar;
import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;
import org.firstinspires.ftc.teamcode.Util.*;

/**
 * Created by Sumanth Kondapalli on 11/16/2017.
 */
@Autonomous(name = "RedCloseMGY")
public class RedCloseMGAuton extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    VisionUtil vision = new VisionUtil(this);
    RelicRecoveryVuMark reading;
    double startTime = 0;
    String tester = "";
    VisionUtil visionUtil = new VisionUtil(this);
    double angle;

    double startEnc;
    double targetEnc;
    enum DriveState
    {
        HIT_JEWEL,
        DRIVE_TO_POS,
        ROTATE_TO_GLYPH_PIT,
        GET_FIRST_GLYPH,
        GET_SECOND_GLYPH,
        DRIVE_BACK_TO_CRYPTO,
        LEFT,
        RIGHT,
        CENTER,
        ROTATE_DEG_2,
        STOP
    }

    enum JewelState
    {
        RED,
        BLUE,
        NONE,
    }


    int intkaeCounter = 0;
    boolean swap = true;
    enum WheelState
    {
        SPAZ_INTAKE,
        FULL_INTAKE_DOSGLYPHY,
        SPIT_GLYPHS,
        STOP
    }


    enum FourBarState
    {
        INTAKE,
        OUTTAKE,
        OUTTAKE2,
        STOP
    }

    enum ArmState
    {
        ARM_UP,
        ARM_DOWN
    }



    WheelState wheelState = WheelState.STOP;
    DriveState driveState = DriveState.DRIVE_TO_POS;
    DriveState firstDState = DriveState.CENTER;
    FourBarState fourBarState = FourBarState.STOP;
    JewelState jewelState = JewelState.NONE;
    ArmState armState = ArmState.ARM_DOWN;

    @Override
    public void runOpMode() throws InterruptedException {

        AutoTransitioner.transitionOnStop(this,"MainTeleNoPID");

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        reading = vision.readGraph(hardwareMap);
        double dist = 0;

        reading = vision.readGraph2(hardwareMap);
        telemetry.addData("vision", reading);

        switch(reading)
        {
            case LEFT:
            {
                firstDState = DriveState.LEFT;
                angle = 165;
                break;
            }
            case RIGHT:
            {
                firstDState = DriveState.RIGHT;
                angle = -165;
                break;

            }
            case CENTER:
            {
                firstDState = DriveState.CENTER;
                angle = 180;
                break;
            }
            default:
            {
                firstDState = DriveState.CENTER;
            }
        }

        waitForStart();


        if (opModeIsActive()) {
            robot.jewelArm.armDown();
            //robot.wheels.latch();
            sleep(1200);
            if (String.valueOf(robot.jewelArm.getColor()) == "RED")
            {
                telemetry.addData(String.valueOf(robot.jewelArm.getColor()), "00");
                telemetry.update();
                robot.driveTrain.setMoveDist(-3);
                dist = 3;

            } else if (String.valueOf(robot.jewelArm.getColor()) == "BLUE") {

                telemetry.addData(String.valueOf(robot.jewelArm.getColor()), "00");
                telemetry.update();

                robot.driveTrain.setMoveDist(3);
                dist = -3;

            }
//
            sleep(1000);
            robot.jewelArm.armMid();
            robot.jewelArm.arm2Mid();


        }



        while(opModeIsActive())
        {
            if(gyro.getPitch()>11)
                stop();




            switch (driveState)
            {
                case DRIVE_TO_POS:
                {
                    if(robot.driveTrain.setMoveDist(36 + dist)) {
                        robot.wheels.unLatch();
                        driveState = DriveState.ROTATE_TO_GLYPH_PIT;
                    }
                    break;
                }
                case ROTATE_TO_GLYPH_PIT: {
                    if (robot.driveTrain.rotateDeg(-90))
                    {
                        startEnc = (robot.driveTrain.getLeftCurrentPosition()+robot.driveTrain.getRightCurrentPosition())/2.0;
                        driveState = DriveState.GET_SECOND_GLYPH;
                        wheelState = WheelState.SPAZ_INTAKE;
                        fourBarState = FourBarState.INTAKE;
                    }

                    break;
                }
                case GET_FIRST_GLYPH:
                {
                    robot.driveTrain.setDrive(DriveTrain.Drive.NOTHING);
                    robot.driveTrain.driveStraight();

                    if(wheelState.equals(WheelState.STOP))
                    {
                        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
                        robot.driveTrain.stop();

                        driveState = DriveState.GET_SECOND_GLYPH;


                    }
                    break;

                }
                case GET_SECOND_GLYPH:
                {
                    robot.driveTrain.setDrive(DriveTrain.Drive.NOTHING);
                    robot.driveTrain.driveStraight();
                    if(wheelState.equals(WheelState.STOP))
                    {
                        targetEnc = ((robot.driveTrain.getLeftCurrentPosition()+robot.driveTrain.getRightCurrentPosition())/2.0)
                                -startEnc;
                        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
                        robot.driveTrain.stop();
                        driveState = DriveState.DRIVE_BACK_TO_CRYPTO;
                        fourBarState = FourBarState.OUTTAKE;
                    }
                    break;
                }
                case DRIVE_BACK_TO_CRYPTO:
                {
                    if(robot.driveTrain.setMoveDistEnc(250-(int)targetEnc))
                    {
                        driveState = firstDState;
                    }
                    break;
                }
                case ROTATE_DEG_2:
                {

//                    if(robot.driveTrain.rotateDeg(angle))
//                    {
//                        driveState = DriveState.GET_FIRST_GLYPH;
//                        fourBarState = FourBarState.INTAKE;
//                        wheelState = WheelState.STOP;
//
//                    }
//                    break;
                    if(robot.driveTrain.setMoveDist(-10))
                    {
                        driveState = DriveState.STOP;
                    }
                    break;

                }
                case LEFT:
                {
                    if(robot.driveTrain.rotateDeg(angle))
                    {
                        wheelState = WheelState.SPIT_GLYPHS;
//                        driveState = DriveState.STOP;
//                        firstDState = DriveState.RIGHT;
//                        if(!driveState.equals(DriveState.RIGHT)) {
//                            driveState = DriveState.RIGHT;
//                            angle = -165;
//                        }
//                        else if(driveState.equals(DriveState.RIGHT))
                            driveState = DriveState.STOP;
                    }
                    break;
                }
                case RIGHT:
                {
                    if(robot.driveTrain.rotateDeg(angle))
                    {
                        wheelState = WheelState.SPIT_GLYPHS;
//                        driveState = DriveState.STOP;
//                        firstDState = DriveState.LEFT;
//                        if(!driveState.equals(DriveState.LEFT))
//                        {
//                            driveState = DriveState.LEFT;
//                            angle = 165;
//                        }
//                        else if(driveState.equals(DriveState.LEFT))
                            driveState = DriveState.STOP;

                    }
                    break;

                }
                case CENTER:
                {
                    if(robot.driveTrain.rotateDeg(angle))
                    {
                        wheelState = WheelState.SPIT_GLYPHS;
                        driveState = DriveState.STOP;
//                        if(!driveState.equals(DriveState.RIGHT))
//                        {
//                            driveState = DriveState.RIGHT;
//                            angle = -165;
//                        }
//                        else if(driveState.equals(DriveState.RIGHT))
                            driveState = DriveState.STOP;
                    }
                    break;
                }
                case STOP:
                {
                    robot.driveTrain.stop();
                    robot.wheels.stop();
                    break;
                }
            }


            switch (wheelState)
            {
                case SPAZ_INTAKE:
                {
                    robot.wheels.setLeftServoPwr(-0.5);
                    robot.wheels.setRightWheels(-0.5);
                    intkaeCounter++;
                    if((intkaeCounter%3)==0)
                        swap = !swap;

                    if(swap) {
                        robot.wheels.setLeftWheelPwr(-1);
                        robot.wheels.stopRight();
                    }
                    else
                    {
                        robot.wheels.setRightWheels(-1);
                        robot.wheels.stopLeft();
                    }

                    if(robot.wheels.glyphDist()<3) {
                        robot.wheels.intakeRight();
                        robot.wheels.intakeLeft();
                        wheelState = WheelState.FULL_INTAKE_DOSGLYPHY;
                    }
                    break;
                }
                case FULL_INTAKE_DOSGLYPHY:
                {
                    if(robot.wheels.secondGlyphIntake()) {
                        if(startTime==0)
                            startTime = timer.milliseconds();
                        else {
                            if (((timer.milliseconds() - startTime) > 700)) {
                                if((timer.milliseconds()-startTime)<1500)
                                {
                                    robot.wheels.intakeRight();
                                    robot.wheels.intakeLeft();
                                }
                                else
                                {
                                    startTime = 0;
                                    wheelState = WheelState.STOP;
                                }
                            } else {
                                robot.wheels.setLeftWheelPwr(-1);
                                robot.wheels.setRightWheels(0.2);
                            }
                        }
                    }
                    else
                    {
                        robot.wheels.intakeLeft();
                        robot.wheels.intakeRight();
                    }
                    break;
                }
                case SPIT_GLYPHS:
                {

                    if(robot.wheels.halfOuttake()&&robot.wheels.glyphDist()>3)
                    {
                        if(robot.wheels.fullOuttake2()){
                            wheelState = WheelState.STOP;
                            driveState = DriveState.ROTATE_DEG_2;
                        }

                    }
                    break;
                }
                case STOP:
                {
                    robot.wheels.stop();
                    break;
                }
            }

            switch (fourBarState)
            {
                case INTAKE:
                {
                    robot.bar4.setMoveHeight(1);
                    break;
                }

                case OUTTAKE:
                {
                    robot.bar4.setMoveHeight(8);
                    break;
                }
                case OUTTAKE2:
                {
                    robot.bar4.setMoveHeight(13);
                    break;
                }
                case STOP:
                {
                    robot.bar4.stop();
                    break;
                }
            }


            telemetry.addData("time", timer.milliseconds());
            telemetry.addData("start time", startTime);
            telemetry.addData("diff", (timer.milliseconds()-startTime));
            telemetry.addData("drive state",driveState.toString());
            telemetry.addData("wheel state: ",wheelState.toString());
            telemetry.addData("wheel dist", robot.wheels.glyphDist());
            telemetry.update();

        }

    }
}
