package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 2/16/2018.
 */

@TeleOp(name = "ds")
public class DriveStraight extends OpMode
{

    DriveTrain dt;
    double pastTime;
    RobotConstants constants = new RobotConstants();
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void init()
    {
       dt = new DriveTrain(this.hardwareMap);

       timer.reset();
       timer.startTime();
    }



    @Override
    public void loop()
    {
        double currentTime = timer.milliseconds()-pastTime;
        double lrpm = (dt.getLeftCurrentPosition()/currentTime)%constants.ENCODER_TICKS_PER_REVOLUTION;
        double rprm = (dt.getRightCurrentPosition()/currentTime)%constants.ENCODER_TICKS_PER_REVOLUTION;

        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;




        float lpwr = (float) Math.pow(((yval - xval)), 3);
        float rpwr = (float) correction(lrpm,rprm,Math.pow(( yval + xval ), 3));

        dt.setLeftPower(lpwr);
        dt.setRightPower(rpwr);

        pastTime = timer.milliseconds();
        telemetry.addData("lpr",lpwr);
        telemetry.addData("rpwr",rpwr);
        telemetry.addData("lprm",lrpm);
        telemetry.addData("rprm",rprm);

    }

    public double correction(double lrpm, double rprm, double pwr)
    {

        return pwr+((lrpm-rprm)/100000);
    }

    @Override
    public void stop() {

    }
}
