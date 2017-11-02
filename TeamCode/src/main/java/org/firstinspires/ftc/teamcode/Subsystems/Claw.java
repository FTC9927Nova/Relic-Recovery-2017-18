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
    private DcMotor clWheel  = null;
    private Servo grabber =  null;
    private ModernRoboticsI2cRangeSensor ultrasonicSensor = null;

    boolean isGrabbed = false;
    boolean isOpen = false;

    //TODO: adjust spin
    private double spin = 0.5;

    public Claw(HardwareMap hardwareMap)
    {

//        clWheel = hardwareMap.dcMotor.get("clWheel");
        grabber = hardwareMap.get(Servo.class, "claw");

//        ultrasonicSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "ultrasonic");


        //TODO: Set up min max
        // grabber.scaleRange(0,1);

        //TODO: set cl wheel direction
       //    clWheel.setDirection(DcMotorSimple.Direction.);
    }

    //TODO: adjust position

    //grabber
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

    //clWheel
    public void intake()
    {
        clWheel.setPower(spin);
        checkisgrabbed();
        if(isGrabbed)
        {
            doesNothing();
        }
    }

    public void outtake()
    {
        clWheel.setPower(-spin);
        checkisgrabbed();
        if(isGrabbed)
        {
            doesNothing();
        }

    }
    public Double showPos(){

        return grabber.getPosition();
    }

    public void doesNothing()
    {
        clWheel.setPower(0);
    }


    // ultrasonicsensor
    private void checkisgrabbed()
    {
        if (ultrasonicSensor.getDistance(DistanceUnit.INCH)< 0.5)
            isGrabbed = true;
        else if(ultrasonicSensor.getDistance(DistanceUnit.INCH)>6)
            isGrabbed = false;
    }

    //TODO: ADD DISPLAY
    @Override
    public String display()
    {
        return "Claw"
                +"\n  Spin: " + spin
                +"\n  OpDistance: " + (ultrasonicSensor.cmOptical()/2.54)
                +"\n  isGrabbed  " + isGrabbed
                +"\n  is Claw open:  " + isOpen;
    }
}
