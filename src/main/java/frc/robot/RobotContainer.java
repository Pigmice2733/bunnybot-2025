// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final Drivetrain drivetrain;

    private final Shooter shooter = new Shooter();

    private final CommandXboxController driver;
    private final CommandXboxController operator;
    private final Controls controls;

    public RobotContainer() {
        driver = new CommandXboxController(0);
        operator = new CommandXboxController(1);
        controls = new Controls(driver, operator);
    
        drivetrain = new Drivetrain();
        // Configure the trigger bindings
        configureBindings();
    }

    private void configureBindings() {
        // Shoot when right trigger is pressed.
        operator.rightTrigger().onTrue(shooter.shoot());
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
