package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import org.firstinspires.ftc.

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by Ethan Pereira on 11/2/2017.
 */

public class Potentiometer {

    Potentiometer potentiometer;

    public Potentiometer(HardwareMap hardwareMap) {

        potentiometer = hardwareMap.analogInput.get("pot");

    }



}
