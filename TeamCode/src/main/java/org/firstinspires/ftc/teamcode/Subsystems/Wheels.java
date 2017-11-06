package org.firstinspires.ftc.teamcode.Subsystems;
import org.firstinspires.ftc.teamcode.Util.*;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemTemplate;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Sumanth on 11/2/17.
 */

public class Wheels implements SubsystemTemplate{

    private DcMotor leftWheels, rightWheels;
    private int EncDesiredPos;

    private LimitDevices limitDevices;
    public Wheels(HardwareMap hardwareMap)
    {

        leftWheels = hardwareMap.dcMotor.get("leftIntake");
        rightWheels = hardwareMap.dcMotor.get("rightIntake");

        setMode(Mode.STOP_RESET);
        leftWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public enum Mode
    {
        ENCODERS,
        SPEED,
        STOP_RESET,
        NOTHING
    }

    public void setMode(Mode d) {
        DcMotor.RunMode runMode = null;

        switch (d) {
            case ENCODERS:
                runMode = DcMotor.RunMode.RUN_TO_POSITION;
                break;
            case SPEED:
                runMode = DcMotor.RunMode.RUN_USING_ENCODER;
                break;
            case STOP_RESET:
                runMode = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
                break;
            case NOTHING:
                runMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
                break;

        }

        leftWheels.setMode(runMode);
        rightWheels.setMode(runMode);

    }

    public void moveRotationsLeft(int numberOfRotations){

        setMode(Mode.STOP_RESET);
        EncDesiredPos = numberOfRotations * constant.getWHEELS_TICKS_PER_ROTATION();
        while (Math.abs(EncDesiredPos - leftWheels.getCurrentPosition()) > constant.getWHEELS_TOLERANCE() && !limitDevices.isHit()){

            leftWheels.setPower(1);
        }
        leftWheels.setPower(0);
        leftWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moveRotationsRight(int numberOfRotations){

        setMode(Mode.STOP_RESET);
        EncDesiredPos = numberOfRotations * constant.getWHEELS_TICKS_PER_ROTATION();
        while (Math.abs(EncDesiredPos - rightWheels.getCurrentPosition()) > constant.getWHEELS_TOLERANCE() && !limitDevices.isHit()){
            rightWheels.setPower(1);
        }
        rightWheels.setPower(0);
        rightWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// while (leftWheels.getCurrentPosition() - constant.getWHEELS_TOLERANCE())
    }
    public void intake(){

        leftWheels.setPower(1);
        rightWheels.setPower(1);

    }
    public void outtake(double speed){

        leftWheels.setPower(speed);
        rightWheels.setPower(speed);
    }


    public void leftIntakeBlock(double speed){
        leftWheels.setPower(speed);

    }

    public void rightIntakeBlock(double speed){
        rightWheels.setPower(speed);
    }


    @Override
    public String display() {

        return null;

    }
}
