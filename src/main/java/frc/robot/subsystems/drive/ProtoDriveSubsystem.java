/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;

import riolog.RioLogger;

class ProtoDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ProtoDriveSubsystem.class.getName());

    /**
     * Drive Constants
     */
    private static final double s = 0.0645; // Volts
    private static final double v = 2.84; // VoltSeconds Per Meter
    private static final double a = 0.28; // VoltSecondsSquared Per Meter
    private static final double p = 2.53; // Drive Velocity
    private static final double trackWidth = 0.616; // Meters
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;
    private static final double maxSpeed = 3.04; // Meters Per Second
    private static final double maxAcceleration = 0.5; // Meters Per Second Squared
    private static final boolean leftReversed = false;
    private static final boolean rightReversed = false;
    private static final double wheelRadius = 0.1524; // Meters
    private static final double beltGearing = 1;
    private static final double gearboxGearing = 10.71; // Standard AndyMark KoP chassis Toughbox Mini gearing

    // Voltage constraint for trajectory following
    private final DifferentialDriveVoltageConstraint autoVoltageConstraint;

    // Trajectory configuration for trajectory following
    private final TrajectoryConfig trajectoryConfig;

    /**
     * Mechanisms and Sensors
     */

    private final CANSparkMax leftFrontMotor;
    private final CANSparkMax leftRearMotor;
    private final CANSparkMax rightFrontMotor;
    private final CANSparkMax rightRearMotor;

    private final SpeedControllerGroup left;
    private final SpeedControllerGroup right;

    private final CANEncoder leftEncoder;
    private final CANEncoder rightEncoder;

    private final IGyroSensor nav;

    private final DifferentialDrive drive;
    private final DifferentialDriveKinematics driveKinematics;
    private final DifferentialDriveOdometry driveOdometry;

    ProtoDriveSubsystem() {
        logger.info("constructing");

        leftFrontMotor = new CANSparkMax(23, MotorType.kBrushless);
        leftRearMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightFrontMotor = new CANSparkMax(20, MotorType.kBrushless);
        rightRearMotor = new CANSparkMax(21, MotorType.kBrushless);

        left = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
        right = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);

        left.setInverted(false);
        right.setInverted(true);

        leftEncoder = new CANEncoder(leftFrontMotor);
        rightEncoder = new CANEncoder(rightFrontMotor);

        nav = GyroFactory.getInstance();

        drive = new DifferentialDrive(left, right);
        driveKinematics = new DifferentialDriveKinematics(trackWidth);
        driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(nav.getAngle()));

        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(s, v, a),
                driveKinematics, 10);

        trajectoryConfig = new TrajectoryConfig(maxSpeed, maxAcceleration).setKinematics(driveKinematics)
                .addConstraint(autoVoltageConstraint);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
    }

    @Override
    public void setBrake(boolean brakeOn) {
        if (brakeOn) {
            leftFrontMotor.setIdleMode(IdleMode.kBrake);
            leftRearMotor.setIdleMode(IdleMode.kBrake);
            rightFrontMotor.setIdleMode(IdleMode.kBrake);
            rightRearMotor.setIdleMode(IdleMode.kBrake);
        } else {
            leftFrontMotor.setIdleMode(IdleMode.kCoast);
            leftRearMotor.setIdleMode(IdleMode.kCoast);
            rightFrontMotor.setIdleMode(IdleMode.kCoast);
            rightRearMotor.setIdleMode(IdleMode.kCoast);
        }
    }

    @Override
    public void stop() {
        drive.tankDrive(0, 0);
    }

    /*
     * Drive constraint values
     */

    private static final double speedFactor = 1;
    private static final double turnFactor = 1;
    private static final double speedConstraintFactor = 1;
    private static final double turnConstraintFactor = 1;

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        drive.arcadeDrive(hmiSpeed * speedFactor, hmiTurn * turnFactor);
    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn, boolean constrained) {
        if (constrained) {
            drive.arcadeDrive(hmiSpeed * speedConstraintFactor, hmiTurn * turnConstraintFactor);
        } else {
            drive.arcadeDrive(hmiSpeed * speedFactor, hmiTurn * turnFactor);
        }
    }

    @Override
    public void followPath(final Pose2d start, final List<Translation2d> interiorWaypoints, final Pose2d end) {

        // Create trajectory to follow
        final Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end,
                trajectoryConfig);

        // return the RamseteCommand to run
        CommandScheduler.getInstance()
                .schedule(new RamseteCommand(trajectory, this::getPose, new RamseteController(ramseteB, ramseteZeta),
                        new SimpleMotorFeedforward(s, v, a), driveKinematics, this::getVelocity,
                        new PIDController(p, 0, 0), new PIDController(p, 0, 0), this::tankDriveVolts, this));
    }

    protected double convertInchesToEncoderClicks(double inches) {
        return inches * (1 / 12) // Conversion to feet
                * 3.281 // Conversion to meters
                * (1 / (2 * Math.PI * wheelRadius)) // Convert to wheel revolutions (Circumference)
                * (beltGearing) // Convert to output shaft revolutions (Belt gearing)
                * (1 / gearboxGearing); // Convert to motor revolutions (TB Mini gearing)
    }

    /*
     * RAMSETE Methods
     */

    /**
     * 
     * @param leftVolts
     * @param rightVolts
     */
    private void tankDriveVolts(final double leftVolts, final double rightVolts) {
        leftFrontMotor.setVoltage(leftVolts * (leftReversed ? -1 : 1));
        rightFrontMotor.setVoltage(rightVolts * (rightReversed ? -1 : 1));
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    private Pose2d getPose() {
        return driveOdometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    private DifferentialDriveWheelSpeeds getVelocity() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // TODO Auto-generated method stub

    }

}