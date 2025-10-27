package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
 /// i dont know what im doing!!!! AAAAAAHHHHHHH?? 0_0
    private final double feedTime = .20;
    /// EatSpeed is how fast it sucks the ball into the intake system
    private final double eatPower = .10;
    private DcMotor intake;

    private enum IntakeState {ON, OFF}
    private IntakeState intakeState;

    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "intake");
    }

    public void eat() {
        intake.setPower(eatPower);
        intakeState = IntakeState.ON;
    }
}
