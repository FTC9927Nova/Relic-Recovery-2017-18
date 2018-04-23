package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.StringDataItem;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.PIDLoop;


/**
 * Created by therat0981 on 9/16/17.
 */

public class DriveTrain implements SubsystemTemplate
{
    //TODO: ADD SECOND MOTOR;
    private LinearOpMode opMode;

    private DcMotor l1;
    private DcMotor l2;
    private DcMotor r1;
    private DcMotor r2;
    public Gyro gyro;

    private int leftTarget;
    private int rightTarget;

    private double turnTarget;

    private double heading;

    private double pastAngle;
    private double currentAngle;


    //TODO: ENTER Kp, Ki, Kd
    private PIDLoop driveCL = new PIDLoop(0.01,0,0);
    //driveCL: P:0.00033, 0, 0
    //turnCl: P:0.0075, i:0.0025
    private PIDLoop turnCL = new PIDLoop(0.0035,0,0);
    int l1motorIndex;
    int l2motorIndex;
    int r1motorIndex;
    int r2motorIndex;

    DcMotorControllerEx l1motorControllerEx;
    DcMotorControllerEx l2motorControllerEx;
    DcMotorControllerEx r1motorControllerEx;
    DcMotorControllerEx r2motorControllerEx;


    public enum Side
    {
        LEFT_SIDE,
        RIGHT_SIDE
    }

    public enum Drive
    {
        ENCODERS,
        SPEED,
        STOP_RESET,
        NOTHING
    }

    public enum DriveSpeedController
    {
        BRAKE,
        COAST,
        UNKNOWN
    }



    public DriveTrain(HardwareMap hardwareMap)
    {
        l1 = hardwareMap.dcMotor.get("l1");
        l2 = hardwareMap.dcMotor.get("l2");
        r1 = hardwareMap.dcMotor.get("r1");
        r2 = hardwareMap.dcMotor.get("r2");

        setDrive(Drive.SPEED);

        l1.setDirection(DcMotorSimple.Direction.REVERSE);
        l2.setDirection(DcMotorSimple.Direction.REVERSE);
        r1.setDirection(DcMotorSimple.Direction.FORWARD);
        r2.setDirection(DcMotorSimple.Direction.FORWARD);

        setSpeedController(DriveSpeedController.BRAKE);

        l1motorControllerEx = (DcMotorControllerEx)l1.getController();
        l2motorControllerEx = (DcMotorControllerEx)l2.getController();
        r1motorControllerEx = (DcMotorControllerEx)r1.getController();
        r2motorControllerEx = (DcMotorControllerEx)r2.getController();

        l1motorIndex = ((DcMotorEx)l1).getPortNumber();
        l2motorIndex = ((DcMotorEx)l2).getPortNumber();
        r1motorIndex = ((DcMotorEx)r1).getPortNumber();
        r2motorIndex = ((DcMotorEx)r2).getPortNumber();



    }

    public DriveTrain(HardwareMap hardwareMap, LinearOpMode opMode)
    {
        l1 = hardwareMap.dcMotor.get("l1");
        l2 = hardwareMap.dcMotor.get("l2");
        r1 = hardwareMap.dcMotor.get("r1");
        r2 = hardwareMap.dcMotor.get("r2");

        setDrive(Drive.SPEED);

        l1.setDirection(DcMotorSimple.Direction.FORWARD);
        l2.setDirection(DcMotorSimple.Direction.FORWARD);
        r1.setDirection(DcMotorSimple.Direction.REVERSE);
        r2.setDirection(DcMotorSimple.Direction.REVERSE);

        setSpeedController(DriveSpeedController.BRAKE);

        l1motorControllerEx = (DcMotorControllerEx)l1.getController();
        l2motorControllerEx = (DcMotorControllerEx)l2.getController();
        r1motorControllerEx = (DcMotorControllerEx)r1.getController();
        r2motorControllerEx = (DcMotorControllerEx)r2.getController();

        setDrive(Drive.STOP_RESET);
        this.opMode = opMode;

    }

