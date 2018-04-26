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

    private RobotConstants constants = new RobotConstants();

    private PIDLoop pidLoop = new PIDLoop((1.0/20), (1.0/200), 0);
    public Potentiometer pot;

    public FourBar(HardwareMap hardwareMap){
        fourBar = hardwareMap.dcMotor.get("bar4");
        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pot = new Potentiometer(hardwareMap);
    }

    public FourBar(HardwareMap hardwareMap, LinearOpMode linearOpMode){
        fourBar = hardwareMap.dcMotor.get("bar4");
        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.linearOpMode = linearOpMode;

    }

    public void getPotentiometer(Potentiometer pot)
    {
        this.pot = pot;
    }

    public double getHeight()
    {
//        pot.getInput();
        return pot.getDist();
    }



    public void stop()
    {
        fourBar.setPower(0);
    }

    public boolean setMoveHeight(double targetHeight)
    {
        pidLoop.setTarget(targetHeight);
        double power = pidLoop.pLoop(pot.getDist());
        if(Math.abs(power)>0.2)
            power = 0.2 * Math.signum(power);

        fourBar.setPower(power);

        if(Math.abs((pot.getDist()-targetHeight))<1)
        {
            fourBar.setPower(0);
            return true;
        }

        return false;
    }



    public void setPower(double power){
            fourBar.setPower(power);
    }


    @Override
    public String display() {
        return "";
    }
}
