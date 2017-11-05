package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by Ethan Pereira on 11/2/2017.
 */

public class Pentiometer {
    AnalogInput potentiometer;

    float voltage;
    float percentTurned;
    float angle;
    //Initializes the fourbar

    public Pentiometer (HardwareMap hardwareMap) {
        potentiometer = hardwareMap.analogInput.get("pentiometer");
    }

    public void getInput()
    {
        voltage = (float)potentiometer.getVoltage();
        percentTurned = (voltage/5.0f);
        angle = percentTurned * 250;
    }

    public String display()
    {
        return "voltage: " + voltage
                +"\nPercent Turned: " + percentTurned
                +"\nAngle: " + angle;
    }



}
