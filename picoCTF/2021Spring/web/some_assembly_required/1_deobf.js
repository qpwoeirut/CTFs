// deobfuscating is useless since the flag is just in the WASM source


const arr = ['value', '2wfTpTR', 'instantiate', '275341bEPcme', 'innerHTML', '1195047NznhZg', '1qfevql', 'input', '1699808QuoWhA', 'Correct!', 'check_flag', 'Incorrect!', './JIFxzHyW8W', '23SMpAuA', '802698XOMSrr', 'charCodeAt', '474547vVoGDO', 'getElementById', 'instance', 'copy_char', '43591XxcWUl', '504454llVtzW', 'arrayBuffer', '2NIQmVj', 'result'];
const get_val = function(_0x553839, _0x53c021) {
    return arr[_0x553839 - 470];
};
while (true) {
    try {
        const x = -parseInt(get_val(0x1eb)) + parseInt(get_val(0x1ed)) + -parseInt(get_val(0x1db)) * -parseInt(get_val(0x1d9)) + -parseInt(get_val(0x1e2)) * -parseInt(get_val(0x1e3)) + -parseInt(get_val(0x1de)) * parseInt(get_val(0x1e0)) + parseInt(get_val(0x1d8)) * parseInt(get_val(0x1ea)) + -parseInt(get_val(0x1e5));
        if (x === 0x994c3) break;
        else arr.push(arr.shift());
    } catch (_0x41d31a) {
        arr.push(arr.shift());
    }
}

// arr becomes ["instance", "copy_char", "43591XxcWUl", "504454llVtzW", "arrayBuffer", "2NIQmVj", "result", "value", "2wfTpTR", "instantiate", "275341bEPcme", "innerHTML", "1195047NznhZg", "1qfevql", "input", "1699808QuoWhA", "Correct!", "check_flag", "Incorrect!", "./JIFxzHyW8W", "23SMpAuA", "802698XOMSrr", "charCodeAt", "474547vVoGDO", "getElementById"]

let exports;
(async () => {
    let _0x5f0229 = await fetch(get_val(0x1e9)),
        _0x1d99e9 = await WebAssembly[get_val(0x1df)](await _0x5f0229[get_val(0x1da)]()),
        _0x1f8628 = _0x1d99e9[get_val(0x1d6)];
    exports = _0x1f8628['exports'];
})();

function onButtonPress() {
    let _0x3761f8 = document['getElementById'](get_val(0x1e4))[get_val(0x1dd)];
    for (let _0x16c626 = 0x0; _0x16c626 < _0x3761f8['length']; _0x16c626++) {
        exports[get_val(0x1d7)](_0x3761f8[get_val(0x1ec)](_0x16c626), _0x16c626);
    }
    exports['copy_char'](0x0, _0x3761f8['length']), exports[get_val(0x1e7)]() == 0x1 ? document[get_val(0x1ee)](get_val(0x1dc))[get_val(0x1e1)] = get_val(0x1e6) : document[get_val(0x1ee)](get_val(0x1dc))[get_val(0x1e1)] = get_val(0x1e8);
}

