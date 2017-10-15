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

    private DcMotor l1 = null;
    private DcMotor l2 = null;
    private DcMotor r1 = null;
    private DcMotor r2 = null;
    private Gyro gyro = null;

    private int leftTarget;
    private int rightTarget;

    private double turnTarget;


    //TODO: ENTER Kp, Ki, Kd
    private PIDLoop driveCL = new PIDLoop(.008,0,0);
    private PIDLoop turnCL = new PIDLoop();

    enum Drive
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
        gyro.initGyro(hardwareMap);
        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);
    }


    public DriveTrain(HardwareMap hardwareMap, LinearOpMode opMode)
    {
        l1 = hardwareMap.dcMotor.get("l1");
        l2 = hardwareMap.dcMotor.get("l2");
        r1 = hardwareMap.dcMotor.get("r1");
        r2 = hardwareMap.dcMotor.get("r2");
        gyro.initGyro(hardwareMap);


        r1.setDirection(DcMotorSimple.Direction.REVERSE);


        this.opMode = opMode;
    }

    private void setDrive(Drive d)
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



    private int getLeftCurrentPosition()
    {
        return (int)((l1.getCurrentPosition() + l2.getCurrentPosition()
        )/2 );
    }

    private int getRightCurrentPosition()
    {
//        + r2.getCurrentPosition())
        return (int)((r1.getCurrentPosition()));
    }

    public void setMoveDist(double dist) {

        setSpeedController(DriveSpeedController.BRAKE);
        if (this.opMode.opModeIsActive()) {

            leftTarget = getLeftCurrentPosition()
                    + (int) (dist * constant.getTICKS_PER_INCH());
            rightTarget = getRightCurrentPosition()
                    + (int) (dist * constant.getTICKS_PER_INCH());

            setDrive(Drive.ENCODERS);

            setLeftTarget(leftTarget);
            setRightTarget(rightTarget);

            driveCL.setTarget(leftTarget);

            while(this.opMode.opModeIsActive() &&
                    (Math.abs((getLeftCurrentPosition()-leftTarget))>constant.getDRIVE_TOLERANCE() || Math.abs((getRightCurrentPosition()-rightTarget))>constant.getDRIVE_TOLERANCE()))
            {
                this.opMode.telemetry.addData("",display());

                setLeftPower(driveCL.pLoop(getLeftCurrentPosition()));
                setRightPower(driveCL.pLoop(getLeftCurrentPosition()));
//
//                setLeftPower(0.3);
//                setRightPower(0.3);
                getLogs();
            }

            setLeftPower(0);
            setRightPower(0);
            setDrive(Drive.SPEED);

        }
    }

    public void rotateDeg(double target)
    {
        this.turnTarget = target;
        if (this.opMode.opModeIsActive())
        {
            setDrive(Drive.SPEED);
            setSpeedController(DriveSpeedController.BRAKE);
            turnCL.setTarget(target);
            while(this.opMode.opModeIsActive() &&
                    (Math.abs((gyro.getHeading()-turnTarget))<constant.getTurnTolerance()))
            {
                this.opMode.telemetry.addData("",display());
                setLeftPower(turnCL.pLoop(gyro.getHeading()));
                setRightPower(-turnCL.pLoop(gyro.getHeading()));
            }
            setLeftPower(0);
            setRightPower(0);
        }
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
                +"\n  Gyro: Heading: " + gyro.getHeading() + " yaw: " + gyro.getYaw() + "  roll: " + gyro.getRoll();
    }
}