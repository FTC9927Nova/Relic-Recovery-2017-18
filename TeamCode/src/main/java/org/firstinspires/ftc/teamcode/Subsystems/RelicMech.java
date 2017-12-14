package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Util.Potentiometer;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

import static java.lang.Thread.sleep;

/**
 * Created by Sumanth on 11/8/17.
 */

public class RelicMech implements SubsystemTemplate
{
    private DcMotor relic;
    private Servo claw, extender;
    RobotConstants constant = new RobotConstants();


    public RelicMech(HardwareMap hardwareMap)
    {
        relic = hardwareMap.dcMotor.get("relic");
        relic.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        claw = hardwareMap.servo.get("claw");
        extender = hardwareMap.servo.get("extender");

    }


    public void setPower(double pwr)
    {
        relic.setPower(pwr);
    }
    //TODO: FIND BETTER EXTENDER VALUES
    public void putExtenderDown(){
        extender.setPosition(1);
    }

    public void pullExtenderUp(){
        extender.setPosition(0);
    }

    //TODO: FIND BETTER CLAW VALUES
    public void clawOpen(){
        claw.setPosition(0);
    }
    public void clawClose(){
        claw.setPosition(1);
    }

    public void goAllOut(){
        relic.setPower(1);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        relic.setPower(0);

    }

    public void goAllIn(){
        relic.setPower(-1);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        relic.setPower(0);

    }

    public void withBar4(double currentAngle){
        double potAngle = -(currentAngle-constant.getAngleAt90());
        potAngle = potAngle/10;
        potAngle = (1-potAngle)-0.05;
        potAngle = com.qualcomm.robotcore.util.Range.clip(potAngle, 0, 1);
        extender.setPosition(potAngle);
    }
    @Override
    public String display() {
        return null;
    }
}
