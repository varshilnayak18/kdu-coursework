const os = require('os');

function getInfo() {
    const info = {
        HostName: os.hostname(),
        OperatingSystem: os.platform(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        NumberOfCPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: process.cwd()
    };
    return JSON.stringify(info, null, 4);
}
console.log(getInfo());
module.exports = getInfo;