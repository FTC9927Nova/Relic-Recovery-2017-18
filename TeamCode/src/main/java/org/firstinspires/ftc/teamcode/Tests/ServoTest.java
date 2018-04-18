package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name =  "servoArmTest")
public class ServoTest extends OpMode
{
    Servo servo1;
    Servo servo2;
    @Override
    public void init() {
        servo1 = hardwareMap.servo.get("s1");
        servo2 = hardwareMap.servo.get("s2");
    }

    @Override
    public void loop() {
        if(gamepad1.a)
            servo1.setPosition(1);
        if(gamepad1.b)
            servo1.setPosition(0);
        if(gamepad1.x)
            servo2.setPosition(1);
        if(gamepad1.y)
            servo2.setPosition(0);
    }
}
