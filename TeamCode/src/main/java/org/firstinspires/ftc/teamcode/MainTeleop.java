package org.firstinspires.ftc.teamcode;


import com.qualcomm.analytics.Analytics;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainDos;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.PIDLoop;
import org.firstinspires.ftc.teamcode.Util.Potentiometer;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "Main Teleop")
public class MainTeleop extends OpMode
{

    Robot robot = new Robot();
    Gyro gyro = new Gyro();



    private double currentHeight;
    private double lastheight;


    @Override
    public void init()
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);
        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        robot.driveTrain.setDrive(DriveTrain.Drive.SPEED);

    }


    public void loop() {
        //keeping arm up
        robot.jewelArm.armUp();
        robot.jewelArm.arm2Up();

        currentHeight = robot.bar4.getHeight();

        //Driver Code

        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float lpwr = (float) Math.pow(((yval - xval)), 3);
        float rpwr = (float) Math.pow((yval + xval), 3);


//        turtle mode
        if (gamepad1.right_trigger != 0) {
            lpwr /= 3.0f;
            rpwr /= 3.0f;
        }

        else if(gamepad1.left_trigger != 0){
            lpwr /= 7.0f;
            rpwr /= 7.0f;
        }

        if(Math.abs(lpwr)<0.1)
            lpwr = 0;

        if(Math.abs(rpwr)<0.1)
            rpwr = 0;

//      Setting the power for dt
        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

//      Claw and Extender for Relic
        if (gamepad2.x)
            robot.relic.clawOpen();
        else if (gamepad2.y)
            robot.relic.clawClose();
        if (gamepad2.a)
            robot.relic.pullExtenderUp();
        else if (gamepad2.b)
            robot.relic.putExtenderDown();

        // Arm Control
        if (Math.abs(gamepad2.left_stick_y) > 0.05) {

            robot.bar4.setPower(-gamepad2.left_stick_y/2.0);
            lastheight = robot.bar4.getHeight();

        }  else if(Math.abs(currentHeight-lastheight)>1)
        {
            robot.bar4.setMoveHeight(lastheight);
        }
        else
        {
            robot.bar4.setPower(0);
        }

        // intake wheel
        if(gamepad2.left_trigger!=0) {
            //both glyphs outtake
            robot.wheels.fullOuttake();
        }
        else if(gamepad2.right_trigger!=0) {
            //last glyph outtake
            robot.wheels.halfOuttake();
        }
        else
        {

            if (gamepad2.right_bumper) {
                //left claw intake
                robot.wheels.leftClawIntake();
            } else {
                robot.wheels.setLeftServoPwr(0);
                robot.wheels.setLeftWheelPwr(0);
            }
            if (gamepad2.left_bumper) {
                //right claw intake
                robot.wheels.rightClawIntake();
            } else {
                robot.wheels.setRightServoPwr(0);
                robot.wheels.setRightWheels(0);
            }
        }


        if(gamepad2.right_stick_y!=0){
            robot.wheels.unLatch();
        }

        //Zlides
        if (gamepad2.dpad_up) {
            robot.relic.setPower(1);
        } else if (gamepad2.dpad_down) {
            robot.relic.setPower(-1);
        } else {
            robot.relic.setPower(0);
        }

    }



    public void stop()
    {

    }


}