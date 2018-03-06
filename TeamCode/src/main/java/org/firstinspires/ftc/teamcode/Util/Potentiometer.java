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
    double voltageCalc;
    int percentTurned;
    double percentTurnedDecimal;
    double angle;
    double maxVoltage;

    public Potentiometer(HardwareMap hardwareMap) {
        potentiometer = hardwareMap.analogInput.get("pot");
    }

    public void getInput()
    {
        voltageCalc = potentiometer.getVoltage();
        voltageCalc = (int)(voltageCalc * 10) + 0.5;
        voltageCalc =  voltageCalc;

        voltage = voltageCalc/10;

        maxVoltage = potentiometer.getMaxVoltage();

        percentTurned = (int) (voltage/maxVoltage) * 100;
        percentTurnedDecimal = (voltage/maxVoltage);

        angle = percentTurnedDecimal * 270;
    }

    public double getAngle()
    {
        getInput();
        return angle;
    }

    public String display()
    {
        return "voltage: " + voltage
                +"\nMax Voltage: " + maxVoltage
                +"\nPercent Turned: " + percentTurned
                +"\nAngle: " + angle;
    }

}
