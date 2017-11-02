package org.firstinspires.ftc.teamcode.Subsystems;

import android.webkit.DownloadListener;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by therat0981 on 10/1/17.
 */

public class Claw implements SubsystemTemplate
{
    private Servo grabber =  null;


    boolean isOpen = false;


    public Claw(HardwareMap hardwareMap)
    {
        grabber = hardwareMap.get(Servo.class, "claw");
    }

    public void open()
    {
        isOpen = true;
        grabber.setPosition(0.58);
    }

    public void close()
    {
        isOpen = false;
        grabber.setPosition(0.39);
    }


    //TODO: ADD DISPLAY
    @Override
    public String display()
    {
        return "Claw"
                +"\n  claw pos:  " + grabber.getPosition()
                +"\n  is Claw open:  " + isOpen;
    }
}
