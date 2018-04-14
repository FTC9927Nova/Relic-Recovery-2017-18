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


    //TODO: ENTER Kp, Ki, Kd
    private PIDLoop driveCL = new PIDLoop(0.0023,0,0);
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

        this.opMode = opMode;

        l1motorIndex = ((DcMotorEx)l1).getPortNumber();
        l2motorIndex = ((DcMotorEx)l2).getPortNumber();
        r1motorIndex = ((DcMotorEx)r1).getPortNumber();
        r2motorIndex = ((DcMotorEx)r2).getPortNumber();

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


    public void setLeftPowerNew(){
        PIDCoefficients l1PIDOrig = l1motorControllerEx.getPIDCoefficients(l1motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);
        PIDCoefficients l2pidOrig = l2motorControllerEx.getPIDCoefficients(l2motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

        PIDCoefficients l1PIDNew = new PIDCoefficients(constant.getLP(), constant.getLI(), constant.getLD());
        PIDCoefficients l2PIDNew = new PIDCoefficients(constant.getLP(), constant.getLI(), constant.getLD());

    }
    public void setRightPowerNew(){

        PIDCoefficients r1PIDOrig = r1motorControllerEx.getPIDCoefficients(r1motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);
        PIDCoefficients r2pidOrig = r2motorControllerEx.getPIDCoefficients(r2motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

        PIDCoefficients r1PIDNew = new PIDCoefficients(constant.getR1P(), constant.getR1P(), constant.getR1D());
        PIDCoefficients r2PIDNew = new PIDCoefficients(constant.getR2P(), constant.getR2P(), constant.getR2D());

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

    public void setMoveDist(double dist)
    {

        setSpeedController(DriveSpeedController.BRAKE);
        if (this.opMode.opModeIsActive()) {

            setDrive(Drive.STOP_RESET);

            leftTarget = (int) (dist * constant.getTICKS_PER_INCH());
            rightTarget = (int) (dist * constant.getTICKS_PER_INCH());

            setDrive(Drive.ENCODERS);

            setLeftTarget(leftTarget);
            setRightTarget(rightTarget);

            driveCL.setTarget(leftTarget);
            double initialAngle = gyro.getYaw();

            turnCL.setTarget(initialAngle);
            while(this.opMode.opModeIsActive() &&
                    (Math.abs((getLeftCurrentPosition()-leftTarget))>constant.getDRIVE_TOLERANCE()))
            {
                double pwr = driveCL.pLoop(getLeftCurrentPosition());
                if (pwr > 0.5){
                    pwr = 0.5;
                }

                setLeftPower(pwr);
                setRightPower(pwr);
            }
//            singleSideRotateDeg(Side.RIGHT_SIDE,(gyro.getYaw()-initialAngle),Math.signum(dist));


            setLeftPower(0);
            setRightPower(0);
            setDrive(Drive.SPEED);

        }
    }

    public void setMoveDistEnc(int target){


        setDrive(Drive.STOP_RESET);

        leftTarget = target;


        setDrive(Drive.ENCODERS);

        setLeftTarget(leftTarget);
        setRightTarget(leftTarget);

        driveCL.setTarget(leftTarget);

        while(this.opMode.opModeIsActive() &&
                (Math.abs((getLeftCurrentPosition()-target))>constant.getDRIVE_TOLERANCE() && Math.abs((getRightCurrentPosition()-rightTarget))>constant.getDRIVE_TOLERANCE()))
        {

            setLeftPower(driveCL.pLoop(getLeftCurrentPosition()));
            setRightPower(driveCL.pLoop(getLeftCurrentPosition()));

        }

        setLeftPower(0);
        setRightPower(0);
        setDrive(Drive.SPEED);

    }


    public double getRightPwr(){
        return (r1.getPower() + r2.getPower())/2;
}
    public double getLeftPwr(){
        return (l1.getPower() + l2.getPower())/2;
    }


    public void rotateDeg(double target)
    {
        turnTarget = gyro.getYaw() + target;

        if(turnTarget > 180)
            turnTarget -= 360;

        else if (turnTarget < -180)
            turnTarget += 360;

        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);

        turnCL.setTarget(turnTarget);
        double lpwr;

        while(this.opMode.opModeIsActive() &&
                (Math.abs((gyro.getYaw()-turnTarget))>constant.getTurnTolerance()))
        {
            lpwr = turnCL.pLoop(gyro.getYaw());
            if(Math.abs(lpwr)>0.3)
                lpwr = 0.3 * Math.signum(target);
            this.opMode.telemetry.addData("",display());
            this.opMode.telemetry.addData("",lpwr);
            setLeftPower(lpwr);
            setRightPower(-lpwr);
            this.opMode.telemetry.update();

        }
        setLeftPower(0);
        setRightPower(0);

    }

    public void singleSideRotateDeg(Side side, double target) {
        turnTarget = -gyro.getYaw() + target;

        if (turnTarget > 180)
            turnTarget -= 360;

        else if (turnTarget < -180)
            turnTarget += 360;

        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);

        turnCL.setTarget(turnTarget);

        while (this.opMode.opModeIsActive() &&
                (Math.abs((-gyro.getYaw() - turnTarget)) > constant.getTurnTolerance())) {

            if (side == Side.LEFT_SIDE) {
                setLeftPower(-0.2);
                setRightPower(0);
            }
            if (side == Side.RIGHT_SIDE) {
                setLeftPower(0);
                setRightPower(0.2);
            }
            this.opMode.telemetry.addData("Side", String.valueOf(side));
        }
        setLeftPower(0);
        setRightPower(0);
    }

    public void singleSideRotateDegCorrect(Side side, double target, double power) {
        turnTarget = -gyro.getYaw() + target;

        if (turnTarget > 180)
            turnTarget -= 360;

        else if (turnTarget < -180)
            turnTarget += 360;

        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);

        turnCL.setTarget(turnTarget);

        while (this.opMode.opModeIsActive() &&
                (Math.abs((-gyro.getYaw() - turnTarget)) > constant.getTurnTolerance())) {

            if (side == Side.LEFT_SIDE) {
                setLeftPower(power);
                setRightPower(0);
            }
            if (side == Side.RIGHT_SIDE) {
                setLeftPower(0);
                setRightPower(power);
            }
            this.opMode.telemetry.addData("Side", String.valueOf(side));
        }
        setLeftPower(0);
        setRightPower(0);
    }

    public void singleSideTurnFar(Side side, double target) {
        turnTarget = -gyro.getYaw() + target;

        if (turnTarget > 180)
            turnTarget -= 360;

        else if (turnTarget < -180)
            turnTarget += 360;

        setDrive(Drive.SPEED);
        setSpeedController(DriveSpeedController.BRAKE);

        turnCL.setTarget(turnTarget);
        double negTarget = target/(Math.abs(target));

        while (this.opMode.opModeIsActive() &&
                (Math.abs((-gyro.getYaw() - turnTarget)) > constant.getTurnTolerance())) {

            if (side == Side.LEFT_SIDE) {
                setLeftPower(0.5);
                setRightPower(0);
            }
            if (side == Side.RIGHT_SIDE) {
                setLeftPower(0);
                setRightPower(0.5);
            }
            this.opMode.telemetry.addData("Side", String.valueOf(side));
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
