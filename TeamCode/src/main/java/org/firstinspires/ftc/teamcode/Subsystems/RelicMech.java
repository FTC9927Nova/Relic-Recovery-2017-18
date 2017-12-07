package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

/**
 * Created by Sumanth on 11/8/17.
 */

public class RelicMech implements SubsystemTemplate
{
    private DcMotor relic;
    private Servo claw, extender;

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

    public void halfExtender() { extender.setPosition(.5); }

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

    @Override
    public String display() {
        return "Servo: " + extender.getPosition();
    }
}
