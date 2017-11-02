package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by Ethan Pereira on 11/2/2017.
 */

public class Pentiometer {
AnalogInput potentiometer;
DcMotor fourBar;
RobotConstants constants = new RobotConstants();
PIDLoop pidLoop = new PIDLoop();
    //Initializes the fourbar
    public Pentiometer (HardwareMap hardwareMap) {
        potentiometer = hardwareMap.analogInput.get("pentiometer");
        double value = potentiometer.getVoltage();
    }

    public void setSlidePosition(int target, double power)
    {
        power = Math.abs(power);   // make sure power is positive.
        if (potentiometer.getVoltage() > target) {
            power = -1*power;  // if current position is past target, make the motor go backward.
        }
        while (Math.abs(target - potentiometer.getVoltage()) > constants.getFOUR_BAR_TOLERANCE())
        {
            fourBar.setPower(power);
        }
        fourBar.setPower(0.0);
    }

    //Or


}
