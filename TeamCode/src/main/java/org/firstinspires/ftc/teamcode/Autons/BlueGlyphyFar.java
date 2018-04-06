package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by therat0981 on 12/14/17.
 * This autonomous is for the blue balancing stone further from its corner.
 */

@Autonomous(name = "BlueGlyphyFar")
public class BlueGlyphyFar extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();

    VisionUtil vision = new VisionUtil(this);

    RelicRecoveryVuMark reading;

    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        reading = vision.readGraph(hardwareMap);

        int dist = 0;

        AutoTransitioner.transitionOnStop(this, "MainTeleOp");

        waitForStart();
        if (opModeIsActive()){

            robot.jewelArm.arm2Down();

            sleep(1000);

            telemetry.addData(String.valueOf(robot.jewelArm.getColor2()),"");
            telemetry.update();
            if(String.valueOf(robot.jewelArm.getColor2()) == "RED")
            {
                robot.driveTrain.setMoveDist(5);
                dist += 5;
            }

            else if(String.valueOf(robot.jewelArm.getColor2()) == "BLUE")
            {
                robot.driveTrain.setMoveDist(-5);
                dist-= 5;
            }

            robot.jewelArm.arm2Mid();

            if(reading.equals(RelicRecoveryVuMark.UNKNOWN))
            {
                robot.driveTrain.setMoveDist(-9 - dist);

                reading = vision.readGraph2(hardwareMap);
                sleep(1000);

                dist = -9;
            }

            robot.driveTrain.setMoveDist(34-dist);
            robot.driveTrain.rotateDeg(90);

            switch (reading){

                case RIGHT:{
                    robot.driveTrain.setMoveDist(32);
                    robot.driveTrain.rotateDeg(-87.5);
                    placeBlock();
                    break;
                }

                case CENTER:{
                    robot.driveTrain.setMoveDist(20);
                    robot.driveTrain.rotateDeg(-87.5);
                    placeBlock();
                    break;
                }

                case LEFT:{
                    robot.driveTrain.setMoveDist(5);
                    robot.driveTrain.rotateDeg(-87.5);
                    placeBlock();
                    break;
                }

                default:{
                    robot.driveTrain.setMoveDist(5);
                    robot.driveTrain.rotateDeg(-87.5);
                    placeBlock();
                    break;
                }

            }

        }
    }

    public void placeBlock(){

        robot.driveTrain.setMoveDist(4);

        robot.wheels.setRightWheels(-1);
        robot.wheels.setLeftWheelPwr(-1);
        sleep(500);

        robot.wheels.stopLeft();
        robot.wheels.stopRight();

        robot.driveTrain.setMoveDist(-8);

    }
}
