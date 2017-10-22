package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "TeleOp")

public class MainTeleop extends OpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void init()
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);
    }

    public void loop()
    {

        //Driver Code
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float lpwr = (float) Math.pow((yval + xval), 3);
        float rpwr = (float) Math.pow((yval - xval), 3);


//        turtle mode
        if (gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0)
        {
            lpwr = lpwr/2.0f;
            rpwr = rpwr/2.0f;
        }

        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

        telemetry.addData("",robot.driveTrain.display());

        if(gamepad1.y){

            robot.elevator.setPower(-0.5);

        }

        else if(gamepad1.x){

            robot.elevator.setPower(0.5);

        }
        else{

            robot.elevator.setPower(0.0);

        }

        if (gamepad1.right_bumper){
            robot.claw.open();
        } else if (gamepad1.left_bumper){
            robot.claw.close();
        }

        //Operator Code
        //claw
        //elevator

    }



    public void stop()
    {

    }


}
