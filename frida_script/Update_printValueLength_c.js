Interceptor.attach(Module.findExportByName(ModuleName, MethodName), {
    onEnter: function (args) {
        // var str = ptr(args[0]).readCString();
        // var str = Memory.readCString(args[0]);
        var str = args[0];
        console.log("args[0]: ", str);
        console.log(args[0]);
    },
    onLeave: function (retval) {
    }
});