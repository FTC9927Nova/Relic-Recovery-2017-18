package org.firstinspires.ftc.teamcode.Subsystems;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by therat0981 on 10/5/17.
 */

public class JewelArm implements SubsystemTemplate
{
    private Servo jewlArm = null;
    private ColorSensor jewlCheck = null;


    private boolean isArmDown = false;

    float[] hsvValues = {0F,0f, 0f};
    private final double SCALE_FACTOR = 255;


    public enum ColorDetected
    {
        RED,
        BLUE,
        None
    }


    public JewelArm(HardwareMap hardwareMap)
    {
        jewlArm = hardwareMap.servo.get("jewl");
//        jewlCheck = hardwareMap.get(ColorSensor.class,"jewlCheck");
        jewlCheck = hardwareMap.colorSensor.get("jewlCheck");

        jewlArm.setDirection(Servo.Direction.FORWARD);
        jewlArm.scaleRange(0,1);

        jewlCheck.enableLed(true);
    }

    public void armDown()
    {
        isArmDown = true;
        jewlArm.setPosition(0.88);
    }

    public void armUp()
    {
        isArmDown = false;
        jewlArm.setPosition(0.17);
    }

    //if hue is closer to 180 it is blue cos(pi) is -1
    //if hue is closer to 0 or 360 it is red cos(0) is 1 or cos(360) is 1
    private double scaleHSV()
    {
        getHSV();
        return Math.cos(Math.toRadians(hsvValues[0]));
    }

    public ColorDetected getColor()
    {
        if(scaleHSV()<0)
            return ColorDetected.BLUE;
        else if(scaleHSV()>0)
            return  ColorDetected.RED;
        else
            return ColorDetected.None;
    }




    private void getHSV()
    {
        Color.RGBToHSV((int) (jewlCheck.red() * SCALE_FACTOR),
                (int) (jewlCheck.green() * SCALE_FACTOR),
                (int) (jewlCheck.blue() * SCALE_FACTOR),
                hsvValues);
    }



    @Override
    public String display() {

        return "Jewel Arm"
                +"   \n arm state: " + isArmDown
                +"   \n color-  BLUE: " + jewlCheck.blue() + "   Red: " + jewlCheck.red()
                +"   \n get Color: " + getColor()
                +"   \n cos(hsv[0])" + scaleHSV()
                +"   \n hsv-    Hue: " + hsvValues[0] + "    Saturation: " + hsvValues[1] + "     Value: " + hsvValues[2];
    }
}
