package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/9/17.
 */
@TeleOp(name = "PwrTest")
public class PwrTest extends OpMode {
    DcMotor r1;
    DcMotor r2;
    DcMotor l1;
    DcMotor l2;
    Gyro gyro = new Gyro();

    @Override
    public void init() {

        gyro.initGyro(this.hardwareMap);
        r1 = hardwareMap.dcMotor.get("r1");
        r2 = hardwareMap.dcMotor.get("r2");
        l1 = hardwareMap.dcMotor.get("l1");
        l2 = hardwareMap.dcMotor.get("l2");
    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper){
            r1.setPower(1);
            r2.setPower(1);
        } else {
            r1.setPower(0);
            r2.setPower(0);
        }

        if (gamepad1.left_bumper)
        {
            l1.setPower(1);
            l2.setPower(1);
        } else {
            l1.setPower(0);
            l2.setPower(0);
        }
    }
}
