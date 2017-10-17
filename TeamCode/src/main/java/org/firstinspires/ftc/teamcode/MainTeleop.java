package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by therat0981 on 10/1/17.
 */

public class MainTeleop extends OpMode
{
    Robot robot = new Robot();

    @Override
    public void init()
    {
        robot.initTeleop(hardwareMap);
    }

    public void loop()
    {

        //Driver Code
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;

        float lpwr = (float) Math.pow((yval + xval), 3);
        float rpwr = (float) Math.pow((yval - xval), 3);


        //turtle mode
        if (gamepad1.right_bumper || gamepad1.right_bumper)
        {
            lpwr = lpwr/2;
            rpwr = rpwr/2;
        }

        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

        //Operator Code
        //claw
        //elevator

    }



    public void stop()
    {

    }


}
