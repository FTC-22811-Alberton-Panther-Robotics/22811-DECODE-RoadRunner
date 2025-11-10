//package org.firstinspires.ftc.teamcode.Competition;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.RobotHardware.IntakeHardware;
//import org.firstinspires.ftc.teamcode.RobotHardware.LauncherHardware;
//import org.firstinspires.ftc.teamcode.RobotHardware.MecanumHardware;
//import org.firstinspires.ftc.teamcode.RobotHardware.TransferHardware;
//
//@Disabled
//@TeleOp(name="KalispellTeleopWithField")
//public class TestTeleopWithFieldStuff extends OpMode {
//
//    LauncherHardware launcherHardware = new LauncherHardware();
//    IntakeHardware intakeHardware = new IntakeHardware();
//    MecanumHardware mecanumHardware = new MecanumHardware();
//    TransferHardware transferHardware = new TransferHardware();
//    public boolean shootersOn = false;
//    private boolean aButtonPressed = false;
//    public double targetShooterSpeed_RPM = 2000;
//    private ElapsedTime runtime = new ElapsedTime();
//    private double leftBumperLastTime = 0;
//    private double rightBumperLastTime = 0;
//    private static int SHOOTER_SPEED_INCREMENT = 100;
//
//    @Override
//    public void init(){
//        launcherHardware.init(hardwareMap);
//        intakeHardware.init(hardwareMap);
//        mecanumHardware.init(hardwareMap);
//        transferHardware.init(hardwareMap);
//
//
//    }
//
//
//
//
//    @Override
//    public void loop(){
//            // Driving
//            double forward = -gamepad1.left_stick_y;  // Controls forward and backward movement
//            double strafe = gamepad1.left_stick_x;   // Controls side-to-side movement
//            double turn = gamepad1.right_stick_x;    // Controls turning/rotation
//            mecanumHardware.driveFieldRelative(forward, strafe, turn);
//
//            // Intake
//            if (gamepad1.dpad_up){
//                intakeHardware.runIntake(true);
//            }else if (gamepad1.dpad_down){
//                intakeHardware.runIntake(false);
//            }
//
//            // Launcher flywheel speed
//            if (gamepad1.right_bumper) {
//                if (runtime.seconds() - rightBumperLastTime > .075) {
//                    if (targetShooterSpeed_RPM < LauncherHardware.MAX_SHOOTER_SPEED) {
//                        targetShooterSpeed_RPM += SHOOTER_SPEED_INCREMENT;
//                    }
//                    rightBumperLastTime = runtime.seconds();
//                }
//            } else if (gamepad1.left_bumper) {
//                if (runtime.seconds() - leftBumperLastTime > .075) {
//                    if (targetShooterSpeed_RPM > 0) {
//                        targetShooterSpeed_RPM -= SHOOTER_SPEED_INCREMENT;
//                    }
//                    leftBumperLastTime = runtime.seconds();
//                }
//            }
//            // Toggle shooter
//            if (gamepad1.a) {
//                if (!aButtonPressed) {
//                    aButtonPressed = true;
//                    shootersOn = !shootersOn;
//                }
//            } else {
//                aButtonPressed = false;
//            }
//
//            if (shootersOn) {
//                launcherHardware.runLauncher(targetShooterSpeed_RPM);
//            } else {
//                launcherHardware.runLauncher(0);
//            }
//
//            // Ball transfer
//            transferHardware.Transfer(gamepad1.right_trigger > 0.1);
//
//            // Telemetry
//            telemetry.addData("Status", "Running");
//            telemetry.addData("Left & Right Flywheel RPM",  "%.2f, %.2f", launcherHardware.getLeftFlywheelRPM(), launcherHardware.getRightFlywheelRPM());
//            telemetry.update();
//    }
//
//    public void stop(){
//        transferHardware.Transfer(false);
//        intakeHardware.runIntake(false);
//        launcherHardware.stop();
//    }
//
//}
//
//
//
