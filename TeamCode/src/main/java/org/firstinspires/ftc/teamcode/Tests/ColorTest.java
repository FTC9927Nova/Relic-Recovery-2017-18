package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.RelicMech;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/11/17.
 */
@TeleOp(name = "asdf")
public class ColorTest extends OpMode
{

    RelicMech relic;

    @Override
    public void init()
    {

        relic = new RelicMech(this.hardwareMap);

    }

    @Override
    public void loop()
    {
        if(gamepad2.a)
            relic.clawOpen();
        else if(gamepad2.b)
            relic.clawClose();
        if(gamepad2.y)
            relic.pullExtenderUp();
        else if(gamepad2.x)
            relic.putExtenderDown();
    }
}
