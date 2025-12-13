// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.IntakeConfig;
import frc.robot.Constants.ShooterConfig;
import frc.robot.commands.DriveJoysticks;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

@SuppressWarnings("unused")
public class RobotContainer {
    private final Drivetrain drivetrain;

    private final CommandXboxController driver;
    private final CommandXboxController operator;
    private final Controls controls;

    private boolean robotOriented;

    private SendableChooser<Command> autoChooser;

    public RobotContainer() {
        driver = new CommandXboxController(0);
        operator = new CommandXboxController(1);
        controls = new Controls(driver, operator);
        drivetrain = new Drivetrain();

        robotOriented = false;

        autoChooser = new SendableChooser<Command>();

        configureBindings();
        configureDefaultCommands();
        buildAutoChooser();
    }

    public void configureDefaultCommands() {
        drivetrain.setDefaultCommand(new DriveJoysticks(
                drivetrain,
                controls::getDriveSpeedX,
                controls::getDriveSpeedY,
                controls::getTurnSpeed,
                () -> robotOriented));
    }

    private void configureBindings() {
        driver.a().onTrue(drivetrain.reset());
    }

    private void buildAutoChooser() {
        autoChooser.addOption("None", Commands.none());
        autoChooser.addOption("Drive Forward", drivetrain.driveToPose(new Pose2d(27, 4, new Rotation2d())));
    }

    public Command getAutonomousCommand() {
        System.err.println("RUNNING AUTONOMOUS COMMAND");
        System.err.println(drivetrain.getPose().toString());

        Commands.runOnce(() -> drivetrain.reset());
        drivetrain.setUpAuto();

        Pose2d target = new Pose2d(2.0d, 0.0d, new Rotation2d());
        return drivetrain.driveToPose(target);
        // return autoChooser.getSelected();
    }

    public void autoInit() {
        drivetrain.resetPose(new Pose2d());
    }
}
