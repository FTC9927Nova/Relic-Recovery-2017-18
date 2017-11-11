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
    private int desiredPosEnc;
    private boolean shouldStay = true;

    private Sensor LimitSwitch_Zero;
    //TODO:CHECK IF 2 LIMIT SWITCHES ARE NEEDED
    // Maybe:
    //private Sensor LimitSwitch_Max;
    private RobotConstants constants = new RobotConstants();

    //MAKE SURE NOT TO MESS WITH KD TERM!!!!!!!!!!!!!
    private PIDLoop pidLoop = new PIDLoop(0.007, 0, 0);
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

        return Math.toDegrees(Math.acos(1 - ((Math.pow(height, 2))/(2 * Math.pow(constants.getFOURBARLENGTH(), 2)))));

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

    public void setMoveAngle(double targetAngle)
    {
        pot.getInput();

        pidLoop.setTarget(targetAngle);
        if(pot.getAngle()<targetAngle)
        {
            fourBar.setPower(pidLoop.pidLoop(targetAngle,1));
        }
        else if(pot.getAngle()>targetAngle)
        {
            setPower(0);
        }
    }



    public void setPower(double power){

        fourBar.setPower(power);

    }


    @Override
    public String display() {
        return null;
    }
}
