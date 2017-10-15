package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.VumarkDetect;

/**
 * Created by therat0981 on 10/1/17.
 */

public class Robot
{
    public DriveTrain driveTrain;
    public Claw claw;
    public Elevator elevator;
    public JewelArm jewelArm;
    public VumarkDetect vumarkDetect;


    public Robot()
    {

    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap, linearOpMode);
//          claw = new Claw(hardwareMap);
//        elevator = new Elevator(hardwareMap);
      //  jewelArm = new JewelArm(hardwareMap);
//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

}
