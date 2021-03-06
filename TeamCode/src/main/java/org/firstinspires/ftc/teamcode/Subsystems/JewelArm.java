package org.firstinspires.ftc.teamcode.Subsystems;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by therat0981 on 10/5/17.
 */

public class JewelArm implements SubsystemTemplate
{
    private Servo jewlArm, arm2;
    private ColorSensor jewlCheck, check2;


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
        arm2 = hardwareMap.servo.get("jewl2");


            check2 = hardwareMap.colorSensor.get("color2");
            jewlCheck = hardwareMap.colorSensor.get("color");


        jewlArm.setDirection(Servo.Direction.FORWARD);
        jewlArm.scaleRange(0,1);

        arm2.setDirection(Servo.Direction.REVERSE);
        arm2.scaleRange(0,1);

        jewlCheck.enableLed(false);
        check2.enableLed(false);
    }


    public JewelArm(HardwareMap hardwareMap, boolean isTeleop)
    {
        jewlArm = hardwareMap.servo.get("jewl");
        arm2 = hardwareMap.servo.get("jewl2");

        if(!isTeleop)
        {

            check2 = hardwareMap.colorSensor.get("color2");
            jewlCheck = hardwareMap.colorSensor.get("color");

            jewlCheck.enableLed(false );
            check2.enableLed(false);

        }


        jewlArm.setDirection(Servo.Direction.FORWARD);
        jewlArm.scaleRange(0,1);
        arm2.setDirection(Servo.Direction.REVERSE);
        arm2.scaleRange(0,1);


    }

    public void armDown()
    {
//        isArmDown = true;
        jewlArm.setPosition(0);
    }
    public void arm2Down(){

        arm2.setPosition(0.1);


    }


    public void armMid()
    {
        jewlArm.setPosition(0.8);


    }

    public double getPos2(){
        return arm2.getPosition();
    }

    public double getPos(){
        return  jewlArm.getPosition();
    }
    public void arm2Mid(){

        arm2.setPosition(0.8);


    }

    public void armUp()
    {
        jewlArm.setPosition(0.81);

//        isArmDown = false;

    }

    public void arm2Up(){

        arm2.setPosition(0.81);


    }

    public void arm2SetPos(double val)
    {
        if(val<0)
            val = 0;
        arm2.setPosition(val);
    }

    //if hue is closer to 180 it is blue cos(pi) is -1
    //if hue is closer to 0 or 360 it is red cos(0) is 1 or cos(360) is 1
    private double scaleHSV()
    {
        getHSV();
        return Math.cos(Math.toRadians(hsvValues[0]));

    }

    private double scaleHSV2(){

        getHSV2();
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

    public ColorDetected getColor2(){

        if(scaleHSV2()<0)
            return ColorDetected.BLUE;
        else if(scaleHSV2()>0)
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

    private void getHSV2(){

        Color.RGBToHSV((int) (check2.red() * SCALE_FACTOR),
                (int) (check2.green() * SCALE_FACTOR),
                (int) (check2.blue() * SCALE_FACTOR),
                hsvValues);

    }



    public String getStringVal(){
        if (jewlCheck.blue() > jewlCheck.red()){
            return "BLUE";
        } else if (jewlCheck.blue() < jewlCheck.red()){
//            return "RED";
        }
        return "NOTHING";
    }
    @Override
    public String display() {

        return "Jewel Arm"
                +"   \n right: " + jewlArm.getPosition() + " left " + arm2.getPosition()
                +"   \n get Color: " + getColor()
                +"   \n cos(hsv[0])" + scaleHSV()
                +"   \n get Color2: " + getColor2()
                +"   \n cos(hsv[0])2" + scaleHSV2()
                +"   \n hsv-    Hue: " + hsvValues[0] + "    Saturation: " + hsvValues[1] + "     Value: " + hsvValues[2];

    }
}
