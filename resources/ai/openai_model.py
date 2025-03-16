import openai

openai.api_key = "your_openai_api_key"

def generate_text(prompt):
    response = openai.Completion.create(
        engine="text-davinci-003",  # Or any other model you prefer
        prompt=prompt,
        max_tokens=100
    )
    return response.choices[0].text.strip()

if __name__ == "__main__":
    prompt = "What is AI?"
    result = generate_text(prompt)
    print(result)
