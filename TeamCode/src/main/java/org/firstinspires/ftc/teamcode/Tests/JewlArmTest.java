package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 3/7/18.
 */
@Autonomous(name = "Jewlee")
public class JewlArmTest extends LinearOpMode{

    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(this.hardwareMap);
        robot.init(hardwareMap,this,gyro);
        waitForStart();
        while (opModeIsActive()){

            telemetry.addData("right", robot.jewelArm.getPos());
            telemetry.addData("left", robot.jewelArm.getPos2());
            telemetry.update();

        }
    }
}
