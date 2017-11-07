package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Sumanth on 11/7/17.
 */

@TeleOp(name = "l1Test")
public class l2MotorTest extends OpMode
{
    DcMotor l1;
    @Override
    public void init() {
        l1 = hardwareMap.dcMotor.get("l1");
    }

    @Override
    public void loop()
    {
        l1.setPower(1);
    }

    @Override
    public void stop() {
        l1.setPower(0);
    }
}
