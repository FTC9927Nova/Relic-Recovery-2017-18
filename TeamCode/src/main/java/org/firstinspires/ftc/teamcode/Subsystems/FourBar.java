package org.firstinspires.ftc.teamcode.Subsystems;

import android.hardware.Sensor;
import android.util.Range;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.*;


/**
 * Created by Ethan Pereira on 11/5/2017.
 */


public class FourBar implements SubsystemTemplate {

    private DcMotor fourBar;
    private double targetAngle;;
    private boolean shouldStay = true;
    private double currentAngle = 0;

    private Sensor LimitSwitch_Zero;

    private RobotConstants constants = new RobotConstants();

    private PIDLoop pidLoop = new PIDLoop((1.0/143), (1.0/200), 0);
    private Potentiometer pot;

    public FourBar(HardwareMap hardwareMap){
        fourBar = hardwareMap.dcMotor.get("bar4");
        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void getPotentiometer(Potentiometer pot)
    {
        this.pot = pot;
    }

    public double setHeight(double height)
    {

        return (Math.toDegrees(Math.acos(1 - ((Math.pow(height, 2))/(2 * Math.pow(constants.getFOURBARLENGTH(), 2))))) + 126);

    }

    public void setTargetAngle()
    {
        if(shouldStay)
            targetAngle = pot.getAngle();
        shouldStay = false;
    }

    public double getTargetAngle(){

        return targetAngle;

    }

    public void shouldStayTrue(){

        shouldStay = true;

    }


    public double getCurrentAngle()
    {
        pot.getInput();
        currentAngle = pot.getAngle();
        return currentAngle;
    }




    public void setMoveAngle(double targetAngle)
    {
        pidLoop.setTarget(targetAngle);
        fourBar.setPower(pidLoop.pLoop(pot.getAngle()));
    }



    public void setPower(double power){

        fourBar.setPower(power);

    }


    @Override
    public String display() {
        return
                "\nTarget Angle: " + targetAngle
                +"\nCurrent Angle: " + currentAngle
                +"\nPower " + fourBar.getPower()
                +"\nShould Stay? " + shouldStay;
    }
}
