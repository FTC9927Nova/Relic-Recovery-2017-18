package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Util.PIDLoop;
import org.firstinspires.ftc.teamcode.Util.Potentiometer;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

import static java.lang.Thread.sleep;

/**
 * Created by Sumanth on 11/8/17.
 */

public class RelicMech implements SubsystemTemplate
{
    private DcMotor relic;
    private Servo claw;
    private CRServo extender;
    RobotConstants constant = new RobotConstants();
    private PIDLoop relicCL = new PIDLoop(0.01,0,0);



    public RelicMech(HardwareMap hardwareMap)
    {
        relic = hardwareMap.dcMotor.get("relic");
        relic.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        claw = hardwareMap.servo.get("claw");
        extender = hardwareMap.crservo.get("extender");

    }


    public void setPower(double pwr)
    {
        relic.setPower(pwr);
    }
    //TODO: FIND BETTER EXTENDER VALUES

    public void setExtender(double pos)
    {
        extender.setPower(pos);
    }

    public void putExtenderDown(){
        extender.setPower(-0.1);
    }

    public void pullExtenderUp(){
        extender.setPower(0);
    }

    //TODO: FIND BETTER CLAW VALUES
    public void clawOpen(){
        claw.setPosition(0.1);
    }
    public void clawClose(){
        claw.setPosition(1);
    }


    @Override
    public String display() {
        return null;
    }
}
