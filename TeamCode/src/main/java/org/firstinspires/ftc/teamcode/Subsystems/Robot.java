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
    public FourBar fourBar;
    public JewelArm jewelArm;
    public Wheels wheels;
    public LimitDevices limitDevices;
    public VumarkDetect vumarkDetect;

    public Robot()
    {

    }

    public void init(HardwareMap hardwareMap, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap);
        driveTrain.setGyro(gyrofromOpMode);
//        jewelArm = new JewelArm(hardwareMap);
        fourBar = new FourBar(hardwareMap);
        wheels = new Wheels(hardwareMap);
        limitDevices = new LimitDevices(hardwareMap);
//        elevator = new Lift(hardwareMap);


//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap,linearOpMode);
        driveTrain.setGyro(gyrofromOpMode);
//        jewelArm = new JewelArm(hardwareMap);
        fourBar = new FourBar(hardwareMap);
        wheels = new Wheels(hardwareMap);
        limitDevices = new LimitDevices(hardwareMap);
//        vumarkDetect = new VumarkDetect(hardwareMap);
    }


}
