package org.firstinspires.ftc.teamcode.InDevelopment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotHardware.LauncherHardware;
@TeleOp(name=" Strafer with shooters")
public class DriveWithFlyWheel extends LinearOpMode {

    LauncherHardware LauncherHardware = new LauncherHardware();
    public DcMotor rightBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor leftFrontDrive = null;

    @Override
    public void runOpMode() {
        LauncherHardware. init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Hardware mapping

        ///  motors
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");


        // Set motor directions
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the driver to press START
        waitForStart();

        // This loop runs continuously after START is pressed
        while (opModeIsActive()) {

            // Get joystick values
            double drive = -gamepad1.left_stick_y;  // Controls forward and backward movement
            double strafe = gamepad1.left_stick_x;   // Controls side-to-side movement
            double turn = gamepad1.right_stick_x;    // Controls turning/rotation




            // Combine the joystick inputs to calculate the roundupower for each wheel
            double leftFrontPower = drive + strafe + turn;
            double rightFrontPower = drive - strafe - turn;
            double leftBackPower = drive - strafe + turn;
            double rightBackPower = drive + strafe - turn;

            // Normalize the wheel speeds to be between -1 and 1
            double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            // Send the calculated power to the motors
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);

            // Show the joystick and wheel power values on the Driver Hub screen
            telemetry.addData("Status", "Running");
            telemetry.addData("Drive (Fwd/Rev)", "%.2f", drive);
            telemetry.addData("Strafe (Side)", "%.2f", strafe);
            telemetry.addData("Turn (Rotate)", "%.2f", turn);
            telemetry.addData("Front left/Right Power", "%.2f, %.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right Power", "%.2f, %.2f", leftBackPower, rightBackPower);
            telemetry.update();

        }
    }
}
