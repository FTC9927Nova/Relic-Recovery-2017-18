package org.firstinspires.ftc.teamcode;


import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.PIDLoop;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "TeleOpNew")

public class TeleOpNew extends OpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void init()
    {
        gyro.initGyro(this.hardwareMap);
        robot.init(this.hardwareMap, gyro);
        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        robot.driveTrain.setDrive(DriveTrain.Drive.SPEED);


    }

    public void loop()
    {
//        robot.jewelArm.armMid();

        //Driver Code
        float yval = -gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;

//        pastAnlge = currentAnlge;



        float lpwr = (float) Math.pow(((yval + xval)), 3);
        float rpwr = (float) Math.pow((yval - xval), 3);

//
//        Log.i("Angle", String.valueOf(gyro.getYaw()));//Operator Code
//        Log.i("Past Angle", String.valueOf(pastAnlge));



//        turtle mode
        if (gamepad1.left_bumper|| gamepad1.right_bumper)
        {
            lpwr = lpwr/3.0f;
            rpwr = rpwr/3.0f;
        }
//        Log.i("Error", rate.display());

//        telemetry.addData("nope","false");
//
////        if(gamepad1.left_stick_y !=0 && Math.abs(gamepad1.right_stick_x - 0f)<0.1 ) {
//            telemetry.addData("yup","true");
//
//            //left is greater right
//            if ((pastAnlge - currentAnlge) > 3) {
//                rate.setTarget(pastAnlge);
//                lpwr += (float) rate.pLoop(gyro.getYaw());
//              // Log.i("Error", rate.display());
//            }
//
//            //right is greater
//            else if ((pastAnlge - currentAnlge) < 3) {
//                rate.setTarget(pastAnlge);
//                lpwr -= (float) rate.pLoop(gyro.getYaw());
//               // Log.i("Error", rate.display());
//            }
//
////        }


        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

//        currentAnlge = gyro.getYaw();
//        Log.i("Current Angle", String.valueOf(currentAnlge));

        //   telemetry.addData("",robot.driveTrain.display());

        if(gamepad2.y){

            robot.bar4.setPower(.5);


        }

        else if(gamepad2.x){

            robot.bar4.setPower(-0.125);

        }
        else if(gamepad2.a){

            robot.bar4.setPower(0.05);

        }
        else{

            robot.bar4.setPower(0);

        }

        if (gamepad2.left_bumper){
            robot.wheels.setLeftWheelPwr(1);
        }
        else if(gamepad2.left_trigger != 0){

            robot.wheels.setLeftWheelPwr(-1);

        }
        else{

            robot.wheels.setLeftWheelPwr(0);
        }

        if (gamepad2.right_bumper){
            robot.wheels.setRightWheels(1);
        }
        else if(gamepad2.right_trigger != 0) {

            robot.wheels.setRightWheels(-1);

        }
        else{
            robot.wheels.setRightWheels(0);
        }


        if(gamepad2.dpad_up)
        {
            robot.relic.setPower(1);
        }
        else if(gamepad2.dpad_down)
        {
            robot.relic.setPower(-1);
        }
        else
        {
            robot.relic.setPower(0);
        }



        telemetry.update();



    }



    public void stop()
    {

    }


}