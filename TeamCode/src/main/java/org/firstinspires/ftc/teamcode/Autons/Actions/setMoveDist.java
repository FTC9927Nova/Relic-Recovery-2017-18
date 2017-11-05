package org.firstinspires.ftc.teamcode.Autons.Actions;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.PIDLoop;

/**
 * Created by therat0981 on 11/2/17.
 */

public class setMoveDist implements Action
{
    DriveTrain driveTrain = Robot.getDriveTrainInstance();
    private double target;
    private int leftTarget,rightTarget;
    PIDLoop driveCl = new PIDLoop(0.005, 0, 0);

    public setMoveDist(Double target)
    {
        this.target = target;
    }

    //set Mode to set Target
    @Override
    public void start()
    {
        driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        driveTrain.setDrive(DriveTrain.Drive.ENCODERS);
        driveTrain.setSpeedController(DriveTrain.DriveSpeedController.BRAKE);
        leftTarget = (int) (target * constant.getTICKS_PER_INCH());
        rightTarget = (int) (target * constant.getTICKS_PER_INCH());


        driveTrain.setLeftTarget(leftTarget);
        driveTrain.setRightTarget(rightTarget);

    }

    //return encoder position
    @Override
    public void update()
    {
        driveTrain.setLeftPower(driveCl.pLoop(driveTrain.getLeftCurrentPosition()));
        driveTrain.setRightPower(driveCl.pLoop(driveTrain.getRightCurrentPosition()));
    }

    //check if encoder position  = to target position
    @Override
    public boolean isFinished()
    {
        return Math.abs((driveTrain.getLeftCurrentPosition()-leftTarget))>constant.getDRIVE_TOLERANCE()
                && Math.abs((driveTrain.getRightCurrentPosition()-rightTarget))>constant.getDRIVE_TOLERANCE();
    }


    //Stop
    @Override
    public void stop()
    {
        driveTrain.setLeftPower(0);
        driveTrain.setRightPower(0);
    }
}
