# train.py
import numpy as np
from model import create_model, save_model

def generate_data():
    # Simulating data for training (e.g., 100 samples with 10 features each)
    X = np.random.rand(100, 10)
    y = np.random.rand(100)
    return X, y

def train():
    # Generate training data
    X_train, y_train = generate_data()

    # Create and train the model
    model = create_model()
    model.fit(X_train, y_train, epochs=10, batch_size=8)

    # Save the trained model
    save_model(model)

if __name__ == "__main__":
    train()
