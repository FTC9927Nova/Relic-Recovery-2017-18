package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
@Autonomous(name = "enctester")
@Disabled
public class WheelEncTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftWheels;
        DcMotor rightWheels;
        waitForStart();
        leftWheels = hardwareMap.dcMotor.get("leftIntake");
        rightWheels = hardwareMap.dcMotor.get("rightIntake");

        while (opModeIsActive()){
            telemetry.addData("Left", leftWheels.getCurrentPosition());
            telemetry.addData("Right", rightWheels.getCurrentPosition());
            telemetry.update();
        }
    }
}
