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
    public Lift elevator;
    public JewelArm jewelArm;
    public VumarkDetect vumarkDetect;
    public FourBar bar4;
    public Wheels wheels;
    Gyro gyro = new Gyro();

    public Robot()
    {

    }

    public void init(HardwareMap hardwareMap, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap);
        driveTrain.setGyro(gyrofromOpMode);
        jewelArm = new JewelArm(hardwareMap);
        bar4 = new FourBar(hardwareMap);
        wheels = new Wheels(hardwareMap);



//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap,linearOpMode);
        driveTrain.setGyro(gyrofromOpMode);
        jewelArm = new JewelArm(hardwareMap);
        bar4 = new FourBar(hardwareMap);
        wheels = new Wheels(hardwareMap);


//        vumarkDetect = new VumarkDetect(hardwareMap);
    }


}
