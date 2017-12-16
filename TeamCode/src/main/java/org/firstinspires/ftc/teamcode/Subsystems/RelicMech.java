package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    private Servo claw, extender;
    RobotConstants constant = new RobotConstants();
    private PIDLoop relicCL = new PIDLoop(0.01,0,0);



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
        extender.setPosition(0.8);
    }

    public void pullExtenderUp(){
        extender.setPosition(0);
    }

    //TODO: FIND BETTER CLAW VALUES
    public void clawOpen(){
        claw.setPosition(0.1);
    }
    public void clawClose(){
        claw.setPosition(1);
    }

//    public void setMoveDist(double dist){
//
//        relic.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        relic.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        relic.setTargetPosition((int) (dist * constant.getRELIC_TICKS_PER_INCH()));
//
//        relic.setPower(relicCL.pLoop(relic.getCurrentPosition()));
//
//    }

    @Override
    public String display() {
        return null;
    }
}
