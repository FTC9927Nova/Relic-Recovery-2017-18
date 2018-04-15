package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.*;


/**
 * Created by Ethan Pereira on 11/5/2017.
 */



//TODO: FIX WITH STRING POT
public class FourBar implements SubsystemTemplate {

    private DcMotor fourBar;
    private LinearOpMode linearOpMode;
    private boolean shouldStay = true;
    private double height = 0;
    private DigitalChannel bar4limit;
    private DigitalChannel limitSwitch_Zero;

    private RobotConstants constants = new RobotConstants();

    private PIDLoop pidLoop = new PIDLoop((1.0/60), (1.0/200), 0);
    public Potentiometer pot;

    public FourBar(HardwareMap hardwareMap){
        fourBar = hardwareMap.dcMotor.get("bar4");
        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bar4limit = hardwareMap.digitalChannel.get("limitUp");
        limitSwitch_Zero = hardwareMap.digitalChannel.get("limitDown");
        pot = new Potentiometer(hardwareMap);
    }

    public FourBar(HardwareMap hardwareMap, LinearOpMode linearOpMode){
        fourBar = hardwareMap.dcMotor.get("bar4");
        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bar4limit = hardwareMap.digitalChannel.get("limitUp");
        limitSwitch_Zero = hardwareMap.digitalChannel.get("limitDown");
        this.linearOpMode = linearOpMode;

    }

    public void getPotentiometer(Potentiometer pot)
    {
        this.pot = pot;
    }

    public double getHeight()
    {
        pot.getInput();
        return pot.getDist();
    }

    public void setTargetAngle()
    {
        if(shouldStay)
            shouldStay = false;
    }

    public double getTargetAngle(){

        return 0;

    }

    public void shouldStayTrue(){

        shouldStay = true;

    }


    public double getCurrentAngle()
    {
        pot.getInput();
        height = pot.getDist();
        return height;
    }




    public void setMoveHeight(double targetHeight)
    {
        pidLoop.setTarget(targetHeight);
        while(this.linearOpMode.opModeIsActive()&&Math.abs(pot.getDist()-targetHeight)>0.5) {
            fourBar.setPower(pidLoop.pLoop(pot.getDist()));
        }
        fourBar.setPower(0);
    }

    public boolean isHit()
    {
        return bar4limit.getState();
    }

    public boolean isLowerHit()
    {
        return  limitSwitch_Zero.getState();
    }



    public void setPower(double power){
        fourBar.setPower(power);
    }


    @Override
    public String display() {
        return "";
    }
}
