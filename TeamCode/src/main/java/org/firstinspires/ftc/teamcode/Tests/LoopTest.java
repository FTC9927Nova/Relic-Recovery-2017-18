package org.firstinspires.ftc.teamcode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 3/11/2018.
 */
@Disabled
@Autonomous(name = "LoopTest")
public class LoopTest extends LinearOpMode {
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        double heading;
        waitForStart();
        if (opModeIsActive()) {

            double i = 1;
            int c = 0;
            while (!robot.range.isGlyph() && opModeIsActive()) {
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
                robot.driveTrain.setLeftPower(0);
                robot.driveTrain.setRightPower(0);
            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


        }
    }
}
