package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by therat0981 on 11/9/17.
 */

@TeleOp(name = "robot fix")
public class RobotFix extends OpMode
{
    DcMotor l1;
    DcMotor l2;
    DcMotor r1;
    DcMotor r2;
    @Override
    public void init()
    {
        r1 = hardwareMap.dcMotor.get("r1");
        l1 = hardwareMap.dcMotor.get("l1");
        r2 = hardwareMap.dcMotor.get("r2");
        l2 = hardwareMap.dcMotor.get("l2");
        l1.setDirection(DcMotorSimple.Direction.FORWARD);
        l2.setDirection(DcMotorSimple.Direction.REVERSE);
        r1.setDirection(DcMotorSimple.Direction.REVERSE);
        r2.setDirection(DcMotorSimple.Direction.FORWARD);
        r1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop()
    {
        l1.setPower(gamepad1.left_stick_y);
        l2.setPower(gamepad1.left_stick_y);
        r1.setPower(gamepad1.right_stick_y);
        r2.setPower(gamepad1.right_stick_y);
        telemetry.addData("<",r1.getCurrentPosition());
        telemetry.addData("<",r2.getCurrentPosition());
        telemetry.addData("<",l1.getCurrentPosition());
        telemetry.addData("<",l2.getCurrentPosition());

        telemetry.update();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
