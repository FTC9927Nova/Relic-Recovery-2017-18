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
@Autonomous(name = "BlueCloseMGY")
public class BlueCloseMGAuton extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    VisionUtil vision = new VisionUtil(this);
    RelicRecoveryVuMark reading;
    int dist;
    double startTime = 0;
    String tester = "";
    VisionUtil visionUtil = new VisionUtil(this);
    double angle;
    int c = 0;

    double startEnc;
    double targetEnc;
    enum DriveState
    {
        SKRT,
        DRIVE_TO_POS,
        ROTATE_TO_GLYPH_PIT,
        GET_FIRST_GLYPH,
        GET_SECOND_GLYPH,
        DRIVE_BACK_TO_CRYPTO,
        LEFT,
        RIGHT,
        CENTER,
        JIGGLE_1,
        JIGGLE_2,
        ROTATE_DEG_2,
        FINAL_MOVE,
        BACK_UP,
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
        SPIT_GLYPHS2,
        STOP
    }


    enum FourBarState
    {
        INTAKE,
        OUTTAKE,
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

//        AutoTransitioner.transitionOnStop(this,"MainTeleNoPID");

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        reading = vision.readGraph(hardwareMap);

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
            robot.jewelArm.arm2Down();

            if (visionUtil.isDetected()) {
                reading = vision.readGraph2(hardwareMap);
            }

            sleep(1000);

            if (String.valueOf(robot.jewelArm.getColor2()) == "RED") {
                telemetry.addData(String.valueOf(robot.jewelArm.getColor2()), "00");
                telemetry.update();

                robot.driveTrain.setMoveDist(6);
                dist -= 6;

            } else if (String.valueOf(robot.jewelArm.getColor2()) == "BLUE") {

                telemetry.addData(String.valueOf(robot.jewelArm.getColor2()), "00");
                telemetry.update();
                robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
                robot.driveTrain.setMoveDist(-4);
                dist += 8;

            }
            sleep(500);
//
            robot.jewelArm.armMid();
            robot.jewelArm.arm2Mid();
        }

        while(opModeIsActive())
        {
            if(gyro.getPitch()>12)
                stop();




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
                case JIGGLE_1:
                {
                    if(robot.driveTrain.setMoveDist(-4))
                    {
                        driveState = DriveState.JIGGLE_2;
                    }
                    break;
                }
                case JIGGLE_2:
                {
                    if(robot.driveTrain.setMoveDist(4))
                    {
                        driveState = DriveState.STOP;
                        wheelState = WheelState.SPIT_GLYPHS2;
                    }
                    break;
                }
                case SKRT:
                {
                    if(robot.driveTrain.setMoveDist(10))
                    {
                        driveState = DriveState.GET_FIRST_GLYPH;
                    }
                    break;
                }
                case GET_FIRST_GLYPH:
                {
                    robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
                    robot.driveTrain.setDrive(DriveTrain.Drive.NOTHING);
                    startEnc = (robot.driveTrain.getLeftCurrentPosition()+robot.driveTrain.getRightCurrentPosition())/2.0;
                    robot.driveTrain.driveStraight();

                    if(wheelState.equals(WheelState.FULL_INTAKE_DOSGLYPHY))
                    {
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
                                - startEnc;
                        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
                       robot.driveTrain.stop();
                        driveState = DriveState.DRIVE_BACK_TO_CRYPTO;
                        fourBarState = FourBarState.OUTTAKE;
                    }
                    break;
                }
                case DRIVE_BACK_TO_CRYPTO:
                {
                    if(robot.driveTrain.setMoveDistEnc(400-(int)targetEnc))
                    {
                        driveState = firstDState;
                    }
                    break;
                }
                case ROTATE_DEG_2:
                {
//

                    if(robot.driveTrain.rotateDeg(-angle))
                    {

                        driveState = DriveState.SKRT;
                        wheelState = WheelState.SPAZ_INTAKE;
                    }
                    break;

                }
                case LEFT:
                {
                    if(c == 0){

                        if(robot.driveTrain.rotateDeg(angle) )
                        {
                            driveState = DriveState.STOP;
                            wheelState = WheelState.SPIT_GLYPHS;
                        }

                    }
                    if(c == 1){

                        if(robot.driveTrain.rotateDeg(-angle)){

                            driveState = DriveState.STOP;
                            wheelState = WheelState.SPIT_GLYPHS;

                        }

                    }
                    break;
                }
                case RIGHT:
                {
                    if(c == 0){

                        if(robot.driveTrain.rotateDeg(angle) )
                        {
                            driveState = DriveState.STOP;
                            wheelState = WheelState.SPIT_GLYPHS;
                        }

                    }
                    if(c == 1){

                        if(robot.driveTrain.rotateDeg(-angle)){

                            driveState = DriveState.STOP;
                            wheelState = WheelState.SPIT_GLYPHS;

                        }

                    }


                    break;

                }
                case FINAL_MOVE:
                {
                    if(robot.driveTrain.setMoveDist(-4))
                    {
                        driveState = DriveState.STOP;
                    }
                    break;
                }
                case BACK_UP:
                {

                    if(robot.driveTrain.setMoveDist(-8)){

                        stop();
                        driveState = DriveState.ROTATE_DEG_2;
                        fourBarState = FourBarState.INTAKE;
                    }
                    break;

                }
                case CENTER:
                {
                    if(c == 0){

                        if(robot.driveTrain.rotateDeg(angle) )
                        {
                            driveState = DriveState.STOP;
                            wheelState = WheelState.SPIT_GLYPHS;
                        }

                    }
                    if(c == 1){

                        if(robot.driveTrain.rotateDeg(angle - 15)){

                            driveState = DriveState.STOP;
                            wheelState = WheelState.SPIT_GLYPHS;

                        }

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
                { robot.wheels.setLeftServoPwr(-0.5);
                    robot.wheels.setRightWheels(-0.5);
                    intkaeCounter++;
                    if(robot.wheels.glyphDist()>6.5) {
                        if ((intkaeCounter % 3) == 0)
                            swap = !swap;

                        if (swap) {
                            robot.wheels.setLeftWheelPwr(-1);
                            robot.wheels.stopRight();
                        } else {
                            robot.wheels.setRightWheels(-1);
                            robot.wheels.stopLeft();
                        }
                    }
                    else if(robot.wheels.glyphDist()<6.5 && robot.wheels.glyphDist()>3)
                    {
                        robot.wheels.intakeRight();
                        robot.wheels.intakeLeft();
                    }
                    else
                    {
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

                    if(robot.wheels.halfOuttake())
                    {
                        driveState = DriveState.JIGGLE_1;
                        wheelState = WheelState.STOP;
                    }
                    break;
                }
                case SPIT_GLYPHS2:
                {
                    if(robot.wheels.fullOuttake2())
                    {
                        if(c == 1){

                            stop();

                        }
                        if(c == 0){

                            c++;
                            wheelState = WheelState.STOP;
                            robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
                            driveState = DriveState.BACK_UP;


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
                case STOP:
                {
                    robot.bar4.stop();
                    break;
                }
            }


            telemetry.addData("time", timer.milliseconds());
            telemetry.addData("start time", startTime);
            telemetry.addData("diff", (timer.milliseconds()-startTime));
            telemetry.addData("wheel state: ",wheelState.toString());
            telemetry.addData("Encdoers", startEnc);
            telemetry.addData("avergae",(robot.driveTrain.getRightCurrentPosition()+robot.driveTrain.getLeftCurrentPosition())/2.0);
            Log.i("what happened: ", driveState.toString()  + "      " + wheelState.toString() + "      " + robot.driveTrain.getLeftPwr() + "        " + robot.driveTrain.getRightPwr() + "         " + robot.driveTrain.getLeftCurrentPosition() + "       " + robot.driveTrain.getRightCurrentPosition() + " " +
            startEnc);
            telemetry.addData("drive state", driveState.toString());
            telemetry.update();

        }

    }
}
