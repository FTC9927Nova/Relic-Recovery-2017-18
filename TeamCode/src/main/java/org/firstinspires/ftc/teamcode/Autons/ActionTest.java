package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autons.Actions.Action;
import org.firstinspires.ftc.teamcode.Autons.Actions.Wait;
import org.firstinspires.ftc.teamcode.Autons.Actions.setMoveDist;

/**
 * Created by therat0981 on 11/5/17.
 */

public class ActionTest extends AutoModeBase
{
    public ActionTest(LinearOpMode linearOpMode)
    {
        super(linearOpMode);
    }
    @Override
    public void routine() throws UnexpectedStop {
        runAction(new setMoveDist(10.0));
        runAction(new Wait(2000));
    }
}
