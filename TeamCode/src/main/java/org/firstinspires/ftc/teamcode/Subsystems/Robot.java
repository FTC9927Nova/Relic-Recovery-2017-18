package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.Bumper;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.Potentiometer;

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
    public Bumper bumper;
    public Potentiometer potentiometer;
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
        relic = new RelicMech(hardwareMap);
        bumper = new Bumper(hardwareMap);
        potentiometer = new Potentiometer(hardwareMap);
        bar4.getPotentiometer(potentiometer);



//        vumarkDetect = new VumarkDetect(hardwareMap);
    }

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode, Gyro gyrofromOpMode)
    {
        driveTrain = new DriveTrain(hardwareMap,linearOpMode);
        driveTrain.setGyro(gyrofromOpMode);
        jewelArm = new JewelArm(hardwareMap);
        bar4 = new FourBar(hardwareMap);
        wheels = new Wheels(hardwareMap);
        bumper = new Bumper(hardwareMap);
        potentiometer = new Potentiometer(hardwareMap);
        bar4.getPotentiometer(potentiometer);




//        vumarkDetect = new VumarkDetect(hardwareMap);
    }


}
