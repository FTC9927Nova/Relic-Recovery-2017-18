package org.firstinspires.ftc.teamcode;


import com.qualcomm.analytics.Analytics;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "MainTeleOp")
public class    MainTeleop extends OpMode
{

    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    boolean relic = false;

    @Override
    public void init()
    {
       // gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);
        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        robot.driveTrain.setDrive(DriveTrain.Drive.SPEED);
    }


    public void loop() {
        //keeping arm up
        robot.jewelArm.armUp();
        robot.jewelArm.arm2Up();


        //getting Current Angle of the Four Bar
        robot.bar4.getCurrentAngle();

        //Driver Code

        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float lpwr = (float) Math.pow(((yval - xval)), 3);
        float rpwr = (float) Math.pow((yval + xval), 3);
//
//        float lpwr = gamepad1.left_stick_y;
//        float rpwr = gamepad1.right_stick_y;


//        turtle mode
        if (gamepad1.right_trigger != 0) {
            lpwr /= 3.0f;
            rpwr /= 3.0f;
        }

        else if(gamepad1.left_trigger != 0){
            lpwr /= 9.0f;
            rpwr /= 9.0f;
        }

        if(Math.abs(lpwr)<0.1)
            lpwr = 0;

        if(Math.abs(rpwr)<0.1)
            rpwr = 0;

//      Setting the power for dt
        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

//      Claw and Extender for Relic
//        if (gamepad2.x)
//            robot.relic.clawOpen();
//        else if (gamepad2.y)
//            robot.relic.clawClose();
        if (gamepad2.a)
            robot.relic.pullExtenderUp();
        else if (gamepad2.b)
            robot.relic.putExtenderDown();
        else

        // Arm Control
        if (Math.abs(gamepad2.left_stick_y) > 0.05 && !relic) {

            robot.bar4.shouldStayTrue();
            robot.bar4.setPower(-gamepad2.left_stick_y);

        } else if (Math.abs(gamepad2.left_stick_y) > 0.05 && relic) {

            robot.bar4.shouldStayTrue();
            robot.bar4.setPower((gamepad2.left_stick_y));

        } else {

            robot.bar4.setTargetAngle();
//            robot.bar4.setMoveAngle2(robot.bar4.getTargetAngle());

        }

        if (Math.abs(gamepad2.right_stick_y) > 0.75 && gamepad2.right_bumper) {

            relic = !relic;

        }

        // intake wheel

        if (gamepad2.right_bumper || gamepad2.left_bumper) {
            robot.wheels.setRightWheels(1);
            robot.wheels.setLeftWheelPwr(1);
        } else if(gamepad2.left_trigger!=0 || gamepad2.right_trigger!= 0)
        {
            robot.wheels.setLeftWheelPwr(-gamepad2.left_trigger);
            robot.wheels.setRightWheels(-gamepad2.right_trigger);
        }
        else
        {
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);
        }
        if(gamepad2.right_stick_y > 10){

            robot.wheels.servoIntake();

        }
        else if(gamepad2.right_stick_y < 10){

            robot.wheels.servoOuttake();

        }
        else{

            robot.wheels.setRightServoPwr(0);
            robot.wheels.setLeftServoPwr(0);

        }

            //Zlides
            if (gamepad2.dpad_up) {
                robot.relic.setPower(1);
            } else if (gamepad2.dpad_down) {
                robot.relic.setPower(-1);
            } else {
                robot.relic.setPower(0);
            }

            telemetry.addData("anlge",robot.bar4.getCurrentAngle());
            telemetry.addData("upper limit",robot.bar4.isHit());
            telemetry.addData("lowerLimit",robot.bar4.isLowerHit());
            telemetry.addData("ultra",robot.range.isGlyph());
            telemetry.addData("dist",robot.range.getDist());
            telemetry.addData("servo", robot.jewelArm.getPos2());

            telemetry.update();
        }



    public void stop()
    {

    }


}