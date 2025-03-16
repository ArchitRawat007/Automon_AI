# model.py
import tensorflow as tf
from tensorflow.keras import layers

def create_model():
    model = tf.keras.Sequential([
        layers.Dense(64, activation='relu', input_shape=(10,)),  # Example: Input shape (10,) for 10 features
        layers.Dense(32, activation='relu'),
        layers.Dense(1)  # Output layer, regression example (single output value)
    ])
    model.compile(optimizer='adam', loss='mean_squared_error')
    return model

# Save the model for later use in training or prediction
def save_model(model, model_path='model.h5'):
    model.save(model_path)

# Load the model for prediction
def load_model(model_path='model.h5'):
    return tf.keras.models.load_model(model_path)
