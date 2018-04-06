package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.Potentiometer;
import org.firstinspires.ftc.teamcode.Util.Range;

/**
 * Created by therat0981 on 10/1/17.
 */

public class Robot
{
    public DriveTrain driveTrain;
    public JewelArm jewelArm;
    public FourBar bar4;
    public Wheels wheels;
    public RelicMech relic;
    public Potentiometer potentiometer;
    public Range range;


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
        relic = new RelicMech(hardwareMap);
        range = new Range(hardwareMap);
        potentiometer = new Potentiometer(hardwareMap);
        bar4.getPotentiometer(potentiometer);
    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap,linearOpMode);
        driveTrain.setGyro(gyrofromOpMode);
        jewelArm = new JewelArm(hardwareMap);
        bar4 = new FourBar(hardwareMap,linearOpMode);
        wheels = new Wheels(hardwareMap);
        range = new Range(hardwareMap);
        potentiometer = new Potentiometer(hardwareMap);
        bar4.getPotentiometer(potentiometer);
    }


}
