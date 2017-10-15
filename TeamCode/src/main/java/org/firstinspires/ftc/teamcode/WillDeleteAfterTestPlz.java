package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.internal.ftdi.eeprom.FT_EEPROM_232H;

/**
 * Created by therat0981 on 10/13/17.
 */


public class WillDeleteAfterTestPlz extends OpMode
{
    DcMotor l1;
    DcMotor r1;

    @Override
    public void init() {
        l1 = hardwareMap.dcMotor.get("l1");
        r1 = hardwareMap.dcMotor.get("r1");
        l1 = hardwareMap.dcMotor.get("l1");
        r1 = hardwareMap.dcMotor.get("r1");
        l1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        l1.setDirection(DcMotorSimple.Direction.REVERSE);
        r1.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    @Override
    public void loop() {

        telemetry.addData("Left Power: " + l1.getPower(), "    Right Power: " + r1.getPower());

        //Driver Code
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;

        float lpwr = (float) Math.pow((yval + xval), 3);
        float rpwr = (float) Math.pow((yval - xval), 3);


        //turtle mode
        if (gamepad1.right_bumper || gamepad1.right_bumper)
        {
            lpwr = lpwr/2.0f;
            rpwr = rpwr/2.0f;
        }

        l1.setPower(lpwr);
        r1.setPower(rpwr);

    }

    @Override
    public void stop() {
        l1.setPower(0);
        r1.setPower(0);
    }
}
