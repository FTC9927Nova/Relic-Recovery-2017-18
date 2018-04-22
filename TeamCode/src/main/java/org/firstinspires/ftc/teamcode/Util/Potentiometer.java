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
    double MaxDist = 20.5;
    double minVoltge = 0.273;
    double MaxVoltage = 0;

    public Potentiometer(HardwareMap hardwareMap) {
        potentiometer = hardwareMap.analogInput.get("pot");
    }


    public double getDist()
    {
        double dist  = ((potentiometer.getVoltage()-minVoltge)/MaxVoltage)*MaxDist;
        return dist;
    }

    public String display()
    {
        return ""; 
    }

}
