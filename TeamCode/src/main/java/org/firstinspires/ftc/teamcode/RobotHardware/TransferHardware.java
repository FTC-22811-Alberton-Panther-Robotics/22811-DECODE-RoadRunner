package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TransferHardware {
    public CRServo transfer = null;


    public void init(HardwareMap hardwareMap){
        transfer = hardwareMap.get(CRServo.class, "transfer");


    }

}

