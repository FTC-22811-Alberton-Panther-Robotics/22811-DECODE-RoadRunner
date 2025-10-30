package org.firstinspires.ftc.teamcode.RobotHardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.InDevelopment.DriveWithFlyWheel.MAX_SHOOTER_SPEED;

import static java.lang.Runtime.getRuntime;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class    Shooter {


    public DcMotorEx leftFlywheel = null;
    public DcMotorEx rightFlywheel = null;;
    public double leftBumperLastTime, rightBumperLastTime = 0;
    public static double MAX_SHOOTER_SPEED = 6000;
    double shooterSpeed = 3000;
    public boolean shootersOn = false;
    public boolean aButtonPressed = false;

    public void init(HardwareMap hwMap){


        leftFlywheel = hardwareMap.get(DcMotorEx.class, "leftFlywheel");
        rightFlywheel = hardwareMap.get(DcMotorEx.class, "rightFlywheel");

        rightFlywheel.setDirection(DcMotor.Direction.REVERSE);
        leftFlywheel.setDirection(DcMotor.Direction.FORWARD);


    }

    public void shoot( )
    {

        if (gamepad1.right_bumper) {
            if (getRuntime() - rightBumperLastTime > .075) {
                if (shooterSpeed < MAX_SHOOTER_SPEED) shooterSpeed += 100;
                rightBumperLastTime = getRuntime();
            }
        }

        else if (gamepad1.left_bumper) {
            if (getRuntime() - leftBumperLastTime > .075) {
                if (shooterSpeed > 0) shooterSpeed -= 100;
                leftBumperLastTime = getRuntime();
            }
        }

        if(gamepad1.a){
            if (!aButtonPressed)
            {
                aButtonPressed = true;
                shootersOn = !shootersOn;
            }
        } else aButtonPressed = false;

        if (shootersOn) {
            rightFlywheel.setVelocity(shooterSpeed * 28 / 60); // goBilda yellow jacket 6000rpm motor reads 28 ticks/rev, convert from rev/minute to rev/sec
            leftFlywheel.setVelocity(shooterSpeed * 28 / 60);
        }else {
            rightFlywheel.setVelocity(0);
            leftFlywheel.setVelocity(0);
        }

    }






}
