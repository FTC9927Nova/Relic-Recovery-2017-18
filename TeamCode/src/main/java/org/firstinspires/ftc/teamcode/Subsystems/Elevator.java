package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.*;

/**
 * Created by therat0981 on 10/1/17.
 */

public class Elevator implements SubsystemTemplate
{



    private DcMotor elevator = null;

    private int target;

    private int currentLevel = 0;

    private int pastPos, currentPos;

    private int targetLevel;

    boolean hasReached = false;


    private PIDLoop elevatorCL = new PIDLoop(.005,0.005,0.001);

    private PIDLoop correction = new PIDLoop();

    public Elevator(HardwareMap hardwareMap)
        {

            elevator = hardwareMap.dcMotor.get("elevator");

            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }

    public void setElevatorMode(DcMotor.RunMode runMode)
    {
        elevator.setMode(runMode);
    }




    public void setMoveDist(double dist) {

        if (!hasReached) {

            target = elevator.getCurrentPosition()
                    + (int) (dist * constant.getELEVATOR_TICKS_PER_INCH());

            elevator.setTargetPosition(target);

            elevatorCL.setTarget(target);

            while(!hasReached && (Math.abs((elevator.getCurrentPosition() - target)) > constant.getDRIVE_TOLERANCE()))
            {
                elevator.setPower(elevatorCL.pLoop(elevator.getCurrentPosition()));
            }

            hasReached = true;

            elevator.setPower(0);

        }

        hasReached = false;


    }

    public int getCurrentLevel(){

        return currentLevel;

    }

    public void resetEnc(){

        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public int getEnc(){

        return elevator.getCurrentPosition();

    }

    public void setPower(double power){

        elevator.setPower(power);

    }

    public void getPastPos()
    {
        elevator.getCurrentPosition();
    }

    public double stayInPlace()
    {
        if(Math.abs(pastPos-target)< 15)
        {
            elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            elevatorCL.setTarget(target);
            return elevatorCL.pidLoop(pastPos,0.01);
        }
        return 0;
    }

    public void moveLevel(int level){
        if(level <= 2 && level >= 0) {
            targetLevel = level - currentLevel;
            currentLevel = level;
            setMoveDist(targetLevel * 6.25);

        }

    }

    //TODO: ADD DISPLAY
    @Override
    public String display() {
        return  "error " +  elevatorCL.getError();
    }
}
