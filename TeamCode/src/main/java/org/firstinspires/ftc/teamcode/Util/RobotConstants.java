package org.firstinspires.ftc.teamcode.Util;

/**
 * Created by therat0981 on 10/5/17.
 */

public class RobotConstants
{
    private final double ROBOT_WIDTH = 17;
    private final double ROBOT_LENGTH = 17;
    private final double WHEEL_DIAMETER = 4;
    private final int DRIVE_TOLERANCE = 5;
    private final double TURN_TOLERANCE = .3;
    private final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

    //TODO: WITH TESTING
    private final int ENCODER_TICKS_PER_REVOLUTION = 1440;

    public int getTICKS_PER_INCH()
    {
        return (int)(ENCODER_TICKS_PER_REVOLUTION * WHEEL_CIRCUMFERENCE);
    }

    public int getDRIVE_TOLERANCE()
    {
        return DRIVE_TOLERANCE;
    }

    public double getTurnTolerance()
    {
        return TURN_TOLERANCE;
    }




}
