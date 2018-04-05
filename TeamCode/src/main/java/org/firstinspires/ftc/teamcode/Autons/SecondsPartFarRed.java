package org.firstinspires.ftc.teamcode.Autons;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 3/11/2018.
 */
@Disabled
@Autonomous(name = "SecondReddieFreddie")
public class SecondsPartFarRed extends LinearOpMode {
    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();
    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        double heading;
        waitForStart();
        if (opModeIsActive()){
            robot.driveTrain.setMoveDist(3);
            robot.driveTrain.singleSideRotateDegCorrect(DriveTrain.Side.RIGHT_SIDE, gyro.getYaw()+57,1);
//            robot.driveTrain.rotateDeg(-64.5);
            timer.startTime();
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            robot.driveTrain.setMoveDist(12);
            double i = 1;
            int c = 0;
            while(!robot.range.isGlyph() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){
                c = (int)(i);
                if(c%2==0)
                {
                    robot.wheels.intakeLeft();
                    robot.wheels.setRightWheels(0);
                }
                else
                {
                    robot.wheels.intakeRight();
                    robot.wheels.setLeftWheelPwr(0);
                }
                i+=0.4;
                robot.driveTrain.setLeftPower(0.4);
                robot.driveTrain.setRightPower(0.4);
                Log.i("dist",robot.range.getDist()+"");
            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);


            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;

            robot.driveTrain.setMoveDistEnc(-leftTarget);

            robot.bar4.setMoveAngle(167);

            //TODO: CHANGE FOR REEAL AUTO
            robot.driveTrain.rotateDeg(-gyro.getYaw()+85);
            robot.driveTrain.setMoveDist(3);

            placeBlock();




        }

    }
    public void placeBlock(){

        sleep(200);
        while(robot.range.getDist()<10 && opModeIsActive()) {
            robot.wheels.setRightWheels(1);
            robot.wheels.setLeftWheelPwr(1);
        }

        robot.wheels.setRightWheels(1);
        robot.wheels.setLeftWheelPwr(1);

        sleep(500);

        robot.wheels.stopLeft();
        robot.wheels.stopRight();
    }
}
