package org.firstinspires.ftc.teamcode.Subsystems;
import org.firstinspires.ftc.teamcode.Util.*;
import org.firstinspires.ftc.teamcode.Subsystems.SubsystemTemplate;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Sumanth on 11/2/17.
 */

public class Wheels implements SubsystemTemplate{

    private DcMotor leftWheels, rightWheels;
    private DigitalChannel bumper;
    public Wheels(HardwareMap hardwareMap)
    {

        leftWheels = hardwareMap.dcMotor.get("leftIntake");
        rightWheels = hardwareMap.dcMotor.get("rightIntake");
//        leftWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        setMode(Mode.STOP_RESET);
//        leftWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        leftWheels.setDirection(DcMotorSimple.Direction.FORWARD);
        rightWheels.setDirection(DcMotorSimple.Direction.REVERSE);

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

    public void intakeLeft(){

        leftWheels.setPower(1);

    }
    public void intakeRight() {

        rightWheels.setPower(1);
    }

    public void outtakeLeft(){

        leftWheels.setPower(-1);

    }

    public void outtakeRight(){

        rightWheels.setPower(-1);
    }


    public void stopLeft()
    {
        leftWheels.setPower(0);
    }

    public void stopRight(){

        rightWheels.setPower(0);

    }

    public void setLeftWheelPwr(double a)
    {
            leftWheels.setPower(a);
    }
    public void setRightWheels(double b)
    {
            rightWheels.setPower(b);
    }



    @Override
    public String display() {

        return null;

    }
}
