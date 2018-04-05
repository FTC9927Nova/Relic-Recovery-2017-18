package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by Sumanth on 3/10/18.
 */
@Disabled
@Autonomous(name = "tester bester lester the lark" , group = "9927")
public class TesterBester extends LinearOpMode {

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
        if (opModeIsActive()) {
            firstAnlge = gyro.getYaw();
            robot.jewelArm.armDown();

            if (visionUtil.isDetected()) {
                reading = vision.readGraph2(hardwareMap);
            }

            sleep(1000);

            if (String.valueOf(robot.jewelArm.getColor()) == "BLUE") {
                telemetry.addData(String.valueOf(robot.jewelArm.getColor()), "00");
                telemetry.update();

                robot.driveTrain.rotateDeg(-10);
                dist -= 4;

            } else if (String.valueOf(robot.jewelArm.getColor()) == "RED") {

                telemetry.addData(String.valueOf(robot.jewelArm.getColor()), "00");
                telemetry.update();

                robot.driveTrain.rotateDeg(10);
                dist += 4;

            }
//
            robot.jewelArm.armMid();
            robot.jewelArm.arm2Up();




        }
    }
}