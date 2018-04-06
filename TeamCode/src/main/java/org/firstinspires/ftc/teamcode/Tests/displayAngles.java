package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
@TeleOp(name = "displayAngles")
public class displayAngles extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        while (opModeIsActive()){
            telemetry.addData("Yaw", gyro.getYaw());
            telemetry.addData("Pitch", gyro.getPitch());
            telemetry.addData("Roll", gyro.getRoll());
            telemetry.update();
        }
    }
}
