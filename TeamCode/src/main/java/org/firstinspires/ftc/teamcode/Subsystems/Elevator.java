package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

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

    private int encoderZero, maintainPos;


    private PIDLoop elevatorCL = new PIDLoop(.005,0.005,0.001);

    private PIDLoop correction = new PIDLoop();

    public Elevator(HardwareMap hardwareMap)
        {

            elevator = hardwareMap.dcMotor.get("elevator");

            elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            encoderZero = getEnc();

        }

    public void setElevatorMode(DcMotor.RunMode runMode)
    {
        elevator.setMode(runMode);
    }



        //TODO: Uncomment if ever needed for auton
//    public void setMoveDist(double dist) {
//
//        if (!hasReached) {
//
//            target = elevator.getCurrentPosition()
//                    + (int) (dist * constant.getELEVATOR_TICKS_PER_INCH());
//
//            elevator.setTargetPosition(target);
//
//            elevatorCL.setTarget(target);
//
//            while(!hasReached && (Math.abs((elevator.getCurrentPosition() - target)) > constant.getDRIVE_TOLERANCE()))
//            {
//                elevator.setPower(elevatorCL.pLoop(elevator.getCurrentPosition()));
//            }
//
//            hasReached = true;
//
//            elevator.setPower(0);
//
//        }
//
//        hasReached = false;
//
//
//    }



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

    public void stayInPlace()
    {

        if((maintainPos-getEnc()) > 15)
        {

            elevator.setPower(Range.clip(25 *(maintainPos - getEnc())/ (maintainPos) , -1, 1));

        }

        else if((getEnc() - maintainPos) > 15){

            elevator.setPower(Range.clip(25 *(getEnc() - maintainPos)/ (maintainPos) , -1, 1));
        }

        else{

            elevator.setPower(0);

        }

    }

    public void moveUp(){

        elevator.setPower(-Math.pow((encoderZero - getEnc())/ (encoderZero), 2));

    }

    public void moveDown(){

        elevator.setPower(Math.pow((encoderZero - getEnc())/ (encoderZero), 2));

    }

    public void setELevatorZero(int encVal){

        encoderZero = encVal;

    }

    public void setMaintainPos(int encVal){

        maintainPos = encVal;

    }

    //TODO: ADD DISPLAY
    @Override
    public String display() {
        return  "error " +  elevatorCL.getError();
    }
}
