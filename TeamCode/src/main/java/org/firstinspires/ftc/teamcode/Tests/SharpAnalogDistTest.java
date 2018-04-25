package org.firstinspires.ftc.teamcode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;

/**
 * Created by Sumanth on 4/2/18.
 */

@TeleOp(name = " test ")
public class SharpAnalogDistTest extends OpMode
{
    AnalogInput analogSensor;
    boolean toggle = false;

    double x = 0.25;

    @Override
    public void init() {
        analogSensor = hardwareMap.analogInput.get("distyListy");
    }


    @Override
    public void loop()
    {
        double input = analogSensor.getVoltage();

//        toggle = false;
        telemetry.addData("voltage",analogSensor.getVoltage());
        telemetry.addData("max voltage", analogSensor.getMaxVoltage());
        telemetry.addData("RealInput", input);
        double dist = 1.8694*(Math.pow(input,-1.086));
        telemetry.addData("dist: ", dist);
        telemetry.update();

    }

    public void stop()
    {

    }

}
