package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Ethan Pereira on 10/24/2017.
 */
@Autonomous(name = "csTest")
public class ColorSensorTest extends LinearOpMode{


    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this, gyro );
        gyro.initGyro(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Color", robot.jewelArm.getColor());
            telemetry.update();
        }
    }
}
