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

    private int targetLevel;

    private LinearOpMode opMode;

    private PIDLoop elevatorCL = new PIDLoop();



    public Elevator(HardwareMap hardwareMap)
        {

            elevator = hardwareMap.dcMotor.get("elevator");

            elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            this.opMode = opMode;
        }


    public void setMoveDist(double dist) {

        if (this.opMode.opModeIsActive()) {

            target = elevator.getCurrentPosition()
                    + (int) (dist * constant.getELEVATOR_TICKS_PER_INCH());


            elevator.setTargetPosition(target);

            elevatorCL.setTarget(target);

            while(this.opMode.opModeIsActive() &&
                    (Math.abs((elevator.getCurrentPosition() - target)) > constant.getDRIVE_TOLERANCE()))
            {
                elevator.setPower(elevatorCL.pLoop(elevator.getCurrentPosition()));
            }

            elevator.setPower(0);

        }
    }

    public void moveLevel(int level){

        targetLevel = level - currentLevel;
        currentLevel = level;

        setMoveDist(targetLevel * 6);

    }

    //TODO: ADD DISPLAY
    @Override
    public String display() {
        return null;
    }
}