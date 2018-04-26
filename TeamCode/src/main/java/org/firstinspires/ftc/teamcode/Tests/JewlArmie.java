package org.firstinspires.ftc.teamcode.Tests;
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
@Autonomous(name = "red jewel test")
public class JewlArmie extends LinearOpMode {

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
            sleep(1000);
            if (String.valueOf(robot.jewelArm.getColor()) == "RED")
            {
                telemetry.addData(String.valueOf(robot.jewelArm.getColor()), "00");
                telemetry.update();
                robot.driveTrain.setMoveDist(4);

            } else if (String.valueOf(robot.jewelArm.getColor()) == "BLUE") {

                telemetry.addData(String.valueOf(robot.jewelArm.getColor()), "00");
                telemetry.update();

                robot.driveTrain.setMoveDist(-4);

            }
//
            robot.jewelArm.armMid();
            robot.jewelArm.arm2Mid();
        }
        while(opModeIsActive())
        {
           telemetry.addData("this is insanely stupid", "i ahte life");
           telemetry.update();

        }

    }
}
