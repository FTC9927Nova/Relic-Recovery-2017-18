package org.firstinspires.ftc.teamcode.Subsystems;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
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


    //TODO: ENTER Kp, Ki, Kd
    private PIDLoop driveCL = new PIDLoop(.01,0,0);
    private PIDLoop turnCL = new PIDLoop();
    private PIDLoop straightCL = new PIDLoop();

    public enum Drive
    {
        ENCODERS,
        SPEED,
        STOP_RESET,
        NOTHING
    }

    enum DriveSpeedController
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

        l1.setDirection(DcMotorSimple.Direction.FORWARD);
        l2.setDirection(DcMotorSimple.Direction.REVERSE);
        r1.setDirection(DcMotorSimple.Direction.REVERSE);
        r2.setDirection(DcMotorSimple.Direction.FORWARD);

        setSpeedController(DriveSpeedController.BRAKE);
    }


    public DriveTrain(HardwareMap hardwareMap, LinearOpMode opMode)
    {
        l1 = hardwareMap.dcMotor.get("l1");
        l2 = hardwareMap.dcMotor.get("l2");
        r1 = hardwareMap.dcMotor.get("r1");
        r2 = hardwareMap.dcMotor.get("r2");


        l1.setDirection(DcMotorSimple.Direction.REVERSE);
        l2.setDirection(DcMotorSimple.Direction.FORWARD);
        r1.setDirection(DcMotorSimple.Direction.FORWARD);
        r2.setDirection(DcMotorSimple.Direction.REVERSE);


        setDrive(Drive.STOP_RESET);

        this.opMode = opMode;
    }

    public void setGyro(Gyro gyro)
    {
        this.gyro = gyro;
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

    private void setSpeedController(DriveSpeedController s)
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

    private void setLeftTarget(int target)
    {
        l1.setTargetPosition(target);
        l2.setTargetPosition(target);
    }

    private void setRightTarget(int target)
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

        return (int)((r1.getCurrentPosition()+ r2.getCurrentPosition())/2.0);
    }

    public boolean checkEncoders()
    {
        return true;
    }

    public void setMoveDist(double dist)
    {
//        setDrive(Drive.ENCODERS);

        setSpeedController(DriveSpeedController.BRAKE);
        if (this.opMode.opModeIsActive()) {

            setDrive(Drive.STOP_RESET);

            setDrive(Drive.ENCODERS);

            leftTarget = (int) (dist * constant.getTICKS_PER_INCH());
            rightTarget = (int) (dist * constant.getTICKS_PER_INCH());

            setLeftTarget(leftTarget);
            setRightTarget(rightTarget);

            while(this.opMode.opModeIsActive() &&
                    (Math.abs(getLeftCurrentPosition()-leftTarget)>constant.getDRIVE_TOLERANCE() || Math.abs(getRightCurrentPosition()-rightTarget)>constant.getDRIVE_TOLERANCE())) {
                setLeftPower(.5);
                setRightPower(.5);
                this.opMode.telemetry.addData("",display());
                Log.i("R1", String.valueOf(r1.getCurrentPosition()));
                Log.i("R2", String.valueOf(r2.getCurrentPosition()));
                Log.i("L1", String.valueOf(l1.getCurrentPosition()));
                Log.i("L2", String.valueOf(l2.getCurrentPosition()));

            }

            setLeftPower(0);
            setRightPower(0);

            setDrive(Drive.SPEED);

        }
    }
    public double getRightPwr(){
        return (r1.getPower() + r2.getPower())/2;
}
    public double getLeftPwr(){
        return (l1.getPower() + l2.getPower())/2;
    }


    //TODO: TURN W/O PID LOOP
    public void rotateDeg(double target)
    {
        this.turnTarget = target;

        setDrive(Drive.NOTHING);
        turnCL.setTarget(target);
        while(this.opMode.opModeIsActive() &&
                (Math.abs((gyro.getYaw()-turnTarget))>constant.getTurnTolerance()))
        {
            this.opMode.telemetry.addData("",display());
            setLeftPower(turnCL.pLoop(gyro.getYaw()));
            setRightPower(-turnCL.pLoop(gyro.getYaw()));
            opMode.telemetry.addData("Deg", gyro.getYaw());
            opMode.telemetry.update();
        }
        setLeftPower(0);
        setRightPower(0);

    }

    public void getLogs(){
        Log.i("L1", String.valueOf(l1.getPower()));
        Log.i("L2", String.valueOf(l2.getCurrentPosition()));
        Log.i("R1", String.valueOf(r1.getPower()));

        Log.i("Error", String.valueOf(driveCL.getError()));
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
