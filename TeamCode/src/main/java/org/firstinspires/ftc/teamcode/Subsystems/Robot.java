package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.Gyro;
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
    Gyro gyro = new Gyro();

    public Robot()
    {

    }

    public void init(HardwareMap hardwareMap)
    {
        driveTrain = new DriveTrain(hardwareMap);
        gyro.initGyro(hardwareMap);
//          claw = new Claw(hardwareMap);
//        elevator = new Arm(hardwareMap);
      //  jewelArm = new JewelArm(hardwareMap);
//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap,linearOpMode);
        driveTrain.setGyro(gyrofromOpMode);
//        claw = new Claw(hardwareMap);
//        elevator = new Arm(hardwareMap);
          jewelArm = new JewelArm(hardwareMap);
//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

}
