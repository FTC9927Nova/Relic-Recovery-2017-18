package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
    Servo extender;
    private double currentAngle,posAngle;
    private final double minVal = 36.18, maxTurn = 0.9;
    @Override
    public void init() {
        pot = new Potentiometer(hardwareMap);
        extender = hardwareMap.servo.get("extender");


    }

    @Override
    public void loop()
    {

        if(gamepad1.b) {
            pot.getInput();
            posAngle =  maxTurn - (((int)(((pot.getAngle() - minVal)/180.0)*100)))/100.0;
            extender.setPosition(Range.clip(posAngle,0,1));
        }
        else{



        }

        telemetry.addData("angle",pot.getAngle());
        telemetry.addData("posAngle", posAngle);
        telemetry.addData("servo target", extender.getPosition());
        telemetry.update();


    }

    @Override
    public void stop() {

    }
}
