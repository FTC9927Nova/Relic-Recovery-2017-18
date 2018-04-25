package org.firstinspires.ftc.teamcode;


import com.qualcomm.analytics.Analytics;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.FourBar;
import org.firstinspires.ftc.teamcode.Subsystems.RelicMech;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import org.firstinspires.ftc.teamcode.Subsystems.Wheels;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "OPteleop")
@Disabled
public class OperatorTeleop extends OpMode
{

    Robot robot = new Robot();

    DcMotor leftIntake;
    DcMotor rightIntake;



    @Override
    public void init()
    {
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");

    }


    public void loop() {

        if (gamepad1.right_trigger != 0){
            rightIntake.setPower(gamepad1.right_trigger);
        } else if (gamepad1.right_bumper){
            rightIntake.setPower(-1);
        } else {
            rightIntake.setPower(0);
        }

        if (gamepad1.left_trigger != 0){
            leftIntake.setPower(gamepad1.left_trigger);
        } else if (gamepad1.left_bumper){
            leftIntake.setPower(-1);
        } else {
            leftIntake.setPower(0);
        }
    }



    public void stop()
    {

    }


}