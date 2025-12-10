package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.CANConfig;

public class Intake {
    private SparkMax motor;

    public Intake() {
        // motor = new SparkMax(CANConfig.INTAKE_INDEXER_PORT, MotorType.kBrushless);
        // SparkMaxConfig intakeConfig = new SparkMaxConfig();
        // motor.configure(intakeConfig, ResetMode.kResetSafeParameters,
        // PersistMode.kPersistParameters);
    }

    /**
     * Sets the speed of the intake motor
     * 
     * @param spd Speed of the motor, between -1.0 and 1.0
     */
    public void setIntakeSpeed(double spd) {
        // Sets the speed and clamps between -1.0 and 1.0
        motor.set(Math.max(-1, Math.min(1, spd)));
    }

    /**
     * @return Speed of the intake motor
     */
    public double getIntakeSpeed() {
        return motor.get();
    }

    /**
     * Sets the speed of the intake motor to a specified speed
     * 
     * @param spd Speed of the motor, between -1.0 and 1.0
     */
    public Command startIntake(double spd) {
        return Commands.runOnce(() -> setIntakeSpeed(spd));
    }

    /**
     * Sets the speed of the intake motor to 0
     */
    public Command stopIntake() {
        return Commands.runOnce(() -> setIntakeSpeed(0));
    }
}
