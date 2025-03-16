# predict.py
import numpy as np
from model import load_model

def generate_input_data():
    # Simulating input data (e.g., 1 sample with 10 features)
    X_input = np.random.rand(1, 10)
    return X_input

def predict():
    # Load the trained model
    model = load_model('model.h5')

    # Generate input data for prediction
    X_input = generate_input_data()

    # Make a prediction
    prediction = model.predict(X_input)

    return prediction[0][0]  # Return the first value of the prediction

if __name__ == "__main__":
    print(predict())
