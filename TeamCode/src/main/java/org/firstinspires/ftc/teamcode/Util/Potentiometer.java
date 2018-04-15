package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by Ethan Pereira on 11/2/2017.
 * This utility is used to give us information about the Four Bar angle.
 */

public class Potentiometer {

    AnalogInput potentiometer;

    double voltage;
    double maxDist = 23;
    double minVoltage =1.75;
    double maxVoltage = 2.5;
    double dist;

    public Potentiometer(HardwareMap hardwareMap) {
        potentiometer = hardwareMap.analogInput.get("pot");
    }

    public void getInput()
    {
        voltage = potentiometer.getVoltage();
        dist = (voltage-minVoltage)*(maxDist/(maxVoltage-minVoltage));
    }

    public double getDist()
    {
        getInput();
        return dist;
    }

    public String display()
    {
        return "voltage: " + voltage
                +"\nMax Voltage: " + maxVoltage
                +"\nAngle: " + dist;
    }

}
