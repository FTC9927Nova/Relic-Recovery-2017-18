package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 3/7/18.
 */
@Disabled
@Autonomous(name = "LimitoBivito")
public class LimitUpDownTest extends LinearOpMode{

    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,this,gyro);
        waitForStart();
        if (opModeIsActive()){
            telemetry.addData("Angle", robot.bar4.getCurrentAngle());
            telemetry.update();
            robot.bar4.setMoveAngle(265.9);

        }
    }
}
