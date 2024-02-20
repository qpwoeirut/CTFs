const interval = setInterval(() => {
    ws.send(JSON.stringify([
        Msg.CLIENT_UPDATE,
        [ [0, 0], [1e-300, 0] ]
    ]));
    moved = false;
}, 50);
