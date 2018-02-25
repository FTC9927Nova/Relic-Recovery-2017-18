package org.firstinspires.ftc.teamcode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 2/24/2018.
 */

@Autonomous(name = "2ndGlyphyTest")
public class SecondGlyphy extends LinearOpMode
{
    Robot robot = new Robot();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    Gyro gyro = new Gyro();
    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,this,gyro);
        waitForStart();
        if(opModeIsActive())
        {
            double heading = gyro.getYaw();

            robot.driveTrain.rotateDeg(180);

            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            timer.reset();
            timer.startTime();
            while(!robot.range.isGlyph() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){

                if (timer.milliseconds() <= 1000) {

                    robot.wheels.setLeftWheelPwr(0);
                    robot.wheels.intakeRight();

                }
                else{

                    robot.wheels.intakeRight();
                    robot.wheels.setLeftWheelPwr(-0.4);
                }
                robot.driveTrain.setLeftPower(0.2);
                robot.driveTrain.setRightPower(0.2);

                Log.i("dist",robot.range.getDist()+"");
            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;

            robot.driveTrain.setMoveDist(-10.5);
            robot.bar4.setMoveAngle(167);
            robot.driveTrain.setMoveDistEnc((leftTarget- (10 * constant.getTICKS_PER_INCH())));

            robot.driveTrain.rotateDeg(180);




            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);



        }
    }
}
