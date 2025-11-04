package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.CANConfig;
import frc.robot.Constants.IndexerConfig;
import frc.robot.Constants.IntakeConfig;

public class Intake {
    private SparkMax motor;

    public Intake() {
        motor = new SparkMax(CANConfig.INTAKE_PORT, MotorType.kBrushless);
        SparkMaxConfig intakeConfig = new SparkMaxConfig();
        motor.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    /**
     * @param percent The speed of the motor in percent, between -1.0 and 1.0
     */
    public void setIntakeSpeed(double percent) {
        // Sets the speed and clamps between -1.0 and 1.0
        motor.set(Math.max(-1, Math.min(1, percent)));
    }

    public double getIntakeSpeed() {
        return motor.get();
    }

    public Command startIntake() {
        return Commands.runOnce(() -> setIntakeSpeed(IntakeConfig.INTAKE_SPEED));
    }

    public Command stopIntake() {
        return Commands.runOnce(() -> setIntakeSpeed(0));
    }
}
