package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

@TeleOp(name = "EncReturn")
@Disabled
public class getEncRetune extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot();
        Gyro gyro = new Gyro();

        robot.init(hardwareMap, this, gyro);
        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        waitForStart();
        while (opModeIsActive()){

            telemetry.addData("Left", robot.driveTrain.getLeftCurrentPosition());
            telemetry.addData("Right", robot.driveTrain.getRightCurrentPosition());
            telemetry.update();
        }

    }
}
