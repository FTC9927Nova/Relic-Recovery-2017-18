package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Created by Ethan Pereira on 3/8/2018.
 */

public class GoToPos
{
    private LinearOpMode linearOpMode;
    private double xTarget = 0, yTarget = 0, phiTarget =0;
    RobotPos Target = new RobotPos(xTarget,yTarget,phiTarget);
    LeftRightVelMatrix velMatrix;
    double vel = 12;
    RobotPos Current;
    DcMotor l1, l2, r1, r2;

    private double omega,targetOmega;

    public GoToPos(RobotPos current, RobotPos target)
    {

    }


    public boolean hasReached()
    {
        return Math.abs(omega-0.5)<0;
    }
    public void goToPos()
    {
        velMatrix = new LeftRightVelMatrix(vel,omega);
        while(linearOpMode.opModeIsActive()&&!hasReached())
        {
            omega = Math.atan2((Target.getY()-Current.getY()),Target.getX()-Current.getX());

            ((DcMotorEx)l1).setVelocity(velMatrix.vl/2, AngleUnit.DEGREES);
            ((DcMotorEx)l2).setVelocity(velMatrix.vl/2, AngleUnit.DEGREES);
            ((DcMotorEx)r1).setVelocity(velMatrix.vl/2, AngleUnit.DEGREES);
            ((DcMotorEx)r2).setVelocity(velMatrix.vl/2, AngleUnit.DEGREES);
            velMatrix.update();;
            //TODO: CELAN UP AND OTHER MATRIX AND MATH DOUBLE CHECK
        }
    }
}
