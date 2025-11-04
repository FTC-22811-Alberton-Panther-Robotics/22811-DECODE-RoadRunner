package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    public DcMotorEx leftFlywheel = null;
    public DcMotorEx rightFlywheel = null;
    public static double MAX_SHOOTER_SPEED = 6000;
    public static final double TICKS_PER_REV = 28;
    public double targetShooterSpeed_RPM = 3000;
    public boolean shootersOn = false;

    private double leftBumperLastTime = 0;
    private double rightBumperLastTime = 0;
    private boolean aButtonPressed = false;


    public void init(HardwareMap hardwareMap) {
        leftFlywheel = hardwareMap.get(DcMotorEx.class, "leftFlywheel");
        rightFlywheel = hardwareMap.get(DcMotorEx.class, "rightFlywheel");

        leftFlywheel.setDirection(DcMotor.Direction.REVERSE);
        rightFlywheel.setDirection(DcMotor.Direction.FORWARD);

        leftFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset state
        targetShooterSpeed_RPM = 3000;
        shootersOn = false;
        aButtonPressed = false;
        leftBumperLastTime = 0;
        rightBumperLastTime = 0;
    }


    public void run(Gamepad gamepad, double runtime) {

        if (gamepad.right_bumper) {
            if (runtime - rightBumperLastTime > .075) {
                if (targetShooterSpeed_RPM < MAX_SHOOTER_SPEED) {
                    targetShooterSpeed_RPM += 100;
                }
                rightBumperLastTime = runtime;
            }
        } else if (gamepad.left_bumper) {
            if (runtime - leftBumperLastTime > .075) {
                if (targetShooterSpeed_RPM > 0) {
                    targetShooterSpeed_RPM -= 100;
                }
                leftBumperLastTime = runtime;
            }
        }


        if (gamepad.a) {
            if (!aButtonPressed) {
                aButtonPressed = true;
                shootersOn = !shootersOn;
            }
        } else {
            aButtonPressed = false;
        }

        if (shootersOn) {

            double targetVelocity_TPS = (targetShooterSpeed_RPM * TICKS_PER_REV) / 60.0;
            rightFlywheel.setVelocity(targetVelocity_TPS);
            leftFlywheel.setVelocity(targetVelocity_TPS);
        } else {
            rightFlywheel.setVelocity(0);
            leftFlywheel.setVelocity(0);
        }
    }

    public void stop() {
        shootersOn = false;
        leftFlywheel.setVelocity(0);
        rightFlywheel.setVelocity(0);
    }
}

