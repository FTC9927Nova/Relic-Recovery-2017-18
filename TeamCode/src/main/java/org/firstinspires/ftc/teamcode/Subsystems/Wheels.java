package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Created by Sumanth on 11/2/17.
 */

public class Wheels implements SubsystemTemplate{

    private DcMotor leftWheels, rightWheels;
    private CRServo lservoIntkae, rservoIntkae;
    private Servo latchyCatchy;
    private AnalogInput distSensor;
    private double MinGlyphDist = 1.5;
    private double MaxGlyphDist = 3;
    private double avgEncTicks;
    private double prevChagne = 0;
    private boolean checkGlyphS = false;
    boolean hasReached = false;
    public Wheels(HardwareMap hardwareMap)
    {

        leftWheels = hardwareMap.dcMotor.get("leftIntake");
        rightWheels = hardwareMap.dcMotor.get("rightIntake");

        lservoIntkae = hardwareMap.crservo.get("lsintake");
        rservoIntkae = hardwareMap.crservo.get("rsintake");
        distSensor = hardwareMap.analogInput.get("distyListy");

        latchyCatchy = hardwareMap.servo.get("latch");


        leftWheels.setDirection(DcMotorSimple.Direction.FORWARD);
        rightWheels.setDirection(DcMotorSimple.Direction.REVERSE);
        lservoIntkae.setDirection(DcMotorSimple.Direction.FORWARD);
        rservoIntkae.setDirection(DcMotorSimple.Direction.REVERSE);
        latchyCatchy.setDirection(Servo.Direction.FORWARD);

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

    public void setServopower(double pwr)
    {
        lservoIntkae.setPower(pwr);
        rservoIntkae.setPower(pwr);
    }
    public void servoIntake(){

        setLeftServoPwr(-1);
        setRightServoPwr(-1);

    }

    public boolean secondGlyphIntake()
    {
        DcMotorEx lw = (DcMotorEx)leftWheels;
        DcMotorEx rw = (DcMotorEx)rightWheels;

        if(avgEncTicks>8)
            hasReached = true;

        avgEncTicks = ((lw.getVelocity(AngleUnit.RADIANS) + rw.getVelocity(AngleUnit.RADIANS))/2.0);
        if(avgEncTicks<8 &&hasReached)
        {
            return true;
        }

        return false;


    }


    public void servoOuttake(){

        setLeftServoPwr(1);
        setRightServoPwr(1);

    }

    public void intakeLeft(){
        leftWheels.setPower(-1);

    }
    public void intakeRight() {

        rightWheels.setPower(-1);
    }

    public void outtakeLeft(){

        leftWheels.setPower(1);
    }


    public void outtakeRight(){

        rightWheels.setPower(1);
    }


    public void stopLeft()
    {
        leftWheels.setPower(0);
    }

    public void stopRight(){
        rightWheels.setPower(0);

    }

    public boolean hasFirstGlyph()
    {
        return (glyphDist()<MinGlyphDist);
    }

    public double glyphDist()
    {
        return 1.8694*(Math.pow(distSensor.getVoltage(),-1.086));
    }

    public void leftSideOuttake()
    {
        outtakeLeft();
        lservoIntkae.setPower(0.5);
    }
    public void rightSideOuttake()
    {
        outtakeRight();
        rservoIntkae.setPower(0.5);
    }


    public void leftClawIntake()
    {
        intakeLeft();
        if(glyphDist()> MinGlyphDist)
        {
            lservoIntkae.setPower(-0.5);
        }
        else
        {
            lservoIntkae.setPower(0);
        }
    }

    public void rightClawIntake()
    {
        intakeRight();
        if(glyphDist()> MinGlyphDist)
        {
            rservoIntkae.setPower(-0.5);
        }
        else
        {
            rservoIntkae.setPower(0);
        }
    }

    public void fullOuttake()
    {
        rservoIntkae.setPower(Range.clip(0.35-(glyphDist()/18),0.1,0.35));
        lservoIntkae.setPower(Range.clip(0.35-(glyphDist()/18),0.1,0.35));
        outtakeLeft();
        outtakeRight();
    }

    public boolean halfOuttake()
    {
        if(glyphDist()>MaxGlyphDist) {
            lservoIntkae.setPower(0);
            rservoIntkae.setPower(0);
            setLeftWheelPwr(0);
            setRightWheels(0);
            return true;
        }
        else
        {
            lservoIntkae.setPower(0.5);
            rservoIntkae.setPower(0.5);
            leftWheels.setPower(0.7);
            rightWheels.setPower(0.7);
            return false;
        }
    }

    public void unLatch(){

        latchyCatchy.setPosition(0.75);

    }

    public void latch(){

        latchyCatchy.setPosition(0.15);

    }

    public void stop()
    {
        setRightWheels(0);
        setLeftWheelPwr(0);
        setLeftServoPwr(0);
        setRightServoPwr(0);
    }






    public void setLeftWheelPwr(double a)
    {
            leftWheels.setPower(a);
    }
    public void setRightWheels(double b)
    {
            rightWheels.setPower(b);
    }
    public void setLeftServoPwr(double c) { lservoIntkae.setPower(c); }
    public void setRightServoPwr(double d) { rservoIntkae.setPower(d); }


    public String getWheelVelocity()
    {
        DcMotorEx lw = (DcMotorEx)leftWheels;
        DcMotorEx rw = (DcMotorEx)rightWheels;
        return lw.getVelocity(AngleUnit.RADIANS) + "    "  +rw.getVelocity(AngleUnit.RADIANS);
    }


    @Override
    public String display() {

        return "left pwr " + leftWheels.getPower()
                +"\nright pwr " + rightWheels.getPower();

    }
}
