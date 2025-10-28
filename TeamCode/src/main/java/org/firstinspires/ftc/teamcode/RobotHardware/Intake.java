package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
 /// i dont know what im doing!!!! AAAAAAHHHHHHH?? 0_0
    private final double feedTime = .20;
    /// eatPower is how fast it takes the ball into the intake system
    private final double eatPower = .10;
    private DcMotor intake;

    private enum IntakeState {ON, OFF}
    private IntakeState intakeState;

    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "intake");
        intakeState = IntakeState.OFF;
        intakeOff();
    }
    /// im so sad i cant name intakeOn eat "insert sad face here" im keeping eatPower the same i don't care
    public void intakeOn() {
        intake.setPower(eatPower);
        intakeState = IntakeState.ON;
    }
    /// intakeOff is the opposite of intakeOn
    public void intakeOff() {
        intake.setPower(0);
        intakeState = IntakeState.OFF;
    }
    /// Nothing can go wrong right?
    /// Everything went wrong...

//    public void updateState(){
//        switch(intakeState){
//            case ON:
//                intakeOn();
//                break;
//            case OFF:
//                intakeOff();
//                break;
//            default:
//                intakeOff();
//                break;
//        }
//
//    }
//
//    public void startIntake(){
//
//    }












    /// What are you looking at <0>_<0>
}
