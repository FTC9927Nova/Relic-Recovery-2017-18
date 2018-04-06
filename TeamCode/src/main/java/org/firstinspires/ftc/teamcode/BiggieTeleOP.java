package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "I love it when you call me big pop-pa!")
@Disabled
public class BiggieTeleOP extends OpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void init()
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);

        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        robot.driveTrain.setDrive(DriveTrain.Drive.SPEED);

    }

    public void loop()
    {

        robot.jewelArm.armMid();
        robot.jewelArm.arm2Mid();

        robot.bar4.getCurrentAngle();

        float yval = -gamepad2.left_stick_y;
        float xval = gamepad2.right_stick_x;

        float lpwr = (float) Math.pow(((yval + xval)), 3);
        float rpwr = (float) Math.pow((yval - xval), 3);

        if (gamepad2.right_trigger != 0 || gamepad2.left_trigger != 0) {
            lpwr = lpwr / 3.0f;
            rpwr = rpwr / 3.0f;
        }


        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

        if (gamepad2.y)
            robot.relic.clawOpen();

        else if (gamepad2.x)
            robot.relic.clawClose();

        if (gamepad2.a)
            robot.relic.pullExtenderUp();

        else if (gamepad2.b)
            robot.relic.putExtenderDown();


        if (Math.abs(gamepad2.right_stick_y) > 0.05)
        {
            robot.bar4.shouldStayTrue();
            robot.bar4.setPower(-gamepad2.right_stick_y);
        }
        else
        {
            robot.bar4.setTargetAngle();    //only runs after running shouldStayTrue
            robot.bar4.setMoveAngle(robot.bar4.getTargetAngle());
        }


        if (gamepad2.left_bumper)
        {
            robot.wheels.setLeftWheelPwr(0.7);
        }

        else
        {
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);
        }

        if (gamepad2.right_bumper)
        {
            robot.wheels.setRightWheels(0.7);
        }

        else if (gamepad2.right_trigger != 0)
        {
            robot.wheels.setRightWheels(-1);
            robot.wheels.setLeftWheelPwr(-1);
        }

        else
        {
            robot.wheels.setRightWheels(0);
            robot.wheels.setLeftWheelPwr(0);
        }

        if (gamepad2.dpad_up)
        {
            robot.relic.setPower(1);
        }

        else if (gamepad2.dpad_down)
        {
            robot.relic.setPower(-1);
        }

        else
        {
            robot.relic.setPower(0);
        }

        if (-gamepad2.right_stick_y > 10)
        {
            robot.jewelArm.armMid();
        }

        else if (-gamepad2.right_stick_y < -10)
        {
            robot.jewelArm.armUp();
        }

    }

    public void stop() {

    }

}