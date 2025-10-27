package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.CANConfig;
import frc.robot.Constants.IndexerConfig;

public class Indexer {
    private SparkMax upperMotor;
    private SparkMax lowerMotor;

    public Indexer() {
        upperMotor = new SparkMax(CANConfig.INDEXER_PORT, MotorType.kBrushless);
        lowerMotor = new SparkMax(CANConfig.INDEXER_PORT, MotorType.kBrushless);
        SparkMaxConfig indexerConfig = new SparkMaxConfig();
        upperMotor.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        lowerMotor.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setIndexerOutput(double percent) {
        if (Math.abs(percent) > 1)
            return;
        upperMotor.set(percent);
        lowerMotor.set(percent);
    }

    public double getUpperIndexerSpeed() {
        return upperMotor.get();
    }

    public double getLowerIndexerSpeed() {
        return lowerMotor.get();
    }

    public Command startIndexer(boolean forward) {
        return Commands
                .runOnce(() -> setIndexerOutput(forward ? IndexerConfig.INDEXER_SPEED : -IndexerConfig.INDEXER_SPEED));
    }

    public Command stopIndexer() {
        return Commands.runOnce(() -> setIndexerOutput(0));
    }
}
