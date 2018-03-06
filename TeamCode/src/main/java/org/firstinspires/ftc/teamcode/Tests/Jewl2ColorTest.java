package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Ethan Pereira on 2/24/2018.
 */
@Autonomous(name = "Jewl2 ColorTest")
public class Jewl2ColorTest  extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot();
        Gyro gyro = new Gyro();
        robot.init(hardwareMap, this, gyro);
        waitForStart();

        while (opModeIsActive()){
            robot.jewelArm.arm2SetPos(-gamepad1.left_stick_y);
            telemetry.addData("",robot.jewelArm.display());
            telemetry.update();
        }
    }
}