    public void setGyro(Gyro gyro)
    {
        this.gyro = gyro;
        heading = getAngle();
    }

    public void setDrive(Drive d)
    {
        DcMotor.RunMode runMode = null;

        switch (d)
        {
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

        l1.setMode(runMode);
        l2.setMode(runMode);
        r1.setMode(runMode);
        r2.setMode(runMode);
    }

    public void setSpeedController(DriveSpeedController s)
    {
        DcMotor.ZeroPowerBehavior zeroPowerBehavior = null;
        switch(s)
        {
            case BRAKE:
                zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE;
                break;
            case COAST:
                zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT;
                break;
            case UNKNOWN:
                zeroPowerBehavior = DcMotor.ZeroPowerBehavior.UNKNOWN;
                break;
        }

        l1.setZeroPowerBehavior(zeroPowerBehavior);
        l2.setZeroPowerBehavior(zeroPowerBehavior);
        r1.setZeroPowerBehavior(zeroPowerBehavior);
        r2.setZeroPowerBehavior(zeroPowerBehavior);

    }



    public void setLeftPower(double power)
    {
        power = Range.clip(power, -1,1);
        l1.setPower(power);
        l2.setPower(power);
    }

    public void setRightPower(double power)
    {
        power = Range.clip(power, -1,1);
        r1.setPower(power);
        r2.setPower(power);
    }

    public void setLeftTarget(int target)
    {
        l1.setTargetPosition(target);
        l2.setTargetPosition(target);
    }

    public void setRightTarget(int target)
    {
        r1.setTargetPosition(target);
        r2.setTargetPosition(target);
    }

    public int getRightTarget(){

        return r1.getTargetPosition();

    }

    public int getLeftTarget(){

        return l1.getTargetPosition();

    }

    public int getLeftCurrentPosition()
    {
        return l1.getCurrentPosition();

    }

    public int getRightCurrentPosition()
    {

        return r1.getCurrentPosition();
    }

    public boolean checkEncoders()
    {
        return true;
    }

    public boolean setMoveDist(double dist)
    {

        setSpeedController(DriveSpeedController.BRAKE);

        leftTarget = (int) (dist * constant.getTICKS_PER_INCH());
        rightTarget = (int) (dist * constant.getTICKS_PER_INCH());

        setDrive(Drive.ENCODERS);

        setLeftTarget(leftTarget);
        setRightTarget(rightTarget);

        driveCL.setTarget(leftTarget);

        double pwr = driveCL.pLoop(getLeftCurrentPosition());
        setLeftPower(pwr);
        setRightPower(pwr);

        if((Math.abs((getLeftCurrentPosition()-leftTarget))<constant.getDRIVE_TOLERANCE()))
        {
            setLeftPower(0);
            setRightPower(0);
            setDrive(Drive.STOP_RESET);
            return true;
        }
        return false;
    }

    public boolean setMoveDistEnc(int target){

        setSpeedController(DriveSpeedController.BRAKE);
        setDrive(Drive.ENCODERS);

        leftTarget = target;

        setLeftTarget(leftTarget);
        setRightTarget(leftTarget);

        driveCL.setTarget(leftTarget);

        setLeftPower(driveCL.pLoop(getLeftCurrentPosition()));
        setRightPower(driveCL.pLoop(getLeftCurrentPosition()));

        if(Math.abs((getLeftCurrentPosition()-target))<constant.getDRIVE_TOLERANCE())
        {
            setLeftPower(0);
            setRightPower(0);
            setDrive(Drive.STOP_RESET);
            return true;
        }

        return false;


    }
    public double getRightPwr(){
        return (r1.getPower() + r2.getPower())/2;
}
    public double getLeftPwr(){
        return (l1.getPower() + l2.getPower())/2;
    }


    public boolean rotateDeg(double target)
    {
        turnTarget = heading + target;

        if(turnTarget > 180)
            turnTarget -= 360;
        else if (turnTarget < -180)
            turnTarget += 360;

        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);

        turnCL.setTarget(turnTarget);
        double lpwr;
        boolean isPowerTooLow = false;

        lpwr = turnCL.pLoop(gyro.getYaw());
        if(Math.abs(lpwr)>0.25)
            lpwr = 0.25 * Math.signum(target);
        if(Math.abs(lpwr)<0.07)
            isPowerTooLow = true;
        setLeftPower(lpwr);
        setRightPower(-lpwr);

        if((Math.abs((gyro.getYaw()-turnTarget))<constant.getTurnTolerance())
                && isPowerTooLow)
        {
            setLeftPower(0);
            setRightPower(0);
            setDrive(Drive.ENCODERS);
            heading = gyro.getYaw();
            return true;
        }

        return false;
    }

