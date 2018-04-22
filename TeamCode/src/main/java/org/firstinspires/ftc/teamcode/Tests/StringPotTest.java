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
    double MaxDist = 20.5;
    double minVoltge = 0.273;
    double MaxVoltage = 0;



    @Override
    public void init() {
        stringPot = hardwareMap.analogInput.get("pot");
        minVoltge = stringPot.getVoltage();
        MaxVoltage =1.793-minVoltge;
    }


    @Override
    public void loop()
    {
        double dist  = ((stringPot.getVoltage()-minVoltge)/MaxVoltage)*MaxDist;
        telemetry.addData("voltage", stringPot.getVoltage());
        telemetry.addData("dist", dist);
        telemetry.update();
    }
}
