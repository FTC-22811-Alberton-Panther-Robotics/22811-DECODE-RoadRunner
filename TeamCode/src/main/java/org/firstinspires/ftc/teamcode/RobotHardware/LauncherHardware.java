package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LauncherHardware {

    public DcMotorEx leftFlywheel = null;
    public DcMotorEx rightFlywheel = null;
    public static double MAX_SHOOTER_SPEED = 6000;
    public static final double TICKS_PER_REV = 28;





    public void init(HardwareMap hardwareMap) {
        leftFlywheel = hardwareMap.get(DcMotorEx.class, "leftFlywheel");
        rightFlywheel = hardwareMap.get(DcMotorEx.class, "rightFlywheel");

        leftFlywheel.setDirection(DcMotor.Direction.REVERSE);
        rightFlywheel.setDirection(DcMotor.Direction.FORWARD);

        leftFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFlywheel.setVelocity(0);
        rightFlywheel.setVelocity(0);
    }

    public void runLauncher(double targetShooterSpeed_RPM) {

        double targetVelocity_TPS = (targetShooterSpeed_RPM * TICKS_PER_REV) / 60.0;
        rightFlywheel.setVelocity(targetVelocity_TPS);
        leftFlywheel.setVelocity(targetVelocity_TPS);
    }

    public void stop() {
        leftFlywheel.setVelocity(0);
        rightFlywheel.setVelocity(0);
    }
}

