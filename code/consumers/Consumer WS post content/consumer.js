const http = require('http');

const data = JSON.stringify({
    data: "This is a sample body for the content." 
});

const options = {
    hostname: 'localhost',
    port: 3000,
    path: '/api/content',
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Content-Length': Buffer.byteLength(data)  
    }
};

const req = http.request(options, (res) => {
    let responseBody = '';

    res.on('data', (chunk) => {
        responseBody += chunk;
    });

    res.on('end', () => {
        try {
            console.log('Response:', JSON.parse(responseBody));  
        } catch (error) {
            console.error('Failed to parse JSON response:', error);
        }
    });
});

req.on('error', (error) => {
    console.error('Error:', error);
});

req.write(data);
req.end();
