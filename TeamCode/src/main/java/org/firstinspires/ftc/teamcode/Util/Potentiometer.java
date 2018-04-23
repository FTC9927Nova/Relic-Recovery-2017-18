package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by Ethan Pereira on 11/2/2017.
 * This utility is used to give us information about the Four Bar angle.
 */

public class Potentiometer {

    AnalogInput potentiometer;
    DigitalChannel lowerLimit;
    DigitalChannel upperLimit;

    double voltage;
    double MaxDist = 20.5;
    double minVoltge = 0;
    double MaxVoltage = 0;

    public Potentiometer(HardwareMap hardwareMap) {
        potentiometer = hardwareMap.analogInput.get("pot");
        lowerLimit = hardwareMap.digitalChannel.get("limitDown");
        upperLimit = hardwareMap.digitalChannel.get("limitUp");
        minVoltge = potentiometer.getVoltage();
        MaxVoltage = 1.973-minVoltge;
    }


    public double getDist()
    {
        if(!lowerLimit.getState())
        {
            minVoltge = potentiometer.getVoltage();
        }
        if(!upperLimit.getState())
        {
            MaxVoltage = potentiometer.getVoltage()-minVoltge;
        }
        double dist  = (((potentiometer.getVoltage()-minVoltge)/MaxVoltage)*MaxDist)*0.99898043;
        return ((int)((dist*100.0)+0.5))/100.0;
    }

    public String display()
    {
        return "Upper Limit: " + upperLimit.getState() +  "  Max Voltage:  " + MaxVoltage +
                "\nLower Limit " + lowerLimit.getState() +  " Min Voltage " + minVoltge +
                "\nCurrent Voltage " + potentiometer.getVoltage() + " Get Height" + getDist();
    }

}
