package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Ethan Pereira on 10/25/2017.
 */
@TeleOp(name = "ClawTest")
public class ClawTest extends OpMode {

    Servo servo;
    @Override
    public void init() {
        servo = hardwareMap.servo.get("claw");

    }

    @Override
    public void loop() {
        telemetry.addData("Pos", servo.getPosition());
        telemetry.update();
    }
}
