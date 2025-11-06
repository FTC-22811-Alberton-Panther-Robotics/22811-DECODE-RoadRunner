package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TransferHardware {
    public CRServo transferServo;

    public void init(HardwareMap hardwareMap){
        transferServo = hardwareMap.get(CRServo.class, "transfer");
        transferServo.setPower();
    }

    public void runTransfer( boolean transferOn){
        if(transferOn ) {
            transferServo.setPower(1);
        }else {
            transferServo.setPower(0);
        }
    }
}

