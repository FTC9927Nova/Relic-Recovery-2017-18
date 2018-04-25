package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.teamcode.Util.Potentiometer;

/**
 * Created by Sumanth on 4/3/18.
 */

@TeleOp(name = "StringPOt Test")
@Disabled
public class StringPotTest extends OpMode
{
   Potentiometer pot;

    @Override
    public void init() {
       pot = new Potentiometer(hardwareMap);
    }


    @Override
    public void loop()
    {
        telemetry.addData("",pot.display());
        telemetry.update();
    }
}