    public void driveStraight()
    {
        pastAngle = currentAngle;
        currentAngle = Math.atan(Math.tan(Math.toRadians(getAngle())));
        double diff = currentAngle-pastAngle;
        double correction = diff/17.0;
        setLeftPower(0.3+correction);
        setRightPower(0.3-correction);

    }

    public double getAngle()
    {
        return gyro.getYaw();
    }

    public boolean singleSideRotateDeg(Side side, double target) {
        turnTarget = gyro.getYaw() + target;

        if (turnTarget > 180)
            turnTarget -= 360;

        else if (turnTarget < -180)
            turnTarget += 360;

        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);

        turnCL.setTarget(turnTarget);


        if (side == Side.LEFT_SIDE) {
            double pwr = turnCL.pLoop(gyro.getYaw());
            if(Math.abs(pwr)<0.05)
                pwr = 0.05 * Math.signum(target);
            setLeftPower(pwr);
            setRightPower(0);
        }
        if (side == Side.RIGHT_SIDE) {
            double pwr = turnCL.pLoop(gyro.getYaw());
            if(Math.abs(pwr)<0.05)
                pwr = 0.05 * Math.signum(target);
            setLeftPower(0);
            setRightPower(pwr);
        }


        if ((Math.abs((-gyro.getYaw() - turnTarget)) < (constant.getTurnTolerance()))) {
            setRightPower(0);
            setLeftPower(0);
            return true;
        }


        this.opMode.telemetry.addData("Side", String.valueOf(side));
        this.opMode.telemetry.update();

        return false;

    }

    public void stop()
    {
        setLeftPower(0);
        setRightPower(0);
    }


    public void getLogs(){
        Log.i("L1", String.valueOf(l1.getCurrentPosition()));
        Log.i("L2", String.valueOf(l2.getCurrentPosition()));
        Log.i("R1", String.valueOf(r1.getCurrentPosition()));
        Log.i("R2", String.valueOf(r2.getCurrentPosition()));



    }
    @Override
    public String display() {
        return "Drive Train   LeftTarget: " + this.leftTarget + "   RightTarget: " + this.rightTarget +  "   TurnTarget: " + turnTarget
                +"\n PID DRIVE ( " + driveCL.getKp() + "," + driveCL.getKi() + "," + driveCL.getKd() + ")"
                +"    PID TURN ( " + turnCL.getKp() + "," + turnCL.getKi() + "," + turnCL.getKd() + ")"
                +"\n  L1: pow: " + l1.getPower() + " enc: " + l1.getCurrentPosition() + " average: " + getLeftCurrentPosition()
                +"\n  L2: pow: " + l2.getPower() + " enc: " + l2.getCurrentPosition() + " average: " + getLeftCurrentPosition()
                +"\n  R1: pow: " + r1.getPower() + " enc: " + r1.getCurrentPosition() + " average: " + getRightCurrentPosition()
                +"\n  R2: pow: " + r2.getPower() + " enc: " + r2.getCurrentPosition() + " average: " + getRightCurrentPosition()
                +"\n  Gyro: Heading: " + gyro.getPitch() + " yaw: " + gyro.getYaw() + "  roll: " + gyro.getRoll();
    }
}
