package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Util.Potentiometer;

/**
 * Created by Sumanth on 12/12/17.
 */

@TeleOp(name = "servo arm test")
public class ServoArmTest extends OpMode
{
    Potentiometer pot;
    CRServo extender;
    @Override
    public void init() {
        pot = new Potentiometer(hardwareMap);
        extender = hardwareMap.crservo.get("extender");
    }

    @Override
    public void loop()
    {
        extender.setPower(gamepad1.left_stick_y);
    }

    @Override
    public void stop() {

    }
}
