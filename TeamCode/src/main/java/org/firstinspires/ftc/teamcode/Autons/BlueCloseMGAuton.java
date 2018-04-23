package org.firstinspires.ftc.teamcode.Autons;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.FourBar;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;
import org.firstinspires.ftc.teamcode.Util.*;

/**
 * Created by Sumanth Kondapalli on 11/16/2017.
 */
@Autonomous(name = "BlueCloseMGY")
public class BlueCloseMGAuton extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    VisionUtil vision = new VisionUtil(this);
    RelicRecoveryVuMark reading;
    double startTime = 0;
    String tester = "";
    VisionUtil visionUtil = new VisionUtil(this);

    double firstAnlge;

    double startEnc;
    double targetEnc;
    enum DriveState
    {
        DRIVE_TO_POS,
        ROTATE_TO_GLYPH_PIT,
        GET_FIRST_GLYPH,
        GET_SECOND_GLYPH,
        DRIVE_BACK_TO_CRYPTO,
        LEFT,
        RIGHT,
        CENTER,
        STOP
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
        STOP
    }

    WheelState wheelState = WheelState.STOP;
    DriveState driveState = DriveState.DRIVE_TO_POS;
    DriveState firstDState = DriveState.CENTER;
    FourBarState fourBarState = FourBarState.STOP;

    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        reading = vision.readGraph(hardwareMap);

        double prevAngle = 0;

        int dist = 24;
        reading = vision.readGraph2(hardwareMap);
        telemetry.addData("vision", reading);
        switch(reading)
        {
            case LEFT:
            {
                firstDState = DriveState.LEFT;
                break;
            }
            case RIGHT:
            {
                firstDState = DriveState.RIGHT;
                break;

            }
            case CENTER:
            {
                firstDState = DriveState.CENTER;
                break;
            }
            default:
            {
                firstDState = DriveState.CENTER;
            }
        }
        telemetry.addData("first state", firstDState.toString());
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            switch (driveState)
            {
                case DRIVE_TO_POS:
                {
                    if(robot.driveTrain.setMoveDist(36)) {
                        robot.wheels.unLatch();
                        driveState = DriveState.ROTATE_TO_GLYPH_PIT;
                    }
                    break;
                }
                case ROTATE_TO_GLYPH_PIT: {
                    if (robot.driveTrain.rotateDeg(90))
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

                        driveState = DriveState.STOP;
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
                        fourBarState = FourBarState.OUTTAKE;
                        robot.driveTrain.stop();
                        driveState = DriveState.DRIVE_BACK_TO_CRYPTO;
                    }
                    break;
                }
                case DRIVE_BACK_TO_CRYPTO:
                {
                    if(robot.driveTrain.setMoveDistEnc(350-(int)targetEnc))
                    {
                        driveState = firstDState;
                    }
                    break;
                }
                case LEFT:
                {
                    if(robot.driveTrain.rotateDeg(165))
                    {
                        wheelState = WheelState.SPIT_GLYPHS;
                        driveState = DriveState.STOP;
                    }
                    break;
                }
                case RIGHT:
                {
                    if(robot.driveTrain.rotateDeg(-165))
                    {
                        wheelState = WheelState.SPIT_GLYPHS;
                        driveState = DriveState.STOP;
                    }
                    break;

                }
                case CENTER:
                {
                    if(robot.driveTrain.rotateDeg(180))
                    {
                        wheelState = WheelState.SPIT_GLYPHS;
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
                    robot.wheels.fullOuttake();
                    if(robot.wheels.glyphDist()>8)
                    {
                        wheelState = WheelState.STOP;
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
                    robot.bar4.setMoveHeight(7);
                    break;
                }
                case STOP:
                {
                    robot.bar4.stop();
                    break;
                }
            }




            telemetry.update();

        }

    }
}
