package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.CANConfig;

public class Indexer {
    private SparkMax motor;

    public Indexer() {
        motor = new SparkMax(CANConfig.INTAKE_INDEXER_PORT, MotorType.kBrushless);
        SparkMaxConfig indexerConfig = new SparkMaxConfig();
        motor.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    /**
     * Sets the speed of both indexer motors
     * 
     * @param pct Value between -1 and 1 for the speed to be set to
     */
    public void setIndexerSpeed(double pct) {
        if (Math.abs(pct) > 1)
            return;
        motor.set(pct);
    }

    /**
     * @return Speed of the indexer motor
     */
    public double getIndexerSpeed() {
        return motor.get();
    }

    /**
     * Sets the speed of both indexer motors to the default indexer speed. Can be
     * set to go forwards or backwards.
     * 
     * @param fwd Boolean value of whether the motors are running forwards or
     *            backwards
     */
    public Command startIndexer(double spd) {
        return Commands.runOnce(() -> setIndexerSpeed(spd));
    }

    /**
     * Sets the speed of both indexer motors to 0
     */
    public Command stopIndexer() {
        return Commands.runOnce(() -> setIndexerSpeed(0));
    }
}
