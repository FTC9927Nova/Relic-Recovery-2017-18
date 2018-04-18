package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

/**
 * Created by Sumanth on 4/3/18.
 */

@TeleOp(name = "StringPOt Test")
public class StringPotTest extends OpMode
{
    AnalogInput stringPot;
    double MaxDist = 22;
    double MaxVoltage =1.9;


    @Override
    public void init() {
        stringPot = hardwareMap.analogInput.get("as");
    }

    @Override
    public void loop()
    {
        double dist  = (stringPot.getVoltage()/MaxVoltage)*MaxDist;
        telemetry.addData("voltage", stringPot.getVoltage());
        telemetry.addData("dist", dist);
        telemetry.update();
    }
}
