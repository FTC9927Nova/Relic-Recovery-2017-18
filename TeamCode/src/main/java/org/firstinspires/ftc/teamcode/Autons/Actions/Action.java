package org.firstinspires.ftc.teamcode.Autons.Actions;

import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by therat0981 on 11/2/17.
 */

public interface Action
{
     RobotConstants constant = new RobotConstants();
     void start();

     void update();

     boolean isFinished();

     void stop();
}
