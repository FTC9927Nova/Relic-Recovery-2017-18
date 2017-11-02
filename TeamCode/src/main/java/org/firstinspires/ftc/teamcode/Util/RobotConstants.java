package org.firstinspires.ftc.teamcode.Util;

/**
 * Created by therat0981 on 10/5/17.
 */

public class RobotConstants
{
    private final double ROBOT_WIDTH = 17;
    private final double ROBOT_LENGTH = 17;
    private final double WHEEL_DIAMETER = 4;
    private final int DRIVE_TOLERANCE = 30;
//    20
    //It was 5 at first and worked really well
    private final double TURN_TOLERANCE = .3;
    private final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

    private final double FOUR_BAR_TOLERANCE = 0;

    //TODO: WITH TESTING
    private final int ENCODER_TICKS_PER_REVOLUTION = 831;

    //TODO: GET ELEVATOR MEASUREMENTS
    private final int ELEVATOR_TICKS_PER_ONE_MOVEMENT = -1163;
    private final int ELEVATOR_INCHES_PER_ONE_MOVEMENT = 13;
//    15.875
    private final int ELEVATOR_TICKS_PER_INCH = ELEVATOR_TICKS_PER_ONE_MOVEMENT / ELEVATOR_INCHES_PER_ONE_MOVEMENT;

    public int getTICKS_PER_INCH()
    {
        return (int)(ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
    }

    public int getELEVATOR_TICKS_PER_INCH() { return ELEVATOR_TICKS_PER_INCH; }

    public int getDRIVE_TOLERANCE()
    {
        return DRIVE_TOLERANCE;
    }

    public double getTurnTolerance()
    {
        return TURN_TOLERANCE;
    }

    public double getFOUR_BAR_TOLERANCE() { return FOUR_BAR_TOLERANCE;}



}
