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
    private static DriveTrain driveTrain;
    private Claw claw;
    private static Lift elevator;
    private static JewelArm jewelArm;

    public Robot()
    {

    }

    public static DriveTrain getDriveTrainInstance()
    {
       return driveTrain;
    }
    public static Lift getLiftInstance()
    {
        return elevator;
    }




    public void init(HardwareMap hardwareMap, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap);
        driveTrain.setGyro(gyrofromOpMode);
        claw = new Claw(hardwareMap);
        jewelArm = new JewelArm(hardwareMap);
        elevator = new Lift(hardwareMap);


//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap,linearOpMode);
        driveTrain.setGyro(gyrofromOpMode);
        claw = new Claw(hardwareMap);
        jewelArm = new JewelArm(hardwareMap);
        elevator = new Lift(hardwareMap);

//        vumarkDetect = new VumarkDetect(hardwareMap);
    }


}
