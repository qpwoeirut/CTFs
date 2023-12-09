<?php if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $link = $_POST["link"];

    $validatedLink = filter_var($link, FILTER_VALIDATE_URL);

    if ($validatedLink) {
        // Fetch the content of the link
        $parsedUrl = parse_url($validatedLink);
        $host = $parsedUrl['host'];
        $ipv4Pattern = '/^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$/';

        if (strtolower($host) == 'localhost') {
            echo("Invalid link. Please provide a valid external link.");
            exit();
        }

        if (preg_match($ipv4Pattern, $host)) {
            echo("Error! Please avoid using IP addresses!");
            exit();
        }

        $content = @file_get_contents($validatedLink);
            $imageInfo = @getimagesize($validatedLink);

            if ($imageInfo !== false) {
                // If the content is an image, embed it in the HTML
                echo "<!DOCTYPE html>
                      <html lang='en'>
                      <head>
                          <meta charset='UTF-8'>
                          <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                          <title>Link Content Display</title>
                      </head>
                      <body>
                          <h2>Content of the Link</h2>
                          <img src='" . $validatedLink . "' alt='Embedded Image'>
                      </body>
                      </html>";
            } else {
                // If the content is not an image, display it as plaintext
                echo "<!DOCTYPE html>
                      <html lang='en'>
                      <head>
                          <meta charset='UTF-8'>
                          <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                          <title>Link Content Display</title>
                      </head>
                      <body>
                          <h2>Content of the Link</h2>
                          <p>" . htmlspecialchars($content) . "</p>
                      </body>
                      </html>";
            }

    } else {
        echo("Invalid link provided.");
    }
} else {
    // If the form is not submitted through POST, redirect to the form page
    header("Location: index.html");
    exit();
}
?>