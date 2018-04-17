package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Sumanth on 11/2/17.
 */

public class Wheels implements SubsystemTemplate{

    private DcMotor leftWheels, rightWheels;
    private CRServo lservoIntkae, rservoIntkae;
    private Servo latchyCatchy;
    private AnalogInput distSensor;
    private double MinGlyphDist = 1.5;
    private double MaxGlyphDist = 4;
    private double avgEncTicks;
    private double prevChagne;
    private boolean checkGlyphS = false;
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

    public void secondGlyphIntake()
    {
        checkGlyphS = false;
        intakeLeft();
        intakeRight();
        avgEncTicks = ((leftWheels.getCurrentPosition() + rightWheels.getCurrentPosition())/2.0)/10;
       if(avgEncTicks<prevChagne)
       {
           stopLeft();
           stopRight();
           checkGlyphS = true;

       }
       prevChagne = avgEncTicks;

    }

    public boolean checkSecondGlyph()
    {
        return checkGlyphS;
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

    public double glyphDist()
    {
        return 1.8694*(Math.pow(distSensor.getVoltage(),-1.086));
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
        rservoIntkae.setPower(1);
        lservoIntkae.setPower(1);
        outtakeLeft();
        outtakeRight();
    }

    public void halfOuttake()
    {
        if(glyphDist()>MaxGlyphDist) {
            lservoIntkae.setPower(0);
            rservoIntkae.setPower(0);
            setLeftWheelPwr(0);
            setRightWheels(0);
        }
        else
        {
            lservoIntkae.setPower(0.5);
            rservoIntkae.setPower(0.5);
            intakeLeft();
            intakeRight();
        }
    }

    public void unLatch(){

        latchyCatchy.setPosition(0.75);

    }

    public void latch(){

        latchyCatchy.setPosition(0.15);

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


    @Override
    public String display() {

        return "left pwr " + leftWheels.getPower()
                +"\nright pwr " + rightWheels.getPower();

    }
}
