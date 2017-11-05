package org.firstinspires.ftc.teamcode.Subsystems;

import android.hardware.Sensor;
import android.util.Printer;
import android.util.Range;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Util.PIDLoop;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 11/5/2017.
 */

public class FourBar implements SubsystemTemplate {


    private DcMotor fourBar;
    private int targetPos;
    private int desiredPosEnc;
    private Boolean isReachedPos;
    private Sensor LimitSwitch_Zero;
    //TODO:CHECK IF 2 LIMIT SWITCHES ARE NEEDED
    // Maybe:
    //private Sensor LimitSwitch_Max;
    private RobotConstants constants = new RobotConstants();
    private PIDLoop pidLoop = new PIDLoop(0.05, 0, 0);
    public FourBar(HardwareMap hardwareMap){
        fourBar = hardwareMap.dcMotor.get("fourBar");
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setPos(int desiredPos) {
        if (Math.abs(desiredPosEnc - fourBar.getCurrentPosition()) > constants.getFOUR_BAR_TOLERANCE()) {
            isReachedPos = false;
            desiredPosEnc = constants.getFOURBAR_TICKS_PER_LEVEL() - fourBar.getCurrentPosition();
            while (Math.abs(desiredPosEnc - fourBar.getCurrentPosition()) > constants.getFOUR_BAR_TOLERANCE()) {
                fourBar.setPower(com.qualcomm.robotcore.util.Range.clip(pidLoop.pLoop(desiredPos), -1, 1));
            }
            fourBar.setPower(0);
            fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        isReachedPos = true;


    }

    public void stayInPlace(){
        targetPos = fourBar.getCurrentPosition();
        if ((targetPos-fourBar.getCurrentPosition()) > constants.getFOUR_BAR_TOLERANCE()){
            fourBar.setPower(com.qualcomm.robotcore.util.Range.clip(pidLoop.pLoop(targetPos-fourBar.getCurrentPosition()) , -1, 1));
        } else if ((targetPos-fourBar.getCurrentPosition()) < -constants.getFOUR_BAR_TOLERANCE()){
            fourBar.setPower(com.qualcomm.robotcore.util.Range.clip(pidLoop.pLoop(targetPos-fourBar.getCurrentPosition()) , -1, 1));
        } else{
            fourBar.setPower(0);
            fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        //TODO:Check if fourBar going up is Negative vals or Positive Vals

    }

    public void resetEnc(){

        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public int getEnc(){

        return fourBar.getCurrentPosition();

    }

    public void setPower(double power){

        fourBar.setPower(power);

    }


    @Override
    public String display() {
        return null;
    }
}
