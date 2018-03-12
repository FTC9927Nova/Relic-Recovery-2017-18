package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Ethan Pereira on 2/24/2018.
 */

public class Range {
    ModernRoboticsI2cRangeSensor rangeSensor;

    public Range(HardwareMap hardwareMap) {
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
    }

    public double getDist()
    {
        return rangeSensor.getDistance(DistanceUnit.CM);
    }

    public boolean isGlyph()
    {
        return getDist()<6;
    }

}
